package kr.ac.deu.computer_engineering.Absenteeism.Management.service.statement;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.ScheduleRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Statement.StatementRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatementService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final StatementRepository statementRepository;
}
