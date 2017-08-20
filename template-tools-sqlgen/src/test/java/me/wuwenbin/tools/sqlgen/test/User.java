package me.wuwenbin.tools.sqlgen.test;


import me.wuwenbin.tools.sqlgen.annotation.SQLColumn;
import me.wuwenbin.tools.sqlgen.annotation.SQLTable;
import me.wuwenbin.tools.sqlgen.constant.Router;

/**
 * Created by wuwenbin on 2017/7/8.
 */
@SQLTable
public class User extends Person {

    @SQLColumn(pk = true)
    private Integer id;

    @SQLColumn(routers = 100)
    private String username;

    @SQLColumn(routers = Router.B)
    private String password;

    @SQLColumn(routers = Router.C)
    private String cnName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
