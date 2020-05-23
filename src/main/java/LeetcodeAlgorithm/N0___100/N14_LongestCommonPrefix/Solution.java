package LeetcodeAlgorithm.N0___100.N14_LongestCommonPrefix;

import java.util.Scanner;

//垂直比较
public class Solution {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < minLength) {
                minLength = strs[i].length();
            }
        }
        int j = 0;
        for (; j < minLength; j++) {
            if (!isSameAtIndex(strs, j)){
                break;
            }
        }
        return strs[0].substring(0,j);
    }
    public  static boolean isSameAtIndex(String strs[], int index){
        int i = 0;
        while (i < strs.length - 1){
            if (strs[i].charAt(index) == strs[i+1].charAt(index)){
                i++;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("请输入字符串并用逗号隔开：");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] arr = str.split(",");
        System.out.println("字符串的公共头串是：" + longestCommonPrefix(arr));


    }
}
