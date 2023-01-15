package ru.gb.lesson7_springdata_hw.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppError {
    public String message;

    public AppError( String message)
    {
        this.message = message;
    }
}
