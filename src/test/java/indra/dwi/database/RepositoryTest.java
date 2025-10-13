package indra.dwi.database;

import indra.dwi.database.entity.Comment;
import indra.dwi.database.repository.CommentRepository;
import indra.dwi.database.repository.CommentRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositoryTest {

    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("indra@test.com", "hi");
        commentRepository.insertComment(comment);

        Assertions.assertNotNull(comment.getId());
        System.out.println(comment.getId());
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findCommentById(8704);
        Assertions.assertNotNull(comment);

        System.out.println(comment.getId());
        System.out.println(comment.getEmail());
        System.out.println(comment.getComment());

        Comment foundComment = commentRepository.findCommentById(8704);
        Assertions.assertNotNull(foundComment);
    }

    @Test
    void testFindAll() {
        List<Comment> comments = commentRepository.findAll();
        System.out.println(comments.size());
    }

    @Test
    void testFindAllByEmail() {
        List<Comment> comments = commentRepository.findAllByEmail("hi");
        System.out.println(comments.size());
    }

}
