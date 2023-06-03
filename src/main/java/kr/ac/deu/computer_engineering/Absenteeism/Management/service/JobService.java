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
        Integer currentYear = LocalDate.now().getYear();
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        List<User> userList = userRepository.findAllByDateOfLeaveIsNullAndDateOfJoinIsNotNullAndDateOfJoinBefore(oneYearAgo);
        List<HealthCheckHistory> histories = healthCheckHistoryRepository.findAllByUserInAndApplyYear(userList, currentYear);
        List<User> historyUserList = histories.stream().map(HealthCheckHistory::getUser).collect(Collectors.toList());
        userList.removeIf(user -> historyUserList.stream().anyMatch(historyUser -> Objects.equals(historyUser.getId(), user.getId())));
        if (userList.size() > 0) {
            for (User user: userList) {
                HealthCheckHistory healthCheck = new HealthCheckHistory();
                healthCheck.setUser(user);
                healthCheck.setIsVerified(false);
                healthCheck.setIsCompleted(false);
                if (user.getIsOfficer()) {
                    if ((currentYear % 2 == (user.getBirthDay().getYear() % 2))) {
                        healthCheck.setApplyYear(currentYear);
                        healthCheckHistoryRepository.save(healthCheck);
                    }
                } else {
                    healthCheck.setApplyYear(currentYear);
                    healthCheckHistoryRepository.save(healthCheck);
                }
            }
        }
    }
}
