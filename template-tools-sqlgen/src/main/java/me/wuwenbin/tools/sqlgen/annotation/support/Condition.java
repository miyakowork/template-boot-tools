package me.wuwenbin.tools.sqlgen.annotation.support;

/**
 * created by Wuwenbin on 2017/10/4 at 18:39
 */
public enum Condition {

    EQ("="),
    LT("<"),
    GT(">"),
    LTE("<="),
    GTE(">="),
    NE("<>"),
    LIKE("LIKE"),
    NOT_LIKE("NOT LIKE");

    private String cnd;

    Condition(String cnd) {
        this.cnd = cnd;
    }

    public String getCnd() {
        return this.cnd;
    }

}
