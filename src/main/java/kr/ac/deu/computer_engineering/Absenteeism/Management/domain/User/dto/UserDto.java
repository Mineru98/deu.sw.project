package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.Team;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.Encrypt;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {
    @NotEmpty(message = "빈문자열은 허용하지 않습니다.")
    @NotNull(message = "이름을 입력해주세요.")
    @Size(min = 2, max = 8, message = "이름은 2자를 넘어야하고, 8자를 초과할 수 없습니다.")
    private String name; // 직원 이름

    @NotEmpty(message = "빈문자열은 허용하지 않습니다.")
    @NotNull(message = "아이디를 입력해주세요.")
    @Size(min = 4, max = 16, message = "아이디는 4자를 넘어야하고, 16자를 초과할 수 없습니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$", message = "아이디는 영어와 숫자로만 입력되어야 합니다.")
    private String username; // 로그인 계정

    @NotEmpty(message = "빈문자열은 허용하지 않습니다.")
    @NotNull(message = "입사일을 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$",
            message = "비밀번호는 최소 8자리 이상, 최대 16자리 이하의 소문자, 대문자, 숫자, 특수문자(@$!%*#?&)를 포함해야 합니다.")
    private String password;

    @NotNull(message = "입사일을 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate dateOfJoin; // 입사일

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate dateOfLeave; // 퇴사일

    @NotNull(message = "생년월일을 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate birthDay; // 생년월일

    @Pattern(regexp = "^\\+?[0-9]{1,3}-?[0-9]{1,4}-?[0-9]{4}$",
            message = "연락처 패턴에 맞게 입력해주세요.")
    private String contactNumber; // 연락처

    @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$",
            message = "MAC 주소 패턴에 맞게 입력해주세요.")
    private String networkMacAddress; // MAC 주소

    private Boolean isManager; // 관리자 여부

    private Boolean isOfficer; // 사무직 여부

    @NotNull(message = "부서를 선택해주세요.")
    private Long teamId; // 부서

    @NotNull(message = "회사를 선택해주세요.")
    private Long companyId; // 회사

    @NotNull(message = "직급을 선택해주세요.")
    private Long rankId; // 직급

    @NotNull(message = "시스템 권한을 선택해주세요.")
    @Size(min = 1, message = "최소 한개 이상의 시스템 권한을 선택해야합니다.")
    private List<Long> roleIdList; // 시스템 권한

    // 최종 사용자 정보
    public User toEntity(Team team, Rank rank, Company company) {
        return User.builder()
                .name(name)
                .username(username)
                .dateOfJoin(dateOfJoin)
                .birthDay(birthDay)
                .isManager(isManager)
                .isOfficer(isOfficer)
                .contactNumber(contactNumber)
                .password(Encrypt.encode(password))
                .team(team)
                .rank(rank)
                .company(company)
                .build();
    }
}
