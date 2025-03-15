package dev.joaopdias.blog.core.user.dtos;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Getter;

@Getter
public class LoginUserDto {
    private String email;
    private String password;

    public boolean verifyPassword(String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(this.password, hashedPassword);
    }
}
