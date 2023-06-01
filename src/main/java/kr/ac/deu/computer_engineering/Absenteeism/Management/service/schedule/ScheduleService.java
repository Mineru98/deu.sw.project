package kr.ac.deu.computer_engineering.Absenteeism.Management.service.schedule;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.Schedule;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.ScheduleRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.dto.ScheduleDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.TypeOfTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    
    // 일정 목록 조회
    @Transactional(readOnly = true)
    public List<Schedule> getList(Long userId, LocalDate beginDate, LocalDate endDate) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return scheduleRepository.findAllByUserAndApplyDateTimeBetween(user.get(), beginDate, endDate);
        } else {
            throw new Exception("존재하지 않는 직원입니다.");
        }
    }

    // 일정 상세 조회
    @Transactional(readOnly = true)
    public Schedule getSchduleById(Long id) throws Exception {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) throw new Exception("존재하지 않는 일정입니다.");
        return schedule.get();
    }

    // 일정 등록
    @Transactional
    public void createSchedule(ScheduleDto dto) throws Exception {
        Optional<User> user = userRepository.findById(dto.getUserId());
        if (user.isEmpty()) throw new Exception("존재하지 않는 직원입니다.");
        else {
            Schedule schedule = dto.toEntity(user.get(), TypeOfTask.valueOf(dto.getTypeOfTask()));
            scheduleRepository.save(schedule);
        }
    }

    // 일정 수정
    @Transactional
    public void updateSchedule(Long id, ScheduleDto dto) throws Exception {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) throw new Exception("존재하지 않는 일정입니다.");
        schedule.ifPresent(t -> {
            t.setDescription(dto.getDescription());
            t.setApplyDateTime(dto.getApplyDateTime());
            t.setIp(dto.getIp());
            t.setLat(dto.getLat());
            t.setLng(dto.getLng());
            t.setIsVerified(dto.getIsVerified());
            scheduleRepository.save(t);
        });
    }

    // 일정 승락
    @Transactional
    public void approveSchedule(Long id, ScheduleDto dto) throws Exception {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) throw new Exception("존재하지 않는 일정입니다.");
        schedule.ifPresent(t -> {
            t.setIsVerified(dto.getIsVerified());
        });
    }

    // 일정 삭제
    @Transactional
    public void deleteSchedule(Long id) throws Exception {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) throw new Exception("존재하지 않는 일정입니다.");
        schedule.ifPresent(scheduleRepository::delete);
    }
}
