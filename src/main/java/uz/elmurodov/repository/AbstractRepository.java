package uz.elmurodov.repository;

import uz.elmurodov.config.PConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Elmurodov Javohir, Sat 10:00 AM. 12/18/2021
 */
public abstract class AbstractRepository {
    private static Connection connection;
    protected StringBuilder query = new StringBuilder();
    PreparedStatement preparedStatement = null;

    protected void executeWithout() {
        if (preparedStatement != null) {
            try {
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    protected PreparedStatement getPreparedStatement(Object... args) {
        try {
            preparedStatement = getConnection().prepareStatement(query.toString());
            prepare(preparedStatement, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    private void prepare(PreparedStatement preparedStatement, Object... args) {
        for (int i = 1; i <= args.length; i++) {
            try {
                preparedStatement.setObject(i, args[i - 1]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(PConfig.get("jdbc.driver"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
