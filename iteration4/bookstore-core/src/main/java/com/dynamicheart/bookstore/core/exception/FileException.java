package com.dynamicheart.bookstore.core.exception;

/**
 * Created by dynamicheart on 7/5/2017.
 */
public class FileException extends Exception {

    private static final long serialVersionUID = 7069128458511580615L;

    private int exceptionType = 0;//regular error


    private String messageCode = null;



    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public FileException() {
        super();
    }

    public FileException(String messageCode) {
        super();
        this.messageCode = messageCode;
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileException(Throwable cause) {
        super(cause);
    }

    public FileException(int exceptionType) {
        super();
        this.exceptionType = exceptionType;
    }

    public FileException(int exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public FileException(int exceptionType, String message, String messageCode) {
        super(message);
        this.messageCode = messageCode;
        this.exceptionType = exceptionType;
    }

    public int getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(int exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getMessageCode() {
        return messageCode;
    }

}
