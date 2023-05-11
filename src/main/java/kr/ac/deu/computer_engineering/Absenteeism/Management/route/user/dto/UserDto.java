package kr.ac.deu.computer_engineering.Absenteeism.Management.route.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private LocalDate dateOfJoin;

    private LocalDate dateOfLeave;

    @NotNull
    private String contactNumber;

    private String networkMacAddress;
}
