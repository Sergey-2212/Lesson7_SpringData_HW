package ru.gb.lesson7_springdata_hw.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
        //прокидываем сообщение в супер-класс
    }
}
