package me.wuwenbin.tools.sqlgen.annotation.not;

import java.lang.annotation.*;

/**
 * created by Wuwenbin on 2017/10/6 at 10:06
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface NotInsert {
}
