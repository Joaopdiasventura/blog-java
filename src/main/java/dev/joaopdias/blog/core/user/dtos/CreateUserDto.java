package dev.joaopdias.blog.core.user.dtos;

import dev.joaopdias.blog.core.user.entities.User;
import lombok.Getter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class CreateUserDto {
    protected String email;
    protected String name;
    protected String password;

    protected void hashPassword() {
        if (this.password == null)
            return;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(this.password);
    }

    public User toEntity() {
        this.hashPassword();
        return new User(this.email, this.name, this.password);
    }
}
