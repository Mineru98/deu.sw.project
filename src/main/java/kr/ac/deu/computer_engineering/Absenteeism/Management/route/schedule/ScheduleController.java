package kr.ac.deu.computer_engineering.Absenteeism.Management.route.schedule;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Account.dto.AccountDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.Schedule;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.dto.ScheduleDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.Schedule.dto.ScheduleDto;
import kr.ac.deu.computer_engineering.Absenteeism.Management.enums.ResState;
import kr.ac.deu.computer_engineering.Absenteeism.Management.handler.exception.ResponseDTO;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/schedule")
public class ScheduleController {
    final private ScheduleService scheduleService;

    @Tag(name = "일정")
    @Operation(
            summary = "일정 목록 조회",
            description = "일정 목록 조회")
    @GetMapping("")
    public ResponseEntity<?> getItemList(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = false, name = "beginDate", defaultValue = "2023-06-12") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate beginDate,
            @RequestParam(required = false, name = "endDate", defaultValue = "2023-06-19") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<Schedule> result = scheduleService.getList(userId, beginDate, endDate);
        return new ResponseEntity<>(new ResponseDTO<>(ResState.OK, result), HttpStatus.OK);
    }

    @Tag(name = "일정")
    @Operation(
            summary = "일정 Id로 상세 조회",
            description = "일정 상세 조회")
    @GetMapping("/{scheduleId}")
    public ResponseEntity<?> getItemById(@PathVariable Long scheduleId) {
        Schedule result = scheduleService.getSchduleById(scheduleId);
        return new ResponseEntity<>(new ResponseDTO<>(ResState.OK, result), HttpStatus.OK);
    }

    @Tag(name = "일정")
    @Operation(
            summary = "일정 정보 생성",
            description = "일정 정보 생성")
    @PostMapping("")
    public ResponseEntity<?> createItem(@RequestBody ScheduleDto dto) {
        scheduleService.createSchedule(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Tag(name = "일정")
    @Operation(
            summary = "일정 Id로 일정 정보 수정",
            description = "일정 정보 수정")
    @PutMapping("/{scheduleId}")
    public ResponseEntity<?> updateItemById(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleDto dto
    ) {
        scheduleService.updateSchedule(scheduleId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "일정")
    @Operation(
            summary = "일정 Id로 일정 승인",
            description = "일정 승인")
    @PutMapping("/{scheduleId}/approve")
    public ResponseEntity<?> updateItemApproveById(@PathVariable Long scheduleId, @RequestBody ScheduleDto dto) {
        scheduleService.approveSchedule(scheduleId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "일정")
    @Operation(
            summary = "일정 Id로 일정 정보 삭제",
            description = "일정 정보 삭제")
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<?> deleteItemById(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
