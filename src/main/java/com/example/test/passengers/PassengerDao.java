package com.example.test.passengers;

public interface PassengerDao {
    void insert(Passenger passenger) throws PassengerExistsException;

    void update(String id, String name);

    void delete(Passenger passenger);

    Passenger getById(String id);
}
