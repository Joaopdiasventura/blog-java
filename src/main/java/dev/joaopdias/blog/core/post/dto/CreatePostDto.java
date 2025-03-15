package dev.joaopdias.blog.core.post.dto;

import dev.joaopdias.blog.core.post.entities.Post;
import dev.joaopdias.blog.core.user.UserService;
import lombok.Getter;

@Getter
public class CreatePostDto {

    private String title;
    private String content;
    private String userEmail;

    public Post toEntity(UserService userService) {
        return new Post(this.title, this.content, userService.findByEmail(this.userEmail));
    }
}
