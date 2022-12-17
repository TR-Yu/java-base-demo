package tech.tryu.jdbc;

import tech.tryu.jdbc.jdbcinterface.CallableStatementTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * ${DESCRIPTION}
 *
 * @author ${USER}
 * @date ${YEAR}-${MONTH}-${DAY} ${TIME}
 * @since 1.0.0
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Main main = new Main();
       /* main.connectionLeakTestOnSingleThread();*/
      /*  main.connectionLeakTestMutileThread();*/

       /* PreParedStatementTest statement = new PreParedStatementTest();*/
        /*try {
            statement.preParedStatementTest();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
       /* try {
            statement.preParedStatementStrSqlTest();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        CallableStatementTest statementTest = new CallableStatementTest();

        try {
            statementTest.callStatementTest();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


    }

    /**
     * 多线程链接未关闭测试， 会报"Too many connections"错误
     * max_connections 默认 151；
     *
     Command-Line Format	--max-connections=#
     System Variable	max_connections
     Scope	Global
     Dynamic	Yes
     Type	Integer
     Default Value	151
     Minimum Value	1
     Maximum Value	100000
     */
    private void connectionLeakTestMutileThread() {

        Runnable runnable = this::connectionLeakTestOnSingleThread;
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(runnable);
            thread.setName("thread - " + i);
            thread.start();
        }

        /*for (;;);*/
    }

    /**
     * 只有一个线程，则只有一个客户端，不会报错
     */
    private void connectionLeakTestOnSingleThread() {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            log.info(String.format("已获取connection:%s", connection.toString()));

            for (int i = 0; i < 1; i++) {
                try {
                    log.info(String.valueOf(i));
                    PreparedStatement preparedStatement = connection.prepareStatement("select * from sys_role");
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet != null) {
                        while ( resultSet.next()){
                            log.info(String.valueOf(resultSet.getLong(1)));
                            log.info((String) resultSet.getObject(2));
                        }
                    }
                    assert resultSet != null;
                    resultSet.close();
                    preparedStatement.close();
                    TimeUnit.HOURS.sleep(1);
                } catch (SQLException e) {
                    log.info(String.format("获取 %s",e.toString()));
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.info(e.toString());
                    throw new RuntimeException(e);
                }
            }
        }



    }
}