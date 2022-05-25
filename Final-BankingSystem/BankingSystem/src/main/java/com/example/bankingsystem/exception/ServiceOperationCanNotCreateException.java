package com.example.bankingsystem.exception;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 19.05.2022
 */
public final class ServiceOperationCanNotCreateException extends BaseException {

    private ServiceOperationCanNotCreateException(String message) {
        super(message);
    }

    public static class CustomerIsAlreadyCreatedException extends BaseException {
        public CustomerIsAlreadyCreatedException(String message) {
            super(message);
        }

    }

    public static class UserIsAlreadyCreatedException extends BaseException {
        public UserIsAlreadyCreatedException(String message) {
            super(message);
        }

    }

    public static class AccountIsAlreadyCreatedException extends BaseException {
        public AccountIsAlreadyCreatedException(String message) {
            super(message);
        }

    }
    public static class UserCanNotCreatException extends BaseException {
        public UserCanNotCreatException(String message) {
            super(message);
        }

    }




}
