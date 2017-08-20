package me.wuwenbin.tools.util.exception;

/**
 * created by Wuwenbin on 2017/8/20 at 11:34
 */
public class LangException extends RuntimeException {

    public LangException(String message) {
        super(message);
    }

    public LangException(Throwable e) {
        super(e);
    }

    public LangException(String message, Throwable e) {
        super(message, e);
    }
}
