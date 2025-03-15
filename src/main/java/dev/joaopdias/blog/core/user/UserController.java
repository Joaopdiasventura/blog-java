package dev.joaopdias.blog.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.joaopdias.blog.core.user.dtos.CreateUserDto;
import dev.joaopdias.blog.core.user.dtos.LoginUserDto;
import dev.joaopdias.blog.core.user.dtos.UpdateUserDto;
import dev.joaopdias.blog.core.user.entities.User;
import dev.joaopdias.blog.shared.interfaces.AuthMessage;
import dev.joaopdias.blog.shared.interfaces.Message;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController()
@RequestMapping("/user")
@Validated()
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public AuthMessage create(@RequestBody CreateUserDto createUserDto) {
        return this.userService.create(createUserDto);
    }

    @PostMapping("login")
    public AuthMessage login(@RequestBody LoginUserDto loginUserDto) {
        return this.userService.login(loginUserDto);
    }

    @GetMapping("{jwt}")
    public User decodeJwt(@PathVariable("jwt") String jwt) {
        return this.userService.decodeJwt(jwt);
    }

    @PatchMapping("{email}")
    public Message update(@PathVariable("email") String email, @RequestBody UpdateUserDto updateUserDto) {
        return this.userService.update(email, updateUserDto);
    }

    @DeleteMapping("{email}")
    public Message delete(@PathVariable("email") String email) {
        return this.userService.delete(email);
    }
}
