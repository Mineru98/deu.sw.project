package kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthDto {
    private Long userId;

    private String name;

    private Long teamId;

    private String teamName;

    private String rankName;

    private List<String> roleList;
}
