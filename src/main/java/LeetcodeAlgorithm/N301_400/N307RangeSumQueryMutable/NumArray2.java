package LeetcodeAlgorithm.N301_400.N307RangeSumQueryMutable;

class NumArray2 {
    private BinaryIndexedTree bit;
    private int[] nums;

    public NumArray2(int[] nums) {
        this.nums = nums;
        bit = new BinaryIndexedTree(nums);
        for (int i = 0; i < nums.length; i++) bit.update(i, nums[i]);
    }

    public void update(int i, int val) {
        bit.update(i, val - nums[i]);
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        return bit.query(j) - bit.query(i - 1);
    }

    //BinaryIndexedTree Code
    class BinaryIndexedTree {
        private int[] arr;
        private int n;

        public BinaryIndexedTree(int[] nums) {
            int len = nums.length;
            n = len + 1;
            arr = new int[n];
        }

        public void update(int i, int val) {
            i++;
            while (i < n) {
                arr[i] += val;
                i += (i & - i);
            }
        }

        public int query(int i) {
            i++;
            int sum = 0;
            while (i > 0) {
                sum += arr[i];
                i -= (i & -i);
            }

            return sum;
        }

    }
}