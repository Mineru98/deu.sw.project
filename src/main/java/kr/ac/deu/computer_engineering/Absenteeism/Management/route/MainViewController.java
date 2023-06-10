package kr.ac.deu.computer_engineering.Absenteeism.Management.route;

import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.RoleValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class MainViewController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String mainView(Model model, HttpServletRequest req) {
        model.addAttribute("title", "메인 페이지");
        HttpSession session = req.getSession();
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("teamName", session.getAttribute("teamName"));
        model.addAttribute("rankName", session.getAttribute("rankName"));
        if (RoleValidate.getUserId(session) != null) {
            // View에게 Controller로부터
            return "main/index.html";
        } else {
            return "auth/login.html";
        }
    }
}
