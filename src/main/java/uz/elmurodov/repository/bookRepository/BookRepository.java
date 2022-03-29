package uz.elmurodov.repository.bookRepository;

import uz.elmurodov.entity.Book;
import uz.elmurodov.repository.AbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository extends AbstractRepository {

    public static void save(Book book, String chatID) {
        String  sql="insert into books (id, bookName, description, Author, category,ownerId) values(?,?,?,?,?,?)";
        try (Connection connection= getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(sql)){
           preparedStatement.setString(1,book.getId());
           preparedStatement.setString(2,book.getBookName());
           preparedStatement.setString(3,book.getDescription());
           preparedStatement.setString(4,book.getAuthor());
           preparedStatement.setString(5,book.getCategory());
           preparedStatement.setString(6,book.getOwnerId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Book> get(String message,Integer n) {
        List<Book>books=new ArrayList<>();
        String sql="select * from books  where books.ownerId like ?  or category like ? or bookName like  ? or author like ? limit 10 offset ? ";
        try (Connection connection= getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(sql)){

            preparedStatement.setString(1,message);
            preparedStatement.setString(2,"%"+message+"%");
            preparedStatement.setString(3,"%"+message+"%");
            preparedStatement.setString(4,"%"+message+"%");
            preparedStatement.setInt(5,n);

            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                Book book=new Book();
                book.setAuthor(resultSet.getString("author"));
                book.setCategory(resultSet.getString("category"));
                book.setId(resultSet.getString("bookId"));
                book.setDescription(resultSet.getString("description"));
                book.setBookName(resultSet.getString("bookName"));
                books.add(book);
            }
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
        return books;
    }

    public static List<Book> getMY(String message,Integer n) {
        List<Book>books=new ArrayList<>();
        String sql="select * from books  where books.ownerId like ?  limit 5 offset ? ";
        try (Connection connection= getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(sql)){

            preparedStatement.setString(1,message);
            preparedStatement.setInt(2,n);
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                Book book=new Book();
                book.setAuthor(resultSet.getString("author"));
                book.setCategory(resultSet.getString("category"));
                book.setId(resultSet.getString("bookId"));
                book.setDescription(resultSet.getString("description"));
                book.setBookName(resultSet.getString("bookName"));
                books.add(book);
            }
        } catch (SQLException throwAbles) {
            throwAbles.printStackTrace();
        }
        return books;
    }



}
