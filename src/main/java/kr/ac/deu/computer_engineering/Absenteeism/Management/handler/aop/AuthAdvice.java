package kr.ac.deu.computer_engineering.Absenteeism.Management.handler.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Component
@Aspect
public class AuthAdvice {
    @Around("!within(kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth.*) && within(kr.ac.deu.computer_engineering.Absenteeism.Management.route..*)")
    public Object checkSession(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[AOP]: Auth AOP start");
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            if (obj instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) obj;
                HttpSession session = request.getSession();
                if (!isSessionValid(session)) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
        }
        return joinPoint.proceed();
    }

    private boolean isSessionValid(HttpSession session) {
        Object userId = session.getAttribute("userId");
        List<?> roleList = (List) session.getAttribute("roleList");
        return userId != null && roleList != null;
    }
}
