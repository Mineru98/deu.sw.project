package kr.ac.deu.computer_engineering.Absenteeism.Management.route.company;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/company")
public class CompanyViewController {
    @RequestMapping("/list")
    public String listView(Model model) {
        return "company/list.html";
    }

    @RequestMapping("/detailView")
    public String detailView(Model model) {
        return "company/item.html";
    }
}
