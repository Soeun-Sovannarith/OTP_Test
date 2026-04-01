package com.rith.testotp.Controller;

import com.rith.testotp.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otp")
public class MailApiController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        String sentOtp = mailService.sendOtp(email);
        if (sentOtp != null) {
            return ResponseEntity.ok("OTP sent to " + email);
        } else {
            return ResponseEntity.status(500).body("Failed to send OTP to " + email);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otpCode) {
        try {
            mailService.verifyOtp(email, otpCode);
            return ResponseEntity.ok("OTP Verified Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Verification Failed: " + e.getMessage());
        }
    }
}
