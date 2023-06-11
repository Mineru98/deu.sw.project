package kr.ac.deu.computer_engineering.Absenteeism.Management;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.CompanyRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistory;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistoryRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.RankRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.Team;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.TeamRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SchedulerTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RankRepository rankRepository;
    @Autowired
    private HealthCheckHistoryRepository healthCheckHistoryRepository;

    @Test
    public void calcHealthCheckHistory() {
        LocalDate inputDate = LocalDate.parse("2023-06-05");
        Integer currentYear = inputDate.getYear();
        LocalDate oneYearAgo = inputDate.minusYears(1);

        Optional<Company> company = companyRepository.findById(1L, Company.class);
        Optional<Team> team = teamRepository.findById(1L);
        Optional<Rank> rank = rankRepository.findById(1L, Rank.class);

        List<User> userList = new ArrayList<>();
        int case_1 = 1;
        int case_2 = 1;
        int case_3 = 1;
        for (int i = 1; i <= 16; i++) {
            User user_1 = new User();
            Long tmp_id = (1978L + i);
            user_1.setName(String.valueOf(tmp_id));
            user_1.setUsername(String.valueOf(tmp_id));
            user_1.setBirthDay(LocalDate.parse((tmp_id) + "-01-01"));

            if (case_1 <= 2) {
                user_1.setDateOfJoin(LocalDate.parse("2022-01-01"));
            } else if (case_1 <= 4) {
                user_1.setDateOfJoin(LocalDate.parse("2023-01-01"));
            } else if (case_1 <= 6) {
                user_1.setDateOfJoin(LocalDate.parse("2022-01-01"));
            } else if (case_1 <= 8) {
                user_1.setDateOfJoin(LocalDate.parse("2023-01-01"));
            } else if (case_1 <= 10) {
                user_1.setDateOfJoin(LocalDate.parse("2022-01-01"));
            } else if (case_1 <= 12) {
                user_1.setDateOfJoin(LocalDate.parse("2023-01-01"));
            } else if (case_1 <= 14) {
                user_1.setDateOfJoin(LocalDate.parse("2022-01-01"));
            } else if (case_1 <= 16) {
                user_1.setDateOfJoin(LocalDate.parse("2023-01-01"));
            }
            case_1 += 1;

            if (case_2 < 9) {
                if (case_2 < 5) {
                    user_1.setDateOfLeave(null);
                } else {
                    user_1.setDateOfLeave(LocalDate.parse("2023-01-01"));
                }
            } else {
                if (case_2 < 13) {
                    user_1.setDateOfLeave(LocalDate.parse("2023-01-01"));
                } else {
                    user_1.setDateOfLeave(null);
                }
            }

            case_2 += 1;

            if (case_3 < 9) {
                user_1.setIsOfficer(true);
            } else {
                user_1.setIsOfficer(false);
            }
            case_3 += 1;
            user_1.setCompany(company.get());
            user_1.setRank(rank.get());
            user_1.setTeam(team.get());
            userList.add(user_1);
        }
        userRepository.saveAll(userList);
        for (User user_1 : userList) {
            System.out.println("UserId : " + user_1.getId() + ", " +
                    "사무직여부 : " + user_1.getIsOfficer() + ", " +
                    "생년월일 : " + user_1.getBirthDay() + ", " +
                    "입사일 : " + user_1.getDateOfJoin() + ", " +
                    "퇴사일 : " + user_1.getDateOfLeave());
        }
        userList = userRepository.findAllByDateOfLeaveIsNullAndDateOfJoinIsNotNullAndDateOfJoinBefore(oneYearAgo);
        // 입사일로부터 1년이 지났고, 퇴사를 하지 않았은 직원들 중 현재 년도에 건강검진을 한 적이 있는 직원 목록을 가져옵니다.
        List<HealthCheckHistory> histories = healthCheckHistoryRepository.findAllByUserInAndApplyYear(userList, currentYear);
        List<User> historyUserList = histories.stream().map(HealthCheckHistory::getUser).collect(Collectors.toList());
        // 현재 년도에 건강검진 내역이 있는 직원을 제거합니다.
        userList.removeIf(user -> historyUserList.stream().anyMatch(historyUser -> Objects.equals(historyUser.getId(), user.getId())));
        for (User user : userList) {
            HealthCheckHistory healthCheck = new HealthCheckHistory();
            healthCheck.setUser(user);
            healthCheck.setIsVerified(false);
            healthCheck.setIsCompleted(false);
            // 비사무직이거나 사무직인 경우 현재 년도와 생년월일의 홀&짝이 동일한 경우 건강검진 내역을 추가합니다.
            if (!user.getIsOfficer() || user.getIsOfficer() && (currentYear % 2 == (user.getBirthDay().getYear() % 2))) {
                healthCheck.setApplyYear(currentYear);
                    System.out.println("UserId : " + healthCheck.getUser().getId() + ", " +
                            "사무직여부 : " + healthCheck.getUser().getIsOfficer() + ", " +
                            "생년월일 : " + healthCheck.getUser().getBirthDay() + ", " +
                            "입사일 : " + healthCheck.getUser().getDateOfJoin() + ", " +
                            "퇴사일 : " + healthCheck.getUser().getDateOfLeave() + ", " +
                            "건강검진해당년도 : " + healthCheck.getApplyYear());
            }
        }
    }
}
