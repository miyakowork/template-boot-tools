package me.wuwenbin.tools.http.jodd;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * 因为Jodd的用法比较简单暴力，所以此处不做详细拆分.
 * 详细请看<a href="https://jodd.org/util/http.html">官方文档</a>
 * created by Wuwenbin on 2017/9/17 at 10:07
 */
public class JoddHttp {


    //=============================以下为核心方法============================

    /**
     * 获取返回结果对象,返回的所有内容都可以从此对象中获取，
     * 以下的几种方法为常用的几种，所以单独写作方法出来以便调用
     *
     * @param httpRequest httpRequest可通过（新建实例+链式配置）/（静态方法）两种模式生成
     * @return
     */
    public HttpResponse send(HttpRequest httpRequest) {
        return httpRequest.send();
    }
    //=============================以上为核心方法============================

    /**
     * raw body content, always in ISO-8859-1 encoding.
     *
     * @return
     */
    public String getRawBody(HttpResponse httpResponse) {
        return httpResponse.body();
    }

    /**
     * body text, ie string encoded as specified by "Content-Type"
     * 注意：
     * Character encoding used in bodyText() is one set in the response headers.
     * If response does not specify the encoding in it's headers (but e.g. only on the HTML page),
     * you must specify the encoding with charset() method before calling bodyText().
     *
     * @param httpResponse
     * @return
     */
    public String getBodyText(HttpResponse httpResponse) {
        return httpResponse.bodyText();
    }

    /**
     * returns raw body as byte array, so e.g. downloaded file can be saved.
     *
     * @param httpResponse
     * @return
     */
    public byte[] getBodyBytes(HttpResponse httpResponse) {
        return httpResponse.bodyBytes();
    }
}
