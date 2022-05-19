package com.example.bankingsystem.exception;

public final class ServiceOperationNotDeleteException extends BaseException{
    private ServiceOperationNotDeleteException(String message) {
        super(message);
    }
    public static class CustomerBalanceNotZero extends BaseException {

        public CustomerBalanceNotZero(String message) {
            super(message);
        }
    }
}
