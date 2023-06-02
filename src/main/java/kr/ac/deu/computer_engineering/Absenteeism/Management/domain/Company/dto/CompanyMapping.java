package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public interface CompanyMapping {
    Long getId();

    String getCompanyName();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    LocalDate getSettlementDate();

    Boolean getIsMain();

    String getAddress();

    Double getLat();

    Double getLng();
}
