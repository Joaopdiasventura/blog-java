package dev.joaopdias.blog.core.user.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserDto extends CreateUserDto {

    public void setEmail(String email) {
        this.email = email;
    }

}
