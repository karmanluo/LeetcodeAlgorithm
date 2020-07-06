package LeetcodeAlgorithm.N201_300.N260找出只出现一次的数字多个;

public class Solution {
    /**
     *     假设a，b是只出现一次的数
     *     异或所有数之后得到 (a xor b)
     *      (a xor b) 一定不是0，异或的特性 “相同为0，不同为1”
     *      If bit_i in (a xor b) is 1, bit_i at a and b are different.i位为1，表i位不同
     *      Find bit_i using the low bit formula m & -m      找到了a，b第一个低位不同的地方
     * Partition the numbers into two groups: one group with bit_i == 1 and the other group with bit_i == 0.
     * a is in one group and b is in the other.
     * a is the only single number in its group.
     * b is also the only single number in its group.
     * XORing all numbers in a's group to get a
     * XORing all numbers in b's group to get b
     * Alternatively, XOR (a xor b) with a gets you b.
     */
    public int[] singleNumber(int[] nums) {
        int diff = 0;

        //得到a，b异或后的值
        for (int num : nums){
            diff ^= num;
        }

        //找到a，b第一个不同值的异或位
        diff &= -diff;   //-diff == ~(diff - 1)

        int[] res = new int[]{0, 0};
        for (int num : nums){
            if ((num & diff) != 0){
                res[0] ^= num;
            }else {
                res[1] ^= num;
            }
        }
        return res;
    }
}
