package com.example.bankingsystem.exception;

public final class ServiceOperationNotFoundException extends BaseException {
    private ServiceOperationNotFoundException(String message) {
        super(message);
    }


    public static class CustomerNotFoundException extends BaseException {

        public CustomerNotFoundException(String message) {
            super(message);
        }
    }
    public static class AccountNotFoundException extends BaseException {

        public AccountNotFoundException(String message) {
            super(message);
        }
    }
    public static class TransferNotFoundException extends BaseException {

        public TransferNotFoundException(String message) {
            super(message);
        }
    }
}