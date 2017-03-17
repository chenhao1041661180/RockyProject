package com.jungle.utility.utils;

import android.annotation.SuppressLint;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static com.jungle.utility.utils.ConvertUtils.bytes2HexString;
import static com.jungle.utility.utils.ConvertUtils.hexString2Bytes;

/**
 * 功能描述：加密解密工具类
 * 创 建 人：Rocky
 * 创建时间：2017-03-10
 * 版 本 号：V1.0
 * 修 改 人：Rocky（2017-03-10）
 */
public class CryptUtils {

    // 用于加密的KEY值
    private static String DEFAULT_KEY = "YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4";

    /**
     * 构造函数
     */
    private CryptUtils() {
        throw new AssertionError();
    }

    /**
     * 对字符进行MD5加密
     *
     * @param str 被加密字符串
     * @return 加密串
     */
    public static String md5(String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 解密数据
     *
     * @param message 密文
     * @param key     解密key
     * @return 解密后文本
     */
    public static String decrypt(String message, String key) {
        String result = "";
        try {
            byte[] bytes = convertHexString(message);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("GBK"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("GBK"));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] retByte = cipher.doFinal(bytes);
            result = new String(retByte, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 不用传key的解密
     *
     * @param message 要解密的数据
     * @return 解密后文本
     */
    public static String decrypt(String message) {
        String key = md5(DEFAULT_KEY).substring(0, 8).toUpperCase();
        return decrypt(message, key);
    }

    /**
     * 加密数据
     *
     * @param message 文本
     * @param key     加密key
     * @return 加密后密文
     */
    @SuppressLint("TrulyRandom")
    public static String encrypt(String message, String key) {
        String result = "";
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("GBK"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("GBK"));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] bytes = cipher.doFinal(message.getBytes("GBK"));
            result = toHexString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 不用传key的加密
     *
     * @param message 加密文本
     * @return 加密后数据
     */
    public static String encrypt(String message) {
        String key = md5(DEFAULT_KEY).substring(0, 8).toUpperCase();
        return encrypt(message, key);
    }

    /**
     * 十六进制字符转byte
     *
     * @param value 十六进制字符
     * @return byte byte
     */
    public static byte[] convertHexString(String value) {
        byte digest[] = new byte[value.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = value.substring(2 * i, 2 * i + 2);
            int byteValue = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }
        return digest;
    }

    /**
     * byte转十六进制字符
     *
     * @param b byte
     * @return 十六进制字符
     */
    public static String toHexString(byte b[]) {
        StringBuilder hexString = new StringBuilder();
        for (byte aB : b) {
            String plainText = Integer.toHexString(0xff & aB);
            if (plainText.length() < 2)
                plainText = "0" + plainText;
            hexString.append(plainText);
        }
        return hexString.toString();
    }

    /**
     * 设置加密密钥
     *
     * @param crypt 密钥
     */
    public static void init(String crypt) {
        DEFAULT_KEY = crypt;
    }

    /*********************** 哈希加密相关 ***********************/
    /**
     * MD2加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptMD2ToString(String data) {
        return encryptMD2ToString(data.getBytes());
    }

    /**
     * MD2加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptMD2ToString(byte[] data) {
        return bytes2HexString(encryptMD2(data));
    }

    /**
     * MD2加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptMD2(byte[] data) {
        return encryptAlgorithm(data, "MD2");
    }

    /**
     * MD5加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptMD5ToString(String data) {
        return encryptMD5ToString(data.getBytes());
    }

    /**
     * MD5加密
     *
     * @param data 明文字符串
     * @param salt 盐
     * @return 16进制加盐密文
     */
    public static String encryptMD5ToString(String data, String salt) {
        return bytes2HexString(encryptMD5((data + salt).getBytes()));
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptMD5ToString(byte[] data) {
        return bytes2HexString(encryptMD5(data));
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @param salt 盐字节数组
     * @return 16进制加盐密文
     */
    public static String encryptMD5ToString(byte[] data, byte[] salt) {
        byte[] dataSalt = new byte[data.length + salt.length];
        System.arraycopy(data, 0, dataSalt, 0, data.length);
        System.arraycopy(salt, 0, dataSalt, data.length, salt.length);
        return bytes2HexString(encryptMD5(dataSalt));
    }

    /**
     * MD5加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptMD5(byte[] data) {
        return encryptAlgorithm(data, "MD5");
    }

    /**
     * SHA1加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA1ToString(String data) {
        return encryptSHA1ToString(data.getBytes());
    }

    /**
     * SHA1加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA1ToString(byte[] data) {
        return bytes2HexString(encryptSHA1(data));
    }

    /**
     * SHA1加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA1(byte[] data) {
        return encryptAlgorithm(data, "SHA-1");
    }

    /**
     * SHA224加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA224ToString(String data) {
        return encryptSHA224ToString(data.getBytes());
    }

    /**
     * SHA224加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA224ToString(byte[] data) {
        return bytes2HexString(encryptSHA224(data));
    }

    /**
     * SHA224加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA224(byte[] data) {
        return encryptAlgorithm(data, "SHA-224");
    }

    /**
     * SHA256加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA256ToString(String data) {
        return encryptSHA256ToString(data.getBytes());
    }

    /**
     * SHA256加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA256ToString(byte[] data) {
        return bytes2HexString(encryptSHA256(data));
    }

    /**
     * SHA256加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA256(byte[] data) {
        return encryptAlgorithm(data, "SHA-256");
    }

    /**
     * SHA384加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA384ToString(String data) {
        return encryptSHA384ToString(data.getBytes());
    }

    /**
     * SHA384加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA384ToString(byte[] data) {
        return bytes2HexString(encryptSHA384(data));
    }

    /**
     * SHA384加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA384(byte[] data) {
        return encryptAlgorithm(data, "SHA-384");
    }

    /**
     * SHA512加密
     *
     * @param data 明文字符串
     * @return 16进制密文
     */
    public static String encryptSHA512ToString(String data) {
        return encryptSHA512ToString(data.getBytes());
    }

    /**
     * SHA512加密
     *
     * @param data 明文字节数组
     * @return 16进制密文
     */
    public static String encryptSHA512ToString(byte[] data) {
        return bytes2HexString(encryptSHA512(data));
    }

    /**
     * SHA512加密
     *
     * @param data 明文字节数组
     * @return 密文字节数组
     */
    public static byte[] encryptSHA512(byte[] data) {
        return encryptAlgorithm(data, "SHA-512");
    }

    /**
     * 对data进行algorithm算法加密
     *
     * @param data      明文字节数组
     * @param algorithm 加密算法
     * @return 密文字节数组
     */
    private static byte[] encryptAlgorithm(byte[] data, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param filePath 文件路径
     * @return 文件的16进制密文
     */
    public static String encryptMD5File2String(String filePath) {
        return encryptMD5File2String(new File(filePath));
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param filePath 文件路径
     * @return 文件的MD5校验码
     */
    public static byte[] encryptMD5File(String filePath) {
        return encryptMD5File(new File(filePath));
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param file 文件
     * @return 文件的16进制密文
     */
    public static String encryptMD5File2String(File file) {
        return encryptMD5File(file) != null ? bytes2HexString(encryptMD5File(file)) : "";
    }

    /**
     * 获取文件的MD5校验码
     *
     * @param file 文件
     * @return 文件的MD5校验码
     */
    public static byte[] encryptMD5File(File file) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            FileChannel channel = in.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buffer);
            return md.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }
        return null;
    }

    /************************ DES加密相关 ***********************/
    /**
     * DES转变
     * <p>法算法名称/加密模式/填充方式</p>
     * <p>加密模式有：电子密码本模式ECB、加密块链模式CBC、加密反馈模式CFB、输出反馈模式OFB</p>
     * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
     */
    public static String DES_Transformation = "DES/ECB/NoPadding";
    private static final String DES_Algorithm = "DES";

    /**
     * @param data           数据
     * @param key            秘钥
     * @param algorithm      采用何种DES算法
     * @param transformation 转变
     * @param isEncrypt      是否加密
     * @return 密文或者明文，适用于DES，3DES，AES
     */
    public static byte[] DESTemplet(byte[] data, byte[] key, String algorithm, String transformation, boolean isEncrypt) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
            Cipher cipher = Cipher.getInstance(transformation);
            SecureRandom random = new SecureRandom();
            cipher.init(isEncrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, random);
            return cipher.doFinal(data);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES加密后转为Base64编码
     *
     * @param data 明文
     * @param key  8字节秘钥
     * @return Base64密文
     */
    public static byte[] encryptDES2Base64(byte[] data, byte[] key) {
        return EncodeUtils.base64Encode(encryptDES(data, key));
    }

    /**
     * DES加密后转为16进制
     *
     * @param data 明文
     * @param key  8字节秘钥
     * @return 16进制密文
     */
    public static String encryptDES2HexString(byte[] data, byte[] key) {
        return bytes2HexString(encryptDES(data, key));
    }

    /**
     * DES加密
     *
     * @param data 明文
     * @param key  8字节秘钥
     * @return 密文
     */
    public static byte[] encryptDES(byte[] data, byte[] key) {
        return DESTemplet(data, key, DES_Algorithm, DES_Transformation, true);
    }

    /**
     * DES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  8字节秘钥
     * @return 明文
     */
    public static byte[] decryptBase64DES(byte[] data, byte[] key) {
        return decryptDES(EncodeUtils.base64Decode(data), key);
    }

    /**
     * DES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  8字节秘钥
     * @return 明文
     */
    public static byte[] decryptHexStringDES(String data, byte[] key) {
        return decryptDES(hexString2Bytes(data), key);
    }

    /**
     * DES解密
     *
     * @param data 密文
     * @param key  8字节秘钥
     * @return 明文
     */
    public static byte[] decryptDES(byte[] data, byte[] key) {
        return DESTemplet(data, key, DES_Algorithm, DES_Transformation, false);
    }

    /************************ 3DES加密相关 ***********************/
    /**
     * 3DES转变
     * <p>法算法名称/加密模式/填充方式</p>
     * <p>加密模式有：电子密码本模式ECB、加密块链模式CBC、加密反馈模式CFB、输出反馈模式OFB</p>
     * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
     */
    public static String TripleDES_Transformation = "DESede/ECB/NoPadding";
    private static final String TripleDES_Algorithm = "DESede";


    /**
     * 3DES加密后转为Base64编码
     *
     * @param data 明文
     * @param key  24字节秘钥
     * @return Base64密文
     */
    public static byte[] encrypt3DES2Base64(byte[] data, byte[] key) {
        return EncodeUtils.base64Encode(encrypt3DES(data, key));
    }

    /**
     * 3DES加密后转为16进制
     *
     * @param data 明文
     * @param key  24字节秘钥
     * @return 16进制密文
     */
    public static String encrypt3DES2HexString(byte[] data, byte[] key) {
        return bytes2HexString(encrypt3DES(data, key));
    }

    /**
     * 3DES加密
     *
     * @param data 明文
     * @param key  24字节密钥
     * @return 密文
     */
    public static byte[] encrypt3DES(byte[] data, byte[] key) {
        return DESTemplet(data, key, TripleDES_Algorithm, TripleDES_Transformation, true);
    }

    /**
     * 3DES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  24字节秘钥
     * @return 明文
     */
    public static byte[] decryptBase64_3DES(byte[] data, byte[] key) {
        return decrypt3DES(EncodeUtils.base64Decode(data), key);
    }

    /**
     * 3DES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  24字节秘钥
     * @return 明文
     */
    public static byte[] decryptHexString3DES(String data, byte[] key) {
        return decrypt3DES(hexString2Bytes(data), key);
    }

    /**
     * 3DES解密
     *
     * @param data 密文
     * @param key  24字节密钥
     * @return 明文
     */
    public static byte[] decrypt3DES(byte[] data, byte[] key) {
        return DESTemplet(data, key, TripleDES_Algorithm, TripleDES_Transformation, false);
    }

    /************************ AES加密相关 ***********************/
    /**
     * AES转变
     * <p>法算法名称/加密模式/填充方式</p>
     * <p>加密模式有：电子密码本模式ECB、加密块链模式CBC、加密反馈模式CFB、输出反馈模式OFB</p>
     * <p>填充方式有：NoPadding、ZerosPadding、PKCS5Padding</p>
     */
    public static String AES_Transformation = "AES/ECB/NoPadding";
    private static final String AES_Algorithm = "AES";


    /**
     * AES加密后转为Base64编码
     *
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @return Base64密文
     */
    public static byte[] encryptAES2Base64(byte[] data, byte[] key) {
        return EncodeUtils.base64Encode(encryptAES(data, key));
    }

    /**
     * AES加密后转为16进制
     *
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @return 16进制密文
     */
    public static String encryptAES2HexString(byte[] data, byte[] key) {
        return bytes2HexString(encryptAES(data, key));
    }

    /**
     * AES加密
     *
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @return 密文
     */
    public static byte[] encryptAES(byte[] data, byte[] key) {
        return DESTemplet(data, key, AES_Algorithm, AES_Transformation, true);
    }

    /**
     * AES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  16、24、32字节秘钥
     * @return 明文
     */
    public static byte[] decryptBase64AES(byte[] data, byte[] key) {
        return decryptAES(EncodeUtils.base64Decode(data), key);
    }

    /**
     * AES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  16、24、32字节秘钥
     * @return 明文
     */
    public static byte[] decryptHexStringAES(String data, byte[] key) {
        return decryptAES(hexString2Bytes(data), key);
    }

    /**
     * AES解密
     *
     * @param data 密文
     * @param key  16、24、32字节秘钥
     * @return 明文
     */
    public static byte[] decryptAES(byte[] data, byte[] key) {
        return DESTemplet(data, key, AES_Algorithm, AES_Transformation, false);
    }
}
