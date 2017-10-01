package me.wuwenbin.tools.sqlgen.annotation;

import me.wuwenbin.tools.sqlgen.constant.Router;

import java.lang.annotation.*;

/**
 * created by Wuwenbin on 2017/10/1 at 20:04
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SQLIgnore {

    int[] routers() default Router.DEFAULT;
}
