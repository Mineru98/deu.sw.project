package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByUserAndApplyDateTimeBetween(User user, LocalDate startDate, LocalDate endDate);

    List<Schedule> findAllByApplyDateTime(LocalDateTime applyDateTime);

    void deleteAllByUserAndApplyDateTimeBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}