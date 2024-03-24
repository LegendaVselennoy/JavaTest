package com.example.test;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private List<CustomerV> customers = new ArrayList<>();

    public boolean contains(String name) {
        return customers.stream().anyMatch(customer -> customer.getName().equals(name));
    }

    public void persist(CustomerV customer) {
        customers.add(customer);
    }
}
