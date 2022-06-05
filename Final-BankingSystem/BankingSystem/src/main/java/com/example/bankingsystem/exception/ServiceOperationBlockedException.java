package com.example.bankingsystem.exception;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 24.05.2022
 */
public final class ServiceOperationBlockedException extends BaseException {


    private ServiceOperationBlockedException(String message) {
        super(message);
    }


    public static class CardBlockedException extends BaseException {
        public CardBlockedException(String message) {
            super(message);
        }
    }
}
