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
import java.time.LocalDateTime;
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
            if (dto.getTypeOfTask().equals("출근")) {
                LocalDateTime compareBeginDate = LocalDateTime.now();
                LocalDateTime compareEndDate = LocalDateTime.now();
                // 현재 날짜의 시간을 오전 9시로 설정
                compareBeginDate.withHour(9);
                // 현재 날짜의 1일을 더한 날의 오전 6시로 설정
                compareEndDate.plusDays(1L);
                compareEndDate.withHour(6);
                // 현재 날짜의 오전 9시와 현재 날짜의 1일 뒤의 날짜의 오전 6시 사이에 적용 날짜가 해당이 되어야 출근이 가능하다.
                if (!(dto.getApplyDateTime().isAfter(compareBeginDate) && dto.getApplyDateTime().isBefore(compareEndDate))) {
                    throw new Exception("허용 되지 않는 범위의 출근 시간입니다.");
                }
            } else if (dto.getTypeOfTask().equals("퇴근")) {
                LocalDateTime compareBeginDate = LocalDateTime.now();
                LocalDateTime compareEndDate = LocalDateTime.now();
                if (0 < LocalDateTime.now().getHour() && LocalDateTime.now().getHour() < 6) {
                    // 현재 날짜의 1일을 뺀 날의 오전 9시로 설정
                    compareEndDate.minusDays(1L);
                    compareBeginDate.withHour(9);
                    // 현재 날짜 오전 6시로 설정
                    compareEndDate.withHour(6);
                } else {
                    // 현재 날짜의 시간을 오전 9시로 설정
                    compareBeginDate.withHour(9);
                    // 현재 날짜의 1일을 더한 날의 오전 6시로 설정
                    compareEndDate.plusDays(1L);
                    compareEndDate.withHour(6);
                }
                // 전 날짜의 오전 9시와 현재 날짜의 날짜의 오전 6시 사이에 적용 날짜가 해당이 되어야 출근이 가능하다.
                if (!(dto.getApplyDateTime().isAfter(compareBeginDate) && dto.getApplyDateTime().isBefore(compareEndDate))) {
                    throw new Exception("허용 되지 않는 범위의 퇴근 시간입니다.");
                }
            }

            List<Schedule> scheduleList = scheduleRepository.findAllByApplyDateTime(dto.getApplyDateTime());
            if (dto.getTypeOfTask().equals("휴가")) {
                if (scheduleList.size() > 0) {
                    // 이미 해당 날짜에 출근/퇴근 내용이 있다면 모두 제거한다.
                    LocalDateTime beginDate = dto.getApplyDateTime();
                    LocalDateTime endDate = dto.getApplyDateTime();
                    // 해당 날짜의 시간을 오전 9시로 설정
                    beginDate.withHour(9);
                    // 해당 날짜의 1일을 더한 날의 오전 6시로 설정
                    endDate.plusDays(1L);
                    endDate.withHour(6);
                    scheduleRepository.deleteAllByUserAndApplyDateTimeBetween(dto.getUserId(), beginDate, endDate);
                }
                LocalDateTime startApplyDateTime = dto.getApplyDateTime();
                startApplyDateTime.withHour(9);
                LocalDateTime endApplyDateTime = dto.getApplyDateTime();
                endApplyDateTime.withHour(18);
                Schedule schedule1 = dto.toEntity(user.get(), startApplyDateTime, TypeOfTask.valueOf("출근"));
                Schedule schedule2 = dto.toEntity(user.get(), endApplyDateTime, TypeOfTask.valueOf("퇴근"));
                scheduleRepository.save(schedule1);
                scheduleRepository.save(schedule2);
            } else {
                if (dto.getTypeOfTask().equals("출근") && scheduleList.size() > 0) {
                    throw new Exception("해당 일정에 이미 근무 기록이 있습니다.");
                } else if (dto.getTypeOfTask().equals("퇴근") && scheduleList.size() != 1) {
                    throw new Exception("해당 일정에 출근 기록이 없습니다.");
                }
                Schedule schedule = dto.toEntity(user.get(), TypeOfTask.valueOf(dto.getTypeOfTask()));
                scheduleRepository.save(schedule);
            }
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
