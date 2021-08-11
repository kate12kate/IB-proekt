package mk.ukim.finki.ibproekt.controller;

import mk.ukim.finki.ibproekt.service.DAOService;
import mk.ukim.finki.ibproekt.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.Random;

@Controller
public class TwoFactorServiceController {

    @Autowired
    EmailService emailService;
    @Autowired
    DAOService daoService;

    @RequestMapping(value="/users/{userid}/emails/{emailid}/2fa", method=RequestMethod.PUT)
    public ResponseEntity<Object> send2faCodeinEmail(@PathVariable("userid") String id, @PathVariable("emailid") String emailid) throws AddressException, MessagingException {
        String twoFaCode = String.valueOf(new Random().nextInt(9999) + 1000);
        emailService.sendEmail(emailid, twoFaCode);
        daoService.update2FAProperties(id, twoFaCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/users/{userid}/codes/{2facode}", method=RequestMethod.PUT)
    public ResponseEntity<Object> verify(@PathVariable("userid") String id, @PathVariable("2facode") String code) {

        boolean isValid = daoService.checkCode(id, code);

        if(isValid)
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}

