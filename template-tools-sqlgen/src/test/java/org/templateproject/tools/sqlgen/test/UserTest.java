package org.templateproject.tools.sqlgen.test;


import org.templateproject.tools.sqlgen.entrance.SQLFactory;
import org.templateproject.tools.sqlgen.factory.SQLBeanBuilder;
import org.templateproject.tools.sqlgen.factory.SQLStrBuilder;

/**
 * Created by wuwenbin on 2017/7/8.
 */
public class UserTest {
    public static void main(String[] args) {
        SQLBeanBuilder sbb = SQLFactory.builder(User.class);
        SQLStrBuilder ssb = SQLFactory.builder();
        String sql = ssb.selectAllByColumns(sbb.getTableName(), "enabled");
        System.out.println(sql);
    }
}
