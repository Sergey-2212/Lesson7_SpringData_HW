package ru.gb.lesson7_springdata_hw.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FieldsValidationError {
    private String fieldsErrorMessage;
    public FieldsValidationError(String fieldsErrorMessage) {
        this.fieldsErrorMessage = fieldsErrorMessage;
    }
}
