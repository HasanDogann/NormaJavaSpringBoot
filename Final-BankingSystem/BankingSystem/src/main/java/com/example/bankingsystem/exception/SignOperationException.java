package com.example.bankingsystem.exception;

/**
 * Author Hasan DOGAN
 * BankingSystemApplication.java
 * 25.05.2022
 */
public final class SignOperationException extends BaseException {
    public SignOperationException(String message) {
        super(message);
    }

    public static class LoginBadCredentialsException extends BaseException {
        public LoginBadCredentialsException(String message) {
            super(message);
        }
    }


}
