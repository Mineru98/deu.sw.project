package kr.ac.deu.computer_engineering.Absenteeism.Management.route.allowance;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.AllowanceOfRank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.dto.AllowanceOfRankDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.dto.AllowanceOfRankDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.allowance.AllowanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/allowance")
public class AllowanceController {
    final private AllowanceService allowanceService;

    @Tag(name = "직급별 수당")
    @Operation(
            summary = "직급별 수당 목록 조회",
            description = "직급별 수당 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList(@RequestParam(required = true) Long rankId) throws Exception {
        List<AllowanceOfRank> result = allowanceService.getList(rankId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Tag(name = "직급별 수당")
    @Operation(
            summary = "직급별 수당 Id로 상세 조회",
            description = "직급별 수당 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) throws Exception {
        AllowanceOfRank allowanceOfRank = allowanceService.getAccountById(id);
        return new ResponseEntity<>(allowanceOfRank, HttpStatus.OK);
    }

    @Tag(name = "직급별 수당")
    @Operation(
            summary = "직급별 수당 정보 생성",
            description = "직급별 수당 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem(@RequestBody AllowanceOfRankDto dto) throws Exception {
        allowanceService.createAllowanceOfRank(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "직급별 수당")
    @Operation(
            summary = "직급별 수당 Id로 직급별 수당 정보 수정",
            description = "직급별 수당 정보 수정")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateItemById(
            @PathVariable Long id,
            @RequestBody AllowanceOfRankDto dto
        ) throws Exception {
        allowanceService.updateAllowanceOfRank(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "직급별 수당")
    @Operation(
            summary = "직급별 수당 Id로 직급별 수당 정보 삭제",
            description = "직급별 수당 정보 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long id) throws Exception {
        allowanceService.deleteAllowanceOfRank(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
