package kr.ac.deu.computer_engineering.Absenteeism.Management.service.rank;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankService {
    private final RankRepository rankRepository;
}
