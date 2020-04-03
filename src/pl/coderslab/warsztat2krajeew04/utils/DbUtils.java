package pl.coderslab.warsztat2krajeew04.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private static String DB_NAME = "warsztat2krajeew04";
    private static String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?encoding=utf8&serverTimezone=UTC";
    private static String DB_USER = "root";
    private static String DB_PASS = "Krystian15122002";

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        return con;
    }
}