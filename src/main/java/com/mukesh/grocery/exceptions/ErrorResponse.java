package com.mukesh.grocery.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private int hpptStatus;
    private LocalDateTime dateTime;

    public ErrorResponse(String message, int hpptStatus, LocalDateTime dateTime) {
        this.message = message;
        this.hpptStatus = hpptStatus;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHpptStatus() {
        return hpptStatus;
    }

    public void setHpptStatus(int hpptStatus) {
        this.hpptStatus = hpptStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
