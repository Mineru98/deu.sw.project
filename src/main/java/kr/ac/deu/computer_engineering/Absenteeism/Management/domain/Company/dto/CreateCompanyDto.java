package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.dto;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreateCompanyDto {
    @NotNull
    private String companyName; // 회사명

    private LocalDate settlementDate; // 급여정산날짜

    private Boolean isMain; // 본사 여부

    private String address; // 회사 주소

    private Double lat;

    private Double lng;

    public Company toEntity() {
        return Company.builder()
                .companyName(companyName)
                .settlementDate(settlementDate)
                .address(address)
                .isMain(isMain)
                .companyName(companyName)
                .build();
    }
}
