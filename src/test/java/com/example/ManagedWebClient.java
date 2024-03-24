package com.example;

import com.example.test.WebClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class ManagedWebClient {

    protected WebClient webClient;

    @BeforeEach
    public void setUp(){
        webClient=new WebClient();
    }

    @AfterEach
    public void tearDown(){
        webClient.close();
    }
}
