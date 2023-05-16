package com.mock.CarParkingManagement.exception;

public class UsernameExistedException extends RuntimeException {

    public UsernameExistedException() {
    }

    public UsernameExistedException(String message) {
        super(message);
    }
}
