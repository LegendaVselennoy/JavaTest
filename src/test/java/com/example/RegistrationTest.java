package com.example;

import com.example.test.country.Passenger;
import com.example.test.country.PassengerRegistrationEvent;
import com.example.test.country.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@ExtendWith(SpringExtension.class)
//@EnableAutoConfiguration
//@ImportResource("classpath:application-context.xml")
//@ContextConfiguration("classpath:application-context.xml")
@SpringBootTest
@Import(TestBeans.class)
public class RegistrationTest {

    @Autowired
    private Passenger passenger;
    @Autowired
    private RegistrationManager registrationManager;

    @Test
    public void testPersonRegistration() {
        registrationManager.getApplicationContext()
                .publishEvent(new PassengerRegistrationEvent(passenger));
        assertTrue(passenger.isRegistered());
    }
}
