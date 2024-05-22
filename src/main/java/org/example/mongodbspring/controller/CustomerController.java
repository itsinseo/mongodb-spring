package org.example.mongodbspring.controller;

import lombok.RequiredArgsConstructor;
import org.example.mongodbspring.dto.CustomerDto;
import org.example.mongodbspring.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void save(@RequestBody CustomerDto customerDto) {
        customerService.save(customerDto);
    }

    @PostMapping("/{databaseName}")
    public void saveToDatabase(@RequestBody CustomerDto customerDto, @PathVariable String databaseName) {
        customerService.saveToDatabase(databaseName, customerDto);
    }

    @GetMapping
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{databaseName}")
    public List<CustomerDto> findAllFromDatabase(@PathVariable String databaseName) {
        return customerService.findAllFromDatabase(databaseName);
    }

    @GetMapping("/last-name/{lastName}")
    public List<CustomerDto> findAllByLastName(@PathVariable String lastName) {
        return customerService.findAllByLastName(lastName);
    }

    @PutMapping("/first-name/{firstName}")
    public String findAndReplaceByFirstName(@PathVariable String firstName, @RequestBody CustomerDto customerDto) {
        return customerService.findAndReplaceByFirstName(firstName, customerDto);
    }

    @DeleteMapping("/first-name/{firstName}")
    public String findAndDeleteByFirstName(@PathVariable String firstName) {
        return customerService.findAndDeleteByFirstName(firstName);
    }

}
