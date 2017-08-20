package me.wuwenbin.tools.util.lang;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 功 能 描 述:<br>
 * 带多音字的拼音工具
 * <br>代 码 作 者:张鹏(ZhangPeng)
 * <br>开 发 日 期:2010-11-19下午02:14:49
 * <br>项 目 信 息:JxufeRjb1.0:com.wisdom.core.utils.PinYin.java
 *
 * @author 改进人wuwenbin，50行for循环改为Collections.adAll、76行for循环改为 System.arraycopy96行加入AtomicReference原子类
 */
public class PinYin {

    public Collection<String> getPinyin(String str) {
        if (str != null && !str.trim().equalsIgnoreCase("")) {
            char[] strChar;
            strChar = str.toCharArray();
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
            String[][] temp = new String[str.length()][];
            for (int i = 0; i < strChar.length; i++) {
                char c = strChar[i];
                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
                    try {
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(strChar[i], hanYuPinOutputFormat);
                    } catch (BadHanyuPinyinOutputFormatCombination e) {
                        e.printStackTrace();
                    }
                } else if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122)) {
                    temp[i] = new String[]{String.valueOf(strChar[i])};
                } else {
                    temp[i] = new String[]{""};
                }
            }
            String[] pinyinArray = Exchange(temp);
            Collection<String> pinyinSet = new HashSet<>();
            Collections.addAll(pinyinSet, pinyinArray);
            return pinyinSet;
        }
        return null;
    }

    private String[] Exchange(String[][] strJaggedArray) {
        String[][] temp = DoExchange(strJaggedArray);
        return temp[0];
    }

    private String[][] DoExchange(String[][] strJaggedArray) {
        int len = strJaggedArray.length;
        if (len >= 2) {
            int len1 = strJaggedArray[0].length;
            int len2 = strJaggedArray[1].length;
            int newLength = len1 * len2;
            String[] temp = new String[newLength];
            int Index = 0;
            for (int i = 0; i < len1; i++) {
                for (int j = 0; j < len2; j++) {
                    temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
                    Index++;
                }
            }
            String[][] newArray = new String[len - 1][];
            System.arraycopy(strJaggedArray, 2, newArray, 1, len - 2);
            newArray[0] = temp;
            return DoExchange(newArray);
        } else {
            return strJaggedArray;
        }
    }

    /**
     * 方法名: getFirstSpell
     * 方法作用:  得到拼音首字母
     * 创建人：Jxufe Zhangpeng
     * 创建时间：2014-11-21 上午09:26:26
     * 补充：使用原子类AtomicReference保证一个线程能修改时，其他线程读取到的value为最新的
     *
     * @param @param  chinese
     * @param @return 返回值类型： String
     * @throws
     */
    public String getFirstSpell(String chinese) {
        AtomicReference<StringBuffer> pinyinBuffer = new AtomicReference<>(new StringBuffer());
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char anArr : arr) {
            if (anArr > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(anArr, defaultFormat);
                    if (temp != null) {
                        pinyinBuffer.get().append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinBuffer.get().append(anArr);
            }
        }
        return pinyinBuffer.get().toString().replaceAll("\\W", "").trim();
    }
}
