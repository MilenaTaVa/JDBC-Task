package jm.task.core.jdbc.util;

import jdk.jshell.spi.SPIResolutionException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final Util INSTANCE = new Util();

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Postgres";
    public static final String DRIVER = "org.postgresql.Driver";

    public static Connection connection;

    private Util() {
    }

    public static Util getInstance() throws SQLException {
        return INSTANCE;
    }


    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } return connection;
    }
}
