package LeetcodeAlgorithm.N301_400.N307RangeSumQueryMutable;

/**
 * @Author:KunmingLuo
 * @Date: 2020/5/14 13:50
 */

/**
 *  使用 BIT 树状数组的方法
 */
class NumArray {

    private int[] nums;
    private int n;
    private int[] BIT;

    public NumArray(int[] nums) {
        this.nums = nums;

        n = nums.length;
        BIT = new int[n + 1];
        for (int i = 0; i < n; i++) {
            updateBIT(i, nums[i]);
        }

    }

    private void updateBIT(int i, int addVal) {
        i++;
        while (i <= n){
            BIT[i] += addVal;
            i += (i & -i);
        }
    }

    /**
     *  求出sum(i)的和 目标数据前i项的和
     * @param i
     * @return
     */
    private int getSum(int i){
        int sum = 0;
        i++;
        while (i > 0){
            sum += BIT[i];
            i -= (i & -i);
        }

        return sum;
    }

    public void update(int i, int val) {
        int addVal = val - nums[i];
        nums[i] = val;
        updateBIT(i, addVal);
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */