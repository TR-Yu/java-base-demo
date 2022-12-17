package tech.tryu.jdbc.jdbcinterface;

import tech.tryu.jdbc.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author YU
 * @date 2022-11-11 19:21
 * @since 1.0.0
 */
public class CallableStatementTest {
    private static final Logger log = LoggerFactory.getLogger(PreParedStatementTest.class);

    private final static String CALL_SQL = "{call viewRole()}";

    public void callStatementTest() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        CallableStatement callableStatement = connection.prepareCall(CALL_SQL);
        boolean executeStatus = callableStatement.execute();

        ResultSet resultSet = callableStatement.getResultSet();
        if (executeStatus && resultSet != null) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            if (metaData != null) {
                int count = metaData.getColumnCount();
                while(resultSet.next() && count > 0) {
                    for (int i = 1; i < count + 1; i++) {
                        Object object = resultSet.getObject(i);
                        String columnName = metaData.getColumnName(i);
                        String clazzName = metaData.getColumnClassName(i);
                        log.info(String.format(
                                "object: %s, columName: %s, className: %s",
                                object,columnName,clazzName));
                    }
                }
            }
        }
        resultSet.close();
        callableStatement.close();
        connection.close();
    }
}
