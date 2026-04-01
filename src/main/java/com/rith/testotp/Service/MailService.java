package com.rith.testotp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendOtp(String to) {

        String otp = String.format("%06d", new Random().nextInt(999999));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Your OTP Code");

            String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; text-align: center; border: 1px solid #ddd; max-width: 400px; margin: auto;'>"
                    + "<h2 style='color: #333;'>Your OTP Code</h2>"
                    + "<p style='color: #555;'>Please use the following One Time Password (OTP) to proceed. Do not share this code with anyone.</p>"
                    + "<h1 style='color: #4CAF50; font-size: 36px; padding: 10px; border: 1px dashed #4CAF50; display: inline-block;'>" + otp + "</h1>"
                    + "<p style='color: #999; font-size: 12px;'>If you didn't request this, you can safely ignore this email.</p>"
                    + "</div>";

            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        }

        return otp;
    }
}
