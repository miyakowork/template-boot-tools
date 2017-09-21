package me.wuwenbin.tools.http;

import me.wuwenbin.tools.http.apache.ApacheHttp;
import me.wuwenbin.tools.http.jodd.JoddHttp;

/**
 * HTTP请求工具类分两种
 * 1：Apache的httpComponent
 * 2：Jodd的http请求
 * created by Wuwenbin on 2017/9/16 at 23:40
 */
public interface Http {

    ApacheHttp apache = new ApacheHttp();

    JoddHttp jodd = new JoddHttp();

}
