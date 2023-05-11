package kr.ac.deu.computer_engineering.Absenteeism.Management.route.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.Account;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.dto.CreateAccountDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.dto.UpdateAccountDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/account")
public class AccountController {
    final private AccountService accountService;

    @Tag(name = "계좌")
    @Operation(
            summary = "계좌 목록 조회",
            description = "계좌 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList(@RequestParam(required = false) String q) {
        List<Account> result = accountService.getList(q);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Tag(name = "계좌")
    @Operation(
            summary = "계좌 Id로 상세 조회",
            description = "계좌 상세 조회")
    @GetMapping("/{accountId}")
    public ResponseEntity<?> getItemById(@PathVariable Long accountId) throws Exception {
        Account account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @Tag(name = "계좌")
    @Operation(
            summary = "계좌 정보 생성(직원)",
            description = "계좌 정보 생성(직원)")
    @PostMapping("/user")
    public ResponseEntity<?> createItemForUser(@RequestBody CreateAccountDto dto) throws Exception {
        accountService.createAccountForUser(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "계좌")
    @Operation(
            summary = "계좌 정보 생성(회사)",
            description = "계좌 정보 생성(회사)")
    @PostMapping("/company")
    public ResponseEntity<?> createItemForCompany(@RequestBody CreateAccountDto dto) throws Exception {
        accountService.createAccountForCompany(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "계좌")
    @Operation(
            summary = "계좌 Id로 계좌 정보 수정",
            description = "계좌 정보 수정")
    @PutMapping("/user/{accountId}")
    public ResponseEntity<?> updateItemForUserById(@PathVariable Long accountId, @RequestBody UpdateAccountDto dto) throws Exception {
        accountService.updateAccountForUser(accountId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "계좌")
    @Operation(
            summary = "계좌 Id로 계좌 정보 수정",
            description = "계좌 정보 수정")
    @PutMapping("/company/{accountId}")
    public ResponseEntity<?> updateItemForCompanyById(@PathVariable Long accountId, @RequestBody UpdateAccountDto dto) throws Exception {
        accountService.updateAccountForCompany(accountId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "계좌")
    @Operation(
            summary = "계좌 Id로 계좌 정보 삭제",
            description = "계좌 정보 삭제")
    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long accountId) throws Exception {
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
