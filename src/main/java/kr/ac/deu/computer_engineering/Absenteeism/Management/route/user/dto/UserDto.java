package kr.ac.deu.computer_engineering.Absenteeism.Management.route.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    @NotNull(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "비밀번호 입력해주세요.")
    private String password;

    @NotNull(message = "입사일을 입력해주세요.")
    private LocalDate dateOfJoin;

    private LocalDate dateOfLeave;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "^\\+?[0-9]{1,3}-?[0-9]{1,4}-?[0-9]{4}$")
    private String contactNumber; // 연락처

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$")
    private String networkMacAddress;
}
