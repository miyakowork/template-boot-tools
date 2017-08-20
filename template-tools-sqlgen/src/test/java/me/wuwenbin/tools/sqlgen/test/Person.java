package me.wuwenbin.tools.sqlgen.test;

import me.wuwenbin.tools.sqlgen.annotation.SQLColumn;

/**
 * created by Wuwenbin on 2017/8/16 at 14:10
 */
public class Person {
    private String age;
    @SQLColumn
    private String address;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
