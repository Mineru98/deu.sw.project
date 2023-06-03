package kr.ac.deu.computer_engineering.Absenteeism.Management.service;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistory;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistoryRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class JobService {
    final private UserRepository userRepository;
    final private HealthCheckHistoryRepository healthCheckHistoryRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void calcStatement() {
        // TODO : 급여 계산

    }

    //    @Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "0 0 0 * * *")
    public void calcHealthCheckHistory() {
        log.info("SCHEDULER START");
        // 현재 년도를 가져옵니다.
        Integer currentYear = LocalDate.now().getYear();
        // 현재 날짜에서 1년을 뺀 날짜를 가져옵니다.
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        // 입사일로부터 1년이 지났고, 퇴사를 하지 않았은 직원 목록을 가져옵니다.
        List<User> userList = userRepository.findAllByDateOfLeaveIsNullAndDateOfJoinIsNotNullAndDateOfJoinBefore(oneYearAgo);
        // 입사일로부터 1년이 지났고, 퇴사를 하지 않았은 직원들 중 현재 년도에 건강검진을 한 적이 있는 직원 목록을 가져옵니다.
        List<HealthCheckHistory> histories = healthCheckHistoryRepository.findAllByUserInAndApplyYear(userList, currentYear);
        List<User> historyUserList = histories.stream().map(HealthCheckHistory::getUser).collect(Collectors.toList());
        // 현재 년도에 건강검진 내역이 있는 직원을 제거합니다.
        userList.removeIf(user -> historyUserList.stream().anyMatch(historyUser -> Objects.equals(historyUser.getId(), user.getId())));
        // 직원 목록이 0보다 큰 경우만 다음 로직은 진행합니다.
        if (userList.size() > 0) {
            for (User user : userList) {
                HealthCheckHistory healthCheck = new HealthCheckHistory();
                healthCheck.setUser(user);
                healthCheck.setIsVerified(false);
                healthCheck.setIsCompleted(false);
                // 비사무직이거나 사무직인 경우 현재 년도와 생년월일의 홀&짝이 동일한 경우 건강검진 내역을 추가합니다.g
                if (!user.getIsOfficer() || user.getIsOfficer() && (currentYear % 2 == (user.getBirthDay().getYear() % 2))) {
                    healthCheck.setApplyYear(currentYear);
                    healthCheckHistoryRepository.save(healthCheck);
                }
            }
        }
    }
}
