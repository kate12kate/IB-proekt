package mk.ukim.finki.ibproekt.controller;

import mk.ukim.finki.ibproekt.model.Voter;
import mk.ukim.finki.ibproekt.model.exceptions.VoterNotFound;
import mk.ukim.finki.ibproekt.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/", "/login"})
public class LoginController {
    @Autowired
    public VoterService voterService;

    @GetMapping
    public String login(){
        return "login";
    }

    @PostMapping
    public String loginSuccess(HttpServletRequest request,
                               @RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String ssn)

    {
        Voter voter=null;
        try{
             voter=voterService.login(username,password,ssn);
            request.getSession().setAttribute("voter",voter);
            return "redirect:/home";
        }
         catch (VoterNotFound voterNotFound){
            return "login";
         }
    }

}
