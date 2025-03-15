package dev.joaopdias.blog.core.post;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.joaopdias.blog.core.post.dto.CreatePostDto;
import dev.joaopdias.blog.core.post.entities.Post;
import dev.joaopdias.blog.core.user.UserService;
import dev.joaopdias.blog.shared.interfaces.Message;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    public Message create(CreatePostDto createPostDto) {
        Post post = createPostDto.toEntity(userService);
        postRepository.save(post);
        return new Message("Post criado com sucesso");
    }

    public List<Post> findByOrder(int page) {
        return postRepository.findByOrder(PageRequest.of(page, 10)).getContent();
    }

    public List<Post> findByTitle(String title, int page) {
        return postRepository.findByTitle(title, PageRequest.of(page, 10)).getContent();
    }

    public List<Post> findByUserEmailOrUserName(String searchTerm, int page) {
        return postRepository.findByUserEmailOrUserName(searchTerm, PageRequest.of(page, 10)).getContent();
    }

    public List<Post> findByUserEmail(String email, int page) {
        return postRepository.findByUserEmail(email, PageRequest.of(page, 10)).getContent();
    }

    public Message delete(UUID id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return new Message("Post deletado com sucesso");
    }
}
