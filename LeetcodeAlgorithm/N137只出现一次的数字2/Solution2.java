package LeetcodeAlgorithm.N137只出现一次的数字2;

public class Solution2 {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            int mask = 1 << i;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & mask) != 0){
                    count++;
                    count %= 3;
                }
            }
            if (count != 0){
                res |= mask;
            }
        }
        return res;
    }
}
