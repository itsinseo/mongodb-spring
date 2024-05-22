package org.example.mongodbspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.mongodbspring.entity.Customer;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    public String firstName;
    public String lastName;

    public CustomerDto(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
    }
}
