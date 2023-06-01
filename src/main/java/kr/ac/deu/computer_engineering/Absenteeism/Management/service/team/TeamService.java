package kr.ac.deu.computer_engineering.Absenteeism.Management.service.team;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.Team;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.TeamRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team.dto.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    // 부서 정보 목록 조회
    @Transactional(readOnly = true)
    public List<Team> getList(String name) {
        return teamRepository.findAllByTeamNameContaining(name);
    }

    // 부서 정보 상세 조회
    @Transactional(readOnly = true)
    public Team getTeamById(Long teamId) throws Exception {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) throw new Exception("존재하지 않는 부서입니다.");
        return team.get();
    }

    // 부서 정보 등록
    @Transactional
    public void createTeam(TeamDto dto) throws Exception {
        Team team = dto.toEntity();
        teamRepository.save(team);
    }

    // 부서 정보 수정
    @Transactional
    public void updateTeam(Long teamId, TeamDto dto) throws Exception {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) throw new Exception("존재하지 않는 부서입니다.");
        team.ifPresent(t -> {
            t.setTeamName(dto.getTeamName());
            teamRepository.save(t);
        });
    }

    // 부서 정보 삭제
    @Transactional
    public void deleteTeam(Long teamId) throws Exception {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) throw new Exception("존재하지 않는 부서입니다.");
        team.ifPresent(teamRepository::delete);
    }
}
