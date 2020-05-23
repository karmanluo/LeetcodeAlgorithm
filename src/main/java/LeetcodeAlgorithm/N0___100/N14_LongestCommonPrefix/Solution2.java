package LeetcodeAlgorithm.N0___100.N14_LongestCommonPrefix;

import java.util.Scanner;

//对第一种方法的代码优化
public class Solution2 {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        //首字母按列进行比较
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {//共有strs。length个串需要去比较
                if (strs[j].length() == i || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        System.out.println("请输入字符串并用逗号隔开：");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] arr = str.split(",");
        System.out.println("字符串的公共头串是：" + longestCommonPrefix(arr));
    }
}
