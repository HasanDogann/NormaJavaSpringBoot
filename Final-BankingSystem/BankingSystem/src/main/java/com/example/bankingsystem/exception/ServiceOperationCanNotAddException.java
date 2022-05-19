package com.example.bankingsystem.exception;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 19.05.2022
 */
public final class ServiceOperationCanNotAddException extends BaseException{

        private ServiceOperationCanNotAddException(String message){
            super(message);
        }

        public static class CustomerIsAlreadyCreatedException extends BaseException{
            public CustomerIsAlreadyCreatedException(String message){
                super(message);
            }

        }}
