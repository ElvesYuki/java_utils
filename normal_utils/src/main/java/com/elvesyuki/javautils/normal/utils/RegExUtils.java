package com.elvesyuki.javautils.normal.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author chenjianyu
 * @date 2022/07/07 17:00
 */
public class RegExUtils {

    private static final String REG_EX = "[`~!@#$%^&*+=|{}:;',\\[\\].<>/?！（）—【】’‘；：”“。，、？]";
    private static final String REG_EX_PATH = "[`~!@#$%^&*+=|{};',\\[\\].<>?！（）—【】’‘；：”“。，、？]";

    private RegExUtils() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * 校验字符串是否包含特殊字符
     * @param str 需要校验的字符串
     * @return boolean
     */
    public static boolean isSpecialChar(String... str){
        Pattern pattern = Pattern.compile(REG_EX);
        for (String s : str) {
            if (!s.isEmpty()){
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 校验字符串是否包含特殊字符，是则替换为_
     * @param str 需要替换的字符串
     * @return 替换后的之
     */
    public static String rePlaceSpecialChar(String... str){
        Pattern pattern = Pattern.compile(REG_EX);
        StringBuilder result = new StringBuilder();
        for (String s : str) {
            if (!s.isEmpty()){
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()){
                    result.append(s.replaceAll(REG_EX, "_"));
                } else {
                    result.append(s);
                }
            }
        }
        return result.toString();
    }

    /**
     * 校验字符串是否包含特殊字符，是则替换为_
     * @param str 需要替换的字符串
     * @return 替换后的之
     */
    public static String rePlaceSpecialCharPATH(String... str){
        Pattern pattern = Pattern.compile(REG_EX_PATH);
        StringBuilder result = new StringBuilder();
        for (String s : str) {
            if (!s.isEmpty()){
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()){
                    result.append(s.replaceAll(REG_EX, "_"));
                } else {
                    result.append(s);
                }
            }
        }
        return result.toString();
    }

}
