package com.rith.testotp.Model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserOtp {
    private Long id;
    private String email;
    private String otpCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean isVerified;
}

