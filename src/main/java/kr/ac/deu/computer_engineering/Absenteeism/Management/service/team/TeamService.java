package kr.ac.deu.computer_engineering.Absenteeism.Management.service.team;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
}
