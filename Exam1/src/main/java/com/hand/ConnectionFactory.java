package com.hand;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static String driver;

    private static String url;

    private static String user;

    private static String password;

    private static final ConnectionFactory instance = new ConnectionFactory();

    public static ConnectionFactory getInstance() {
        return instance;
    }

    static {

        driver = System.getenv("DRIVER");
        url = System.getenv("URL");
        user = System.getenv("USER");
        password = System.getenv("PASSWORD");
    }

    private ConnectionFactory() {
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            // 加载驱动
            Class.forName(driver);
            // 打开连接
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
