package kr.ac.deu.computer_engineering.Absenteeism.Management.route.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/account")
public class AccountViewController {
    @RequestMapping("/list")
    public String listView(Model model) {
        return "account/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model) {
        return "account/item.html";
    }
}
