package com.rith.testotp.Repository;

import com.rith.testotp.Model.UserOtp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface OtpMapper {

    @Insert("INSERT INTO user_otps(email, otp_code, created_at, expires_at, is_verified) " +
            "VALUES(#{email}, #{otpCode}, #{createdAt}, #{expiresAt}, #{isVerified})")
    void insertOtp(UserOtp userOtp);

    @Select("SELECT * FROM user_otps WHERE email = #{email} AND otp_code = #{otpCode} AND is_verified = FALSE ORDER BY created_at DESC LIMIT 1")
    UserOtp findLatestValidOtp(@Param("email") String email, @Param("otpCode") String otpCode);

    @Update("UPDATE user_otps SET is_verified = TRUE WHERE id = #{id}")
    void markAsVerified(@Param("id") Long id);
}

