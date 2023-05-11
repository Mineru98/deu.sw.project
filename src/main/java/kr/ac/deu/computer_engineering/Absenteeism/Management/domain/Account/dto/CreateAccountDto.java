package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.dto;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.Account;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateAccountDto {
    @NotNull
    private String accountNumber;

    @NotNull
    private String nameOfBank;

    private String description;

    private Long companyId;

    private Long userId;

    public Account toEntity(Company company) {
        return Account.builder()
                .accountNumber(accountNumber)
                .nameOfBank(nameOfBank)
                .description(description)
                .company(company)
                .build();
    }

    public Account toEntity(User user) {
        return Account.builder()
                .accountNumber(accountNumber)
                .nameOfBank(nameOfBank)
                .description(description)
                .user(user)
                .build();
    }
}
