package kr.ac.deu.computer_engineering.Absenteeism.Management.route.allowance;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.AllowanceOfRank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.AllowanceOfRank.dto.AllowanceOfRankDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.ResState;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.ResponseDTO;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.allowance.AllowanceService;
import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.RoleValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/rank")
public class AllowanceController {
    final private AllowanceService allowanceService;

    @Tag(name = "직급별 수당")
    @Operation(
            summary = "직급별 수당 목록 조회",
            description = "직급별 수당 목록 조회")
    @GetMapping("/{rankId}/allowance")
    public ResponseEntity<?> getItemList(
            HttpServletRequest request,
            @PathVariable Long rankId) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session)) {
            List<AllowanceOfRank> result = allowanceService.getList(rankId);
            return new ResponseEntity<>(new ResponseDTO<>(ResState.OK, result), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Tag(name = "직급별 수당")
    @Operation(
            summary = "직급별 수당 Id로 상세 조회",
            description = "직급별 수당 상세 조회")
    @GetMapping("/{rankId}/allowance/{id}")
    public ResponseEntity<?> getItemById(
            HttpServletRequest request,
            @PathVariable Long rankId,
            @PathVariable Long id) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session)) {
            AllowanceOfRank result = allowanceService.getAccountByRankIdAndId(rankId, id);
            return new ResponseEntity<>(new ResponseDTO<>(ResState.OK, result), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Tag(name = "직급별 수당")
    @Operation(
            summary = "직급별 수당 정보 생성",
            description = "직급별 수당 정보 생성")
    @PostMapping("/allowance")
    public ResponseEntity<?> createItem(
            HttpServletRequest request,
            @Valid @RequestBody AllowanceOfRankDto dto,
            BindingResult bindingResult) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session)) {
            allowanceService.createAllowanceOfRank(dto);
            return new ResponseEntity<>(new ResponseDTO<>(ResState.CREATED), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Tag(name = "직급별 수당")
    @Operation(
            summary = "직급별 수당 Id로 직급별 수당 정보 수정",
            description = "직급별 수당 정보 수정")
    @PutMapping("/allowance/{id}")
    public ResponseEntity<?> updateItemById(
            HttpServletRequest request,
            @PathVariable Long id,
            @Valid @RequestBody AllowanceOfRankDto dto,
            BindingResult bindingResult) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session)) {
            allowanceService.updateAllowanceOfRank(id, dto);
            return new ResponseEntity<>(new ResponseDTO<>(ResState.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Tag(name = "직급별 수당")
    @Operation(
            summary = "직급별 수당 Id로 직급별 수당 정보 삭제",
            description = "직급별 수당 정보 삭제")
    @DeleteMapping("/allowance/{id}")
    public ResponseEntity<?> deleteItemById(
            HttpServletRequest request,
            @PathVariable Long id) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session)) {
            allowanceService.deleteAllowanceOfRank(id);
            return new ResponseEntity<>(new ResponseDTO<>(ResState.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
