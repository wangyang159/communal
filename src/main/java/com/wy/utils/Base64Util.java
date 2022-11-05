package com.wy.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 作者: wangyang <br/>
 * 创建时间: 2022/11/5 <br/>
 * 描述: <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;Base64Util
 *  使用Java的Base64类加解密，加盐函数y=x+k
 */
public class Base64Util {

    /**
     * k函数的k值
     * slen最多加密几位
     * standardCharsets默认的字符集
     */
    private static final Integer k = 57;
    private static final Integer slen = 5;
    private static final Charset standardCharsets = StandardCharsets.UTF_8;

    /**
     * 描述: 加密方法 <br/>
     * 作者: wangyang <br/>
     * 创建时间: 2022/11/5 <br/>
     * 参数: data-要加密的字符串 <br/>
     * 返回值: 加密后的字符串 <br/>
     */
    public static String base64Encoder(String data){
        byte[] b = data.getBytes(standardCharsets);
        enfx(b);
        Base64.Encoder encoder = Base64.getEncoder();
        String result = encoder.encodeToString(b);
        return result;
    }

    /**
     * 描述: 解密方法 <br/>
     * 作者: wangyang <br/>
     * 创建时间: 2022/11/5 <br/>
     * 参数: data-已加密的字符串 <br/>
     * 返回值: 已解密的字符串 <br/>
     */
    public static String base64Decoder(String data){
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] result = decoder.decode(data);
        defx(result);
        return new String(result);
    }

    /**
     * 描述: 加密时的加盐函数 <br/>
     * 作者: wangyang <br/>
     * 创建时间: 2022/11/5 <br/>
     * 参数: 原byte数组 <br/>
     * 返回值: 加盐后的byte数组 <br/>
     */
    private static byte[] enfx(byte[] data){
        //最大加密位数不必加密数据长度短就按照最大加密位数来，反之用数据长度
        int t = 0;
        if(slen < data.length){
            t = slen;
        }else {
            t = data.length;
        }

        for(int i=0; i < t ; i++){
            data[i] = (byte) ( data[i] + k);
        }
        return data;
    }

    /**
     * 描述: 解密用的去盐函数 <br/>
     * 作者: wangyang <br/>
     * 创建时间: 2022/11/5 <br/>
     * 参数: 被加盐后的byte数组 <br/>
     * 返回值: 原数组 <br/>
     */
    private static byte[] defx(byte[] data){
        //解密也要考虑长度
        int t = 0;
        if(slen < data.length){
            t = slen;
        }else {
            t = data.length;
        }

        for(int i=0; i < t ; i++){
            data[i] = (byte) (data[i] - k);
        }
        return data;
    }

}
