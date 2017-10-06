package me.wuwenbin.tools.sqlgen.test;

import me.wuwenbin.tools.sqlgen.annotation.SQLTable;

/**
 * created by Wuwenbin on 2017/10/6 at 10:58
 */
@SQLTable
public class Department {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
