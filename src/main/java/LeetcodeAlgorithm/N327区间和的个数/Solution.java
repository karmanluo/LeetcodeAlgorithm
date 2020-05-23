package LeetcodeAlgorithm.N327区间和的个数;

/**
 * @Author:KunmingLuo
 * @Date: 2020/5/14 9:11
 */
class Solution {

    class BIT{  //binary index tree
        private int[] tree;
        private int[] nums; //表示加进来的数组
        public BIT(int[] nums){
            this.nums = nums;
            int sum = 0;
            int lowBit;

            tree = new int[nums.length + 1];
            //生成BIT
            for (int i = 1; i < tree.length; i++) {
                sum = 0;
                lowBit = i & (~i + 1);
                for (int j = i; j > i - lowBit; j++) {

                }
            }
        }
    }


    //Binary Indexed Tree 树状数组
    //Divide and Conquer based and Binary Indexed Tree based solutions
    public int countRangeSum(int[] nums, int lower, int upper) {

    }
}