package com.hq.news.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.kymjs.kjframe.database.annotate.Id;

/**
 * User bean.
 * @author hq
 * @date 20/11/2015
 * @since 1.0
 */
@XStreamAlias("user")
public class User {

    @Id
    private int userId;
    private String email;
    private String password;
    private String username;
    private String createDate;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
