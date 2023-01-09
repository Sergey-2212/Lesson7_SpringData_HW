package ru.gb.lesson7_springdata_hw.exceptions;

public class AppError {

    public int status;

    public String message;

    public AppError() {
    }

    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
