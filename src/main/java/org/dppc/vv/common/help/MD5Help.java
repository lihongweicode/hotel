package org.dppc.vv.common.help;

import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.MessageDigest;

/**
 * @描述 :
 * @作者 :	zhumh
 * @日期 :	2018/11/30
 * @时间 :	14:03
 */
public class MD5Help {

    public static String encodeMD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String encodeBase64(String data){
        return new sun.misc.BASE64Encoder().encode(data.getBytes());
    }

    /**
     * MD5 & Base64 加密
     * @param cal
     */
    public static String encode(String cal){
        return encodeBase64(MD5Help.encodeMD5(cal));
    }


/*    public static void main(String[] args) throws IOException {
        System.out.println(encode("dppc"));
    }*/
}
