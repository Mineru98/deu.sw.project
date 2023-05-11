package kr.ac.deu.computer_engineering.Absenteeism.Management.route.company;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.Company;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.dto.CreateCompanyDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Company.dto.UpdateCompanyDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> getItemList(@RequestParam(required = false) String q) {
        List<Company> result = companyService.getList(q);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Tag(name = "회사")
    @Operation(
            summary = "회사 Id로 상세 조회",
            description = "회사 상세 조회")
    @GetMapping("/{companyId}")
    public ResponseEntity<?> getItemById(@PathVariable Long companyId) throws Exception {
        Company comp = companyService.getCompanyById(companyId);
        return new ResponseEntity<>(comp, HttpStatus.OK);
    }

    @Tag(name = "회사")
    @Operation(
            summary = "회사 정보 생성",
            description = "회사 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem(
            @RequestBody CreateCompanyDto dto
    ) throws Exception {
        companyService.createCompany(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "회사")
    @Operation(
            summary = "회사 Id로 회사 정보 수정",
            description = "회사 정보 수정")
    @PutMapping("/{companyId}")
    public ResponseEntity<?> updateItemById(
            @RequestBody UpdateCompanyDto dto,
            @PathVariable Long companyId) throws Exception {
        companyService.updateCompany(companyId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "회사")
    @Operation(
            summary = "회사 Id로 직급 정보 삭제",
            description = "회사 정보 삭제")
    @DeleteMapping("/{companyId}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long companyId) throws Exception {
        companyService.deleteCompany(companyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
