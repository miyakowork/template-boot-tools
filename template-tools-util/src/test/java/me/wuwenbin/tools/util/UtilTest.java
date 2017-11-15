package me.wuwenbin.tools.util;

import org.junit.Test;

import java.io.IOException;
import java.util.Set;

/**
 * created by Wuwenbin on 2017/8/20 at 10:20
 */
public class UtilTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
//        Set<Class<? extends Annotation>> as = new HashSet<>();
//        as.add(Deprecated.class);
        Set<Class<?>> classSet = Util.classScan.scan("me.wuwenbin.tools.util", null, null);
        for (Class<?> clazz : classSet) {
            System.out.println(clazz);
        }
    }
}
