package kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByAccountNumberContaining(String accountNumber);

    void deleteAllByUserId(Long userId);
}