package ru.andrey.caraccidentreport.exceptions;

public class DataAccessException extends Exception {

    public DataAccessException (String message, Exception e) {
        super(message, e);
    }
    public DataAccessException (String message) {
        super(message);
    }
}
