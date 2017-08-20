package me.wuwenbin.tools.util;

import me.wuwenbin.tools.util.clazz.ClassScan;
import me.wuwenbin.tools.util.escape.Escape;
import me.wuwenbin.tools.util.hex.Hex;
import me.wuwenbin.tools.util.idcard.IDCard;
import me.wuwenbin.tools.util.lang.*;
import me.wuwenbin.tools.util.properties.Properties;
import me.wuwenbin.tools.util.regex.Regex;
import me.wuwenbin.tools.util.web.WebDate;
import me.wuwenbin.tools.util.web.WebFireWall;
import me.wuwenbin.tools.util.xml.Dom4jXml;
import me.wuwenbin.tools.util.xml.W3cXml;

/**
 * created by Wuwenbin on 2017/8/19 at 23:59
 */
public interface Util {

    /**
     * 字符串工具类
     */
    StringX stringX = new StringX();

    /**
     * 数组工具类
     */
    ArrayX arrayX = new ArrayX();

    /**
     * 文本替换工具
     */
    Placeholder placeholder = new Placeholder();

    /**
     * 拼音操作工具类
     */
    PinYin pinyin = new PinYin();
    /**
     * properties属性文件操作工具类
     */
    Properties properties = new Properties();

    /**
     * xml工具类 w3c规范
     */
    W3cXml w3cXml = new W3cXml();

    /**
     * xml工具类 dom4j
     */
    Dom4jXml dom4jXml = new Dom4jXml();

    /**
     * 16进制换算工具类
     */
    Hex hex = new Hex();

    /**
     * 转义与反转义操作工具类
     */
    Escape escape = new Escape();

    /**
     * 身份证验证
     */
    IDCard idCard = new IDCard();

    /**
     * 数字和数学辅助操作
     */
    NumberX numberX = new NumberX();

    /**
     * java class扫描
     */
    ClassScan classScan = new ClassScan();

    /**
     * 正则表达式
     */
    Regex regex = new Regex();

    /**
     * URL工具类
     */
    Url url = new Url();

    /**
     * 有关web或Controller层获取到的相关日期的操作
     */
    WebDate webDate = new WebDate();

    /**
     * Web防火墙工具类
     */
    WebFireWall webFireWall = new WebFireWall();

}
