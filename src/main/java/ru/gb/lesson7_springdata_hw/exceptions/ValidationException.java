package ru.gb.lesson7_springdata_hw.exceptions;


import java.util.List;
import java.util.stream.Collectors;


public class ValidationException extends RuntimeException {
//    private List<String> errorMessagesList;

    public ValidationException(List<String> errorMessagesList) {
        super(errorMessagesList.stream().collect(Collectors.joining(", ")));
//        this.errorMessagesList = errorMessagesList;
    }
}
