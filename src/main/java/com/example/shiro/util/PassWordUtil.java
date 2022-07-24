package com.example.shiro.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.UnsupportedEncodingException;

public class PassWordUtil {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("加密后颜值");

        ByteSource b = ByteSource.Util.bytes("abcd");
        SimpleHash hash = new SimpleHash("MD5", "123".toCharArray(), new SimpleByteSource("abcd"), 1);
        System.out.println("mima="+hash.toString());
        byte[] bytes = "加密后盐值".getBytes("utf-8");
        String s = new String(bytes, "utf-8");
        System.out.println(s);

        System.out.println(System.getProperty("file.encoding"));
    }
}
