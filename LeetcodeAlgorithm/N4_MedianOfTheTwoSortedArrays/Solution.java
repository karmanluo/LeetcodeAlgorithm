//There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
// Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
// You may assume nums1 and nums2 cannot be both empty.
//
// Example 1:
//
//
//nums1 = [1, 3]
//nums2 = [2]
//
//The median is 2.0
//
//
// Example 2:
//
//
//nums1 = [1, 2]
//nums2 = [3, 4]
//
//The median is (2 + 3)/2 = 2.5
//
//
package LeetcodeAlgorithm.N4_MedianOfTheTwoSortedArrays;
/*
* 时间复杂度：遍历全部数组，O（m + n）
* 空间复杂度：开辟了一个数组，保存合并后的两个数组，O（m + n）
*/
public class Solution {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        if (m == 0) {
            if (n % 2 == 0) {
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            } else {

                return nums2[n / 2];
            }
        }
        if (n == 0) {
            if (m % 2 == 0) {
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            } else {
                return nums1[m / 2];
            }
        }

        int count = 0;
        int i = 0, j = 0;
        while (count != (m + n)) {
            if (i == m) {
                while (j != n) {
                    nums[count++] = nums2[j++];
                }
                break;
            }
            if (j == n) {
                while (i != m) {
                    nums[count++] = nums1[i++];
                }
                break;
            }

            if (nums1[i] < nums2[j]) {
                nums[count++] = nums1[i++];
            } else {
                nums[count++] = nums2[j++];
            }
        }

        if (count % 2 == 0) {
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        } else {
            return nums[count / 2];
        }
    }



    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2,4};
        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println("结果为："+ res);
    }
}
