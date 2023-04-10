package kr.ac.deu.computer_engineering.Absenteeism.Management.service.statement;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Statement.StatementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatementService {
    private final StatementRepository statementRepository;
}
