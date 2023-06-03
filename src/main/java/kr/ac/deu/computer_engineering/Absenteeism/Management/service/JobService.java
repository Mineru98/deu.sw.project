package kr.ac.deu.computer_engineering.Absenteeism.Management.service;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistory;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistoryRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JobService {
    final private UserRepository userRepository;
    final private HealthCheckHistoryRepository healthCheckHistoryRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void calcStatement() {
        // TODO : 급여 계산

    }

    @Scheduled(cron = "0 0 0 * * *")
    public void calcHealthCheckHistory() {
        Integer currentYear = LocalDate.now().getYear();
        List<User> userList = userRepository.findAllByDateOfLeaveIsNull();
        List<HealthCheckHistory> histories = healthCheckHistoryRepository.findAllByUserNotInAndApplyYear(userList, currentYear);
        List<User> users = histories.stream().map(HealthCheckHistory::getUser).collect(Collectors.toList());
        for (User user: users) {
            HealthCheckHistory healthCheck = new HealthCheckHistory();
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
