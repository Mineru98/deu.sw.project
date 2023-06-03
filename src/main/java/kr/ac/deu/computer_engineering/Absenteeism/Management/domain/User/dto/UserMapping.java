package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto;

import java.time.LocalDate;

public interface UserMapping {
    Long getId();

    String getName();

    LocalDate getDateOfJoin();

    LocalDate getDateOfLeave();

    LocalDate getBirthDay();

    String getContactNumber();

    Boolean getIsManager();

    Boolean getIsOfficer();
}
