package me.wuwenbin.tools.util.lang;

import me.wuwenbin.tools.util.Util;
import me.wuwenbin.tools.util.lang.constant.CharConst;
import me.wuwenbin.tools.util.lang.constant.TextConst;

/**
 * 字符串占位模板替换工具,hutool来源
 * 修改部分代码以适应package的更改
 * Created by wuwenbin on 2017/2/16.
 *
 * @author looly
 * @since 1.0
 */
public class Placeholder implements CharConst, TextConst {

    /**
     * 格式化字符串<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： 	format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\：		format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param needReplaceString 字符串模板
     * @param argArray          参数列表
     * @return 结果
     */
    public String format(final String needReplaceString, final Object... argArray) {
        if (Util.stringX.isBlank(needReplaceString) || (argArray == null || argArray.length == 0)) {
            return needReplaceString;
        }
        final int strPatternLength = needReplaceString.length();

        //初始化定义好的长度以获得更好的性能
        StringBuilder sb = new StringBuilder(strPatternLength + 50);

        int handledPosition = 0;//记录已经处理到的位置
        int delimiterIndex;//占位符所在位置
        for (int argIndex = 0; argIndex < argArray.length; argIndex++) {
            delimiterIndex = needReplaceString.indexOf(EMPTY_JSON, handledPosition);
            if (delimiterIndex == -1) {//剩余部分无占位符
                if (handledPosition == 0) { //不带占位符的模板直接返回
                    return needReplaceString;
                } else { //字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
                    sb.append(needReplaceString, handledPosition, strPatternLength);
                    return sb.toString();
                }
            } else {
                if (delimiterIndex > 0 && needReplaceString.charAt(delimiterIndex - 1) == C_BACKSLASH) {//转义符
                    if (delimiterIndex > 1 && needReplaceString.charAt(delimiterIndex - 2) == C_BACKSLASH) {//双转义符
                        //转义符之前还有一个转义符，占位符依旧有效
                        sb.append(needReplaceString, handledPosition, delimiterIndex - 1);
                        sb.append(Util.stringX.utf8Str(argArray[argIndex]));
                        handledPosition = delimiterIndex + 2;
                    } else {
                        //占位符被转义
                        argIndex--;
                        sb.append(needReplaceString, handledPosition, delimiterIndex - 1);
                        sb.append(C_DELIMITER_START);
                        handledPosition = delimiterIndex + 1;
                    }
                } else {//正常占位符
                    sb.append(needReplaceString, handledPosition, delimiterIndex);
                    sb.append(Util.stringX.utf8Str(argArray[argIndex]));
                    handledPosition = delimiterIndex + 2;
                }
            }
        }
        // append the characters following the last {} pair.
        //加入最后一个占位符后所有的字符
        sb.append(needReplaceString, handledPosition, needReplaceString.length());

        return sb.toString();
    }

    /**
     * 包装指定字符串
     *
     * @param str    被包装的字符串
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 包装后的字符串
     */
    public String wrap(String str, String prefix, String suffix) {
        return format("{}{}{}", prefix, str, suffix);
    }

}
