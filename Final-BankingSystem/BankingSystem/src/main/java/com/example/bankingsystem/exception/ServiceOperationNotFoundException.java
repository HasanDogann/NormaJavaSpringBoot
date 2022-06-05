package com.example.bankingsystem.exception;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 24.05.2022
 */
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

    public static class UserNotFoundException extends BaseException {

        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class CardNotFoundException extends BaseException {

        public CardNotFoundException(String message) {
            super(message);
        }
    }
}