package Application;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -1892561327013038124L;
    private long id;
    private String userName;
    private String userPasswd;

    public User(long id, String userName, String userPasswd) {
        this.id = id;
        this.userName = userName;
        this.userPasswd = userPasswd;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        return getUserName() != null ? getUserName().equals(user.getUserName()) : user.getUserName() == null;
    }

}
