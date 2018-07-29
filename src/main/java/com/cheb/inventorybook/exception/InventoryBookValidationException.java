package com.cheb.inventorybook.exception;

public class InventoryBookValidationException extends RuntimeException {

    public InventoryBookValidationException() {
        super();
    }

    public InventoryBookValidationException(String message) {
        super(message);
    }

    public InventoryBookValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryBookValidationException(Throwable cause) {
        super(cause);
    }

    protected InventoryBookValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
