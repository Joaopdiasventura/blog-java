package dev.joaopdias.blog.core.post;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import dev.joaopdias.blog.core.post.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    Page<Post> findByOrder(Pageable pageable);

    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) ORDER BY p.title ASC")
    Page<Post> findByTitle(@Param("title") String title, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE LOWER(p.user.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.user.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  ORDER BY p.title ASC")
    Page<Post> findByUserEmailOrUserName(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.user.email = :email ORDER BY p.title ASC")
    Page<Post> findByUserEmail(@Param("email") String email, Pageable pageable);
}
