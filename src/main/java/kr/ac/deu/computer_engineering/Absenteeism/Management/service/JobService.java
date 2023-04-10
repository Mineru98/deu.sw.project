package kr.ac.deu.computer_engineering.Absenteeism.Management.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobService {

    @Scheduled(cron = "0 0 0 * * *")
    public void calcStatement() {
        // TODO : 급여 계산
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void calcHealthCheckHistory() {
        // TODO : 건강보험 이력 생성
    }
}
