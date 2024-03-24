package com.example;

import com.example.test.country.Country;
import com.example.test.country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootTest
@Import(FlightBuilder.class)
public class Application {

    @Autowired
    private Map<String, Country> countriesMap;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner configureRepository
            (CountryRepository countryRepository) {
        return args -> {
            for (Country country: countriesMap.values()) {
                countryRepository.save(country);
            }
        };
    }
}
