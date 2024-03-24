package com.example.test.country.controller;

import com.example.test.country.Country;
import com.example.test.country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryRepository repository;

    @GetMapping("/countries")
    List<Country>findAll(){
        return repository.findAll();
    }
}
