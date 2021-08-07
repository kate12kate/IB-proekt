package mk.ukim.finki.ibproekt.controller;

import mk.ukim.finki.ibproekt.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("candidates",candidateService.findAll());

        return "home";
    }








}
