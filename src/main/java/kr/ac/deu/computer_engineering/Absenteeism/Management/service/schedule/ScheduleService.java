package kr.ac.deu.computer_engineering.Absenteeism.Management.service.schedule;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
}
