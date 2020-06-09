package LeetcodeAlgorithm.N0___100.N28_Implement_strStr;

public class Solution3 {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;

        int[] lps = new int[needle.length()];
        for (int j = 0, i = 1; i < needle.length(); i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) j = lps[j - 1];
            if (needle.charAt(i) == needle.charAt(j)) lps[i] = ++j;
        }

        for (int k = 0, j = 0; j < haystack.length(); j++) {
            while(k > 0 && haystack.charAt(j) != needle.charAt(k)) k = lps[k - 1];
            if (haystack.charAt(j) == needle.charAt(k)) ++k;
            if (k == needle.length())   return j - k + 1;
        }

        return -1;
    }
}
