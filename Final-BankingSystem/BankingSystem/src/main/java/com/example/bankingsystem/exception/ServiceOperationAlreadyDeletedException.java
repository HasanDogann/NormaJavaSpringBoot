package com.example.bankingsystem.exception;

public final class ServiceOperationAlreadyDeletedException extends BaseException{


    private ServiceOperationAlreadyDeletedException(String message){
        super(message);
    }

    public static class CustomerAlreadyDeletedException extends BaseException{
        public CustomerAlreadyDeletedException(String message){
            super(message);
        }
    }

    public static class AccountAlreadyDeletedException extends BaseException{
        public AccountAlreadyDeletedException(String message){
            super(message);
        }
    }
    public static class CardAlreadyDeletedException extends BaseException{

        public CardAlreadyDeletedException(String message) {
            super(message);
        }
    }

    public static class TransferAlreadyDeletedException extends BaseException{

        public TransferAlreadyDeletedException(String message) {
            super(message);
        }
    }
    public static class UserAlreadyDeletedException extends BaseException{

        public UserAlreadyDeletedException(String message) {
            super(message);
        }
    }









}
