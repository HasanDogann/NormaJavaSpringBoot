package com.example.bankingsystem.exception;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 24.05.2022
 */
public final class ServiceOperationBlockedException extends BaseException{


    private ServiceOperationBlockedException(String message) {
        super(message);
    }


    public static class CardBlockedException extends BaseException{
        public CardBlockedException(String message){
            super(message);
        }
    }
}
