package me.wuwenbin.tools.encrypt;

import me.wuwenbin.tools.encrypt.factory.DigestFactory;
import me.wuwenbin.tools.encrypt.factory.HexFactory;
import me.wuwenbin.tools.encrypt.factory.SecurityUtils;
import me.wuwenbin.tools.encrypt.factory.UrlFactory;

/**
 * created by Wuwenbin on 2017/8/20 at 20:17
 */
public interface Encrypt {

    /**
     * 摘要加密
     */
    DigestFactory digest = new DigestFactory();

    /**
     * hex
     */
    HexFactory hex = new HexFactory();

    /**
     * url编码解码
     */
    UrlFactory url = new UrlFactory();

    /**
     * 安全工具类，主要负责生成一些秘钥加密中间件等
     */
    SecurityUtils secyrityFactory = new SecurityUtils();

}
