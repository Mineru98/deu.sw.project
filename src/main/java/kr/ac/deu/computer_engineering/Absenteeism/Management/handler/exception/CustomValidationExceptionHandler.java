package kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception;

import javax.validation.ValidationException;
import java.util.Map;

public class CustomValidationExceptionHandler extends ValidationException {

    private String message;
    private Map<String, String> errorMap;

    public CustomValidationExceptionHandler(String message) {
        super(message);
    }

    public CustomValidationExceptionHandler(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }
}