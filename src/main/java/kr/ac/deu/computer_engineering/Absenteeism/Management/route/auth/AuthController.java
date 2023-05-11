package kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {
    @Tag(name = "인증")
    @Operation(
            summary = "사용자 로그인",
            description = "사용자 로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", "username");
        session.setAttribute("roleList", "ROLE_CEO");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Tag(name = "인증")
    @Operation(
            summary = "사용자 로그아웃",
            description = "사용자 로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("userId"));
        System.out.println(session.getAttribute("roleList"));
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
