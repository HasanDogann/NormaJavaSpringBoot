package com.example.bankingsystem.exception;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 25.05.2022
 */
public final class TransferOperationException extends BaseException {
    private TransferOperationException(String message) {
        super(message);
    }

    public static class PaymentCanNotProceedException extends BaseException {
        public PaymentCanNotProceedException(String message) {
            super(message);
        }
    }

    public static class TransferCanNotProceedException extends BaseException {
        public TransferCanNotProceedException(String message) {
            super(message);
        }
    }

    public static class PaymentBillsCanNotProceedException extends BaseException {
        public PaymentBillsCanNotProceedException(String message) {
            super(message);
        }
    }

}



