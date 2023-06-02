package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RankRepository extends JpaRepository<Rank, Long> {
    List<Rank> findAllByIdIn(List<Long> id);

    Optional<Rank> findById(Long id);
}