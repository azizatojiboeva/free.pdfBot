package uz.elmurodov.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import uz.elmurodov.entity.Book;
import uz.elmurodov.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;



@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetAll {

    private static final GetAll  instance=new GetAll();
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
    public   Map<String,String> getAllState(){
        getConnection();
        Map<String ,String >state=new HashMap<>();
        try(Statement statement=connection.createStatement()) {
            String sql="select * from users";

            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                state.put(resultSet.getString("chatId"),resultSet.getString("state"));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return state;
    }
    public static  GetAll getInstance(){
        return instance;
    }

    public   Map<String,User> getAll(){
        getConnection();
        Map<String , User>state=new HashMap<>();
        try(Statement statement=connection.createStatement()) {
            String sql="select * from users";

            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){

                User user=new User();
                        user.setUserName(resultSet.getString("userName"));
                        user.setPhoneNumber(resultSet.getString("phoneNumber"));
                        user.setAge(Integer.parseInt((resultSet.getString("age"))));
                        user.setFullName(resultSet.getString("fullName"));
                        user.setGender(resultSet.getString("gender"));
                        user.setRole(resultSet.getString("role"));
                        user.setLanguage(resultSet.getString("language"));
                state.put(resultSet.getString("chatId"),user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return state;
    }


    public List<Book> getBook(String chatId) {
        List<Book> books=new ArrayList<>();
        try(Statement statement=connection.createStatement()) {
            String sql = "select * from books where chatId==%s".formatted(chatId);
            ResultSet resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                Book book=new Book();
                book.setAuthor(resultSet.getString("author"));
                book.setCategory(resultSet.getString("category"));
                book.setId(resultSet.getString("bookId"));
                book.setDescription(resultSet.getString("description"));
                books.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return books;
    }
    public static void updates(String chatId,String data){
        try(Statement statement=connection.createStatement()){
            String sql="update users set language = '%s' where chatId = '%s'".formatted(data,chatId);
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
