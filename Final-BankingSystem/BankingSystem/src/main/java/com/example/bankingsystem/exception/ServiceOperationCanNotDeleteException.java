package com.example.bankingsystem.exception;

public final class ServiceOperationCanNotDeleteException extends BaseException {

    private ServiceOperationCanNotDeleteException(String message) {
        super(message);
    }

    public static class CustomerBalanceNotZero extends BaseException {

        public CustomerBalanceNotZero(String message) {
            super(message);
        }
    }

    public static class AccountBalanceNotZero extends BaseException {

        public AccountBalanceNotZero(String message) {
            super(message);
        }
    }
    public static class AccountCardBalanceOrDebtNotZero extends BaseException {

        public AccountCardBalanceOrDebtNotZero(String message) {
            super(message);
        }
    }
}
