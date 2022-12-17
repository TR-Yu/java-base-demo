package tech.tryu.thread.threadlocal;

/**
 * @author YU
 * @date 2022-10-28 14:12
 * @since 1.0.0
 */
public class Context {
    private String userName;

    public Context(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Context{" +
                "userName='" + userName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
