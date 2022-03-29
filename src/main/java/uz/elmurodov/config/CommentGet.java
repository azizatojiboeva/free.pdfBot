package uz.elmurodov.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uz.elmurodov.entity.Book;
import uz.elmurodov.entity.Comment;
import uz.elmurodov.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Narzullayev Husan, вс 14:21. 02.01.2022
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentGet {

    private static final CommentGet  instance=new CommentGet();
    private static Connection connection;
    private  Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(PConfig.get("jdbc.driver"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public Map<String,String> getAllComment(){
        getConnection();
        Map<String ,String >state=new HashMap<>();
        try(Statement statement=connection.createStatement()) {
            String sql="select * from comment";

            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                state.put(resultSet.getString("chatId"),resultSet.getString("state"));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return state;
    }
    public static  CommentGet getInstance(){
        return instance;
    }
    public   Map<String, Comment> getAll(){
        getConnection();
        Map<String , Comment>state=new HashMap<>();
        try(Statement statement=connection.createStatement()) {
            String sql="select * from comment";

            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                Comment comment=new Comment();
                comment.setFullName(resultSet.getString("fullName"));
                comment.setChatId(resultSet.getString("chatId"));
                comment.setUserName(resultSet.getString("userName"));
                comment.setComment(resultSet.getString("comment"));

                state.put(resultSet.getString("chatId"),comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return state;
    }

//
//    public List<Book> getComment(String chatId) {
//        List<Book> books=new ArrayList<>();
//        try(Statement statement=connection.createStatement()) {
//            String sql = "select * from books where chatId==%s".formatted(chatId);
//            ResultSet resultSet=statement.executeQuery(sql);
//            while(resultSet.next()){
//                Book book=new Book();
//                book.setAuthor(resultSet.getString("author"));
//                book.setCategory(resultSet.getString("category"));
//                book.setId(resultSet.getString("bookId"));
//                book.setDescription(resultSet.getString("description"));
//                books.add(book);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return books;
//    }
//    public static void updates(String chatId,String data){
//        try(Statement statement=connection.createStatement()){
//            String sql="update user set language = '%s' where chatId = '%s'".formatted(data,chatId);
//            statement.execute(sql);
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

}
