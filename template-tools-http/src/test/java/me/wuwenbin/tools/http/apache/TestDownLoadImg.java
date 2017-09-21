package me.wuwenbin.tools.http.apache;

import me.wuwenbin.tools.http.Http;
import me.wuwenbin.tools.http.apache.config.HttpApacheConfig;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 下载demo
 *
 * @author arron
 * @version 1.0
 * @date 2016年6月7日 上午10:29:30
 */
public class TestDownLoadImg {

    public static void main(String[] args) throws Exception {
        String imgUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png"; //百度logo
        File file = new File("c:/baidu.png");
        Http.apache.down(HttpApacheConfig.init().url(imgUrl).out(new FileOutputStream(file)));
        if (file.exists()) {
            System.out.println("图片下载成功了！存放在：" + file.getPath());
        }

        String mp3Url = "http://win.web.rh01.sycdn.kuwo.cn/resource/n1/24/6/707126989.mp3"; //四叶草-好想你
        file = new File("c:/好想你.mp3");
        Http.apache.down(HttpApacheConfig.init().url(mp3Url).out(new FileOutputStream(file)));
        if (file.exists()) {
            System.out.println("mp3下载成功了！存放在：" + file.getPath());
        }
    }
}
