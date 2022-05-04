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
    public static class BasketNotFoundException extends BaseException {
        public BasketNotFoundException(String message) {
            super(message);
        }
    }

    public static class BasketAlreadyDeletedException extends BaseException {
        public BasketAlreadyDeletedException(String message) {
            super(message);
        }
    }

    public static class BasketItemNotFoundException extends BaseException {
        public BasketItemNotFoundException(String message) {
            super(message);
        }
    }

    public static class BasketItemAlreadyDeletedException extends BaseException {
        public BasketItemAlreadyDeletedException(String message) {
            super(message);
        }
    }

    public static class BrandNotFoundException extends BaseException {
        public BrandNotFoundException(String message) {
            super(message);
        }
    }

    public static class BrandAlreadyDeletedException extends BaseException {
        public BrandAlreadyDeletedException(String message) {
            super(message);
        }
    }

    public static class CategoryNotFoundException extends BaseException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }

    public static class CategoryAlreadyDeletedException extends BaseException {
        public CategoryAlreadyDeletedException(String message) {
            super(message);
        }
    }
    public static class OrderNotFoundException extends BaseException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public static class OrderAlreadyDeletedException extends BaseException {
        public OrderAlreadyDeletedException(String message) {
            super(message);
        }
    }
}
