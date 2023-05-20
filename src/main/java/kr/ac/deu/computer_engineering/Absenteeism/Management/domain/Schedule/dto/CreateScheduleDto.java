package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.dto;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.Schedule;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.TypeOfTask;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateScheduleDto {
    private String description;

    private String ip;

    private Double lat;

    private Double lng;

    private Boolean isVerified;

    private String typeOfTask;

    private LocalDateTime applyDateTime;

    private Long userId;

    public Schedule toEntity(User user, TypeOfTask typeOfTask) {
        return Schedule.builder()
                .description(description)
                .ip(ip)
                .lat(lat)
                .lng(lng)
                .isVerified(isVerified)
                .typeOfTask(typeOfTask)
                .applyDateTime(applyDateTime)
                .user(user)
                .build();
    }

    public Schedule toEntity(User user, LocalDateTime applyDateTime, TypeOfTask typeOfTask) {
        return Schedule.builder()
                .description(description)
                .ip(ip)
                .lat(lat)
                .lng(lng)
                .isVerified(isVerified)
                .typeOfTask(typeOfTask)
                .applyDateTime(applyDateTime)
                .user(user)
                .build();
    }
}
