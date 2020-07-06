package 剑指offer.N58左旋转字符转;

public class Solution {
    public static String reverseLeftWords(String s, int n) {
        char[] arr = s.toCharArray();
        int len = arr.length;

        reverse(arr, 0, n - 1);
        reverse(arr, n, len - 1);
        reverse(arr, 0, len - 1);

        return String.valueOf(arr);
    }

    private static void reverse(char[] arr, int left, int right) {
        if (left == right) {
            return;
        }

        for (int i = left; i < (left + right + 1) / 2; i++) {
            char temp = arr[i];
            arr[i] = arr[right - i + left];
            arr[right - i + left] = temp;
        }

        System.out.println(String.valueOf(arr));
    }

    public static void main(String[] args) {
        System.out.println("最终："+reverseLeftWords("abcde", 2));
    }
}
