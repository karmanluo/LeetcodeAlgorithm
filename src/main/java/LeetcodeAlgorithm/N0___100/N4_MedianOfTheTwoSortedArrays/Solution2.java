package LeetcodeAlgorithm.N0___100.N4_MedianOfTheTwoSortedArrays;

public class Solution2 {
/*
* 其实，我们不需要将两个数组真的合并，我们只需要找到中位数在哪里就可以了。

开始的思路是写一个循环，然后里边判断是否到了中位数的位置，到了就返回结果，但这里对偶数和奇数的分类会很麻烦。当其中一个数
* 组遍历完后，出了 for 循环对边界的判断也会分几种情况。总体来说，虽然复杂度不影响，但代码会看起来很乱。然后在 这里 找到
* 了另一种思路。

首先是怎么将奇数和偶数的情况合并一下。

用 len 表示合并后数组的长度，如果是奇数，我们需要知道第 （len + 1）/ 2 个数就可以了，如果遍历的话需要遍历
* int ( len / 2 ) + 1 次。如果是偶数，我们需要知道第 len / 2 和 len / 2 + 1 个数，也是需要遍历 len / 2 + 1 次。
* 所以遍历的话，奇数和偶数都是 len / 2 + 1 次。
返回中位数的话，奇数需要最后一次遍历的结果就可以了，偶数需要最后一次和上一次遍历的结果。所以我们用两个变量 left 和
*  right ，right 保存当前循环的结果，在每次循环前将 right 的值赋给 left 。这样在最后一次循环的时候，left 将得到
*  right 的值，也就是上一次循环的结果，接下来 right 更新为最后一次的结果。
循环中该怎么写，什么时候 A 数组后移，什么时候 B 数组后移。用 aStart 和 bStart 分别表示当前指向 A 数组和 B 数组的位置。
* 如果 aStart 还没有到最后并且此时 A 位置的数字小于 B 位置的数组，那么就可以后移了。
* 也就是aStart ＜ m && A[aStart] < B[bStart]。
但如果 B 数组此刻已经没有数字了，继续取数字B [ bStart ]，则会越界，所以判断下 bStart 是否大于数组长度了，
* 这样 || 后边的就不会执行了，也就不会导致错误了，所以增加为 aStart ＜ m && ( bStart >= n || A [ aStart ] < B [ bStart ] ) 。
* */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])) {
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }
    public static void main(String[] args) {
        int[] nums1 = {1,3, 5, 17};
        int[] nums2 = {2,4, 45};
        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println("结果为："+ res);
    }
}
