package kr.ac.deu.computer_engineering.Absenteeism.Management.route.account;

import kr.ac.deu.computer_engineering.Absenteeism.Management.utils.RoleValidate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/account")
public class AccountViewController {
    @RequestMapping("/list")
    public String listView(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("teamName", session.getAttribute("teamName"));
        model.addAttribute("rankName", session.getAttribute("rankName"));
        model.addAttribute("isRoleManager", RoleValidate.isRoleManager(session));
        model.addAttribute("isRoleCeo", RoleValidate.isRoleCeo(session));
        model.addAttribute("isRoleStaff", RoleValidate.isRoleStaff(session));
        return "account/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("name", session.getAttribute("name"));
        model.addAttribute("teamName", session.getAttribute("teamName"));
        model.addAttribute("rankName", session.getAttribute("rankName"));
        model.addAttribute("isRoleManager", RoleValidate.isRoleManager(session));
        model.addAttribute("isRoleCeo", RoleValidate.isRoleCeo(session));
        model.addAttribute("isRoleStaff", RoleValidate.isRoleStaff(session));
        return "account/item.html";
    }
}
