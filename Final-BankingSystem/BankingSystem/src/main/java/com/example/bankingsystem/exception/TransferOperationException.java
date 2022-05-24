package com.example.bankingsystem.exception;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 25.05.2022
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
}



