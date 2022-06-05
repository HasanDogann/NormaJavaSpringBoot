package com.example.bankingsystem.exception;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 23.05.2022
 */
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
