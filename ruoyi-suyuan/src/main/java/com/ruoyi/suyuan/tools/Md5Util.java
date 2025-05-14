package com.ruoyi.suyuan.tools;

import java.security.MessageDigest;

public class Md5Util {
    // 等价于 EncodeMD5
    public static String encodeMD5(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(value.getBytes("UTF-8"));
            return bytesToHex(digest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 等价于 Md5Encrypt
    public static String md5Encrypt(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes("UTF-8"));
            byte[] digest = md.digest();
            return bytesToHex(digest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 辅助方法：字节数组转16进制字符串
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xff);
            if (hex.length() == 1) sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }
}
