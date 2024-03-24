package com.example;

import com.example.test.country.Country;
import com.example.test.country.Passenger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestBeans {

    @Bean
    Passenger createPassenger(){
        Passenger passenger = new Passenger("John Smith");
        passenger.setCountry(createCountry());
        passenger.setRegistered(false);
        return passenger;
    }

    @Bean
    Country createCountry() {
        Country country = new Country("USA", "US");
        return country;
    }
}
