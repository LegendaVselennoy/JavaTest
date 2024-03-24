package com.example.test;

//import com.example.test.passengers.Passenger;

import com.example.test.country.Passenger;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Flight {

    private String flightNumber;
    private int seats;
    Set<Passenger> passengers = new HashSet<>();

    public Flight(String flightNumber, int seats) {
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    public Set<Passenger> getPassengers() {
        return Collections.unmodifiableSet(passengers);
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getSeats() {
        return seats;
    }

    public int getNumberOfPassengers() {
        return passengers.size();
    }

    public void setSeats(int seats) {
        if (passengers.size() > seats) {
            throw new RuntimeException(
                    "Cannot reduce seats under the number of existing passengers!");
        }
        this.seats = seats;
    }

    public boolean addPassenger(Passenger passenger) {
        if (passengers.size() >= seats) {
            throw new RuntimeException(
                    "Cannot add more passengers than the capacity of the flight!");
        }
        return passengers.add(passenger);
    }

    public boolean removePassenger(Passenger passenger) {
        return passengers.remove(passenger);
    }

    @Override
    public String toString() {
        return "Flight " + getFlightNumber();
    }
}
