package kr.ac.deu.computer_engineering.Absenteeism.Management.service.account;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
}
