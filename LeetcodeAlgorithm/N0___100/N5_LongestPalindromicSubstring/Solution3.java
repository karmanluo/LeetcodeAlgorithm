package LeetcodeAlgorithm.N0___100.N5_LongestPalindromicSubstring;

/*
 * Approach 4: Expand Around Center
 *
 * In fact, we could solve it in O(n^2) time using only constant space
 * */
public class Solution3 {
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }

        char[] charArray = s.toCharArray();

        int maxLen = 0, startIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandCenter(charArray, i, i);
            int len2 = expandCenter(charArray, i, i + 1);

            int len = Math.max(len1, len2);
            if(len > maxLen){
                maxLen = len;
                startIndex = i - (len - 1) / 2;
            }
        }

        return s.substring(startIndex, startIndex + maxLen);
    }

    public int expandCenter(char[] charArr, int L, int R){
        while (L >= 0 && R < charArr.length){
            if (charArr[L] == charArr[R]){
                L--;
                R++;
            }else {
                break;
            }
        }

        return R - L - 1;
    }
}
