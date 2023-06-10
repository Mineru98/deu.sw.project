package kr.ac.deu.computer_engineering.Absenteeism.Management.route.user;

import kr.ac.deu.computer_engineering.Absenteeism.Management.domain.User.dto.UserMapping;
import kr.ac.deu.computer_engineering.Absenteeism.Management.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserViewController {
    private final UserService userService;

    // 직원 목록 조회(바운더리 Class)
    @RequestMapping("/list")
    public String listView(Model model, HttpServletRequest req) {
        model.addAttribute("title", "사용자 목록 페이지");
        HttpSession session = req.getSession();
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("teamName", session.getAttribute("teamName"));
        model.addAttribute("rankName", session.getAttribute("rankName"));
        return "user/list.html";
    }

    // 직원 등록 화면(바운더리 Class)
    @RequestMapping("/add")
    public String addView(Model model, HttpServletRequest req) {
        model.addAttribute("title", "사용자 등록 페이지");
        HttpSession session = req.getSession();
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("teamName", session.getAttribute("teamName"));
        model.addAttribute("rankName", session.getAttribute("rankName"));
        return "user/add.html";
    }

    // 직원 상세 조회 화면(바운더리 Class)
    @RequestMapping("/detailView")
    public String detailView(Model model, HttpServletRequest req) {
        model.addAttribute("title", "사용자 상세 페이지");
        HttpSession session = req.getSession();
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("teamName", session.getAttribute("teamName"));
        model.addAttribute("rankName", session.getAttribute("rankName"));
        return "user/item.html";
    }

    // 직원 자기 화면(바운더리 Class)
    @RequestMapping("/myPage")
    public String myPageView(Model model, HttpServletRequest req) {
        model.addAttribute("title", "마이페이지");
        HttpSession session = req.getSession();
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("teamName", session.getAttribute("teamName"));
        model.addAttribute("rankName", session.getAttribute("rankName"));
        return "user/myPage.html";
    }
}
