package com.xybl.server.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * MD5Util
 * <p>MD5加密类。<br>
 *     注册或修改密码时，调用getEncryptedText(String text)方法。数据库中存储加密后的密码。<br>
 *     校验密码时，使用validText(String text, String enTextInDb)方法。</p>
 *
 * @author hesheng
 * @create 2021/2/7
 **/
public class MD5Util {
    private static final String HEX_NUMS_STR="0123456789ABCDEF";
    private static final Integer SALT_LENGTH=12;

    /**
    * hexStringToByte
    * <p>将16进制字符串转换成字节数组。</p>
    * @param hex java.lang.String. 16进制字符串
    * @return byte[]
    * @author hesheng
    * @create: 2021/2/7
    */
    public static byte[] hexStringToByte(String hex){
        int len=(hex.length()/2);
        byte[] result=new byte[len];
        char[] hexChars=hex.toCharArray();
        for(int i=0;i<len;i++){
            int pos=i*2;
            result[i]=(byte) (HEX_NUMS_STR.indexOf(hexChars[pos])<<4|HEX_NUMS_STR.indexOf(hexChars[pos+1]));
        }
        return result;
    }

    /**
    * byteToHexString
    * <p>将指定byte数组转换成16进制字符串。</p>
    * @param bytes byte[]. byte数组。
    * @return java.lang.String
    * @author hesheng
    * @create: 2021/2/7
    */
    public static String byteToHexString(byte[] bytes){
        StringBuilder hexString=new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
    * getEncryptedText
    * <p>获得加密后的16进制形式密文。密文长度=(16+SALT_LENGTH)*2=56.</p>
    * @param text java.lang.String. 明文。
    * @return java.lang.String 加密成功，密文。失败，"NoSuchAlgorithmException".
    * @author hesheng
    * @create: 2021/2/7
    */
    public static String getEncryptedText(String text){
        byte[] enText=null;
        SecureRandom random=new SecureRandom();
        byte[] salt=new byte[SALT_LENGTH];
        random.nextBytes(salt);

        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(salt);
            md.update(text.getBytes(StandardCharsets.UTF_8));
            byte[] digest=md.digest();

//            System.out.println(digest.length);

            enText=new byte[digest.length+SALT_LENGTH];
            System.arraycopy(salt, 0, enText, 0, SALT_LENGTH);
            System.arraycopy(digest, 0, enText, SALT_LENGTH, digest.length);

//            System.out.println(Arrays.toString(enText));
        }catch (NoSuchAlgorithmException exception){
            enText="NoSuchAlgorithmException".getBytes(StandardCharsets.UTF_8);
            exception.printStackTrace();
        }
        return byteToHexString(enText);
    }

    /**
    * validText
    * <p>检验明文和数据库中的密文是否匹配。</p>
    * @param text java.lang.String. 明文。
     * @param enTextInDb java.lang.String. 数据库中的密文。
    * @return boolean 是否匹配。匹配返回true，失败或报错返回false。
    * @author hesheng
    * @create: 2021/2/7
    */
    public static boolean validText(String text, String enTextInDb){
        boolean validation=false;
        byte[] bytesEnTextInDb=hexStringToByte(enTextInDb);

        byte[] salt=new byte[SALT_LENGTH];
        System.arraycopy(bytesEnTextInDb, 0, salt,0,SALT_LENGTH);
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(salt);
            md.update(text.getBytes(StandardCharsets.UTF_8));

            byte[] digest=md.digest();
            byte[] digestInDb=new byte[bytesEnTextInDb.length-SALT_LENGTH];
            System.arraycopy(bytesEnTextInDb,SALT_LENGTH,digestInDb,0,digestInDb.length);
            validation= Arrays.equals(digest, digestInDb);

        }catch (NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }
        return validation;
    }
}


