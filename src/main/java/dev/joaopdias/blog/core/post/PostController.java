package dev.joaopdias.blog.core.post;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.joaopdias.blog.core.post.dto.CreatePostDto;
import dev.joaopdias.blog.core.post.entities.Post;
import dev.joaopdias.blog.shared.interfaces.Message;

@RestController()
@RequestMapping("/post")
@Validated()
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping()
    public Message create(@RequestBody CreatePostDto createPostDto) {
        return this.postService.create(createPostDto);
    }

    @GetMapping("{id}")
    public Post findById(@PathVariable("id") UUID id){
        return this.postService.findById(id);
    }

    @GetMapping("findByOrder/{page}")
    public List<Post> findByOrder(@PathVariable("page") int page) {
        return this.postService.findByOrder(page < 0 ? 0 : page);
    }

    @GetMapping("findByTitle/{title}/{page}")
    public List<Post> findByTitle(@PathVariable("title") String title, @PathVariable("page") int page) {
        return this.postService.findByTitle(title, page < 0 ? 0 : page);
    }

    @GetMapping("findByUserEmailOrUserName/{searchTerm}/{page}")
    public List<Post> findByUserEmailOrUserName(@PathVariable("searchTerm") String searchTerm,
            @PathVariable("page") int page) {
        return this.postService.findByUserEmailOrUserName(searchTerm, page < 0 ? 0 : page);
    }

    @GetMapping("findByUserEmail/{email}/{page}")
    public List<Post> findByUserEmail(@PathVariable("email") String email,
            @PathVariable("page") int page) {
        return this.postService.findByUserEmail(email, page < 0 ? 0 : page);
    }

    @DeleteMapping("{id}")
    public Message delete(@PathVariable("id") UUID id) {
        return this.postService.delete(id);
    }
}
