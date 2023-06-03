package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RankRepository extends JpaRepository<Rank, Long> {
    Long countByRankName(String rankName);

    <T> List<T> findAllByIdNotIn(List<Long> id, Class<T> type);

    <T> Optional<T> findById(Long id, Class<T> type);
}