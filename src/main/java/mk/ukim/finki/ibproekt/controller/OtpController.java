package mk.ukim.finki.ibproekt.controller;

import mk.ukim.finki.ibproekt.model.Voter;
import mk.ukim.finki.ibproekt.service.EmailService;
import mk.ukim.finki.ibproekt.service.OTPService;
import mk.ukim.finki.ibproekt.service.VoterService;
import mk.ukim.finki.ibproekt.utility.EmailTemplate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller

public class OtpController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OTPService otpService;

    @Autowired
    public EmailService emailService;
    @Autowired
    public VoterService voterService;

    @GetMapping("/generateOtp")
    public String generateOtp(HttpServletRequest request, Authentication authentication) {

        Voter voter= (Voter) authentication.getPrincipal();
        int otp = otpService.generateOTP(voter.getUsername());

        logger.info("OTP : " + otp);

        //Generate The Template to send OTP
        EmailTemplate template = new EmailTemplate("C:/Users/kater/Documents/GitHub/ib-proekt/email-template/SendCode.txt");

        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("user", voter.getUsername());
        replacements.put("otpnum", String.valueOf(otp));

        String message = template.getTemplate(replacements);


        emailService.sendOtpMessage(voter.getEmail(), "OTP -SpringBoot", message);

        return "otppage";
    }

    @PostMapping(value = "/validateOtp")
    public String validateOtp(@RequestParam("otp") int otp, HttpServletRequest request, Authentication authentication, Model model) {

        Voter voter= (Voter) authentication.getPrincipal();

        logger.info(" Otp Number : " + otp);

        //Validate the Otp
        if (otp >= 0) {
            int serverOtp = otpService.getOtp(voter.getUsername());

            if (serverOtp > 0) {
                if (otp == serverOtp) {
                    otpService.clearOtp(voter.getUsername());
                    return "redirect:/home";

                }
            }
        }
        return "redirect:/login";
    }
}



