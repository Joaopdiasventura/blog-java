package dev.joaopdias.blog.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.joaopdias.blog.core.user.dtos.CreateUserDto;
import dev.joaopdias.blog.core.user.dtos.LoginUserDto;
import dev.joaopdias.blog.core.user.dtos.UpdateUserDto;
import dev.joaopdias.blog.core.user.entities.User;
import dev.joaopdias.blog.shared.interfaces.AuthMessage;
import dev.joaopdias.blog.shared.interfaces.Message;
import dev.joaopdias.blog.shared.security.JwtService;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public AuthMessage create(CreateUserDto createUserDto) {
        throwIfEmailIsUsed(createUserDto.getEmail());
        User user = userRepository.save(createUserDto.toEntity());
        String token = jwtService.generateJwt(user.getEmail());
        return new AuthMessage("Usuário criado com sucesso", user, token);
    }

    public AuthMessage login(LoginUserDto loginUserDto) {
        User user = findByEmail(loginUserDto.getEmail());
        if (!loginUserDto.verifyPassword(user.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta");
        String token = jwtService.generateJwt(user.getEmail());
        return new AuthMessage("Usuário logado com sucesso", user, token);
    }

    public User decodeJwt(String jwt) {
        String email = jwtService.decodeJwt(jwt);
        User user = findByEmail(email);
        return user;
    }

    public Message update(String email, UpdateUserDto updateUserDto) {
        findByEmail(email);
        updateUserDto.setEmail(email);
        User user = updateUserDto.toEntity();
        userRepository.save(user);
        return new Message("Usuário atualizado com sucesso");
    }

    public Message delete(String email) {
        User user = findByEmail(email);
        userRepository.delete(user);
        return new Message("Usuário deletado com sucesso");
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
        return user;
    }

    private void throwIfEmailIsUsed(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse email já está sendo utilizado");
    }
}
