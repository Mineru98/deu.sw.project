package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthCheckHistoryRepository extends JpaRepository<HealthCheckHistory, Long> {
    List<HealthCheckHistory> findAllByUserAndApplyYear(User user, Integer applyYear);

    List<HealthCheckHistory> findAllByUserInAndApplyYear(List<User> user, Integer applyYear);

    void deleteAllByUserId(Long userId);
}
