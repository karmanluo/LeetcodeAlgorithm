package LeetcodeAlgorithm.N401_500.N481神奇字符串;

public class Solution {
    public int magicalString(int n) {
        String str = "122";
        int k = 1;  // k 代表要追加的数字
        for (int i = 2; str.length() < n; i++) {
            for (int j = 0; j < str.charAt(i) - '0'; j++) str += k;
            k = 3 - k;
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '1') cnt++;
        }

        return cnt;
    }
}
