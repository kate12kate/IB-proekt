package mk.ukim.finki.ibproekt.controller;

import mk.ukim.finki.ibproekt.model.Voter;
import mk.ukim.finki.ibproekt.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public String getHomePage(Model model, Authentication authentication){
        model.addAttribute("candidates",candidateService.findAll());
        model.addAttribute("voter",(Voter)authentication.getPrincipal());
        return "home";
    }








}
