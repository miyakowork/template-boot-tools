package me.wuwenbin.tools.http.apache.config;

import me.wuwenbin.tools.http.apache.config.method.HttpMethod;
import me.wuwenbin.tools.http.apache.util.ApacheHttpUtils;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * apache的Http请求配置
 * created by Wuwenbin on 2017/9/17 at 10:31
 */
public class HttpApacheConfig {

    private HttpApacheConfig() {
    }

    public static HttpApacheConfig init() {
        return new HttpApacheConfig();
    }

    /**
     * HttpClient对象
     */
    private HttpClient client;

    /**
     * Header头信息
     */
    private Header[] headers;

    /**
     * 是否返回response的headers
     */
    private boolean isReturnRespHeaders;

    /**
     * 请求方法
     */
    private HttpMethod method = HttpMethod.GET;

    /**
     * 请求方法名称
     */
    private String methodName;

    /**
     * 用于cookie操作
     */
    private HttpContext context;

    /**
     * 传递参数
     */
    private Map<String, Object> map;

    /**
     * 以json格式作为输入参数
     */
    private String json;

    /**
     * 输入输出编码
     */
    private String encoding = Charset.defaultCharset().displayName();

    /**
     * 输入编码
     */
    private String inenc;

    /**
     * 输出编码
     */
    private String outenc;

    /**
     * 解决多线程下载时，strean被close的问题
     */
    private static final ThreadLocal<OutputStream> outs = new ThreadLocal<OutputStream>();

    /**
     * 解决多线程处理时，url被覆盖问题
     */
    private static final ThreadLocal<String> urls = new ThreadLocal<String>();

    /**
     * HttpClient对象
     */
    public HttpApacheConfig client(HttpClient client) {
        this.client = client;
        return this;
    }

    /**
     * 资源url
     */
    public HttpApacheConfig url(String url) {
        urls.set(url);
        return this;
    }

    /**
     * Header头信息
     */
    public HttpApacheConfig headers(Header[] headers) {
        this.headers = headers;
        return this;
    }

    /**
     * Header头信息(是否返回response中的headers)
     */
    public HttpApacheConfig headers(Header[] headers, boolean isReturnRespHeaders) {
        this.headers = headers;
        this.isReturnRespHeaders = isReturnRespHeaders;
        return this;
    }

    /**
     * 请求方法
     */
    public HttpApacheConfig method(HttpMethod method) {
        this.method = method;
        return this;
    }

    /**
     * 请求方法
     */
    public HttpApacheConfig methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    /**
     * cookie操作相关
     */
    public HttpApacheConfig context(HttpContext context) {
        this.context = context;
        return this;
    }

    /**
     * 传递参数
     */
    public HttpApacheConfig params(Map<String, Object> map) {
        synchronized (this) {
            if (this.map == null || map == null) {
                this.map = map;
            } else {
                this.map.putAll(map);
                ;
            }
        }
        return this;
    }

    /**
     * 以json格式字符串作为参数
     */
    public HttpApacheConfig json(String json) {
        this.json = json;
        map = new HashMap<>();
        map.put(ApacheHttpUtils.ENTITY_STRING, json);
        return this;
    }

    /**
     * 上传文件时用到
     */
    public HttpApacheConfig files(String[] filePaths) {
        return files(filePaths, "file");
    }

    /**
     * 上传文件时用到
     *
     * @param filePaths 待上传文件所在路径
     */
    public HttpApacheConfig files(String[] filePaths, String inputName) {
        return files(filePaths, inputName, false);
    }

    /**
     * 上传文件时用到
     *
     * @param filePaths                     待上传文件所在路径
     * @param inputName                     即file input 标签的name值，默认为file
     * @param forceRemoveContentTypeChraset
     * @return
     */
    public HttpApacheConfig files(String[] filePaths, String inputName, boolean forceRemoveContentTypeChraset) {
        synchronized (getClass()) {
            if (this.map == null) {
                this.map = new HashMap<>();
            }
        }
        map.put(ApacheHttpUtils.ENTITY_MULTIPART, filePaths);
        map.put(ApacheHttpUtils.ENTITY_MULTIPART + ".name", inputName);
        map.put(ApacheHttpUtils.ENTITY_MULTIPART + ".rmCharset", forceRemoveContentTypeChraset);
        return this;
    }

    /**
     * 输入输出编码
     */
    public HttpApacheConfig encoding(String encoding) {
        //设置输入输出
        inenc(encoding);
        outenc(encoding);
        this.encoding = encoding;
        return this;
    }

    /**
     * 输入编码
     */
    public HttpApacheConfig inenc(String inenc) {
        this.inenc = inenc;
        return this;
    }

    /**
     * 输出编码
     */
    public HttpApacheConfig outenc(String outenc) {
        this.outenc = outenc;
        return this;
    }

    /**
     * 输出流对象
     */
    public HttpApacheConfig out(OutputStream out) {
        outs.set(out);
        return this;
    }

    public HttpClient client() {
        return client;
    }

    public Header[] headers() {
        return headers;
    }

    public boolean isReturnRespHeaders() {
        return isReturnRespHeaders;
    }

    public String url() {
        return urls.get();
    }

    public HttpMethod method() {
        return method;
    }

    public String methodName() {
        return methodName;
    }

    public HttpContext context() {
        return context;
    }

    public Map<String, Object> params() {
        return map;
    }

    public String json() {
        return json;
    }

    public String encoding() {
        return encoding;
    }

    public String inenc() {
        return inenc == null ? encoding : inenc;
    }

    public String outenc() {
        return outenc == null ? encoding : outenc;
    }

    public OutputStream out() {
        return outs.get();
    }


}
