package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    <T> List<T> findAllByNameContaining(String Name, Class<T> type);

    <T> List<T> findAllByNameContainingAndTeamId(String Name, Long teamId, Class<T> type);

    Long countByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    <T> Optional<T> findById(Long id, Class<T> type);

    List<User> findAllByDateOfLeaveIsNull();

    void deleteById(Long id);
}
