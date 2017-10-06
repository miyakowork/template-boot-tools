package me.wuwenbin.tools.sqlgen.test;


import me.wuwenbin.tools.sqlgen.annotation.SQLColumn;
import me.wuwenbin.tools.sqlgen.annotation.SQLTable;
import me.wuwenbin.tools.sqlgen.annotation.not.NotSelect;
import me.wuwenbin.tools.sqlgen.constant.Router;

/**
 * Created by wuwenbin on 2017/7/8.
 */
@SQLTable
public class User extends Person {

    @SQLColumn(pk = true)
    private Integer id;

    @SQLColumn(routers = Router.B, update = false)
    private String username;

    @SQLColumn(routers = Router.B)
    @NotSelect
    private String password;

    @SQLColumn(routers = Router.C)
    private String cnName;

    private String email;

    private String deptName;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
