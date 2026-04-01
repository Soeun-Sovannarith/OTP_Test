package com.rith.testotp.Controller;

import com.rith.testotp.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping({"/", "/send-otp"})
    public String showForm() {
        return "otp-form";
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam("email") String email, Model model) {
        try {
            mailService.sendOtp(email);
            model.addAttribute("message", "Success! A verification code has been sent to " + email);
            model.addAttribute("isError", false);
        } catch (Exception e) {
            model.addAttribute("message", "Error: " + e.getMessage());
            model.addAttribute("isError", true);
        }
        return "otp-form";
    }
}
