package com.example;

import com.example.test.passengers.Passenger;
import com.example.test.passengers.PassengerDao;
import com.example.test.passengers.PassengerExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith({ExecutionContextExtension.class,
             DatabaseOperationsExtension.class,
             DataAccessObjectParameterResolver.class,
             LogPassengerExistsExceptionExtension.class})
public class PassengerTest {

    private PassengerDao passengerDao;

    public PassengerTest(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    @Test
    void testPassenger() throws IOException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        assertEquals("Passenger John Smith with identifier: 123-456-789",
                passenger.toString());
    }

    @Test
    void testInsertPassenger() {
        Passenger passenger = new Passenger("123-456-789",
                "John Smith");
        try {
            passengerDao.insert(passenger);
        } catch (PassengerExistsException e) {
            throw new RuntimeException(e);
        }
        assertEquals("John Smith",
                passengerDao.getById("123-456-789").getName());
    }
    @Test
    void testUpdatePassenger() {
        Passenger passenger = new Passenger("123-456-789",
                "John Smith");
        try {
            passengerDao.insert(passenger);
        } catch (PassengerExistsException e) {
            throw new RuntimeException(e);
        }
        passengerDao.update("123-456-789", "Michael Smith");
        assertEquals("Michael Smith",
                passengerDao.getById("123-456-789").getName());
    }
    @Test
    void testDeletePassenger() {
        Passenger passenger = new Passenger("123-456-789",
                "John Smith");
        try {
            passengerDao.insert(passenger);
        } catch (PassengerExistsException e) {
            throw new RuntimeException(e);
        }
        passengerDao.delete(passenger);
        assertNull(passengerDao.getById("123-456-789"));
    }
}
