package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AllowanceOfRankRepository extends JpaRepository<AllowanceOfRank, Long> {
    <T> List <T> findAllByRank(Rank rank, Class<T> type);

    <T> Optional<T> findById(Long id, Class<T> type);

    <T> Optional<T> findByRankIdAndId(Long rankId, Long id, Class<T> type);
}