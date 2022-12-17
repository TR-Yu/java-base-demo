package tech.tryu.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 获取链接工厂方法
 *
 * @author YU
 * @date 2022-11-11 19:03
 * @since 1.0.0
 */
public class ConnectionFactory {
    private static final Logger log = LoggerFactory.getLogger(ConnectionFactory.class);

    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://172.24.36.229:3306/SqlTest?serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String userName = "mybatics";
        String password = "yren1994";
        try {
            connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }
    }
}
