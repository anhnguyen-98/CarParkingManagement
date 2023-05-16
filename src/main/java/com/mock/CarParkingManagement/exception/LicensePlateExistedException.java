package com.mock.CarParkingManagement.exception;

public class LicensePlateExistedException extends RuntimeException {

    public LicensePlateExistedException() {
    }

    public LicensePlateExistedException(String message) {
        super(message);
    }
}
