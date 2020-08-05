package LeetcodeAlgorithm.N301_400.N307RangeSumQueryMutable;

class NumArray3 {
    int[] tree;
    int n;

    public NumArray3(int[] nums) {
        if (nums.length > 0) {
            tree = new int[nums.length * 2];
            n = nums.length;
            for (int i = n, j = 0; i < 2 * n; ++i, ++j) {
                tree[i] = nums[j];
            }
            for (int i = nums.length - 1; i > 0; --i) {
                tree[i] = tree[2 * i] + tree[2 * i + 1];
            }
        }
    }

    public void update(int i, int val) {
        int pos = n + i;
        tree[pos] = val;
        while (pos > 0) {
            int left = pos, right = pos;
            if (pos % 2 == 0) {
                right = pos + 1;
            } else if (pos % 2 == 1)
                left = pos - 1;
            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    public int sumRange(int i, int j) {
        int l = n + i, r = j + n;
        int sum = 0;
        while (l <= r) {
            if (l % 2 == 1) {
                sum += tree[l];
                l++;
            }
            if (r % 2 == 0) {
                sum += tree[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }
}