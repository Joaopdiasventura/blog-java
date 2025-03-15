package dev.joaopdias.blog.shared.interfaces;

import dev.joaopdias.blog.core.user.entities.User;
import lombok.Getter;

@Getter
public class AuthMessage extends Message {
    private User user;
    private String token;

    public AuthMessage(String message, User user, String token) {
        super(message);
        this.user = user;
        this.token = token;
    }    
}
