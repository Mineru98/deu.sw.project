package kr.ac.deu.computer_engineering.Absenteeism.Management.service.health;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistory;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistoryRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.CustomIllegalStateExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HealthService {
    private final UserRepository userRepository;
    private final HealthCheckHistoryRepository healthCheckHistoryRepository;

    @Transactional(readOnly = true)
    public List<HealthCheckHistory> getList() {
        return healthCheckHistoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<HealthCheckHistory> getListByUserId(Long userId, Integer applyYear) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return healthCheckHistoryRepository.findAllByUserAndApplyYear(user.get(), applyYear);
        } else {
            throw new CustomIllegalStateExceptionHandler("존재하지 않는 직원입니다.");
        }
    }
}
