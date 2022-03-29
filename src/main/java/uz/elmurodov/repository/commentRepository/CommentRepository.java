package uz.elmurodov.repository.commentRepository;

import uz.elmurodov.entity.Book;
import uz.elmurodov.entity.Comment;
import uz.elmurodov.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Narzullayev Husan, вс 14:31. 02.01.2022
 */
public class CommentRepository extends AbstractRepository {
    private static final CommentRepository instance = new CommentRepository();

    public static CommentRepository getInstance() {
        return instance;
    }

    public static void save(Comment comment) {
        String sql = "insert into comment (id,chatId,fullName,userName,comment) values(?,?,?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(2, comment.getChatId());
            preparedStatement.setString(3, comment.getFullName());
            preparedStatement.setString(4, comment.getUserName());
            preparedStatement.setString(5, comment.getComment());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Comment> get(String chatId, Integer n) {
        List<Comment> comments = new ArrayList<>();
        String sql = "select * from comment limit 5 offset ? ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, n);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setChatId(resultSet.getString("chatId"));
                comment.setFullName(resultSet.getString("fullName"));
                comment.setUserName(resultSet.getString("userName"));
                comment.setComment(resultSet.getString("comment"));
                comments.add(comment);
            }
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
        return comments;
    }
}
