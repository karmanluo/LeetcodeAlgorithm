package LeetcodeAlgorithm.N0___100.N76_MinimumWindowSubstring;

public class Solution2 {
    public String minWindow(String s, String t) {
        //winFreq   表示S的字串词频数组  会变化
        //tFreq     表示T词频数组
        //distance  表示滑动窗口内部包含T中的字符的个数
        int sLen = s.length();
        int tLen = t.length();

        if (sLen == 0 || tLen == 0 || sLen < tLen) return "";

        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        //ascii('z') = 122
        int[] winFreq = new int[128];
        int[] tFreq = new int[128];

        for (char c : charArrayT) {
            tFreq[c]++;
        }

        //滑动窗口内部包含多少T中的字符，对应字符频数超过不重复计算
        int count = 0;
        int minLen = sLen + 1;
        int begin = 0;

        int left = 0;
        int right = 0;
        // [left, right)
        while (right < sLen) {
            char r = charArrayS[right];
            if (tFreq[r] == 0) {    //当前字符在t中没有出现过，右移
                right++;
                continue;   //不执行后面的逻辑, 直接开始下一次
            }

            if (winFreq[r] < tFreq[r]){
                count++;
            }
            winFreq[r]++;
            right++;

            while (count == tLen){

                if (right - left < minLen){
                     minLen = right - left;
                     begin = left;
                }

                char l = charArrayS[left];
                if (tFreq[l] == 0){
                    left++;
                    continue;
                }

                //执行这里相当于left在里面
                if (winFreq[l] == tFreq[l]){
                    count--;
                }
                winFreq[l]--;
                left++;
            }
        }

        if (minLen == sLen + 1) return "";

        return s.substring(begin, begin + minLen);
    }
}
