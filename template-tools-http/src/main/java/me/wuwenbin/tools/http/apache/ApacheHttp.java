package me.wuwenbin.tools.http.apache;

import me.wuwenbin.tools.http.apache.config.HttpApacheConfig;
import me.wuwenbin.tools.http.apache.config.client.TemplateHttpClientBuilder;
import me.wuwenbin.tools.http.apache.config.method.HttpMethod;
import me.wuwenbin.tools.http.apache.util.ApacheHttpUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用apache的http请求，其所有的请求参数包含HttpClient对象、url(必须有)、请求方式、请求参数parasMap、header数组、编码格式encoding。
 * 每个参数都有相应的类可以链式调用配置生成
 * created by Wuwenbin on 2017/9/17 at 10:06
 */
public class ApacheHttp {

    //默认采用的http协议的HttpClient对象
    private static HttpClient client4HTTP;

    //默认采用的https协议的HttpClient对象
    private static HttpClient client4HTTPS;

    static {
        try {
            client4HTTP = TemplateHttpClientBuilder.init().build();
            client4HTTPS = TemplateHttpClientBuilder.init().ssl().build();
        } catch (Exception e) {
            ApacheHttpUtils.errorException("创建https协议的HttpClient对象出错：{}", e);
        }
    }

    /**
     * 判定是否开启连接池、及url是http还是https <br>
     * 如果已开启连接池，则自动调用build方法，从连接池中获取client对象<br>
     * 否则，直接返回相应的默认client对象<br>
     *
     * @param config 请求参数配置
     * @throws Exception
     */
    private void create(HttpApacheConfig config) throws Exception {
        if (config.client() == null) {//如果为空，设为默认client对象
            if (config.url().toLowerCase().startsWith("https://")) {
                config.client(client4HTTPS);
            } else {
                config.client(client4HTTP);
            }
        }
    }

    //==============================以下为get/HTTP请求方法==========================

    /**
     * 以Get方式，请求资源或服务
     *
     * @param url 资源地址
     * @return 返回处理结果
     */
    public String get(String url) throws Exception {
        return get(HttpApacheConfig.init().url(url));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param url     资源地址
     * @param headers 请求头信息
     * @return 返回处理结果
     */
    public String get(String url, Header[] headers) throws Exception {
        return get(HttpApacheConfig.init().url(url).headers(headers));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param url      资源地址
     * @param paramMap 请求参数
     * @return 返回处理结果
     * @throws Exception
     */
    public String get(String url, Map<String, Object> paramMap) throws Exception {
        return get(HttpApacheConfig.init().url(url).params(paramMap));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param url      资源地址
     * @param headers  请求头信息
     * @param paramMap 请求参数
     * @return 返回处理结果
     * @throws Exception
     */
    public String get(String url, Header[] headers, Map<String, Object> paramMap) throws Exception {
        return get(HttpApacheConfig.init().url(url).headers(headers).params(paramMap));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param url      资源地址
     * @param paramMap 请求参数
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String get(String url, Map<String, Object> paramMap, String encoding) throws Exception {
        return get(HttpApacheConfig.init().url(url).params(paramMap).encoding(encoding));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param url      资源地址
     * @param headers  请求头信息
     * @param paramMap 请求参数
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String get(String url, Header[] headers, Map<String, Object> paramMap, String encoding) throws Exception {
        return get(HttpApacheConfig.init().url(url).headers(headers).params(paramMap).encoding(encoding));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param url      资源地址
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    public String get(String url, String encoding) throws Exception {
        return get(HttpApacheConfig.init().url(url).encoding(encoding));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param url      资源地址
     * @param headers  请求头信息
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    public String get(String url, Header[] headers, String encoding) throws Exception {
        return get(HttpApacheConfig.init().url(url).headers(headers).encoding(encoding));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param paramMap 请求参数
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String get(HttpClient client, String url, Header[] headers, Map<String, Object> paramMap, HttpContext context, String encoding) throws Exception {
        return get(HttpApacheConfig.init().client(client).url(url).headers(headers).params(paramMap).context(context).encoding(encoding));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws Exception
     */
    public String get(HttpApacheConfig config) throws Exception {
        return send(config.method(HttpMethod.GET));
    }
//====================================以上为get/HTTP请求方法===============================================

    //==============================以下为post/HTTP请求方法==========================

    /**
     * 以post方式，请求资源或服务
     *
     * @param url 资源地址
     * @return 返回处理结果
     */
    public String post(String url) throws Exception {
        return post(HttpApacheConfig.init().url(url));
    }

    /**
     * 以post方式，请求资源或服务
     *
     * @param url     资源地址
     * @param headers 请求头信息
     * @return 返回处理结果
     */
    public String post(String url, Header[] headers) throws Exception {
        return post(HttpApacheConfig.init().url(url).headers(headers));
    }

    /**
     * 以post方式，请求资源或服务
     *
     * @param url      资源地址
     * @param paramMap 请求参数
     * @return 返回处理结果
     * @throws Exception
     */
    public String post(String url, Map<String, Object> paramMap) throws Exception {
        return post(HttpApacheConfig.init().url(url).params(paramMap));
    }

    /**
     * 以v方式，请求资源或服务
     *
     * @param url      资源地址
     * @param headers  请求头信息
     * @param paramMap 请求参数
     * @return 返回处理结果
     * @throws Exception
     */
    public String post(String url, Header[] headers, Map<String, Object> paramMap) throws Exception {
        return post(HttpApacheConfig.init().url(url).headers(headers).params(paramMap));
    }

    /**
     * 以post方式，请求资源或服务
     *
     * @param url      资源地址
     * @param paramMap 请求参数
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String post(String url, Map<String, Object> paramMap, String encoding) throws Exception {
        return post(HttpApacheConfig.init().url(url).params(paramMap).encoding(encoding));
    }

    /**
     * 以post方式，请求资源或服务
     *
     * @param url      资源地址
     * @param headers  请求头信息
     * @param paramMap 请求参数
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String post(String url, Header[] headers, Map<String, Object> paramMap, String encoding) throws Exception {
        return post(HttpApacheConfig.init().url(url).headers(headers).params(paramMap).encoding(encoding));
    }

    /**
     * 以post方式，请求资源或服务
     *
     * @param url      资源地址
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    public String post(String url, String encoding) throws Exception {
        return post(HttpApacheConfig.init().url(url).encoding(encoding));
    }

    /**
     * 以post方式，请求资源或服务
     *
     * @param url      资源地址
     * @param headers  请求头信息
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    public String post(String url, Header[] headers, String encoding) throws Exception {
        return post(HttpApacheConfig.init().url(url).headers(headers).encoding(encoding));
    }

    /**
     * 以post方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param paramMap 请求参数
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String post(HttpClient client, String url, Header[] headers, Map<String, Object> paramMap, HttpContext context, String encoding) throws Exception {
        return post(HttpApacheConfig.init().client(client).url(url).headers(headers).params(paramMap).context(context).encoding(encoding));
    }

    /**
     * 以post方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws Exception
     */
    public String post(HttpApacheConfig config) throws Exception {
        return send(config.method(HttpMethod.POST));
    }
//====================================以上为post/HTTP请求方法===============================================


    //==============================以下为put/HTTP请求方法==========================

    /**
     * 以Put方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param parasMap 请求参数
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String put(HttpClient client, String url, Map<String, Object> parasMap, Header[] headers, HttpContext context, String encoding) throws Exception {
        return put(HttpApacheConfig.init().client(client).url(url).headers(headers).params(parasMap).context(context).encoding(encoding));
    }

    /**
     * 以Put方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws Exception
     */
    public String put(HttpApacheConfig config) throws Exception {
        return send(config.method(HttpMethod.PUT));
    }
//====================================以上为put/HTTP请求方法===============================================

    //==============================以下为delete/HTTP请求方法==========================

    /**
     * 以Delete方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String delete(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws Exception {
        return delete(HttpApacheConfig.init().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Delete方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws Exception
     */
    public String delete(HttpApacheConfig config) throws Exception {
        return send(config.method(HttpMethod.DELETE));
    }
//====================================以上为delete/HTTP请求方法===============================================

    //==============================以下为patch/HTTP请求方法==========================

    /**
     * 以Patch方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param parasMap 请求参数
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String patch(HttpClient client, String url, Map<String, Object> parasMap, Header[] headers, HttpContext context, String encoding) throws Exception {
        return patch(HttpApacheConfig.init().client(client).url(url).headers(headers).params(parasMap).context(context).encoding(encoding));
    }

    /**
     * 以Patch方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws Exception
     */
    public String patch(HttpApacheConfig config) throws Exception {
        return send(config.method(HttpMethod.PATCH));
    }
//====================================以上为patch/HTTP请求方法===============================================

    //==============================以下为head/HTTP请求方法==========================

    /**
     * 以Head方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String head(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws Exception {
        return head(HttpApacheConfig.init().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Head方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws Exception
     */
    public String head(HttpApacheConfig config) throws Exception {
        return send(config.method(HttpMethod.HEAD));
    }
//====================================以上为head/HTTP请求方法===============================================
    //==============================以下为options/HTTP请求方法==========================

    /**
     * 以Options方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String options(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws Exception {
        return options(HttpApacheConfig.init().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Options方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws Exception
     */
    public String options(HttpApacheConfig config) throws Exception {
        return send(config.method(HttpMethod.OPTIONS));
    }

    //====================================以上为options/HTTP请求方法===============================================

    //==============================以下为trace/HTTP请求方法==========================

    /**
     * 以Trace方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws Exception
     */
    public String trace(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws Exception {
        return trace(HttpApacheConfig.init().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Trace方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws Exception
     */
    public String trace(HttpApacheConfig config) throws Exception {
        return send(config.method(HttpMethod.TRACE));
    }

    //====================================以上为trace/HTTP请求方法===============================================


    //==============================以下为upload/down/HTTP请求方法==========================

    /**
     * 下载文件
     *
     * @param client  client对象
     * @param url     资源地址
     * @param headers 请求头信息
     * @param context http上下文，用于cookie操作
     * @param out     输出流
     * @return 返回处理结果
     * @throws Exception
     */
    public OutputStream down(HttpClient client, String url, Header[] headers, HttpContext context, OutputStream out) throws Exception {
        return down(HttpApacheConfig.init().client(client).url(url).headers(headers).context(context).out(out));
    }

    /**
     * 下载文件
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws Exception
     */
    public OutputStream down(HttpApacheConfig config) throws Exception {
        return resp2Stream(execute(config.method(HttpMethod.GET)), config.out());
    }

    /**
     * 上传文件
     *
     * @param client  client对象
     * @param url     资源地址
     * @param headers 请求头信息
     * @param context http上下文，用于cookie操作
     * @return 返回处理结果
     * @throws Exception
     */
    public String upload(HttpClient client, String url, Header[] headers, HttpContext context) throws Exception {
        return upload(HttpApacheConfig.init().client(client).url(url).headers(headers).context(context));
    }

    /**
     * 上传文件
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws Exception
     */
    public String upload(HttpApacheConfig config) throws Exception {
        if (config.method() != HttpMethod.POST && config.method() != HttpMethod.PUT) {
            config.method(HttpMethod.POST);
        }
        return send(config);
    }

    //====================================以上为upload/down/HTTP请求方法===============================================

//==============================以下为status/HTTP请求方法==========================

    /**
     * 查看资源链接情况，返回状态码
     *
     * @param client  client对象
     * @param url     资源地址
     * @param headers 请求头信息
     * @param context http上下文，用于cookie操作
     * @return 返回处理结果
     * @throws Exception
     */
    public int status(HttpClient client, String url, Header[] headers, HttpContext context, HttpMethod method) throws Exception {
        return status(HttpApacheConfig.init().client(client).url(url).headers(headers).context(context).method(method));
    }

    /**
     * 查看资源链接情况，返回状态码
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws Exception
     */
    public int status(HttpApacheConfig config) throws Exception {
        return resp2Int(execute(config));
    }

    //====================================以上为status/down/HTTP请求方法===============================================

    //==============================以下为http核心请求方法==========================

    /**
     * 请求资源或服务
     *
     * @param config 请求参数配置
     * @return
     * @throws Exception
     */
    public String send(HttpApacheConfig config) throws Exception {
        return resp2String(execute(config), config.outenc());
    }

    /**
     * 请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回HttpResponse对象
     * @throws Exception
     */
    private HttpResponse execute(HttpApacheConfig config) throws Exception {
        create(config);//获取链接
        HttpResponse resp;
        try {
            //创建请求对象
            HttpRequestBase request = getRequest(config.url(), config.method());

            //设置header信息
            request.setHeaders(config.headers());

            //判断是否支持设置entity(仅HttpPost、HttpPut、HttpPatch支持)
            if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
                List<NameValuePair> nvps = new ArrayList<>();

                //检测url中是否存在参数
                config.url(ApacheHttpUtils.checkHasParas(config.url(), nvps, config.inenc()));

                //装填参数
                HttpEntity entity = ApacheHttpUtils.map2HttpEntity(nvps, config.params(), config.inenc());

                //设置参数到请求对象中
                ((HttpEntityEnclosingRequestBase) request).setEntity(entity);

                ApacheHttpUtils.info("请求地址：" + config.url());
                if (nvps.size() > 0) {
                    ApacheHttpUtils.info("请求参数：" + nvps.toString());
                }
                if (config.json() != null) {
                    ApacheHttpUtils.info("请求参数：" + config.json());
                }
            } else {
                int idx = config.url().indexOf("?");
                ApacheHttpUtils.info("请求地址：" + config.url().substring(0, (idx > 0 ? idx : config.url().length())));
                if (idx > 0) {
                    ApacheHttpUtils.info("请求参数：" + config.url().substring(idx + 1));
                }
            }
            //执行请求操作，并拿到结果（同步阻塞）
            resp = (config.context() == null) ? config.client().execute(request) : config.client().execute(request, config.context());

            if (config.isReturnRespHeaders()) {
                //获取所有response的header信息
                config.headers(resp.getAllHeaders());
            }

            //获取结果实体
            return resp;

        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    //==============================以下为http请求的辅助工具方法==========================

    /**
     * 转化为字符串
     *
     * @param resp     响应对象
     * @param encoding 编码
     * @return
     * @throws Exception
     */
    private static String resp2String(HttpResponse resp, String encoding) throws Exception {
        String body;
        try {
            if (resp.getEntity() != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(resp.getEntity(), encoding);
                ApacheHttpUtils.info(body);
            } else {//有可能是head请求
                body = resp.getStatusLine().toString();
            }
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            throw new Exception(e);
        } finally {
            close(resp);
        }
        return body;
    }

    /**
     * 转化为数字
     *
     * @param resp 响应对象
     * @return
     * @throws Exception
     */
    private static int resp2Int(HttpResponse resp) throws Exception {
        int statusCode;
        try {
            statusCode = resp.getStatusLine().getStatusCode();
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            throw new Exception(e);
        } finally {
            close(resp);
        }
        return statusCode;
    }

    /**
     * 转化为流
     *
     * @param resp 响应对象
     * @param out  输出流
     * @return
     * @throws Exception
     */
    public static OutputStream resp2Stream(HttpResponse resp, OutputStream out) throws Exception {
        try {
            resp.getEntity().writeTo(out);
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            throw new Exception(e);
        } finally {
            close(resp);
        }
        return out;
    }

    /**
     * 根据请求方法名，获取request对象
     *
     * @param url    资源地址
     * @param method 请求方式
     * @return
     */
    private static HttpRequestBase getRequest(String url, HttpMethod method) {
        HttpRequestBase request;
        switch (method.getCode()) {
            case 0:// HttpGet
                request = new HttpGet(url);
                break;
            case 1:// HttpPost
                request = new HttpPost(url);
                break;
            case 2:// HttpHead
                request = new HttpHead(url);
                break;
            case 3:// HttpPut
                request = new HttpPut(url);
                break;
            case 4:// HttpDelete
                request = new HttpDelete(url);
                break;
            case 5:// HttpTrace
                request = new HttpTrace(url);
                break;
            case 6:// HttpPatch
                request = new HttpPatch(url);
                break;
            case 7:// HttpOptions
                request = new HttpOptions(url);
                break;
            default:
                request = new HttpPost(url);
                break;
        }
        return request;
    }

    /**
     * 尝试关闭response
     *
     * @param resp HttpResponse对象
     */
    private static void close(HttpResponse resp) {
        try {
            if (resp == null) return;
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if (CloseableHttpResponse.class.isAssignableFrom(resp.getClass())) {
                ((CloseableHttpResponse) resp).close();
            }
        } catch (IOException e) {
            ApacheHttpUtils.exception(e);
        }
    }
}
