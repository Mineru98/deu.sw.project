package kr.ac.deu.computer_engineering.Absenteeism.Management.service.allowance;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.AllowanceOfRankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllowanceService {
    private final AllowanceOfRankRepository allowanceOfRankRepository;
}
