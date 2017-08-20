package me.wuwenbin.tools.encrypt.symmetric.support;

/**
 * 对称加密模式，
 * 使用说明详情官方文档,
 * 下列所有注释均来自官方文档
 *
 * @see <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"></a>
 * created by Wuwenbin on 2017/8/19 at 21:48
 */
public enum Mode {
    /**
     * No mode.
     */
    NONE,

    /**
     * Cipher Block Chaining Mode, as defined in <a href="http://csrc.nist.gov/publications/PubsFIPS.html">FIPS PUB 81</a>.
     */
    CBC,

    /**
     * Cipher Feedback Mode, as defined in <a href="http://csrc.nist.gov/publications/PubsFIPS.html">FIPS PUB 81</a>.
     * <p>
     * Using modes such as CFB and OFB, block ciphers can encrypt data in units smaller than the cipher's actual block size.
     * When requesting such a mode, you may optionally specify the number of bits to be processed at a time by appending this number to the mode name as shown in the "DES/CFB8/NoPadding"
     * and "DES/OFB32/PKCS5Padding" transformations. If no such number is specified, a provider-specific default is used.
     * (For example, the SunJCE provider uses a default of 64 bits for DES.) Thus, block ciphers can be turned into byte-oriented stream ciphers by using an 8-bit mode such as CFB8 or OFB8.
     */
    CFB_x,

    /**
     * A simplification of OFB, Counter mode updates the input block as a counter.
     */
    CTR,

    /**
     * Cipher Text Stealing, as described in Bruce Schneier's book Applied Cryptography-Second Edition, John Wiley and Sons, 1996.
     */
    CTS,

    /**
     * Electronic Codebook Mode, as defined in FIPS PUB 81 (generally this mode should not be used for multiple blocks of data).
     */
    ECB,

    /**
     * Output Feedback Mode, as defined in <a href="http://csrc.nist.gov/publications/PubsFIPS.html">FIPS PUB 81</a>.
     * <p>
     * Using modes such as CFB and OFB, block ciphers can encrypt data in units smaller than the cipher's actual block size. When requesting such a mode,
     * you may optionally specify the number of bits to be processed at a time by appending this number to the mode name as shown in the "DES/CFB8/NoPadding"
     * and "DES/OFB32/PKCS5Padding" transformations. If no such number is specified, a provider-specific default is used.
     * (For example, the SunJCE provider uses a default of 64 bits for DES.) Thus, block ciphers can be turned into byte-oriented stream ciphers by using an 8-bit mode such as CFB8 or OFB8.
     */
    OFB_x,

    /**
     * Propagating Cipher Block Chaining, as defined by <a href="http://web.mit.edu/kerberos/">Kerberos V4</a>.
     */
    PCBC
}
