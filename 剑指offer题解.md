
[TOC]
# 剑指offer题解

#### [面试题03. 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

找出数组中重复的数字。


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：

```
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 
```


限制：

```
2 <= n <= 100000
```



```java
//方法一：很容易想到先排序然后比较相邻两个数的方法，时间复杂度得0(nlgn)

//方法二：数字都在0~n-1的范围，利用数组本身的空间长度即可
//空间复杂度o(n)
public class Solution{
    public int findRepeatNumber(int[] nums) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
            if (arr[nums[i]] > 1) return nums[i];
        }
        return -1;
    }
}
```

#### [面试题22. 链表中倒数第k个节点(快慢指针)](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。

示例：

```
给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
```

```java
//快慢指针，先让快指针走k步，然后两个指针同步走，当快指针走到头时，慢指针就是链表倒数第k个节点。
public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode frontNode = head, behindNode = head;
    
        while (frontNode != null && k > 0){
            frontNode = frontNode.next;
            k--;
        }
    
        if (k > 0) return null;
    
        while (frontNode != null){
            frontNode = frontNode.next;
            behindNode = behindNode.next;
        }
    
        return behindNode;
    }
```

#### [面试题26. 树的子结构](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/)

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

例如:
给定的树 A:

    	 3
    	/ \
       4   5
      / \
     1   2
    给定的树 B：
    
       4 
      /
     1
    返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
示例 1：

```
输入：A = [1,2,3], B = [3,1]
输出：false
```

示例 2：

```
输入：A = [3,4,5,1,2], B = [4,1]
输出：true
```

限制：

0 <= 节点个数 <= 10000

```java
//这道题存在问题的地方：A和B中默认没有重复的数字
class Solution {
    //思路，找到和B根节点值相同的位置
   public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;

        if (A != null && B != null && A.val == B.val){
            //helper用来判断两个树是否相等
            return helper(A, B);
        }

        if (isSubStructure(A.left, B))  return true;
        if (isSubStructure(A.right, B)) return true;

        return false;   
    }

    private boolean helper(TreeNode a, TreeNode b) {
        if (b == null) return true;

        if (a != null && b!= null && a.val != b.val)
            return false;
        if (a != null && b!= null && a.val == b.val)
            return helper(a.left, b.left) && helper(a.right, b.right);
        return false;
    }
}
```

#### [面试题28. 对称的二叉树](https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/)

请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    	1
       / \
      2   2
     / \ / \
    3  4 4  3

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    	1
       / \
      2   2
       \   \
       3    3
示例 1：

```
输入：root = [1,2,2,3,4,4,3]
输出：true
```

示例 2：

```
输入：root = [1,2,2,null,3,null,3]
输出：false
```

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)   return true;

        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        
        if (root1 == null || root2 == null) return false;
        
        return root1.val == root2.val && helper(root1.left, root2.right) && helper(root1.right, root2.left);
    }
}
```

#### [面试题29. 顺时针打印矩阵](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
示例 1：
```
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
```
示例 2：
```
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
```
限制：

```
0 <= matrix.length <= 100
0 <= matrix[i].length <= 100
```



```java
public class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];

        int total = matrix.length * matrix[0].length;
        int[] res = new int[total];
        int index = 0;
        int up = 0, down = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (true){
            for (int i = left; i <= right; i++) res[index++] = matrix[up][i];
            if (++up > down) break;
            for (int i = up; i <= down; i++) res[index++] = matrix[i][right];
            if (--right < left) break;
            for (int i = right; i >= left; i--) res[index++] = matrix[down][i];
            if (--down < up) break;
            for (int i = down; i >= up; i--) res[index++] = matrix[i][left];
            if (++left > right) break;
        }
        return res;
    }
}
```







#### [面试题31. 栈的压入、弹出序列](https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/)

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

**示例 1：**

```
输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
```

**示例 2：**

```
输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
输出：false
解释：1 不能在 2 之前弹出。
```



```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) return false;
        if (pushed.length == 0) return true;

        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && popped[index] == stack.peek()){
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }
}
```



# Leetcode 重点题目

#### [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

示例 1:

```
输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]
```


示例 2:

```
输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]


```



```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        if (matrix.length == 0) return list;

        int m = matrix.length;
        int n = matrix[0].length;
        int up = 0, down = m - 1, left = 0, right = n - 1;
        //记住下面这段，很关键，，，如何遍历
        while (true) {
            for (int i = left; i <= right; i++)
                list.add(matrix[up][i]);
            if (++up > down) break;
            for (int j = up; j <= down; j++)
                list.add(matrix[j][right]);
            if (--right < left) break;
            for (int k = right; k >= left; k--)
                list.add(matrix[down][k]);
            if (--down < up) break;
            for (int l = down; l >= up; l--)
                list.add(matrix[l][left]);
            if (++left > right) break;
        }
        return list;
    }
}
```







#### [56. 合并区间（字节跳动）](https://leetcode-cn.com/problems/merge-intervals/)

给出一个区间的集合，请合并所有重叠的区间。

**示例 1:**

```
输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
```

**示例 2:**

```
输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
```

```java
/**
	1.将每一个区间按照第一个字母来排序
	2.排序后两个区间比较的时候，后一个区间的第一个元素>第一个区间的第二个元素-----表示不需要区间合并
						   后一个区间的第一个元素<=第一个区间的第二个元素----表示需要区间合并
*/

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)  return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return  a[0] < b[0] ? -1 : a[0] == b[0] ? 0 : 1;
            }
        });

        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] interval :intervals) {
            if (interval[0] <= newInterval[1]){
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
            else{
                newInterval = interval;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
```

#### [128. 最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/)

给定一个未排序的整数数组，找出最长连续序列的长度。

要求算法的**时间复杂度为 O(n)。**

示例:

```
输入: [100, 4, 200, 1, 3, 2]
输出: 4
解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
```

```java
public class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums){
            if (!map.containsKey(num)){
                int left = (map.containsKey(num - 1)) ? map.get(num - 1) : 0;
                int right = (map.containsKey(num + 1)) ? map.get(num + 1) : 0;
                //sum表示num所在序列的长度
                int sum = left + right + 1;
                map.put(num, sum);

                res = Math.max(res, sum);

                //将num数所对应的边界值的  长度  也进行更新
                //如果没有左右边界，不会产生影响
                map.put(num - left, sum);
                map.put(num + right, sum);
            }else {
                continue;
            }
        }
        return res;
    }
}
```

#### [169. 多数元素](https://leetcode-cn.com/problems/majority-element/)

给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1:

```
输入: [3,2,3]
输出: 3
```


示例 2:

```
输入: [2,2,1,1,1,2,2]
输出: 2
```

```java
public class Solution {
    public int majorityElement(int[] nums) {
        int count = 1;
        int major = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                major = nums[i];
            } else if (major == nums[i]) {
                count++;
            } else count--;

        }
        return major;
    }
}
```







#### [189. 旋转数组](https://leetcode-cn.com/problems/rotate-array/)

给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

示例 1:

```
输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
```


示例 2:

```
输入: [-1,-100,3,99] 和 k = 2
输出: [3,99,-1,-100]
解释: 
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
```

**说明:**

- 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
- 要求使用空间复杂度为 O(1) 的 **原地** 算法。

```java
public class Solution {
    public void rotate(int[] nums, int k) {
        if (k % nums.length == 0) return;
        int r = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, r - 1);
        reverse(nums, r, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        for (int i = 0; i < (end - start + 1) >>> 1; i++) {
            int tmp = nums[start + i];
            nums[start + i] = nums[end - i];
            nums[end - i] = tmp;
        }
    }
}
```







#### [199. 二叉树的右视图（字节跳动）](https://leetcode-cn.com/problems/binary-tree-right-side-view/)

给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

**示例:**

```
输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

**解题思路**

```
这道题解法，最开始想的也是层序遍历，不多说。 想了下用递归来做，递归最大深度加1的时候就将结果添加到集合中，这里递归必须先右后左。
```



```java
//递归
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root, 0);
        return res;
    }

    private void helper(List<Integer> res, TreeNode curr, int currDepth) {
        if (curr == null) return;

        if (currDepth == res.size())//很关键
            res.add(curr.val);

        helper(res, curr.right, currDepth + 1);
        helper(res, curr.left, currDepth + 1);
    }
}
-----------------------------------------------------------------------
//非递归
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        //层次遍历的思想
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1){
                    res.add(node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return res;
    }
}
```



#### [263. 丑数](https://leetcode-cn.com/problems/ugly-number/)

编写一个程序判断给定的数是否为丑数。

丑数就是只包含质因数 2, 3, 5 的正整数。**1 是丑数。**

示例 1:

```
输入: 6
输出: true
解释: 6 = 2 × 3
```


示例 2:

```
输入: 8
输出: true
解释: 8 = 2 × 2 × 2
```


示例 3:

```
输入: 14
输出: false 
解释: 14 不是丑数，因为它包含了另外一个质因数 7。
```


说明：

- **1 是丑数。**
-  输入不会超过 32 位有符号整数的范围: [−231,  231 − 1]。



```JAVA
class Solution {
    public boolean isUgly(int num) {
        if (num<1) return false;
        while (num%5==0){
            num/=5;
        }
        while (num%3==0){
            num/=3;
        }
        while (num%2==0){
            num>>=1;
        }
        return num == 1;
    }
}
```



#### [264. 丑数 II(三指针)](https://leetcode-cn.com/problems/ugly-number-ii/)

编写一个程序，找出第 `n` 个丑数。

丑数就是只包含质因数 `2, 3, 5` 的**正整数**。

示例:

```
输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
```


说明:  

- 1 是丑数。
- n 不超过1690。

```java
//方法一：暴力法一个个判断，利用上一题目
//方法二：三指针
public class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Math.min(factor2, Math.min(factor3, factor5));
            ugly[i] = min;
            if (min == factor2)
                factor2 = 2 * ugly[++index2];
            if (min == factor3)
                factor3 = 3 * ugly[++index3];
            if (min == factor5)
                factor5 = 5 * ugly[++index5];
        }
        return ugly[n - 1];
    }
}
```





#### [442. 数组中重复的数据](https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/)

给定一个整数数组 a，其中1 ≤ a[i] ≤ *n* （*n*为数组长度）, 其中有些元素出现**两次**而其他元素出现**一次**。

找到所有出现**两次**的元素。

你可以不用到任何额外空间并在O(*n*)时间复杂度内解决这个问题吗？

**示例：**

```
输入:
[4,3,2,7,8,2,3,1]

输出:
[2,3]
```



```java
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null) return null;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0){
                nums[index] = - nums[index];
            }else{
                res.add(Math.abs(nums[i]));
            }
        }
        return res;
    }
}
```



#### [387. 字符串中的第一个唯一字符](https://leetcode-cn.com/problems/first-unique-character-in-a-string/)

给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

案例:

```
s = "leetcode"
返回 0.
```

```
s = "loveleetcode",
返回 2.
```


注意事项：您可以假定该字符串只包含小写字母。

```java
public class Solution {
    public int firstUniqChar(String s) {
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
}
```

```java
public class Solution2 {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0)
            return -1;

        Map<Character, Integer> map = new HashMap();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
```

ASCII可显示字符：ASCII码对应的十进制从32-126，说明都在256之内。





# LintCode相关题目

#### [144.交错正负数](https://www.lintcode.com/problem/interleaving-positive-and-negative-numbers/description)

给出一个含有正整数和负整数的数组，重新排列成一个正负数交错的数组。

不需要保持正整数或者负整数原来的顺序。



**样例**

```
输入 : [-1, -2, -3, 4, 5, 6]
输出 : [-1, 5, -2, 4, -3, 6]
解释 : 或者仍和满足条件的答案 
```

**挑战**

完成题目，且不消耗额外的空间。

```java
public class Solution {
    /**
     * 解题思路：
     * 首先将正数移到左边，左边正数多则和负数交换（数据reverse一下）
     * 然后将指针后移即可
     */
    public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length < 3) return;

        int n = A.length;
        int countPositive = 0;
        int posIndex = 0;

        int left = 1;
        int right = 0;
        //将正数移到左边
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                swap(A, i, posIndex);
                posIndex++;
                countPositive++;
            }
        }
        //reverse一下
        if (countPositive > n / 2) {
            left = 0;
            right = 1;
            int leftIndex = 0;
            int rightIndex = A.length - 1;
            while (leftIndex < rightIndex) {
                swap(A, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }
		//正负交错的一个交换过程
        while (left < n && right < n) {
            while (left < n && A[left] > 0) left += 2;
            while (right < n && A[right] < 0) right += 2;
            if (left >= n || right >= n) break;
            swap(A, left, right);
        }
    }

    private void swap(int[] a, int i, int posIndex) {
        int tmp = a[i];
        a[i] = a[posIndex];
        a[posIndex] = tmp;
    }
}
```



#### [138.子数组之和](https://www.lintcode.com/problem/subarray-sum/description)

给定一个整数数组，找到和为零的子数组。你的代码应该返回满足要求的子数组的**起始位置**和**结束位置**，**至少有一个子数组的和为 0**

**样例 1:**

```
输入: [-3, 1, 2, -3, 4]
输出: [0,2] 或 [1,3]	
样例解释： 返回任意一段和为0的区间即可。
```

**样例 2:**

```
输入: [-3, 1, -4, 2, -3, 4]
输出: [1,5]
```



```java
public class Solution {
    //利用了 Prefix Sum的思想
    
    //解题思路：用hashmap存历史sum的和以及对应的index
    //当出现的sum值出现在hashmap中的时候，说明存在子序列导致sum没变
    //也就是说存在了和为 0 的子序列

    //思路延申，也可以求和为 x 的子序列
    public List<Integer> subarraySum(int[] nums) {
        //拿来存中间的sum值以及对应的index
        HashMap<Integer, Integer> map = new HashMap<>();
        //存结果的index
        List<Integer> res = new ArrayList<>();

        //初始化map
        map.put(0, -1);
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            //表示sum在之前的map记录过，表示找到了子序列为0的结果
            if (map.containsKey(sum)){
                res.add(map.get(sum) + 1);
                res.add(i);
            }

            map.put(sum, i);
        }
        return res;
    }
}
```



#### [139. 最接近零的子数组和（难）](https://www.lintcode.com/problem/subarray-sum-closest/description)

给定一个整数数组，找到一个和最接近于零的子数组。返回第一个和最右一个指数。你的代码应该返回满足要求的子数组的起始位置和结束位置

**样例1**

```
输入: 
[-3,1,1,-3,5] 
输出: 
[0,2]
解释: [0,2], [1,3], [1,1], [2,2], [0,4]
```

要求：O(nlogn)的时间复杂度

注意事项：数据保证任意数的和都在[-2^{31},2^{31}-1][−231,231−1]范围内

```java
//明确思路
class Pair {
    int sum;
    int index;
    public Pair(int s, int i) {
        sum = s;
        index = i;
    }
}

public class Solution {
    public int[] subarraySumClosest(int[] nums) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }

        int len = nums.length;
        if(len == 1) {
            res[0] = res[1] = 0;
            return res;
        }
        //定义一个sum数组，每个对象里面存了目前的和以及对应的i
        Pair[] sums = new Pair[len+1];
        int prev = 0;
        sums[0] = new Pair(0, 0);
        //这里也可以用hashmap和上一道题一样的方式来存，分别存 和 与 i
        for (int i = 1; i <= len; i++) {
            sums[i] = new Pair(prev + nums[i-1], i);
            prev = sums[i].sum;
        }
        //HashMap不能做这个事情，对其进行排序
        //这里排序之后得到sums，从小到大的顺序排列
        Arrays.sort(sums, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return a.sum - b.sum;
            }
        });
        int ans = Integer.MAX_VALUE;

        //从头到尾遍历，比较相邻之差找最小，找到最小的之和再访问原先记录的index
        for (int i = 1; i <= len; i++) {
            if (ans > sums[i].sum - sums[i-1].sum) {
                ans = sums[i].sum - sums[i-1].sum;
                int[] temp = new int[]{sums[i].index - 1, sums[i - 1].index - 1};
                Arrays.sort(temp);
                res[0] = temp[0] + 1;
                res[1] = temp[1];
            }
        }

        return res;
    }
}
```







# 排序相关题目

#### [463.Sort Integers（冒泡排序、选择排序、插入排序）](https://www.lintcode.com/problem/sort-integers/description)

给一组整数，按照升序排序，使用选择排序，冒泡排序，插入排序或者任何 O(n2) 的排序算法。

```
样例  1:
	输入:  [3, 2, 1, 4, 5]
	输出:  [1, 2, 3, 4, 5]
	
	样例解释: 
	返回排序后的数组。

样例 2:
	输入:  [1, 1, 2, 1, 1]
	输出:  [1, 1, 1, 1, 2]
	
	样例解释: 
	返回排好序的数组。
```

```java
public class Solution {
    //冒泡排序
    public void sortIntegers(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = 0; j < A.length - 1 - i; j++) {
                if (A[j] > A[j + 1]){
                    int tmp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = tmp;
                }
            }
        }
    }
}

public class Solution2 {
    //插入排序
    public void sortIntegers(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int currNumber = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > currNumber) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = currNumber;
        }
    }
}

public class Solution {
    //选择排序
    public void sortIntegers(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int tmp = nums[i];
            int flag = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < tmp) {
                    tmp = nums[j];
                    flag = j;
                }
            }
            nums[flag] = nums[i];
            nums[i] = tmp;
        }
    }
}
```





#### [464. Sort Integers II（快速排序、归并排序、堆排序）](https://www.lintcode.com/problem/sort-integers-ii/description)

给一组整数，请将其在原地按照升序排序。使用归并排序，快速排序，堆排序或者任何其他 O(*n* log *n*) 的排序算法。

**例1：**

```
输入：[3,2,1,4,5]，
输出：[1,2,3,4,5]。
```

**例2：**

```
输入：[2,3,1]，
输出：[1,2,3]。
```

```java
public class Solution{
    //快速排序----记住这种就可以了----简单又精髓---
    public void sortIntegers2(int[] A) {
        quickSort( A, 0, A.length - 1);
    }

    private void quickSort(int[] A, int start, int end) {
        if (start >= end) return;

        int pivotIndex = partition(A, start, end);
        quickSort(A, start, pivotIndex - 1);
        quickSort(A, pivotIndex + 1, end);
    }

    private int partition(int[] A, int start, int end) {
        int pivotIndex = start+ ((end-start)>>>1); 
        int pivot = A[pivotIndex];//其他位置也可以

        //将pivot移到最后
        swap(A, pivotIndex, end);
        //用于标记最后改交换到的位置
        int finalIndex = start;
        //将小于的元素放到左边
        for (int i = start; i < end; i++) {
            if (A[i] < pivot){
                swap(A, i, finalIndex);
                finalIndex++;
            }
        }
        //最后交换到最终位置
        swap(A, finalIndex, end);
        return finalIndex;
    }

    public void swap(int[] nums,int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
```



[堆排序参考链接，博客详细原文](//https://blog.csdn.net/zdp072/article/details/44227317)

```java
public class Solution {
    //归并排序
    public void sortIntegers2(int[] A) {
        mergeSort(A, 0, A.length - 1);
    }

    private void mergeSort(int[] nums, int low, int high) {
        int mid = low + ((high - low) >>> 1);

        if (low < high) {
            mergeSort(nums, low, mid);
            mergeSort(nums, mid + 1, high);
            //合并
            merge(nums, low, mid, high);
        }
    }

    //merge函数实际上是将两个有序数组合并成一个有序数组
    //因为数组有序，合并很简单，只要维护几个指针就可以了
    private void merge(int[] arr, int low, int mid, int high) {
        //temp数组用于暂存合并的结果
        int[] temp = new int[high - low + 1];
        //左半边的指针
        int i = low;
        //右半边的指针
        int j = mid+1;
        //合并后数组的指针
        int k = 0;

        //将记录由小到大地放进temp数组
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        //接下来两个while循环是为了将剩余的（比另一边多出来的个数）放到temp数组中
        while(i <= mid)
            temp[k++] = arr[i++];

        while(j <= high)
            temp[k++] = arr[j++];

        //将temp数组中的元素写入到待排数组中
        for(int i = 0; i < temp.length; i++)
            arr[low + i] = temp[i];

    }
}
```

```java
public class Solution3 {
    //堆排序
    public void heapSort(int[] arr) {
        //将待排序的序列排成一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            heapAdjust(arr, i, arr.length);//调整的节点i以后的树的变化
        }
        // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0); // 将堆顶记录和当前未经排序子序列的最后一个记录交换
            heapAdjust(arr, 0, i);
        }
    }

    private void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    private void heapAdjust(int[] arr, int i, int n) {
        int father;
        int childIndex;
        for (father = arr[i]; leftChild(i) < n; i = childIndex){
            childIndex = leftChild(i);
            if (childIndex != n - 1 && arr[childIndex] < arr[childIndex + 1]){
                childIndex++;
            }
            if (arr[childIndex] > arr[i]){
                arr[i] = arr[childIndex];
            }else {
                break;
            }
            arr[childIndex] = father;
        }
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }
}
```





#### [148. 排序链表](https://leetcode-cn.com/problems/sort-list/)

在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:

```
输入: 4->2->1->3
输出: 1->2->3->4
```


示例 2:

```
输入: -1->5->3->4->0
输出: -1->0->3->4->5
```



```java
//方法1 归并排序
public class Solution {
    public ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null)   return head;

        // step 1. cut the list to two halves
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode list2 = slow.next;
        slow.next = null;

        // step 2. sort each half
        ListNode l1 = mergeSort(head);
        ListNode l2 = mergeSort(list2);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                node.next = list1;
                list1 = list1.next;
            }else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        if (list1 != null) node.next = list1;
        if (list2 != null) node.next = list2;
        return dummy.next;
    }
}
```



```java
//方法二  快速排序
public class Solution2 {
    public ListNode quickSort(ListNode h) {
        if (h == null || h.next == null)
            return h;

        /*split into three list*/
        ListNode fakesmall = new ListNode(0), small = fakesmall;
        ListNode fakeequal = new ListNode(0), equal = fakeequal;
        ListNode fakelarge = new ListNode(0), large = fakelarge;

        ListNode cur = h; // pivot is h.
        while (cur != null) {
            if (cur.val < h.val) {
                small.next = cur;
                small = small.next;
            } else if (cur.val == h.val){
                equal.next = cur;
                equal = equal.next;
            }
            else{
                large.next = cur;
                large = large.next;
            }
            cur = cur.next;
        }

        // put an end.
        small.next = equal.next = large.next = null;
        // merge them and return . merge reusing below one. merge for quicksort should be simplified.
        return merge((merge(quickSort(fakesmall.next), quickSort(fakelarge.next))), fakeequal.next);
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        if (list1 != null) node.next = list1;
        if (list2 != null) node.next = list2;
        return dummy.next;
    }
}
```





#### [215. 数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)

在未排序的数组中找到第 **k** 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```


示例 2:

```
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```



```java
//调用函数
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
```

```java
//优先队列
public class Solution2 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int val : nums) {
            priorityQueue.offer(val);
            if (priorityQueue.size() > k)
                priorityQueue.poll();
        }
        return priorityQueue.peek();
    }
}
```

```java
//快速排序
class Solution3 {
    public int findKthLargest(int[] nums, int k) {
        int size = nums.length;
        // kth largest is indexed (N - k)th smallest
        return quickselect(nums ,0, size - 1, size - k);
    }

    public int quickselect(int[] nums, int left, int right, int destPos) {
        //destPos只做判断是否相等来用
        if (left == right)
            return nums[left];
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);

        pivot_index = partition(nums, left, right, pivot_index);

        // the pivot is on (N - k)th smallest position
        if (destPos == pivot_index)
            return nums[destPos];
            // go left side
        else if (destPos < pivot_index)
            return quickselect(nums, left, pivot_index - 1, destPos);
        // go right side
        return quickselect(nums, pivot_index + 1, right, destPos);
    }

    public void swap(int[] nums,int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public int partition(int[] nums, int left, int right, int pivot_index) {
        int pivot = nums[pivot_index];
        // 1. move pivot to end
        swap(nums ,pivot_index, right);
        int store_index = left;
        // 2. move all smaller elements to the left
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums,store_index, i);
                store_index++;
            }
        }
        // 3. move pivot to its final place
        swap(nums ,store_index, right);

        return store_index;
    }
}
```







#### [347. 前 K 个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/)

给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

示例 1:

```
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
```


示例 2:

```
输入: nums = [1], k = 1
输出: [1]
```

说明：

- `你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。`
- `你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。`



```java
//桶排序
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequentMap = new HashMap<>();

        for (int n : nums){
            frequentMap.put(n, frequentMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequentMap.keySet()){
            int frequency = frequentMap.get(key);
            if (bucket[frequency] == null){
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null){
                for (int i = 0; i < bucket[pos].size() && res.size() < k; i++) {
                    res.add(bucket[pos].get(i));
                }
            }
        }
        return res;
    }
}
```



```java
//使用了heap的思想
public class Solution2 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> frequentMap = new HashMap<>();
        for (int n : nums) {
            frequentMap.put(n, frequentMap.getOrDefault(n, 0) + 1);
        }
        //小根堆好
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        for (Map.Entry<Integer, Integer> entry : frequentMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) minHeap.poll();
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            res.add(minHeap.poll().getKey());
        }
        return res;
    }
}
```







#### [532逆序对](https://www.lintcode.com/problem/reverse-pairs/description)

在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。给你一个数组，求出这个数组中逆序对的总数。
概括：如果a[i] > a[j] 且 i < j， a[i] 和 a[j] 构成一个逆序对。

**样例1**

```
输入: A = [2, 4, 1, 3, 5]
输出: 3
解释:
(2, 1), (4, 1), (4, 3) 是逆序对
```

**样例2**

```
输入: A = [1, 2, 3, 4]
输出: 0
解释:
没有逆序对
```



```java
//利用归并排序的思想
public class Solution {
    public long reversePairs(int[] A) {
        return mergeSort(A, 0, A.length - 1);
    }

    private long mergeSort(int[] A, int low, int high) {
        if (low >= high) return 0;

        int mid = low + ((high - low) >>> 1);
        int sum = 0;
        sum += mergeSort(A, low, mid);
        sum += mergeSort(A, mid + 1, high);
        sum += merge(A, low, mid, high);
        return sum;
    }

    private int merge(int[] A, int low, int mid, int high) {
        int[] tmp = new int[high - low + 1];
        int leftIndex = low;
        int rightIndex = mid + 1;
        int index = 0;
        int sum = 0;

        while (leftIndex <= mid && rightIndex <= high) {
            if (A[leftIndex] <= A[rightIndex]) {
                tmp[index++] = A[leftIndex++];
            } else {
                tmp[index++] = A[rightIndex++];
                sum += mid - leftIndex + 1;
            }
        }
        while (leftIndex <= mid) tmp[index++] = A[leftIndex++];
        while (rightIndex <= high) tmp[index++] = A[rightIndex++];

        for (int i = 0; i < tmp.length; i++) {
            A[low+i] = tmp[i];
        }
        return sum;
    }
}
```







#### [315. 计算右侧小于当前元素的个数](https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/)

给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

示例:

```
输入: [5,2,6,1]
输出: [2,1,1,0] 
解释:
5 的右侧有 2 个更小的元素 (2 和 1).
2 的右侧仅有 1 个更小的元素 (1).
6 的右侧有 1 个更小的元素 (1).
1 的右侧有 0 个更小的元素.
```



```java
//归并排序的应用----看不懂跳过
class Solution {
    int[] count;
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();

        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        mergeSort(nums, indexes, 0, nums.length - 1);
        for (int i = 0; i < count.length; i++) {
            res.add(count[i]);
        }
        return res;
    }

    private void mergeSort(int[] nums, int[] indexes, int start, int end) {
        if (start >= end) return;

        int mid = start + ((end - start) >>> 1);
        mergeSort(nums, indexes, start, mid);
        mergeSort(nums, indexes,mid + 1, end);
        merge(nums, indexes, start, mid, end);
    }

    private void merge(int[] nums, int[] indexes, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int leftIndex = start;
        int rightIndex = mid + 1;
        int tmpIndex = 0;
        int rightLargeNumCount = 0;

        while (leftIndex <= mid && rightIndex <= end){
            if (nums[indexes[leftIndex]] > nums[indexes[rightIndex]]){
                tmp[tmpIndex++] = indexes[rightIndex++];
                rightLargeNumCount++;
            }else {
                tmp[tmpIndex++] = indexes[leftIndex];
                count[indexes[leftIndex]] += rightLargeNumCount;
                leftIndex++;
            }
        }

        while (leftIndex <= mid){
            tmp[tmpIndex++] = indexes[leftIndex];
            count[indexes[leftIndex]] += rightLargeNumCount;
            leftIndex++;
        }

        while (rightIndex <= end){
            tmp[tmpIndex++] = indexes[rightIndex++];
        }

        for (int i = 0; i < tmp.length; i++) {
            indexes[start + i] = tmp[i];
        }
    }
}
```







#### [207. 课程表 Course Schedule](https://leetcode-cn.com/problems/course-schedule/)

现在你总共有 n 门课需要选，记为 0 到 n-1。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]

给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？

示例 1:

```
输入: 2, [[1,0]] 
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
```


示例 2:

```
输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
```

说明:

- 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
- 你可以假定输入的先决条件中没有重复的边。



提示:

- 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
- 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
  拓扑排序也可以通过 BFS 完成。



```java
//拓扑排序
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int select = 0;
        while (!queue.isEmpty()) {
            select++;
            int course = queue.poll();
            for (int[] pair : prerequisites) {
                if (pair[1] == course) {
                    indegree[pair[0]]--;
                    if (indegree[pair[0]] == 0) {
                        queue.add(pair[0]);
                    }
                }
            }
        }
        return numCourses == select;
    }
}
```

# 二分

#### [35. 搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/)

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

**示例 1:**

```
输入: [1,3,5,6], 5
输出: 2
```

**示例 2:**

```
输入: [1,3,5,6], 2
输出: 1
```

**示例 3:**

```
输入: [1,3,5,6], 7
输出: 4
```

**示例 4:**

```
输入: [1,3,5,6], 0
输出: 0
```



```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        
        while(start <= end){
            int mid = start + ((end - start) >>> 1);

            if(nums[mid] == target)		return mid;
            else if(nums[mid] > target)		end = mid - 1;
            else	 start = mid + 1;
        }
		
        return start;
    }
}
```



#### [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

给定一个按照升序排列的整数数组 `nums`，和一个目标值 `target`。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 *O*(log *n*) 级别。

如果数组中不存在目标值，返回 `[-1, -1]`。

**示例 1:**

```
输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]
```

**示例 2:**

```
输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]
```

```java
class Solution {
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }
}
```

#### [162. 寻找峰值](https://leetcode-cn.com/problems/find-peak-element/)

峰值元素是指其值大于左右相邻值的元素。

给定一个输入数组 `nums`，其中 `nums[i] ≠ nums[i+1]`，找到峰值元素并返回其索引。

数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。

你可以假设 `nums[-1] = nums[n] = -∞`。

**示例 1:**

```
输入: nums = [1,2,3,1]
输出: 2
解释: 3 是峰值元素，你的函数应该返回其索引 2。
```

**示例 2:**

```
输入: nums = [1,2,1,3,5,6,4]
输出: 1 或 5 
解释: 你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
```

**说明:**

你的解法应该是 *O*(*logN*) 时间复杂度的。



```java
public class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            //哪个大，就把边界放在哪边，每次mid都是为了缩小边界
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
```

#### [74. 搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/)

编写一个高效的算法来判断 *m* x *n* 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

- 每行中的整数从左到右按升序排列。
- 每行的第一个整数大于前一行的最后一个整数。

**示例 1:**

```
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
输出: true
```

**示例 2:**

```
输入:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
输出: false
```

```java
//把整个二维数组看成一个有序的数组就可以了
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)    return false;

        int rows = matrix.length, cols = matrix[0].length;
        int start = 0, end = rows * cols - 1;

        while(start <= end){
            int mid = start + ((end - start) >>> 1);
            
            if(target == matrix[mid / cols][mid % cols])    return true;
            else if(target < matrix[mid / cols][mid % cols])    end = mid - 1;
            else    start = mid + 1;
        }

        return false;
    }
}
```





# JAVA程序设计题目

#### 设计一个泛型的获取数组最小值的函数.

并且这个方法只能接受Number的子类并且实现了Comparable接口。

```java
//作者：李蒙
//链接：https://www.zhihu.com/question/25548135/answer/33605942
//来源：知乎
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

//<T extends Comparable<? super T>>首先这是运用了java的泛型①extends后面跟的类型如<任意字符 extends 类/接口>表示泛型的上限import java.util.*;
class Demo<T extends AbstractList>{}
public class Test
{
    public static void main(String[] args) {
	Demo<ArrayList> p = null; // 编译正确
//这里因为ArrayList是AbstractList的子类所以通过
//如果改为Demo<AbstractCollection> p = null;就会报错这样就限制了上限
    }
}
//②同样的super表示泛型的下限③<T extends Comparable<? super T>>这里来分析T表示任意字符名，extends对泛型上限进行了限制即T必须是Comparable<? super T>的子类，然后<? super T>表示Comparable<>中的类型下限为T！这样来看一段代码辅助理解import java.util.GregorianCalendar;

class Demo<T extends Comparable<? super T>>{}

public class Test1
{
	public static void main(String[] args) {
	   Demo<GregorianCalendar> p = null; // 编译正确
    }
}
//这个可以理解为<GregorianCalendar extends Comparable<Calendar>>是可以运行成功的！因为Calendar为GregorianCalendar 的父类并且GregorianCalendar 实现了Comparable<Calendar>,可查看api！.

//如果是如下代码则运行不成功import java.util.GregorianCalendar;

class Demo<T extends Comparable<T>>{}
//这里把? super去掉了
public class Test
{
	public static void main(String[] args) {
	   Demo<GregorianCalendar> p = null; 
        }
}

//编译会报错！因为<T extends Comparable<T>>相当于<GregorianCalendar extends Comparable<GregorianCalendar>>但是GregorianCalendar并没有实现Comparable<GregorianCalendar>而是实现的Comparable<Calendar>，这里不在限制范围之内所以会报错！
```


```java
public class 泛型实现最小值函数 {
    private static <T extends Number & Comparable<? super T>>T min(T[] values){
        if (values.length == 0 || values == null) return null;
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            if (min.compareTo(values[i]) > 0) min = values[i];
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println("minInteger: " + min(new Integer[]{4,1,2,3}));
        System.out.println("minDouble: " + min(new Double[]{4.2, 1.2, 2.0, 3.4}));
//        System.out.println("typeError: "+ min(new String[]{"1", "2", "3"}));
    }
}	
```

#### [225. 用队列实现栈](https://leetcode-cn.com/problems/implement-stack-using-queues/)

使用队列实现栈的下列操作：

```
push(x) -- 元素 x 入栈
pop() -- 移除栈顶元素
top() -- 获取栈顶元素
empty() -- 返回栈是否为空
```


注意:

- 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
- 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
- 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。

```java
class MyStack {

    /** Initialize your data structure here. */
    public MyStack() {

    }
    
    /** Push element x onto stack. */
    public void push(int x) {

    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {

    }
    
    /** Get the top element. */
    public int top() {
        
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {

    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
```

#### [面试题09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

示例 1：

```
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
```


示例 2：

```
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
```


提示：

- 1 <= values <= 10000
- 最多会对 appendTail、deleteHead 进行 10000 次调用

```java
class CQueue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (!stack2.isEmpty()) return stack2.pop();
        else while (!stack1.isEmpty()) stack2.push(stack1.pop());
        if (!stack2.isEmpty()) return stack2.pop();
        else return -1;
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```

#### [面试题30. 包含min函数的栈](https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/)

定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。



示例:

```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.
```

```java
class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;
    /** initialize your data structure here. */
    public MinStack() {
        this.stack = new Stack<>();
        this.minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || minStack.peek() > x){
            minStack.push(x);
        }else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
```

#### [146. LRU缓存机制(双链表+hashmap)（**LinkedHashMap**）](https://leetcode-cn.com/problems/lru-cache/)

运用你所掌握的数据结构，设计和实现一个 [LRU (最近最少使用) 缓存机制](https://baike.baidu.com/item/LRU)。它应该支持以下操作： 获取数据 `get` 和 写入数据 `put` 。

获取数据 `get(key)` - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
写入数据 `put(key, value)` - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。

**进阶:**

你是否可以在 **O(1)** 时间复杂度内完成这两种操作？

**示例:**

```
LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得密钥 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得密钥 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
```

```java
class LRUCache {

    //LinkedHashMap插入是有顺序的
    private LinkedHashMap<Integer, Integer> map;
    private int SIZE;

    public LRUCache(int capacity) {
        map = new LinkedHashMap<>();
        SIZE = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int value = map.remove(key);
            map.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
        } else if (map.size() + 1 > SIZE) {
            map.remove(map.keySet().iterator().next());
        }
        map.put(key, value);
    }
}
```

```java
//双向链表+HashMap的方法
import java.util.HashMap;
import java.util.Map;

public class LRU缓存 {

    //head 与 tail 都不能存值，方便检索，新来的值放在头，老的自然就在尾巴侧
    final Node head = new Node(0, 0);
    final Node tail = new Node(0, 0);
    final Map<Integer, Node> map; //map存的是键值与节点信息
    final int capacity;

    public LRU缓存(int capacity) {
        this.capacity = capacity;
        map = new HashMap(capacity);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        int res = -1;
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            insertToHead(n);
            res = n.value;
        }
        return res;
    }

    //之前出现过，插入到链表头
    //之前未出现过，但是容量已满，map删尾巴前面一个对应的最久未使用的节点
    //将节点放入双向链表，并且放进hashmap
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            n.value = value;
            insertToHead(n);
        } else {

            if (map.size() == capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }

            Node n = new Node(key, value);
            insertToHead(n);
            map.put(key, n);
        }
    }

    //将双向链表中当前节点删除
    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    //将n节点插在头结点的后面
    private void insertToHead(Node n) {
        Node headNext = head.next;
        head.next = n;
        headNext.prev = n;
        n.prev = head;
        n.next = headNext;
    }

    //子类定义出Node的格式
    class Node {
        Node prev, next;
        int key, value;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }
}
```






# 中位数/滑动窗口

#### [295. 数据流的中位数(大小堆)](https://leetcode-cn.com/problems/find-median-from-data-stream/)

中位数是**有序列表**中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

例如，

```
[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5
```

设计一个支持以下两种操作的数据结构：

- void addNum(int num) - 从数据流中添加一个整数到数据结构中。
- double findMedian() - 返回目前所有元素的中位数。
  示例：

```
addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
```


进阶:

- 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
- 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？

```java
public class MedianFinder {
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;
    /** initialize your data structure here. */
    public MedianFinder() {
        //中位数左半部分，用大顶堆
        small = new PriorityQueue<>(Collections.reverseOrder());
       //
       // PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
       // new Comparator<Integer>() {
       //  public int compare(Integer i1, Integer i2) {
       //      return i2.compareTo(i1);
       //   }
       //}
   	   // );
        //中位数右半部分，用小顶堆
        large = new PriorityQueue<>();
    }

    public void addNum(int num) {
        //一个数进来，左边是大顶堆
        small.offer(num);
        //每次进都是先进左边，然后到右边，通过poll可以保证到右边的是大的数
        large.offer(small.poll());
        //每次到右边会导致右边的数更多，左边小的时候，将右边小顶堆的值放到左边去
        if (small.size() < large.size()){
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        if (small.size() == large.size())
            return ((double)small.peek() + (double)large.peek()) / 2.0;
        else {
            return small.peek();
        }
    }
}
```



#### [480. 滑动窗口中位数（大小堆）](https://leetcode-cn.com/problems/sliding-window-median/)

中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。

例如：

```
[2,3,4]，中位数是 3

[2,3]，中位数是 (2 + 3) / 2 = 2.5
```

给出一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。

示例：

```
给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。

窗口位置                      中位数

---------------               -----

[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
```

提示：

- 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
- 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。

```java
class Solution {
    PriorityQueue<Integer> small = new PriorityQueue<>((i1, i2)->i2.compareTo(i1));
    PriorityQueue<Integer> large = new PriorityQueue<>();

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
        if (n < 0) return new double[0];

        double[] res = new double[n];
        for (int i = 0; i <= nums.length; i++) {
            if (i >= k){
                res[i - k] = findMedian();
                remove(nums[i - k]);
            }
            if (i < nums.length){
                addNum(nums[i]);
            }
        }
        return res;
    }

    private void remove(int num) {
        if (num > findMedian())
            large.remove(num);
        else 
            small.remove(num);
        
        if ((small.size() - large.size()) > 1){
            large.offer(small.poll());
        }
        if (small.size() < large.size()){
            small.offer(large.poll());
        }
    }

    public void addNum(int num) {
        //一个数进来，左边是大顶堆
        small.offer(num);
        //每次进都是先进左边，然后到右边，通过poll可以保证到右边的是大的数
        large.offer(small.poll());
        //每次到右边会导致右边的数更多，左边小的时候，将右边小顶堆的值放到左边去
        if (small.size() < large.size()){
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        if (small.size() == large.size())
            return ((double)small.peek() + (double)large.peek()) / 2.0;
        else {
            return small.peek();
        }
    }
}
```



#### [239. 滑动窗口最大值（双端队列）](https://leetcode-cn.com/problems/sliding-window-maximum/)

给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

示例:

```
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值

---------------               -----

[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```


提示：

- 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。

进阶：

- 你能在线性时间复杂度内解决此题吗？

```java
//画图可以更好的理解思路

//linkedlist 实现的接口有Collection <E>， Deque <E>， List <E>， Queue <E>
//可以当双端队列来用LinkedList  ArrayDeque也实现了Deque<E>

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];

        int n = nums.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        //dq用来存的是数组index
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            //如果双端队列里面有K的元素了，就不能再加了，得先去掉队头index
            if (!dq.isEmpty() && (i - dq.peek() + 1) > k) {
                dq.poll();
            }
            //如果队尾存的index对应的num[index]比进来的值num[i]要小，说明没用，不要了
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (i + 1 - k >= 0){
                r[ri++] = nums[dq.peek()];
            }
        }
        return r;
    }
}
```











# DP解法

#### [53. 最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

```
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```


进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。



```java
//o(n)空间复杂度得dp
class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1]: 0);
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
```

```java
//o(1)空间复杂度的dp
public class Solution2 {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = dp;

        for (int i = 1; i < nums.length; i++) {
            dp = (dp > 0 ? dp : 0) + nums[i];
            max = Math.max(dp, max);
        }
        return max;
    }
}
```





#### [152. 乘积最大子序列](https://leetcode-cn.com/problems/maximum-product-subarray/)

给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

示例 1:

```
输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
```


示例 2:

```
输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
```



```java
public class Solution {
    public int maxProduct(int[] nums) {
        // store the result that is the max we have found so far
        int res = nums[0];

        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        for (int i = 1, imax = res, imin = res; i < nums.length; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (nums[i] < 0) {
                int t = imax;
                imax = imin;
                imin = t;
            }

            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            imax = Math.max(res * nums[i], nums[i]);
            imin = Math.min(res * nums[i], nums[i]);

            // the newly computed max value is a candidate for our global result
            res = Math.max(imax, res);
        }
        return res;
    }
}
```

# 位操作、异或

#### [136. 只出现一次的数字](https://leetcode-cn.com/problems/single-number/)

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有**线性**时间复杂度。 你可以**不使用额外空间**来实现吗？

示例 1:

```
输入: [2,2,1]
输出: 1
```


示例 2:

```
输入: [4,1,2,1,2]
输出: 4
```



```java
//运算符总结：
//	& 	与运算		全1为1
//	| 	或运算		有1为1
//  ~	非运算		位为0，结果是1，如果位为1，结果是0
//	^	异或运算	位相同为0，位不同为1
public class Solution {
    public int singleNumber(int[] nums) {
        int r = 0;
        
       	// 	N ^ N = N
        // 	N ^ N = 0	异或满足交换律
        //	 N1 ^ N1 ^ N2 ^ N2 ^..............^ Nx ^ Nx ^ N
        //	= (N1^N1) ^ (N2^N2) ^..............^ (Nx^Nx) ^ N
        //	= N
        for (int i = 0; i < nums.length; i++) {
            r ^= nums[i]; 
        }
        return r;
    }
}
```



#### [137. 只出现一次的数字 II](https://leetcode-cn.com/problems/single-number-ii/)

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

```
输入: [2,2,3,2]
输出: 3
```


示例 2:

```
输入: [0,1,0,1,0,1,99]
输出: 99
```



```java
public class Solution {
    /**
     * 假设有一个数为x,那么则有如下规律：
     * 0 ^ x = x,
     * x ^ x = 0；
     * x & ~x = 0,
     * x & ~0 =x;
     *
     * -那么就是很好解释上面的代码了。一开始a = 0, b = 0;
     *
     * x第一次出现后，a = (a ^ x) & ~b的结果为 a = x, b = (b ^ x) & ~a的结果为此时因为a = x了，所以b = 0。
     *
     * x第二次出现：a = (a ^ x) & ~b, a = (x ^ x) & ~0, a = 0; b = (b ^ x) & ~a 化简， b = (0 ^ x) & ~0 ,b = x;
     *
     * x第三次出现：a = (a ^ x) & ~b， a = (0 ^ x) & ~x ,a = 0; b = (b ^ x) & ~a 化简， b = (x ^ x) & ~0 , b = 0;所以出现三次同一个数，a和b最终都变回了0.
     *
     * 只出现一次的数，按照上面x第一次出现的规律可知a = x, b = 0;因此最后返回a.
     */
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for(int num : nums){
            a = (a ^ num) & ~ b;
            b = (b ^ num) & ~ a;
        }
        return a;
    }
}
```

```java
public class Solution2 {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            int mask = 1 << i;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & mask) != 0){
                    count++;
                    count %= 3;
                }
            }
            if (count != 0){
                res |= mask;
            }
        }
        return res;
    }
}
```





#### [260. 只出现一次的数字 III](https://leetcode-cn.com/problems/single-number-iii/)

给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。

示例 :

```
输入: [1,2,1,3,2,5]
输出: [3,5]
注意：

结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
```

你的算法应该具有**线性时间**复杂度。你能否仅使用**常数空间**复杂度来实现？

```java
//负数的二级制表示：---反码+1----即可
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
```

# 双指针

#### [392. 判断子序列](https://leetcode-cn.com/problems/is-subsequence/)


给定字符串 **s** 和 **t** ，判断 **s** 是否为 **t** 的子序列。

你可以认为 **s** 和 **t** 中仅包含英文小写字母。字符串 **t** 可能会很长（长度 ~= 500,000），而 **s** 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，`"ace"`是`"abcde"`的一个子序列，而`"aec"`不是）。

**示例 1:**
**s** = "abc", **t** = "ahbgdc"

返回 `true`.

**示例 2:**
**s** = "axc", **t** = `"ahbgdc"`

返回 `false`.

**后续挑战** **:**

如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

```java
public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()){
            if (s.charAt(indexS) == t.charAt(indexT)){
                indexS++;
                if (indexS == s.length())   return true;
            }
            indexT++;
        }
        return false;
    }
}
```

#### [167. 两数之和 II - 输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)

给定一个**已按照升序排列** 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:

- 返回的下标值（index1 和 index2）不是从零开始的。
- 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
  示例:

```
输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
```

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] r = new int[2];
        if (numbers == null || numbers.length < 2)
            return r;
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                r[0] = left + 1;
                r[1] = right + 1;
                break;
            } else if (sum > target) right--;
            else left++;
        }

        return r;
    }
}
```

#### [15. 三数之和(排序+双指针)](https://leetcode-cn.com/problems/3sum/)

给定一个包含 *n* 个整数的数组 `nums`，判断 `nums` 中是否存在三个元素 *a，b，c ，*使得 *a + b + c =* 0 ？找出所有满足条件且不重复的三元组。

**注意：**答案中不可以包含重复的三元组。

**示例：**

```
给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```



```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int lo = i + 1, hi = nums.length - 1, target = 0 - nums[i];
                while (lo < hi) {
                    if (target == nums[lo] + nums[hi]) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (target > nums[lo] + nums[hi]) lo++;
                    else hi--;
                }
            }
        }

        return res;
    }
}
```

#### [16. 最接近的三数之和(排序+双指针)](https://leetcode-cn.com/problems/3sum-closest/)

难度中等368收藏分享切换为英文关注反馈

给定一个包括 *n* 个整数的数组 `nums` 和 一个目标值 `target`。找出 `nums` 中的三个整数，使得它们的和与 `target` 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

```
例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
```

```java
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3 || nums == null) return 0;

        //res初始值不能随意设置
        int res = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i - 1] != nums[i])){
                int lo = i + 1, hi = nums.length - 1;
                while (lo < hi){
                    int sum = nums[i] + nums[lo] + nums[hi];
                    if (sum > target){
                        hi--;
                    }else if (sum == target) return sum;
                    else lo++;

                    if (Math.abs(target - sum) < Math.abs(target - res)){
                        res = sum;
                    }
                }
            }
        }
        return res;
    }
}
```

#### [18. 四数之和(kSum+双指针)](https://leetcode-cn.com/problems/4sum/)

给定一个包含 *n* 个整数的数组 `nums` 和一个目标值 `target`，判断 `nums` 中是否存在四个元素 *a，**b，c* 和 *d* ，使得 *a* + *b* + *c* + *d* 的值与 `target` 相等？找出所有满足条件且不重复的四元组。

**注意：**

答案中不可以包含重复的四元组。

**示例：**

```
给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
```

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        return kSum(nums, target, 4, 0);
    }

    public ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index){
        ArrayList<List<Integer>> res = new ArrayList<>();
        if(index >= nums.length) return res;

        if(k == 2){
            int lo = index, hi = nums.length - 1;
            while(lo < hi){
                if(target == nums[lo] + nums[hi]){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[lo]);
                    temp.add(nums[hi]);
                    res.add(temp);
                    while(lo < hi && nums[lo] == nums[lo + 1]) lo++;
                    while(lo < hi && nums[hi] == nums[hi - 1]) hi--;
                    lo++;
                    hi--;
                }else if(target > nums[lo] + nums[hi])  lo++;
                else    hi--;
            }
        }else{//K > 2的时候
            for(int i = index; i < nums.length - k + 1; i++){
                //下层的res返回回来成为这里的tmp
                ArrayList<List<Integer>> tmp = kSum(nums, target - nums[i], k - 1, i + 1);
                if(tmp != null){
                    for(List<Integer> t : tmp){
                        t.add(0, nums[i]);
                    }
                    res.addAll(tmp);
                }
                while(i < nums.length - k + 1 && nums[i] == nums[i + 1]){
                    i++;
                }
            }

            
        }
        return res;
    } 
}
```

#### [3. 无重复字符的最长子串（双指针/滑动窗口）](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

给定一个字符串，请你找出其中不含有重复字符的 **最长子串** 的长度。

**示例 1:**

```
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 2:**

```
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```

**示例 3:**

```
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

```java
class Solution {
    //最优的解法:HashMap存值和其下一个位置的index
    //双指针做滑动窗口
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int res = 0;

        for(int i = 0, j = 0; j < len; j++){
            if(map.containsKey(s.charAt(j))){
                //当前j对应的元素在之前出现过，但是滑动窗口已经经过了的情况容易大意忽略
                i = Math.max(i, map.get(s.charAt(j)));
            }
            res = Math.max(res, j - i + 1);
            map.put(s.charAt(j), j + 1);
        } 
        return res;
    }
}
```

#### [42. 接雨水（双指针）](https://leetcode-cn.com/problems/trapping-rain-water/)

给定 *n* 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png)

上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 

**示例:**

```
输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
```
```
解题的核心思想：一个index一个水量，然后求和(首尾位置没有水)
```

```java
//常规方法

/*
* 解题思路：从左到右按列求水量
* 1.从左往右进行扫描（第一个位置和最后一个位置不可能有水）
* 2.扫描到的位置，求出左边最高柱left_max和右边最高柱right_max
* 3.左右left_max、right_max求出最小的min.
* 4.当前位置的水量为min-height[i]
* 5.计算水量之和
*
* 时间复杂度：o(n^2) 空间复杂度o(1)
* */

class Solution {
    public int trap(int[] height) {
        int res = 0;
        //从左往右进行扫描（第一个位置和最后一个位置不可能有水）
        for (int i = 1; i < height.length - 1; i++) {
            //求出左边最高柱left_max
            int left_max = 0;
            for (int j = i - 1; j >= 0 ; j--) {
                if (height[j] > left_max)   left_max = height[j];
            }
            //右边最高柱right_max
            int right_max = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > right_max)  right_max = height[j];
            }
            //左右left_max、right_max求出最小的min.
            int min = left_max < right_max ? left_max : right_max;
            if (min > height[i]) res += min - height[i];
        }
        return res;
    }
}
```

```java
/ * 解法二 解题思路：实际上是方法一的改进---空间换时间

 *   
 * 1.max_left[i]、max_right[i]分别代表第 i 列左边和右边最高的墙的高度
 * 2.max_left[i] = Max(max_left[i-1],height[i-1])、max_right[i] = Max(max_right[i+1],height[i+1])
 * 3.扫描求水量之和
 *
 * 时间复杂度：三次扫描 3*n，所以是 o（n）          空间复杂度：o(n)
 * */
public class Solution2 {
    public int trap(int[] height) {
        int res = 0;
        int[] left_max = new int[height.length];
        int[] right_max = new int[height.length];
        //每个位置，确定左边最大
        for (int i = 1; i < height.length - 1; i++) {
            left_max[i] = Math.max(left_max[i - 1], height[i - 1]);
        }
        //每个位置，确定右边最大
        for (int i = height.length - 2; i > 0; i--) {
            right_max[i] = Math.max(right_max[i + 1] ,height[i + 1]);
        }
        //计算水量和
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(left_max[i], right_max[i]);
            if (min > height[i]) res += min - height[i];
        }
        return res;
    }
}
```


```java
//最优
//两个指针往中间走，走一个位置算一个位置，走到正中间就结束
public class Solution3 {
    public int trap(int[] height) {
        int res = 0, max_left = 0, max_right = 0, left = 0, right = height.length - 1;
        while(left < right){
            //最开始的时候进行一次比较可以确定在哪一边去更新，左边更小，去左边更新判断
            if (height[left] < height[right]){//水量最多和max_left一样多
                if (height[left] >= max_left)
                     max_left = height[left];
                else
                    res += max_left - height[left];
                left++;
            }
            //右边更小，去右边判断
            else {
                if (height[right] >= max_right)
                    max_right = height[right];
                else
                    res += max_right - height[right];
                right--;
            }
        }
        return res;
    }
}
```

#### [82. 删除排序链表中的重复元素 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)

给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 *没有重复出现* 的数字。

**示例 1:**

```
输入: 1->2->3->3->4->4->5
输出: 1->2->5
```

**示例 2:**

```
输入: 1->1->1->2->3
输出: 2->3
```

```java
class Solution { 
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        //use two pointers, slow - track the node before the dup nodes,
        // fast - to find the last node of dups.
        ListNode slow = dummy, fast = head;
        slow.next = fast;
        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val)
                fast = fast.next;
            //不等说明有重
            if (slow.next != fast) {
                slow.next = fast.next;
                //fast到新的位置去看看新的元素是否有问题
                fast = slow.next;
            }else{
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummy.next;
    }
}
```

#### [234. 回文链表（快慢双指针）](https://leetcode-cn.com/problems/palindrome-linked-list/)

请判断一个链表是否为回文链表。

**示例 1:**

```
输入: 1->2
输出: false
```

**示例 2:**

```
输入: 1->2->2->1
输出: true
```

**进阶：**
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

```java
class Solution {
   public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //奇数的时候需要将slow指针右移
        if (fast != null){
            slow = slow.next;
        }
        //翻转少的一半
        slow = reverse(slow);
        fast = head;

        while (slow != null){
            if (slow.val != fast.val)
                return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
```

#### [141. 环形链表（链表中是否有环）（快慢双指针）](https://leetcode-cn.com/problems/linked-list-cycle/)

给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 `pos` 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 `pos` 是 `-1`，则在该链表中没有环。

**示例 1：**

```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png)

**示例 2：**

```
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png)

**示例 3：**

```
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png)



Is this diagram help you understand?

- When fast and slow meet at point p, the length they have run are 'a+2b+c' and 'a+b'.
- Since the fast is 2 times faster than the slow. So a+2b+c == 2(a+b), then we get 'a==c'.
- So when another slow2 pointer run from head to 'q', at the same time, previous slow pointer will run from 'p' to 'q', so they meet at the pointer 'q' together.
  ![enter image description here](https://farm6.staticflickr.com/5758/22715587283_bdb4ba8434.jpg)

```java
public class Solution {
    public boolean hasCycle(ListNode head) {

        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) return true;
        }
        
        return false;
    }
}
```

#### [142. 环形链表 II(双指针)](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

难度中等376收藏分享切换为英文关注反馈

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 `null`。

为了表示给定链表中的环，我们使用整数 `pos` 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 `pos` 是 `-1`，则在该链表中没有环。

**说明：**不允许修改给定的链表。

 

**示例 1：**

```
输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png)

**示例 2：**

```
输入：head = [1,2], pos = 0
输出：tail connects to node index 0
解释：链表中有一个环，其尾部连接到第一个节点。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png)

**示例 3：**

```
输入：head = [1], pos = -1
输出：no cycle
解释：链表中没有环。
```

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png)

**进阶：**
你是否可以不用额外空间解决此题？

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                while(head != slow){
                    slow = slow.next;
                    head = head.next;
                }    
                return slow;
            }
        }
        
        return null;
    }
}
```



#### [23. 合并K个排序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

合并 *k* 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

**示例:**

```
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
```



# 链表

#### [206. 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)

反转一个单链表。

**示例:**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

**进阶:**
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

```java
//迭代
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        while(curr != null){
            ListNode nextNode = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextNode;
        }
        return pre;
    }
}
//递归
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        //注意reverse后的头就是p，p和这里的head.next是不一样的
        head.next.next = head;
        head.next = null;
        return p;
    }
}
```

#### [92. 反转链表 II](https://leetcode-cn.com/problems/reverse-linked-list-ii/)

反转从位置 *m* 到 *n* 的链表。请使用一趟扫描完成反转。

**说明:**
1 ≤ *m* ≤ *n* ≤ 链表长度。

**示例:**

```
输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
```

```java
class Solution {
     public ListNode reverseBetween(ListNode head, int m, int n) {
         //必须用dummy，否则会出现pre指向为空的情况，m为1的时候
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy, pre = null;
        for (int i = 0; i < m; i++) {
            pre = curr;
            curr = curr.next;
        }
        ListNode firstTail = pre;
        ListNode reverseTail = curr;
        for (int i = m; i <= n; i++) {
            ListNode nextNode = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextNode;
        }
        firstTail.next = pre;
        reverseTail.next = curr;
        return dummy.next;
    }
}
```

#### [86. 分隔链表(328 Odd Even Linked List 解法一样)](https://leetcode-cn.com/problems/partition-list/)

给定一个链表和一个特定值 *x*，对链表进行分隔，使得所有小于 *x* 的节点都在大于或等于 *x* 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。

**示例:**

```
输入: head = 1->4->3->2->5->2, x = 3
输出: 1->2->2->4->3->5
```

```java
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);
        ListNode curr1 = dummy1, curr2 = dummy2;

        while (head != null){
            if (head.val < x){
                curr1.next = head;
                curr1 = head;
            }else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;
        curr1.next = dummy2.next;
        return dummy1.next;
    }
}
```

#### [61. 旋转链表](https://leetcode-cn.com/problems/rotate-list/)

给定一个链表，旋转链表，将链表每个节点向右移动 *k* 个位置，其中 *k* 是非负数。

**示例 1:**

```
输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
```

**示例 2:**

```
输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
```

```java
public ListNode rotateRight(ListNode head, int k) {
    if(head == null || k == 0) {
        return head;
    }
    ListNode p = head;
    int len = 1;
    while(p.next != null) {
        p = p.next;
        len++;
    }
    p.next = head;
    k %= len;
    for(int i = 0; i < len - k; i++) {
        p = p.next;
    }
    head = p.next;
    p.next = null;
    return head;
}
```





#### [2. 两数相加](https://leetcode-cn.com/problems/add-two-numbers/)

给出两个 **非空** 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 **逆序** 的方式存储的，并且它们的每个节点只能存储 **一位** 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

**示例：**

```
输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
```

```java
public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
```



#### [445. 两数相加 II](https://leetcode-cn.com/problems/add-two-numbers-ii/)

给定两个**非空**链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

**进阶:**

如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

**示例:**

```
输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出: 7 -> 8 -> 0 -> 7
```

```java
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        
        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        
        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }
        
        return list.val == 0 ? list.next : list;
    }
}
```

#### [24. 两两交换链表中的节点](https://leetcode-cn.com/problems/swap-nodes-in-pairs/)

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

**你不能只是单纯的改变节点内部的值**，而是需要实际的进行节点交换。

**示例:**

```
给定 1->2->3->4, 你应该返回 2->1->4->3.
```

```java
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)  return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
}
```

```java
public class Solution2 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            //交换位置
            first.next = second.next;
            current.next = second;
            second.next = first;
            current = current.next.next;
        }
        return dummy.next;
    }
}
```

#### [160. 相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)

编写一个程序，找到两个单链表相交的起始节点。

如下面的两个链表**：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

在节点 c1 开始相交。

 

**示例 1：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_1.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_1.png)

```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```

 

**示例 2：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_2.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_2.png)

```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```

 

**示例 3：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_3.png)

```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```

 

**注意：**

- 如果两个链表没有交点，返回 `null`.
- 在返回结果后，两个链表仍须保持原有的结构。
- 可假定整个链表结构中没有循环。
- 程序尽量满足 O(*n*) 时间复杂度，且仅用 O(*1*) 内存。

[有疑问，见链接（有图解释）](https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!)

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode a = headA, b = headB;

        while (a != b){
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }
}
```

#### [23. 合并K个排序链表（最小堆、归并）](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

合并 *k* 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

**示例:**

```
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
```

```java
//优先队列的特性
public class Solution2 {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
		//lists.length表示初始化优先队列的长度
        // 依靠自然排序的优先级队列也不允许插入不可比较的对象（这样做可能导致ClassCastException ）。
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (ListNode node : lists) {
            if (node != null)
                queue.add(node);
        }

        while (!queue.isEmpty()){
            //每次取一个最小的元素，并且加入加再tail后面
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next != null){
                queue.add(tail.next);
            }
        }
        return dummy.next;
    }
}
```

```java
//归并排序的思想
public class Solution3 {
    public ListNode mergeKLists(ListNode[] lists) {
        return partition(lists, 0, lists.length - 1);
    }

    public ListNode partition(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        if (start < end) {
            int mid = (start + end) / 2;
            ListNode l1 = partition(lists, start, mid);
            ListNode l2 = partition(lists, mid + 1, end);
            return merge(l1, l2);
        } else
            return null;
    }
    public ListNode merge(ListNode l1, ListNode l2){
        if (l1 == null)return l2;
        if (l2 == null)return l1;
        if (l1.val < l2.val){
            l1.next = merge(l1.next, l2);
            return l1;
        }else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

}
```

#### [138. 复制带随机指针的链表](https://leetcode-cn.com/problems/copy-list-with-random-pointer/)

给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的 **[深拷贝](https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin)**。 

我们用一个由 `n` 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 `[val, random_index]` 表示：

- `val`：一个表示 `Node.val` 的整数。
- `random_index`：随机指针指向的节点索引（范围从 `0` 到 `n-1`）；如果不指向任何节点，则为 `null` 。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png)

```
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png)

```
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
```

**示例 3：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png)**

```
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
```

**示例 4：**

```
输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
```

 

**提示：**

- `-10000 <= Node.val <= 10000`
- `Node.random` 为空（null）或指向链表中的节点。
- 节点数目不超过 1000 。

```java
class Solution {
     /**
    思路：用hashmap来存储原始链表和关系
    **/
    public Node copyRandomList(Node head) {
        Map<Node,Node> hashmap = new HashMap<>();
        Node cur = head;
        while(cur!=null){
            hashmap.put(cur,new Node(cur.val));
            cur = cur.next;
        }
        //先构建next指针
        Node newHead = new Node(0);
        cur = head;
        Node newcur = newHead;
        while(cur != null){
            newcur.next = hashmap.get(cur);
            newcur = newcur.next;
            cur =cur.next;
        }
        
        //构建random指针
        cur =head;
        newcur = newHead.next;
        while(cur != null){
            newcur.random = hashmap.get(cur.random);
            newcur=newcur.next;
            cur = cur.next;
        }
        return newHead.next;
    }
}
```

```java
//leetcode 官方题解，vip答案
public class Solution {
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // Creating a new weaved list of original and copied nodes.
        Node ptr = head;
        while (ptr != null) {

            // Cloned node
            Node newNode = new Node(ptr.val);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }

        ptr = head;

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }
}
```



#### [25. K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)

给你一个链表，每 *k* 个节点一组进行翻转，请你返回翻转后的链表。

*k* 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 *k* 的整数倍，那么请将最后剩余的节点保持原有顺序。

**示例 :**

给定这个链表：`1->2->3->4->5`

当 *k* = 2 时，应当返回: `2->1->4->3->5`

当 *k* = 3 时，应当返回: `3->2->1->4->5`

**说明 :**

- 你的算法只能使用常数的额外空间。
- **你不能只是单纯的改变节点内部的值**，而是需要实际的进行节点交换。

```java
public class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1)
            return head;

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode begin = dummyHead;
        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode reverse(ListNode begin, ListNode end) {
        ListNode curr = begin.next;
        ListNode prev = begin;
        ListNode first = curr;
        ListNode next;
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        first.next = end;
        begin.next = prev;
        return first;
    }
}
```

#### [143. 重排链表(双指针)](https://leetcode-cn.com/problems/reorder-list/)

给定一个单链表 *L*：*L*0→*L*1→…→*L**n*-1→*L*n ，
将其重新排列后变为： *L*0→*L**n*→*L*1→*L**n*-1→*L*2→*L**n*-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

**示例 1:**

```
给定链表 1->2->3->4, 重新排列为 1->4->2->3.
```

**示例 2:**

```
给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
```

```java
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        
        // Find the middle node
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse the second half
        ListNode head2 = reverse(slow.next);
        slow.next = null;
        
        // Intertwine the two halves
        merge(head, head2);
    }
    
    private ListNode reverse(ListNode n) {
        ListNode prev = null, cur = n;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }
    
      public void merge(ListNode left, ListNode right){
        ListNode leftTemp;
        ListNode rightTemp;
        while (left.next != null && right!= null) {
            //1. 保存next节点
            leftTemp = left.next;
            rightTemp = right.next;

            //2. 将右链表的第一个节点插入到左链表中
            // 左链表：1->2->3 右链表：5->4 
            // 合并后的左链表：1->5->2->3 
            left.next = right;
            right.next = leftTemp;

            //3. 移动left和right指针
            //左链表变为：2->3 右链表变为：4
            left = leftTemp;
            right = rightTemp;
        }
    }
}
```

```java
//双端队列
class Solution {
    public void reorderList(ListNode head) {
        LinkedList<ListNode> queue = new LinkedList<>();
        ListNode cur = head;
        //全部放到队列里
        while (cur != null) {
            queue.addLast(cur);
            cur = cur.next;
        }
        
        //每次选两端的加入
        while (!queue.isEmpty()) {
            if (cur == null) {
                cur = queue.pollFirst();
            } else {
                cur.next = queue.pollFirst();
                cur = cur.next;
            }
            cur.next = queue.pollLast();
            cur = cur.next;
        }
        
        if (cur != null) {
            cur.next = null;
        }
    }
}
```