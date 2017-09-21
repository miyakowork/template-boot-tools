package me.wuwenbin.tools.http.apache.util;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;

/**
 * 设置ssl
 * created by Wuwenbin on 2017/9/17 at 10:35
 */
public class SSLx {
    private static final SSLHandler simpleVerifier = new SSLHandler();
    private static SSLSocketFactory sslFactory;
    private static SSLConnectionSocketFactory sslConnFactory;
    private static SSLIOSessionStrategy sslIOSessionStrategy;
    private static SSLx sslx = new SSLx();
    private SSLContext sc;

    public static SSLx getInstance() {
        return sslx;
    }

    public static SSLx custom() {
        return new SSLx();
    }

    // 重写X509TrustManager类的三个方法,信任服务器证书
    private static class SSLHandler implements X509TrustManager, HostnameVerifier {

        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }

        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
        }

        public boolean verify(String paramString, SSLSession paramSSLSession) {
            return true;
        }
    }


    // 信任主机
    public static HostnameVerifier getVerifier() {
        return simpleVerifier;
    }

    public synchronized SSLSocketFactory getSSLSF(SSLProtocolVersion sslProtocolVersion) throws Exception {
        if (sslFactory != null)
            return sslFactory;
        SSLContext sc = getSSLContext(sslProtocolVersion);
        sc.init(null, new TrustManager[]{simpleVerifier}, null);
        sslFactory = sc.getSocketFactory();
        return sslFactory;
    }

    public synchronized SSLConnectionSocketFactory getSSLCONNSF(SSLProtocolVersion sslProtocolVersion) throws Exception {
        if (sslConnFactory != null)
            return sslConnFactory;
        SSLContext sc = getSSLContext(sslProtocolVersion);
        sc.init(null, new TrustManager[]{simpleVerifier}, new java.security.SecureRandom());
        sslConnFactory = new SSLConnectionSocketFactory(sc, simpleVerifier);
        return sslConnFactory;
    }

    public synchronized SSLIOSessionStrategy getSSLIOSS(SSLProtocolVersion sslpv) throws Exception {
        if (sslIOSessionStrategy != null)
            return sslIOSessionStrategy;
        SSLContext sc = getSSLContext(sslpv);
        sc.init(null, new TrustManager[]{simpleVerifier}, new java.security.SecureRandom());
        sslIOSessionStrategy = new SSLIOSessionStrategy(sc, simpleVerifier);
        return sslIOSessionStrategy;
    }

    public SSLx customSSL(String keyStorePath, String keyStorepass) throws Exception {
        FileInputStream inseam = null;
        KeyStore trustStore;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            inseam = new FileInputStream(new File(keyStorePath));
            trustStore.load(inseam, keyStorepass.toCharArray());
            // 相信自己的CA和所有自签名的证书
            sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
        } finally {
            if (inseam != null) {
                inseam.close();
            }
        }
        return this;
    }

    public SSLContext getSSLContext(SSLProtocolVersion sslpv) throws NoSuchAlgorithmException {
        if (sc == null) {
            sc = SSLContext.getInstance(sslpv.getName());
        }
        return sc;
    }

    /**
     * The SSL protocol version (SSLv3, TLSv1, TLSv1.1, TLSv1.2)
     *
     * @author arron
     * @version 1.0
     * @date 2016年11月18日 上午9:35:37
     */
    public static enum SSLProtocolVersion {
        SSL("SSL"),
        SSLv3("SSLv3"),
        TLSv1("TLSv1"),
        TLSv1_1("TLSv1.1"),
        TLSv1_2("TLSv1.2"),;
        private String name;

        SSLProtocolVersion(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static SSLProtocolVersion find(String name) {
            for (SSLProtocolVersion pv : SSLProtocolVersion.values()) {
                if (pv.getName().toUpperCase().equals(name.toUpperCase())) {
                    return pv;
                }
            }
            throw new RuntimeException("未支持当前ssl版本号：" + name);
        }

    }
}
