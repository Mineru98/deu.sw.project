package kr.ac.deu.computer_engineering.Absenteeism.Management.route.allowance;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/allowance")
public class AllowanceViewController {
    @RequestMapping("/list")
    public String listView(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("teamName", session.getAttribute("teamName"));
        model.addAttribute("rankName", session.getAttribute("rankName"));
        return "allowance/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("teamName", session.getAttribute("teamName"));
        model.addAttribute("rankName", session.getAttribute("rankName"));
        return "allowance/item.html";
    }
}
