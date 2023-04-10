package kr.ac.deu.computer_engineering.Absenteeism.Management.route.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/auth")
public class AuthViewController {
    @RequestMapping("/login")
    public String loginView(Model model) {
        return "auth/login.html";
    }
}
