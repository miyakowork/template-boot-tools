package me.wuwenbin.tools.http.apache;


import me.wuwenbin.tools.http.Http;
import me.wuwenbin.tools.http.apache.config.HttpApacheConfig;
import me.wuwenbin.tools.http.apache.config.cookie.HttpCookies;

import java.util.HashMap;
import java.util.Map;

/**
 * 上传功能测试
 *
 * @author arron
 * @version 1.0
 * @date 2016年11月2日 下午1:17:17
 */
public class TestUpload {

    public static void main(String[] args) throws Exception {
        //登录后，为上传做准备
        HttpApacheConfig config = prepareUpload();

        String url = "http://test.free.800m.net:8080/up.php?action=upsave";//上传地址
        String[] filePaths = {"D:\\中国.txt", "D:\\111.txt", "C:\\Users\\160049\\Desktop\\中国.png"};//待上传的文件路径

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("path", "./tomcat/vhost/test/ROOT/");//指定其他参数
        config.url(url) //设定上传地址
                .encoding("GB2312") //设定编码，否则可能会引起中文乱码或导致上传失败
                .files(filePaths, "myfile", true)//.files(filePaths)，如果服务器端有验证input 的name值，则请传递第二个参数，如果上传失败，则尝试第三个参数设置为true
                .params(map);//其他需要提交的参数

        String r = Http.apache.upload(config);//上传
        System.out.println(r);

    }

    /**
     * 登录，并上传文件
     *
     * @return
     * @throws Exception
     */
    private static HttpApacheConfig prepareUpload() throws Exception {
        String url = "http://test.free.800m.net:8080/";
        String loginUrl = url + "login.php";
        String indexUrl = url + "index.php";
        HttpCookies cookies = HttpCookies.init();
        //启用cookie，用于登录后的操作
        HttpApacheConfig config = HttpApacheConfig.init().context(cookies.getContext());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_name", "test");
        map.put("user_pass", "800m.net");
        map.put("action", "login");
        String loginResult = Http.apache.post(config.url(loginUrl).params(map));

        System.out.println("是否登录成功：" + loginResult.contains("成功"));
        //打开主页
        Http.apache.get(config.params(null).url(indexUrl));

        return config;
    }

}
