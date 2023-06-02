package kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;

public class CustomInvalidDataAccessApiUsageExceptionHandler extends InvalidDataAccessApiUsageException {
    public CustomInvalidDataAccessApiUsageExceptionHandler(String msg) {
        super(msg);
    }
}
