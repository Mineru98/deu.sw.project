package kr.ac.deu.computer_engineering.Absenteeism.Management.service.account;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.Account;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.AccountRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.dto.CreateAccountDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.dto.UpdateAccountDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.CompanyRepository;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.dto.CreateCompanyDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.dto.UpdateCompanyDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final AccountRepository accountRepository;

    // 계좌 정보 목록 조회
    @Transactional(readOnly = true)
    public List<Account> getList(String name) {
        return accountRepository.findAllByAccountNumberContaining(name);
    }

    // 계좌 정보 상세 조회
    @Transactional(readOnly = true)
    public Account getAccountById(Long accountId) throws Exception {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) throw new Exception("존재하지 않는 계좌입니다.");
        return account.get();
    }

    // 회사 계좌 정보 등록
    @Transactional
    public void createAccountForCompany(CreateAccountDto dto) throws Exception {
        Optional<Company> company = companyRepository.findById(dto.getCompanyId());
        company.ifPresent(t -> {
            Account account = dto.toEntity(t);
            accountRepository.save(account);
        });
    }

    // 직원 계좌 정보 등록
    @Transactional
    public void createAccountForUser(CreateAccountDto dto) throws Exception {
        Optional<User> user = userRepository.findById(dto.getUserId());
        user.ifPresent(t -> {
            Account account = dto.toEntity(t);
            accountRepository.save(account);
        });
    }

    // 회사 계좌 정보 수정
    @Transactional
    public void updateAccountForCompany(Long accountId, UpdateAccountDto dto) throws Exception {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) throw new Exception("존재하지 않는 계좌입니다.");
        account.ifPresent(t -> {
            t.setAccountNumber(dto.getAccountNumber());
            t.setNameOfBank(dto.getNameOfBank());
            t.setDescription(dto.getDescription());
            if (dto.getCompanyId() != null) {
                Optional<Company> company = companyRepository.findById(dto.getCompanyId());
                company.ifPresent(t::setCompany);
            }
            accountRepository.save(t);
        });
    }

    // 직원 계좌 정보 수정
    @Transactional
    public void updateAccountForUser(Long accountId, UpdateAccountDto dto) throws Exception {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) throw new Exception("존재하지 않는 계좌입니다.");
        account.ifPresent(t -> {
            t.setAccountNumber(dto.getAccountNumber());
            t.setNameOfBank(dto.getNameOfBank());
            t.setDescription(dto.getDescription());
            if (dto.getUserId() != null) {
                Optional<User> user = userRepository.findById(dto.getUserId());
                user.ifPresent(t::setUser);
            }
            accountRepository.save(t);
        });
    }

    // 계좌 정보 삭제
    @Transactional
    public void deleteAccount(Long accountId) throws Exception {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isEmpty()) throw new Exception("존재하지 않는 계좌입니다.");
        account.ifPresent(accountRepository::delete);
    }
}
