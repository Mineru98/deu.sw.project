package kr.ac.deu.computer_engineering.Absenteeism.Management.route.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.health.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/health")
public class HealthController {
    final private HealthService healthService;

    @Tag(name = "직장인건강보험")
    @Operation(
            summary = "직장인건강보험 목록 조회",
            description = "직장인건강보험 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList() {
        return new ResponseEntity<>(HttpStatus.OK);
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
