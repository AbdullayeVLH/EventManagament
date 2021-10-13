package com.example.eventapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EventDoesNotExist extends RuntimeException{
    public EventDoesNotExist() { super("Event Does Not Exist");}
}
