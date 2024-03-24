package com.example.test.passengers;

public class PassengerExistsException extends Exception{

    private Passenger passenger;

    public PassengerExistsException(Passenger passenger,String message) {
        super(message);
        this.passenger = passenger;
    }
}
