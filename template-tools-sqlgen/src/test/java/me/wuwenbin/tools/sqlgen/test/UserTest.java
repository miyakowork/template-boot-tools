package me.wuwenbin.tools.sqlgen.test;


import me.wuwenbin.tools.sqlgen.SqlGen;
import me.wuwenbin.tools.sqlgen.factory.SQLBeanBuilder;
import me.wuwenbin.tools.sqlgen.factory.SQLStrBuilder;

/**
 * Created by wuwenbin on 2017/7/8.
 */
public class UserTest {
    public static void main(String[] args) {
        SQLBeanBuilder sbb = SqlGen.builder(User.class);
        SQLStrBuilder ssb = SqlGen.builder();
        String sql = ssb.selectAllByColumns(sbb.getTableName(), "enabled");
        System.out.println(sql);
    }
}
