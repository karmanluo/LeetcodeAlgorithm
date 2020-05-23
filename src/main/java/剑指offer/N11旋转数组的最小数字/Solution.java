package 剑指offer.N11旋转数组的最小数字;
/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) return 0;

        int n = numbers.length;
        return minArray(numbers, 0, n - 1);
    }

    private int minArray(int[] numbers, int left, int right) {
        if (left == right) return numbers[left];
        int mid = left + ((right - left) >> 1);//注意一下位运算的优先级低于 +

        if (numbers[mid] == numbers[right])
            return minArray(numbers, left, right - 1);
        else if (numbers[mid] > numbers[right])
            return minArray(numbers, mid + 1, right);
        else
            return minArray(numbers, left, mid);
    }
}