package me.wuwenbin.tools.util.regex;

import me.wuwenbin.tools.util.regex.support.RegexConst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>ClassName</b>: BnRegexUtils<br>
 * <b>Description</b>: 封装一些正则相关的操作，使用的时候使用就可以了<br>
 * <b>Version</b>: Ver 1.0<br>
 * <p>
 * <b>author</b>: Wuwenbin<br>
 * <b>date</b>: 2016年8月31日<br>
 * <b>time</b>: 下午2:15:39 <br>
 */
public class Regex implements RegexConst {


    private Pattern numericPattern = Pattern.compile("^[0-9\\-]+$");
    private Pattern floatNumericPattern = Pattern.compile("^[0-9\\-\\.]+$");
    private Pattern abcPattern = Pattern.compile("^[a-z|A-Z]+$");

    /**
     * 判断是否数字表示
     *
     * @param src 源字符串
     * @return 是否数字的标志
     */
    public boolean isNumeric(String src) {
        boolean isNumber = false;
        if (src != null && src.length() > 0) {
            Matcher m = numericPattern.matcher(src);
            if (m.find()) {
                isNumber = true;
            }
        }
        return isNumber;
    }

    /**
     * 判断是否纯字母组合
     *
     * @param src 源字符串
     * @return 是否纯字母组合的标志
     */
    public boolean isABC(String src) {
        boolean return_value = false;
        if (src != null && src.length() > 0) {
            Matcher m = abcPattern.matcher(src);
            if (m.find()) {
                return_value = true;
            }
        }
        return return_value;
    }

    /**
     * 判断是否浮点数字表示
     *
     * @param src 源字符串
     * @return 是否数字的标志
     */
    public boolean isFloatNumeric(String src) {
        boolean return_value = false;
        if (src != null && src.length() > 0) {
            Matcher m = floatNumericPattern.matcher(src);
            if (m.find()) {
                return_value = true;
            }
        }
        return return_value;
    }

    /**
     * 判断字符串str是否符合正则表达式reg
     *
     * @param str 需要处理的字符串
     * @param reg 正则
     * @return 是否匹配
     */
    public boolean isMatch(String str, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 获取符合reg正则表达式的字符串在String中出现的次数
     *
     * @param str 需要处理的字符串
     * @param reg 正则
     * @return 出现的次数
     */
    public int countSubStrReg(String str, String reg) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            i++;
        }
        return i;
    }

    /**
     * 判断是否是符合邮箱
     *
     * @param email 判断的字符串
     * @return 是否是符合的邮箱
     */
    public boolean isEmail(String email) {
        if (email == null || email.length() < 1 || email.length() > 256) {
            return false;
        }
        Pattern pattern = Pattern.compile(REG_EMAIL);
        return pattern.matcher(email).matches();
    }
}
