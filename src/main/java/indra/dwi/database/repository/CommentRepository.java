package indra.dwi.database.repository;

import indra.dwi.database.entity.Comment;
import java.util.List;

public interface CommentRepository {

    void insertComment(Comment comment);

    Comment findCommentById(Integer id);

    List<Comment> findAll();

    List<Comment> findAllByEmail(String email);

}
