package org.example.mongodbspring.controller;

import jakarta.annotation.Nullable;
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

    @PostMapping("/database/{databaseName}")
    public void saveToDatabase(@RequestBody CustomerDto customerDto, @PathVariable String databaseName) {
        customerService.saveToDatabase(databaseName, customerDto);
    }

    @GetMapping("/database/{databaseName}")
    public List<CustomerDto> findAllFromDatabase(@PathVariable String databaseName) {
        return customerService.findAllFromDatabase(databaseName);
    }

    @GetMapping
    public List<CustomerDto> findAllByLastName(@RequestParam @Nullable String lastName) {
        return customerService.findAllByLastName(lastName);
    }

    @PutMapping
    public String findAndReplaceByFirstName(@RequestParam String firstName, @RequestBody CustomerDto customerDto) {
        return customerService.findAndReplaceByFirstName(firstName, customerDto);
    }

    @DeleteMapping
    public String findAndDeleteByFirstName(@RequestParam String firstName) {
        return customerService.findAndDeleteByFirstName(firstName);
    }
}
