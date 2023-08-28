package com.paraparp.realestatestats.errors;

import lombok.Getter;

public class FileProcessingException extends RuntimeException {

    @Getter
    private final String detailedMessage;

    public FileProcessingException(String message, String detailedMessage) {
        super(message);
        this.detailedMessage = detailedMessage;
    }


}