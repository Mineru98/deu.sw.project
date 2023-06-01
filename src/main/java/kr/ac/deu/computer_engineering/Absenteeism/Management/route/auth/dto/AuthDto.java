package kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthDto {
    private Long userId;

    private List<String> roleList;

    private Long teamId;
}
