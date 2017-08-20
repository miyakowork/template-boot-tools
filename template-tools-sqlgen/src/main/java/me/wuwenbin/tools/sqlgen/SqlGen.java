package me.wuwenbin.tools.sqlgen;


import me.wuwenbin.tools.sqlgen.factory.SQLBeanBuilder;
import me.wuwenbin.tools.sqlgen.factory.SQLStrBuilder;

/**
 * 使用入口方法
 * Created by wuwenbin on 2017/1/12.
 *
 * @author wuwenbin
 * @since 1.1.0
 */
public final class SqlGen {

    private volatile static SQLBeanBuilder sqlBeanBuilder;
    private volatile static SQLStrBuilder sqlStrBuilder;

    private SqlGen() {
    }

    /**
     * 生成 {@link SQLBeanBuilder} 实例
     *
     * @param #targetBeanClass 对应实体类
     * @return {@link SQLBeanBuilder}
     */
    public synchronized static SQLBeanBuilder builder(Class<?> targetBeanClass) {
        if (sqlBeanBuilder == null) {
            synchronized (SQLBeanBuilder.class) {
                if (sqlBeanBuilder == null) {
                    sqlBeanBuilder = new SQLBeanBuilder(targetBeanClass);
                }
            }
        } else if (targetBeanClass != sqlBeanBuilder.getBeanClass()) {
            sqlBeanBuilder = new SQLBeanBuilder(targetBeanClass);
        }
        return sqlBeanBuilder;
    }

    /**
     * 生成 {@link SQLStrBuilder} instance
     *
     * @return {@link SQLStrBuilder}
     */
    public static SQLStrBuilder builder() {
        if (sqlStrBuilder == null) {
            synchronized (SQLStrBuilder.class) {
                if (sqlStrBuilder == null) {
                    sqlStrBuilder = new SQLStrBuilder();
                }
            }
        }
        return sqlStrBuilder;
    }

}
