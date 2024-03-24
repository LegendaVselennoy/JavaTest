package com.example;

import com.example.test.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HamcrestListTest {

    private static String FIRST_NAME = "John";
    private static String LAST_NAME = "Smith";
    private static Customer customer = new Customer(FIRST_NAME, LAST_NAME);

    private List<String> values;

    @BeforeEach
    public void setUp(){
        values=new ArrayList<>();
        values.add("Kate");
        values.add("Olay");
        values.add("Marina");
    }

    @Test
    @DisplayName("List without Hamcrest")
    public void testWithoutHamcrest() {
        assertEquals(3, values.size());
        assertTrue(values.contains("Kate")
                || values.contains("Olay")
                || values.contains("Harry"));
    }

    @Test
    @DisplayName("Hamcrest is,anyOf,allOf")
    public void testHamcrestIs(){
        int price1=1,price2=1,price3=2;

        assertThat(1,is(price1));
        assertThat(1,anyOf(is(price2),is(price3)));
        assertThat(1,allOf(is(price1),is(price2)));
    }

    @Test
    @DisplayName("Null expected")
    void testNull(){
        assertThat(null,nullValue());
    }

    @Test
    @DisplayName("Object expected")
    void testNotNull() {
        assertThat(customer, notNullValue());
    }

    @Test
    @DisplayName("Check correct customer properties")
    void checkCorrectCustomerProperties() {
        assertThat(customer, allOf(
                hasProperty("firstName", is(FIRST_NAME)),
                hasProperty("lastName", is(LAST_NAME))
        ));
    }
}
