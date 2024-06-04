package org.example.mongodbspring.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.mongodbspring.config.MongoConfig;
import org.example.mongodbspring.dto.CustomerDto;
import org.example.mongodbspring.entity.Customer;
import org.example.mongodbspring.repository.CustomerRepository;
import org.example.mongodbspring.service.CustomerService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final MongoConfig mongoConfig;
    private final CustomerRepository customerRepository;

    @Override
    public void save(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .firstName(customerDto.firstName)
                .lastName(customerDto.lastName)
                .build();

        customerRepository.save(customer);
    }

    @Override
    public void saveToDatabase(String databaseName, CustomerDto customerDto) {
        MongoTemplate mongoTemplate = mongoConfig.mongoTemplate(databaseName);
        Customer customer = Customer.builder()
                .firstName(customerDto.firstName)
                .lastName(customerDto.lastName)
                .build();

        mongoTemplate.save(customer);
    }

    @Override
    public List<CustomerDto> findAllFromDatabase(String databaseName) {
        MongoTemplate mongoTemplate = mongoConfig.mongoTemplate(databaseName);
        return mongoTemplate.findAll(Customer.class).stream().map(CustomerDto::new).toList();
    }

    @Override
    public List<CustomerDto> findAllByLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            return customerRepository.findAll().stream().map(CustomerDto::new).toList();
        } else {
            return customerRepository.findAllByLastName(lastName).stream().map(CustomerDto::new).toList();
        }
    }

    @Override
    public String findAndReplaceByFirstName(String firstName, CustomerDto customerDto) {
        MongoTemplate mongoTemplate = mongoConfig.mongoTemplate();
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        Customer newCustomer = Customer.builder()
                .firstName(customerDto.firstName)
                .lastName(customerDto.lastName)
                .build();
        Customer customer = mongoTemplate.findAndReplace(query, newCustomer);
        if (customer == null) {
            return "firstName: " + firstName + " not found";
        } else {
            return customer + " replaced to " + newCustomer;
        }
    }

    @Override
    public String findAndDeleteByFirstName(String firstName) {
        MongoTemplate mongoTemplate = mongoConfig.mongoTemplate();
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        Customer customer = mongoTemplate.findAndRemove(query, Customer.class);
        if (customer == null) {
            return "firstName: " + firstName + " not found";
        } else {
            return customer + " deleted";
        }
    }
}
