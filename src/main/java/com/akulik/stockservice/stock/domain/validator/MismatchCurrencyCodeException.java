package com.akulik.stockservice.stock.domain.validator;

public class MismatchCurrencyCodeException extends RuntimeException {

    private static final String ERROR_MESSAGE =
            "Stock name %s has a price per share currency %s doesn't equal EUR";

    public MismatchCurrencyCodeException(String name, String currency) {
        super(String.format(ERROR_MESSAGE, name, currency));
    }

}
