package kr.ac.deu.computer_engineering.Absenteeism.Management.route.company;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.dto.CompanyDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.dto.CompanyMapping;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.company.CompanyService;
import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.RoleValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/company")
public class CompanyController {
    private final CompanyService companyService;

    @Tag(name = "회사")
    @Operation(
            summary = "회사 목록 조회",
            description = "회사 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList(
            HttpServletRequest request,
            @RequestParam(required = false) String q) {
        List<CompanyMapping> result = companyService.getList(q);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Tag(name = "회사")
    @Operation(
            summary = "회사 Id로 상세 조회",
            description = "회사 상세 조회")
    @GetMapping("/{companyId}")
    public ResponseEntity<?> getItemById(
            HttpServletRequest request,
            @PathVariable Long companyId) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session)) {
            CompanyMapping comp = companyService.getCompanyById(companyId);
            return new ResponseEntity<>(comp, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Tag(name = "회사")
    @Operation(
            summary = "회사 정보 생성",
            description = "회사 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem(
            HttpServletRequest request,
            @Valid @RequestBody CompanyDto dto,
            BindingResult bindingResult
    ) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session)) {
            companyService.createCompany(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Tag(name = "회사")
    @Operation(
            summary = "회사 Id로 회사 정보 수정",
            description = "회사 정보 수정")
    @PutMapping("/{companyId}")
    public ResponseEntity<?> updateItemById(
            HttpServletRequest request,
            @PathVariable Long companyId,
            @Valid @RequestBody CompanyDto dto,
            BindingResult bindingResult) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session)) {
            companyService.updateCompany(companyId, dto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Tag(name = "회사")
    @Operation(
            summary = "회사 Id로 직급 정보 삭제",
            description = "회사 정보 삭제")
    @DeleteMapping("/{companyId}")
    public ResponseEntity<?> deleteItemById(
            HttpServletRequest request,
            @PathVariable Long companyId) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session)) {
            companyService.deleteCompany(companyId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
