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
        Properties properties = new Properties();
        InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream("dbconfig.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("读取配置文件错误");
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
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
