package com.example.productorderchain.exception;

public final class BusinessServiceOperationException {

    private BusinessServiceOperationException() {
    }

    public static class CustomerNotFoundException extends BaseException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    public static class CustomerAlreadyDeletedException extends BaseException {
        public CustomerAlreadyDeletedException(String message) {
            super(message);
        }
    }
    public static class ProductNotFoundException extends BaseException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class ProductAlreadyDeletedException extends BaseException {
        public ProductAlreadyDeletedException(String message) {
            super(message);
        }
    }
}
