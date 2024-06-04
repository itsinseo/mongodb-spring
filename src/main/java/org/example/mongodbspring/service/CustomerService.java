package org.example.mongodbspring.service;

import org.example.mongodbspring.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    void save(CustomerDto customerDto);

    void saveToDatabase(String databaseName, CustomerDto customerDto);

    List<CustomerDto> findAllFromDatabase(String databaseName);

    List<CustomerDto> findAllByLastName(String lastName);

    String findAndReplaceByFirstName(String firstName, CustomerDto customerDto);

    String findAndDeleteByFirstName(String firstName);
}