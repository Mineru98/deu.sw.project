package kr.ac.deu.computer_engineering.Absenteeism.Management.route.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.HealthCheckHistory.HealthCheckHistory;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.User;
import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.ResState;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.ResponseDTO;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.JobService;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.health.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/health")
public class HealthController {
    final private HealthService healthService;
    final private JobService jobService;

    @Tag(name = "직장인건강보험")
    @Operation(
            summary = "직장인건강보험 목록 조회",
            description = "직장인건강보험 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = true) Integer applyYear
    ) {
        List<HealthCheckHistory> result = healthService.getList(userId, applyYear);
        return new ResponseEntity<>(new ResponseDTO<>(ResState.OK, result), HttpStatus.OK);
    }

    @Tag(name = "직장인건강보험")
    @Operation(
            summary = "직장인건강보험 생성 스케줄러 테스팅",
            description = "직장인건강보험 생성 스케줄러 테스팅")
    @GetMapping("/testing")
    public ResponseEntity<?> whiteBoxTesting(
            @RequestParam(required = true, name = "dateTime", defaultValue = "2023-06-13T10:00") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime
    ) {
        List<User> result = this.jobService.whiteBoxTesting(dateTime);
        return new ResponseEntity<>(new ResponseDTO<>(ResState.OK, result), HttpStatus.OK);
    }

    @Tag(name = "직장인건강보험")
    @Operation(
            summary = "직장인건강보험 Id로 상세 조회",
            description = "직장인건강보험 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "직장인건강보험")
    @Operation(
            summary = "직장인건강보험 정보 생성",
            description = "직장인건강보험 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "직장인건강보험")
    @Operation(
            summary = "직장인건강보험 Id로 직장인건강보험 정보 수정",
            description = "직장인건강보험 정보 수정")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateItemById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "직장인건강보험")
    @Operation(
            summary = "직장인건강보험 Id로 직장인건강보험 정보 삭제",
            description = "직장인건강보험 정보 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
