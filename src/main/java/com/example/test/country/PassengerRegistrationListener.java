package com.example.test.country;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PassengerRegistrationListener {

    @EventListener
    public void confirmRegistration(PassengerRegistrationEvent
                                            passengerRegistrationEvent) {
        passengerRegistrationEvent.getPassenger().setRegistered(true);
        System.out.println("Confirming the registration for the passenger: "
                + passengerRegistrationEvent.getPassenger());
    }


}
