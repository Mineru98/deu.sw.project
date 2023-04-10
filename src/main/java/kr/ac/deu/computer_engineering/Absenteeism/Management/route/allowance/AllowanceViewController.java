package kr.ac.deu.computer_engineering.Absenteeism.Management.route.allowance;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/allowance")
public class AllowanceViewController {
    @RequestMapping("/list")
    public String listView(Model model) {
        return "allowance/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model) {
        return "allowance/item.html";
    }
}
