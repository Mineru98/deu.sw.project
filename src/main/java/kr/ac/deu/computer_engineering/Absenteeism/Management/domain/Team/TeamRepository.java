package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Team;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    // UC-A24 : 부서명으로 부서 목록 조회
    List<Team> findAllByTeamNameContaining(String teamName);

    Optional<Team> findById(Long id);
}