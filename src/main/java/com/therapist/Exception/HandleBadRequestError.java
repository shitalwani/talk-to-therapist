package com.therapist.Exception;

import lombok.Getter;

@Getter
public class HandleBadRequestError extends RuntimeException{
    public HandleBadRequestError(String message) {
        super(message);
    }
}
