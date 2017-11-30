package com.wz.VisoApp.model.entity;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenwuxiong on 2017/8/4.
 */
public class BaseUtil {

    /**
     * 生成32位UUID(无-)
     *
     * @return
     */
    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * MD5加盐加密
     *
     * @param password
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String MD5(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update((password + "{" + salt.toString() + "}").getBytes(Charset.forName("UTF-8")));
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        byte tmp[] = instance.digest(); // MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
            byte byte0 = tmp[i]; // 取第 i 个字节
            str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,>>> 为逻辑右移，将符号位一起右移
            str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }
        return new String(str);

    }

    /**
     * 判断全部是空
     *
     * @param strings
     * @return
     */
    public static boolean allEmpty(final String... strings) {
        boolean flag = true;
        for (String str : strings) {
            if (str != null && !str.isEmpty()) {
                flag = false;
            }
        }
        return flag;
    }


    /**
     * 存在空值判断
     *
     * @param strings
     * @return
     */
    public static boolean existEmpty(final String... strings) {
        for (String str : strings) {
            if (str == null || str.isEmpty())
                return true;
        }
        return false;
    }

    /**
     * 存在相等判断
     *
     * @param strings
     * @return
     */
    public static boolean existEquals(final String strNum, final String... strings) {
        for (String str : strings) {
            if (strNum.equals(str))
                return true;
        }
        return false;
    }

    /**
     * 格式验证
     *
     * @param value
     * @param regex
     * @return
     */
    public static Boolean Matcher(String value, String regex) {
        if (existEmpty(value, regex)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 存在空值判断
     *
     * @param objects
     * @return
     */
    public static boolean objExistEmpty(Object... objects) {
        for (Object obj : objects) {
            if (obj == null)
                return true;
        }
        return false;
    }

    /**
     * 生成订单号
     *
     * @return
     */
    public static String orderId() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        //有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return format.format(new Date()) + String.format("%015d", hashCodeV);
    }

    /**
     * 模板值替换
     *
     * @param oldStr
     * @param strList
     * @return
     */
    public static String replace(final String oldStr, final List<String> strList) {
        String str = oldStr;
        for (int i = 0; i < strList.size(); i++) {
            str = str.replace("{" + (i + 1) + "}", strList.get(i));
        }
        return str;
    }

}
