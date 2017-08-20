package me.wuwenbin.tools.encrypt.symmetric.support;

/**
 * 对称加密补码方式,
 * 使用说明详情官方文档,
 * 下列所有注释均来自官方文档
 *
 * @see <a href="https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher"></a>
 * created by Wuwenbin on 2017/8/19 at 21:48
 */
public enum Padding {

    /**
     * No padding.
     */
    NoPadding,

    /**
     * This padding for block ciphers is described in <a href="http://www.w3.org/TR/xmlenc-core/#sec-Alg-Block">5.2 Block Encryption Algorithms </a>in the W3C's "XML Encryption Syntax and Processing" document.
     */
    ISO10126Padding,

    /**
     * Optimal Asymmetric Encryption Padding scheme defined in PKCS1,
     * where <digest> should be replaced by the message digest and <mgf> by the mask generation function. Examples: OAEPWithMD5AndMGF1Padding and OAEPWithSHA-512AndMGF1Padding.
     * <p>
     * If OAEPPadding is used, Cipher objects are initialized with a javax.crypto.spec.OAEPParameterSpec object to supply values needed for OAEPPadding.
     */
    OAEPPadding,

    /**
     * The padding scheme described in PKCS #1, used with the RSA algorithm.
     */
    PKCS1Padding,

    /**
     * he padding scheme described in <a href="http://www.rsa.com/rsalabs/node.asp?id=2127">RSA Laboratories, "PKCS #5: Password-Based Encryption Standard," version 1.5, November 1993</a>.
     */
    PKCS5Padding,

    /**
     * The padding scheme defined in the SSL Protocol Version 3.0, November 18, 1996, section 5.2.3.2 (CBC block cipher):
     * block-ciphered struct {
     * opaque content[SSLCompressed.length];
     * opaque MAC[CipherSpec.hash_size];
     * uint8 padding[
     * GenericBlockCipher.padding_length];
     * uint8 padding_length;
     * } GenericBlockCipher;
     * The size of an instance of a GenericBlockCipher must be a multiple of the block cipher's block length.
     * <p>
     * The padding length, which is always present, contributes to the padding, which implies that if:
     * sizeof(content) + sizeof(MAC) % block_length = 0,
     * padding has to be (block_length - 1) bytes long, because of the existence of padding_length.
     * <p>
     * This makes the padding scheme similar (but not quite) to PKCS5Padding, where the padding length is encoded in the padding (and ranges from 1 to block_length). With the SSL scheme, the sizeof(padding) is encoded in the always present padding_length and therefore ranges from 0 to block_length-1.
     */
    SSL3Padding
}
