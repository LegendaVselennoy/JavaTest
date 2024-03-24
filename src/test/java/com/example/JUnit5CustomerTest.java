package com.example;

import com.example.test.CustomerV;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("individual")
public class JUnit5CustomerTest {

    private String CUSTOMER_NAME = "John Smith";

    @Test
    void testCustomer() {
        CustomerV customer = new CustomerV(CUSTOMER_NAME);
        assertEquals("John Smith", customer.getName());
    }
}
