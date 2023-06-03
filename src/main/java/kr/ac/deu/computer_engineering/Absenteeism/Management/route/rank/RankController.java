package kr.ac.deu.computer_engineering.Absenteeism.Management.route.rank;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.Rank;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.dto.RankDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Rank.dto.RankMapping;
import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.ResState;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.ResponseDTO;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.rank.RankService;
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
public class RankController {
    final private RankService rankService;

    @Tag(name = "직급")
    @Operation(summary = "직급 목록 조회", description = "직급 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session) || RoleValidate.isRoleManager(session)) {
            List<RankMapping> result = rankService.getList(session);
            return new ResponseEntity<>(new ResponseDTO<>(ResState.OK, result), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Tag(name = "직급")
    @Operation(summary = "직급 Id로 상세 조회", description = "직급 상세 조회")
    @GetMapping("/{rankId}")
    public ResponseEntity<?> getItemById(
            HttpServletRequest request,
            @PathVariable Long rankId) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session) || RoleValidate.isRoleManager(session)) {
            RankMapping result = rankService.getRankById(rankId);
            return new ResponseEntity<>(new ResponseDTO<>(ResState.OK, result), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Tag(name = "직급")
    @Operation(summary = "직급 정보 생성", description = "직급 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem(
            HttpServletRequest request,
            @Valid @RequestBody RankDto dto,
            BindingResult bindingResult) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session) || RoleValidate.isRoleManager(session)) {
            rankService.createRank(dto);
            return new ResponseEntity<>(new ResponseDTO<>(ResState.CREATED), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Tag(name = "직급")
    @Operation(summary = "직급 Id로 직급 정보 수정", description = "직급 정보 수정")
    @PutMapping("/{rankId}")
    public ResponseEntity<?> updateItemById(
            HttpServletRequest request,
            @PathVariable Long rankId,
            @Valid @RequestBody RankDto dto) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session) || RoleValidate.isRoleManager(session)) {
            rankService.updateRank(rankId, dto);
            return new ResponseEntity<>(new ResponseDTO<>(ResState.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Tag(name = "직급")
    @Operation(summary = "직급 Id로 직급 정보 삭제", description = "직급 정보 삭제")
    @DeleteMapping("/{rankId}")
    public ResponseEntity<?> deleteItemById(
            HttpServletRequest request,
            @PathVariable Long rankId) {
        HttpSession session = request.getSession();
        if (RoleValidate.isRoleCeo(session) || RoleValidate.isRoleManager(session)) {
            rankService.deleteRank(rankId);
            return new ResponseEntity<>(new ResponseDTO<>(ResState.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
