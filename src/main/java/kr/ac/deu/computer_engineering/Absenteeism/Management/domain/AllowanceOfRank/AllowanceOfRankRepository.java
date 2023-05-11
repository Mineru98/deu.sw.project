package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllowanceOfRankRepository extends JpaRepository<AllowanceOfRank, Long> {
    List<AllowanceOfRank> findAllByRank(Rank rank);
}