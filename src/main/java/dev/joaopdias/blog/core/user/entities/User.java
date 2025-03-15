package dev.joaopdias.blog.core.user.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private String email;
    private String name;

    @JsonIgnore
    private String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
