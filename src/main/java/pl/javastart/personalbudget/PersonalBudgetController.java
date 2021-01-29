package pl.javastart.personalbudget;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class PersonalBudgetController {
    private static final ReceiptDao DAO = new ReceiptDao();

    public PersonalBudgetController() {
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("dane_z_sql", DAO.findAll().get());
        return "home";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam(required = false, name = "id") Integer id){
        DAO.deleteById(id);
        return "/test";
    }

    @GetMapping("/add")
    public String add(){
        DAO.add(new Receipt("wydatek", "Wódka", BigDecimal.valueOf(55.12), "2021-01-29"));
        return "/test";
    }

    @GetMapping("/update")
    public String update(){
        DAO.update(new Receipt(3, "wydatek", "Wódka", BigDecimal.valueOf(55.12), "2021-01-29"));
        return "/test";
    }
}