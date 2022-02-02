package com.hsbc.qe.customexceptions;

public class ContentNotFoundException extends Exception {

    public ContentNotFoundException(){
        super();
    }

    public ContentNotFoundException(String message) {
        super(message);
    }
}
