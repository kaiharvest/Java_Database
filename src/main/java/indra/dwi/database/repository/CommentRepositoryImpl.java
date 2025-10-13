package indra.dwi.database.repository;

import indra.dwi.database.ConnectionUtil;
import indra.dwi.database.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {

    @Override
    public void insertComment(Comment comment) {
        String sql = "INSERT INTO comments (email, comment) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, comment.getEmail());
            statement.setString(2, comment.getComment());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comment.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Comment findCommentById(Integer id) {
        String sql = "SELECT * FROM comments WHERE id = ?";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Comment(
                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("comment")
                    );
                }
                return null;
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Comment> findAll() {
        String sql = "SELECT * FROM comments";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            List<Comment> comments = new ArrayList<>();
            while (resultSet.next()) {
                comments.add(new Comment(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("comment")
                ));
            }
            return comments;

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Comment> findAllByEmail(String email) {
        String sql = "SELECT * FROM comments WHERE email = ?";
        try (Connection connection = ConnectionUtil.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Comment> comments = new ArrayList<>();
                while (resultSet.next()) {
                    comments.add(new Comment(
                            resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("comment")
                    ));
                }
                return comments;
            }

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
