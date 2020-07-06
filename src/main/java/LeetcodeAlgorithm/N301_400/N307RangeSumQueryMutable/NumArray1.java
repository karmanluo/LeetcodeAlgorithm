package LeetcodeAlgorithm.N301_400.N307RangeSumQueryMutable;

/**
 * @Author:KunmingLuo
 * @Date: 2020/5/14 16:38
 */
public class NumArray1 {

    private int[] nums;
    private int len;    //nums的长度
    private int[] segmentTree;

    public NumArray1(int[] nums) {
        if (nums.length == 0) return;

        this.nums = nums;
        int height = (int) Math.ceil(Math.log(nums.length) / Math.log(2));
        segmentTree = new int[2 * (int) Math.pow(2, height) - 1];
        len = nums.length;

        buildST(0, len - 1, 0);
    }

    private void buildST(int start, int end, int nodeIndex) {
        if (start == end) {
            segmentTree[nodeIndex] = nums[start];
            return;
        }

        int mid = start + ((end - start) >> 1);
        int leftNode = 2 * nodeIndex + 1;
        int rightNode = 2 * nodeIndex + 2;

        buildST(start, mid, leftNode);
        buildST(mid + 1, end, rightNode);

        segmentTree[nodeIndex] = segmentTree[leftNode] + segmentTree[rightNode];
    }


    private void updateST(int start, int end, int nodeIndex, int updateIndex, int val) {
        if (start == end) {
            nums[updateIndex] = val;
            segmentTree[nodeIndex] = val;
            return;
        }

        int mid = start + ((end - start) >> 1);
        int leftNode = nodeIndex * 2 + 1;
        int rightNode = nodeIndex * 2 + 2;
        if (updateIndex >= start && updateIndex <= mid) {
            updateST(start, mid, leftNode, updateIndex, val);
        } else {
            updateST(mid + 1, end, rightNode, updateIndex, val);
        }

        segmentTree[nodeIndex] = segmentTree[leftNode] + segmentTree[rightNode];
    }

    private int queryST(int start, int end, int nodeIndex, int L, int R) {
        if (end < L || start > R) return 0;
        else if (start == end) return segmentTree[nodeIndex];
        else if (start >= L && end <= R) return segmentTree[nodeIndex];

        int mid = start + ((end - start) >> 1);
        int leftNode = nodeIndex * 2 + 1;
        int rightNode = nodeIndex * 2 + 2;

        int sumLeft = queryST(start, mid, leftNode, L, R);
        int sumRight = queryST(mid + 1, end, rightNode, L, R);

        return sumLeft + sumRight;
    }

    public void update(int i, int val) {
        updateST(0, len -1 , 0, i, val);
    }

    public int sumRange(int i, int j) {
        return queryST(0, len - 1, 0, i, j);
    }
}
