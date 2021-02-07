package com.xybl.server.utils;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MD5UtilTest {
    private String bytesToString(byte[] bytes){
        StringBuilder buffer=new StringBuilder();
        for(byte aByte:bytes){
            if(aByte<10){
                buffer.append("0").append(aByte);
            }else {
                buffer.append(aByte);
            }
        }
        return buffer.toString();
    }

    @Test
    void hexStringToByte() {
        String hexString="31363132373132343938303030303031";
        System.out.println(Arrays.toString(MD5Util.hexStringToByte(hexString)));
    }

    @Test
    void byteToHexString() {
        byte[] bytes="1612712498000001".getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));
        System.out.println(bytesToString(bytes));
        System.out.println(MD5Util.byteToHexString(bytes));
    }

    @Test
    void getEncryptedText() {
        String text="001";
        System.out.println("1:"+MD5Util.getEncryptedText(text));
        System.out.println("2:"+MD5Util.getEncryptedText(text));
    }

    @Test
    void validText() {
        String text="1612712498000002";
        System.out.println(MD5Util.validText(text,"A626269167C21687F00846496C034B9398016396D16B160358994A82"));
    }
}