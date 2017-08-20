package me.wuwenbin.tools.util.regex.support;

/**
 * created by Wuwenbin on 2017/8/20 at 18:23
 */
public interface RegexConst {
    /**
     * Alphanumeric characters
     */
    String REG_ALNUM = "\\p{Alnum}";
    /**
     * Alphabetic characters
     */
    String REG_ALPHA = "\\p{Alpha}";
    /**
     * ASCII characters
     */
    String REG_ASCII = "\\p{ASCII}";
    /**
     * Space and tab
     */
    String REG_BLANK = "\\p{Blank}";
    /**
     * Control characters
     */
    String REG_CNTRL = "\\p{Cntrl}";
    /**
     * Digits
     */
    String REG_DIGITS = "\\p{Digit}";
    /**
     * SVisible characters (i.e. anything except spaces, control characters,
     * etc.)
     */
    String REG_GRAPH = "\\p{Graph}";
    /**
     * Lowercase letters
     */
    String REG_LOWER = "\\p{Lower}";
    /**
     * Visible characters and spaces (i.e. anything except control characters,
     * etc.)
     */
    String REG_PRINT = "\\p{Print}";
    /**
     * Punctuation and symbols.
     */
    String REG_PUNCT = "\\p{Punct}";
    /**
     * All whitespace characters, including line breaks
     */
    String REG_SPACE = "\\p{Space}";
    /**
     * Uppercase letters
     */
    String REG_UPPER = "\\p{Upper}";
    /**
     * Hexadecimal digits
     */
    String REG_XDIGIT = "\\p{XDigit}";
    /**
     * 空白行
     */
    String REG_SPACE_LINE = "\\n\\s*\\r";
    /**
     * 首尾空白字符
     */
    String REG_SPACE_POINT = "^\\s*|\\s*$";
    /**
     * HTML
     */
    String REG_HTML = "<(\\S*?)[^>]*>.*?</\\1>|<.*? />";
    /**
     * Email
     */
    String REG_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 国内固定电话
     */
    String REG_FIXED_TELEPHONE = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
    /**
     * 邮政编码
     */
    String REG_POSTALCODE = "[1-9]\\d{5}(?!\\d)";
    /**
     * 身份证编码
     */
    String REG_IDENTIFICATION_CARD = "\\d{15}|\\d{18}";
    /**
     * URL地址
     */
    String REG_URL = "^http://([w-]+.)+[w-]+(/[w-./?%&=]*)?$";
    /**
     * 移动电话
     */
    String REG_MOBILE_TELEPHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
    /**
     * 合法的名字（字母开头，允许5-16字节，允许字母数字下划线）
     */
    String REG_LEGAL_ACCOUNT = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
    /**
     * i地址
     */
    String REG_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
}
