package LeetcodeAlgorithm.N0___100.N4_MedianOfTheTwoSortedArrays;
/*
*Time complexity: O(log(min(m,n))).
* At first, the searching range is [0,m].And the length of this searching range will be reduced by half after each loop.
* So, we only need log(m) loops.Since we do constant operations in each loop, so the time complexity is O(log(m))
* Since m≤n, so the time complexity is O(log(min(m,n))).
Space complexity: O(1).
We only need constant memory to store 9 local variables, so the space complexity is O(1).
 * */
public class Solution3 {
    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length, n = B.length;
        if (m > n)  return findMedianSortedArrays(B, A);

        int leftTotal = (m + n + 1) / 2;
        int iMin = 0, iMax = m; // i 在 [0, m]的取值范围
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = leftTotal - i;
            if (j != 0 && i != m && B[j - 1] > A[i]) { // i 需要增大
                iMin = i + 1;
            }else if (i != 0 && j != n && A[i - 1] > B[j]) { // i 需要减小
                iMax = i - 1;
            }else { //满足要求  或者  进入了边界的情况
                int maxLeft = 0;
                if (i == 0) maxLeft = B[j - 1];
                else if (j == 0) maxLeft = A[i - 1];
                else maxLeft = Math.max(A[i - 1], B[j - 1]);
                if ((m + n) % 2 == 1) return maxLeft;

                int minRight = 0;
                if (i == m) minRight = B[j];
                else if (j == n) minRight = A[i];
                else minRight = Math.min(A[i], B[j]);

                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0.0;
    }
}
