package org.templateproject.tools.sqlgen.test;


import org.templateproject.tools.sqlgen.constant.Router;
import org.templateproject.tools.sqlgen.entrance.SQLFactory;
import org.templateproject.tools.sqlgen.factory.SQLBeanBuilder;

/**
 * Created by wuwenbin on 2017/7/8.
 */
public class UserTest {
    public static void main(String[] args) {
        SQLBeanBuilder sqlBeanBuilder = SQLFactory.builder(User.class);
        System.out.println(sqlBeanBuilder.insertRoutersWithPk(Router.B));
    }
}
