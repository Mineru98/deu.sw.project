package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    <T> List<T> findAllByNameContaining(String Name, Class<T> type);

    <T> List<T> findAllByNameContainingAndTeamId(String Name, Long teamId, Class<T> type);

    <T> Optional<T> findByUsername(String username, Class<T> type);

    Long countByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    <T> Optional<T> findById(Long id, Class<T> type);

    List<User> findAllByDateOfLeaveIsNullAndDateOfJoinIsNotNullAndDateOfJoinBefore(LocalDate date);

    void deleteById(Long id);
}
