package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JUnit5HamcrestListTest {
    private List<String> values;

    @BeforeEach
    public void setUp() {
        values = new ArrayList<>();
        values.add("Oliver");
        values.add("Jack");
        values.add("Harry");
    }

    @Test
    @DisplayName("List with Hamcrest")
    public void testListWithHamcrest() {
        assertThat(values, hasSize(3));
        assertThat(values, hasItem(anyOf(equalTo("Oliver"), equalTo("Jack"),
                equalTo("Harry"))));
        assertThat("The list doesn't contain all the expected objects, in # order",
                values,
                contains("Oliver", "Jack", "Harry"));
                assertThat("The list doesn't contain all the expected objects",
                        values, containsInAnyOrder("Jack", "Harry", "Oliver"));
    }
}
