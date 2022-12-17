package tech.tryu.jdbc.jdbcinterface;

import tech.tryu.jdbc.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author YU
 * @date 2022-11-11 19:19
 * @since 1.0.0
 */
public class PreParedStatementTest {

    /**
     * 1. 获取到 preParedStatement 类型
     * 执行方法：
     * execute();
     * executeUpdate();
     * executeQuery();
     * getResultSet(); getUpdateCount();
     * 占位符$语句是使用 sql 用 ？； setByte(), setString(), setInt() ....
     * 2. 批量运行
     * preParedStatement.addBatch(); prePareStatement.executeBatch();
     */
    private final static String  QUERY_SQL = "select * from sys_role sr ";
    private final static String  INSERT_SQL =
            "insert into sys_role " +
                    "(role_name,enabled,create_by,create_time)" +
                    "values" +
                    "(?,?,?,?)";
    private final static String UPDATE_SQL = "update sys_role set enabled = 1 where role_name = ?";
    private final static String DELETE_SQL = "delete from sys_role where role_name = ?";

    private final static String COUNT_SQL = "select count(*) count from sys_role";

    private static final Logger log = LoggerFactory.getLogger(PreParedStatementTest.class);

    public void preParedStatementTest () throws SQLException {

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SQL);
        boolean executeStatus = preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
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
        preparedStatement.close();
        connection.close();

    }

    public void preParedStatementStrSqlTest () throws SQLException {

        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
        preparedStatement.setString(1,"营销员");
        preparedStatement.setInt(2,0);
        preparedStatement.setLong(3,1L);
        preparedStatement.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
        int affectRow = preparedStatement.executeUpdate();
        log.info(String.format("已插入row: %d", affectRow));
        preparedStatement.close();

        preparedStatement = connection.prepareStatement(UPDATE_SQL);
        preparedStatement.setString(1,"营销员");
        int updateRow = preparedStatement.executeUpdate();
        log.info(String.format("已更新row: %d",updateRow));
        preparedStatement.close();

        preparedStatement = connection.prepareStatement(DELETE_SQL);
        preparedStatement.setString(1,"营销员");
        int deleteRow = preparedStatement.executeUpdate();
        log.info(String.format("已删除row: %d",deleteRow));
        preparedStatement.close();

        connection.close();
    }

    public void preParedStatementResultTest() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SQL);
        boolean execute = preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        ResultSetMetaData metaData = preparedStatement.getMetaData();
        if (execute && resultSet != null && metaData != null){
            while(resultSet.next()) {
                Object object = resultSet.getObject(1);
                String columnName = metaData.getColumnName(1);
                String clazzName = metaData.getColumnClassName(1);
                log.info(String.format(
                        "object: %s, columName: %s, className: %s",
                        object,columnName,clazzName));
            }
        }
        connection.close();

    }
}