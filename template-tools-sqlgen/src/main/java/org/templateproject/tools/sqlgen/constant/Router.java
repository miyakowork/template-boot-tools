package org.templateproject.tools.sqlgen.constant;

/**
 * 提供一些路由常量，以供快捷使用
 * created by Wuwenbin on 2017/8/16 at 14:41
 */
public interface Router {

    /**
     * 列上没注明router的时候，所有的含有@SQLColumn都含有的默认值
     */
    int DEFAULT = Integer.MIN_VALUE;

    //下面的A~G尝试提供7个已定义的，以供快捷使用
    // 开发者完全可以自己定义整形变量即可
    int A = Integer.MIN_VALUE + 'a';
    int B = Integer.MIN_VALUE + 'b';
    int C = Integer.MIN_VALUE + 'c';
    int D = Integer.MIN_VALUE + 'd';
    int E = Integer.MIN_VALUE + 'e';
    int F = Integer.MIN_VALUE + 'f';
    int G = Integer.MIN_VALUE + 'g';
}
