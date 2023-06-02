package kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception;

import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.ResState;
import lombok.Data;

@Data
public class ResponseDTO<T> {

    private ResState state;
    private String message;
    private T data;
    public ResponseDTO(ResState state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(ResState state) {
        this.state = state;
    }

    public ResponseDTO(ResState state, String message) {
        this.state = state;
        this.message = message;
    }

    public ResponseDTO(ResState state, T data) {
        this.state = state;
        this.data = data;
    }
}
