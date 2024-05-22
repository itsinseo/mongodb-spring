package org.example.mongodbspring.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
public class Customer {

    @Id
    public String id; // standard name for a MongoDB ID; _id or id

    private String firstName;
    private String lastName;

    @Builder
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[%s %s]", firstName, lastName);
    }
}
