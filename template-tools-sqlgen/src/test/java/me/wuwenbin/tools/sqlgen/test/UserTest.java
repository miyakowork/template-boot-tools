package me.wuwenbin.tools.sqlgen.test;


import me.wuwenbin.tools.sqlgen.SQLGen;
import me.wuwenbin.tools.sqlgen.constant.Router;
import me.wuwenbin.tools.sqlgen.factory.SQLBeanBuilder;
import me.wuwenbin.tools.sqlgen.factory.SQLTextBuilder;
import org.junit.Test;

/**
 * Created by wuwenbin on 2017/7/8.
 */
public class UserTest {

    @Test
    public void main() {
        SQLBeanBuilder sbb = SQLGen.builder(User.class);
        SQLTextBuilder ssb = SQLGen.builder();
        String sql = sbb.insertAllWithoutPk();
        sql = sbb.selectPartByPk(Router.B, Router.C);
        System.out.println(sql);
    }

}
