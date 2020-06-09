
[TOC]
# 剑指offer题解



# 字符串

#### [8. 字符串转换整数 (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/)

难度中等711

请你来实现一个 `atoi` 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

- 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
- 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
- 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0 。

**提示：**

- 本题中的空白字符只包括空格字符 `' '` 。
- 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

 

**示例 1:**

```
输入: "42"
输出: 42
```

**示例 2:**

```
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
```

**示例 3:**

```
输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
```

**示例 4:**

```
输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
```

**示例 5:**

```
输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。
```

```java
class Solution {
    public int myAtoi(String str) {
        if (str == null || str.isEmpty())   return 0;
        
        int i = 0, factor = 1, sum = 0;
        while (i < str.length() && str.charAt(i) == ' ')                    i++;
        if(i == str.length())   return 0;
        
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            factor = (str.charAt(i) == '-' ? -1 : 1);
            i++;
        }
        
        while(i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (sum > Integer.MAX_VALUE / 10 || (sum == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 
                                                Integer.MAX_VALUE % 10)) {
                return factor == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            sum = sum * 10 + (str.charAt(i) - '0');
            i++;
        }
        
        return factor * sum;
    }
}
```



# 并查集

#### [547. 朋友圈](https://leetcode-cn.com/problems/friend-circles/)

难度中等236收藏分享切换为英文关注反馈

班上有 **N** 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。

给定一个 **N \* N** 的矩阵 **M**，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生**互为**朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。

**示例 1:**

```
输入: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
输出: 2 
说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
第2个学生自己在一个朋友圈。所以返回2。
```

**示例 2:**

```
输入: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
输出: 1
说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
```

**注意：**

1. N 在[1,200]的范围内。
2. 对于所有学生，有M[i][i] = 1。
3. 如果有M[i][j] = 1，则有M[j][i] = 1。

```java
class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        BCJ bcj = new BCJ(n);
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(M[i][j] == 1)
                    bcj.Union(i, j);
            }
        }

        return bcj.count();
    }

    class BCJ{
        private int[] size;
        private int[] parent;
        private int count; //有多少个集合

        public BCJ(int n){
            this.count = n;
            parent = new int[n];
            size = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void Union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            
            if(rootP == rootQ) return;

            if(size[rootP] > size[rootQ]){
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }else{
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
            count--;
        }

        public int find(int x){
            if(parent[x] == x)  return x;

            return parent[x] = find(parent[x]);
        }

        public boolean isConnected(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            return rootP == rootQ;
        }

        public int count(){
            return count;
        }

    }
}
```





#### [820. 单词的压缩编码](https://leetcode-cn.com/problems/short-encoding-of-words/)

难度中等140收藏分享切换为英文关注反馈

给定一个单词列表，我们将这个列表编码成一个索引字符串 `S` 与一个索引列表 `A`。

例如，如果这个列表是 `["time", "me", "bell"]`，我们就可以将其表示为 `S = "time#bell#"` 和 `indexes = [0, 2, 5]`。

对于每一个索引，我们可以通过从字符串 `S` 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。

那么成功对给定单词列表进行编码的最小字符串长度是多少呢？

 

**示例：**

```
输入: words = ["time", "me", "bell"]
输出: 10
说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
```

 

**提示：**

1. `1 <= words.length <= 2000`
2. `1 <= words[i].length <= 7`
3. 每个单词都是小写字母 。

```java
class Solution {
   public int minimumLengthEncoding(String[] words) {
        int N = words.length;
        
        Comparator<String> cmp = (s1, s2) -> {
            int N1 = s1.length();
            int N2 = s2.length();
            for (int i = 0; i < Math.min(N1, N2); i++) {
                char c1 = s1.charAt(N1 - 1 - i);
                char c2 = s2.charAt(N2 - 1 - i);
                int c = Character.compare(c1, c2);
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(N1, N2);
        };
        // 逆序字典序排序    
        Arrays.sort(words, cmp);

        int res = 0;
        for (int i = 0; i < N; i++) {
            if (i+1 < N && words[i+1].endsWith(words[i])) {
                // 当前单词是下一个单词的后缀，丢弃
            } else {
                res += words[i].length() + 1; // 单词加上一个 '#' 的长度
            }
        }
        return res;
    }
}
```

```java
//字典树
class Solution {
    public int minimumLengthEncoding(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> nodes = new HashMap();

        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            TrieNode cur = trie;
            for (int j = word.length() - 1; j >= 0; --j)
                cur = cur.get(word.charAt(j));
            nodes.put(cur, i);
        }

        int ans = 0;
        for (TrieNode node: nodes.keySet()) {
            if (node.count == 0)
                ans += words[nodes.get(node)].length() + 1;
        }
        return ans;

    }
}

class TrieNode {
    TrieNode[] children;
    int count;
    TrieNode() {
        children = new TrieNode[26];
        count = 0;
    }
    public TrieNode get(char c) {
        if (children[c - 'a'] == null) {
            children[c - 'a'] = new TrieNode();
            count++;
        }
        return children[c - 'a'];
    }
}
```





#### [999. 车的可用捕获量](https://leetcode-cn.com/problems/available-captures-for-rook/)

难度简单33收藏分享切换为英文关注反馈

在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。

车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。

返回车能够在一次移动中捕获到的卒的数量。


**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/1253_example_1_improved.PNG)

```
输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：3
解释：
在本例中，车能够捕获所有的卒。
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/1253_example_2_improved.PNG)

```
输入：[[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：0
解释：
象阻止了车捕获任何卒。
```

**示例 3：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/1253_example_3_improved.PNG)

```
输入：[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
输出：3
解释： 
车可以捕获位置 b5，d6 和 f5 的卒。
```

 

**提示：**

1. `board.length == board[i].length == 8`
2. `board[i][j]` 可以是 `'R'`，`'.'`，`'B'` 或 `'p'`
3. 只有一个格子上存在 `board[i][j] == 'R'`

```java
class Solution {
    public int numRookCaptures(char[][] board) {
        if(board == null || board.length == 0) return 0;
        int m = board.length;
        int n = board[0].length;

        int res = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 'R')
                    //以R 为原点建立坐标系
                    //依次向上找,向下找,向右找,向左找
                    return cap(board,i,j,0,1)+cap(board,i,j,0,-1)+cap(board,i,j,1,0)+cap(board,i,j,-1,0);
            }
        }

        return 0; 
    }

     public int cap(char[][] a,int x,int y,int dx,int dy){
        /*参数说明 
         *a为原数组矩阵
         *x,y为R的坐标
         *dx,dy为增长步长
        */
        while(x>=0 && x<a.length && y>=0 && y<a[x].length && a[x][y]!='B'){
            if(a[x][y]=='p'){
                return 1;
            }
            x+=dx;
            y+=dy;
        }
        return 0;
    }
}
```



#### [179. 最大数](https://leetcode-cn.com/problems/largest-number/)

难度中等240收藏分享切换为英文关注反馈

给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。

**示例 1:**

```
输入: [10,2]
输出: 210
```

**示例 2:**

```
输入: [3,30,34,5,9]
输出: 9534330
```

**说明:** 输出结果可能非常大，所以你需要返回一个字符串而不是整数。

```java
class Solution {
    public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0)    return "";

        String[] s = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            s[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(s, (s1, s2)->(s2 + s1).compareTo(s1 + s2));
        if(s[0].equals("0"))
            return  "0";

        StringBuilder sb = new StringBuilder();
        for(String str : s){
            sb.append(str);
        }

        return sb.toString();
    }
}
```



#### [329. 矩阵中的最长递增路径(DFS)](https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/)

难度困难132收藏分享切换为英文关注反馈

给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

**示例 1:**

```
输入: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
输出: 4 
解释: 最长递增路径为 [1, 2, 6, 9]。
```

**示例 2:**

```
输入: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
输出: 4 
解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
```

```java
class Solution {
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(len, max);
            }
        }
        
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];

        int maxlen = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            maxlen = Math.max(maxlen, len);
        }
        
        cache[i][j] = maxlen;
        return maxlen;
    }
}
```



#### [409. 构造最长回文串](https://leetcode-cn.com/problems/longest-palindrome/)

难度简单146收藏分享切换为英文关注反馈

给定一个包含大写字母和小写字母的字符串，找到通过这些字母**构造成**的最长的回文串。

在构造过程中，请注意区分大小写。比如 `"Aa"` 不能当做一个回文字符串。

**注意:**
假设字符串的长度不会超过 1010。

**示例 1:**

```
输入:
"abccccdd"

输出:
7

解释:
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
```

```java
class Solution {
    public int longestPalindrome(String s) {
        if(s == null || s.length() == 0)    return 0;

        Map<Character, Integer> map = new HashMap<>();

        for(char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c , 0) + 1);
        }

        int result = 0;
        for(char key : map.keySet()){
            if(map.get(key) % 2 == 0){
                result += map.get(key);
            }else{
                result += (map.get(key) / 2) * 2;
            }
        }

        return result == s.length() ? result : result + 1;
    }
}
```

```java
//最优解法
class Solution {
    public int longestPalindrome(String s) {
        int res = 0;
        int[] count = new int[128];
        
        for(char c : s.toCharArray())
            count[c]++;
        
        for(int num : count)
            res += num / 2 * 2;
        
        return  res == s.length() ? res : res + 1;
    }
}
```



#### [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

给定一个无序的整数数组，找到其中最长上升子序列的长度。

**示例:**

```
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
```

**说明:**

- 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
- 你算法的时间复杂度应该为 O(*n2*) 。

**进阶:** 你能将算法的时间复杂度降低到 O(*n* log *n*) 吗?

```java
//此算法时间复杂度nlogn
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0)    return 0;

        int[] dp = new int[nums.length];
        int res = 0;

        for(int num : nums){
            int lo = 0, hi = res;
            while(lo < hi){
                int mid = lo + ((hi - lo) >>> 1);
                if(dp[mid] < num)	lo = mid + 1;
                else	hi = mid;
            }
            //lo代表插入点 
            //res代表下一次新的有序元素进来应该插入的index位置，也代表当前集合里有几个元素
            dp[lo] = num;
            if(res == lo)   res++;
        }

        return res;
    }
}
```

```java
//dp思路
//https://leetcode-cn.com/problems/longest-increasing-subsequence/comments/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0)    return 0;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int res = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(dp[i], res);
        }

        return res;
    }
}
```





#### [1071. 字符串的最大公因子](https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/)

对于字符串 `S` 和 `T`，只有在 `S = T + ... + T`（`T` 与自身连接 1 次或多次）时，我们才认定 “`T` 能除尽 `S`”。

返回最长字符串 `X`，要求满足 `X` 能除尽 `str1` 且 `X` 能除尽 `str2`。

 

**示例 1：**

```
输入：str1 = "ABCABC", str2 = "ABC"
输出："ABC"
```

**示例 2：**

```
输入：str1 = "ABABAB", str2 = "ABAB"
输出："AB"
```

**示例 3：**

```
输入：str1 = "LEET", str2 = "CODE"
输出：""
```

 

**提示：**

1. `1 <= str1.length <= 1000`
2. `1 <= str2.length <= 1000`
3. `str1[i]` 和 `str2[i]` 为大写英文字母

```java
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if(!(str1+str2).equals(str2+str1))   return "";

        int len1 = str1.length();
        int len2 = str2.length();
        
        int index = len1 > len2 ? GCD(len1, len2) : GCD(len2, len1);

        return str1.substring(0, index);
    }

    private int GCD(int len1, int len2){
        if(len2 == 0)   return 0;

        return len1 % len2 == 0 ? len2 : GCD(len2, len1 % len2);
    }

}
```



#### kmp算法dp

```java
//字符串匹配问题
//先介绍暴力解法
// 暴⼒匹配（伪码）		
int search(String pat, String txt) {
	int M = pat.length();
	int N = txt.length();
	for (int i = 0; i <= N - M; i++) {
		for (int j = 0; j < M; j++) {
			if (pat[j] != txt[i+j])
			break;
		} 
        // pat 全都匹配了
		if (j == M) return i;
	} 
    // txt 中不存在 pat ⼦串
	return -1;
}
//缺点：如果pat是比较长而且比较多重复的情况，那么暴力法会做很多重复比对操作
```

```java
package LeetcodeAlgorithm.KMP;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/11 20:40
 */
public class KMP {
    private int[][] dp;
    private String pat;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 影⼦状态 X 初始为 0
        int X = 0;
        // 当前状态 j 从 1 开始
        for (int j = 1; j < M; j++) {
        	for (int c = 0; c < 256; c++) {
        		if (pat.charAt(j) == c)
        			dp[j][c] = j + 1;
        		else
        			dp[j][c] = dp[X][c];
        	} 
            // 更新影⼦状态--影子表示公共的状态
            //如果X发生了更新，表示新出现的 char元素 和之前公共串 位置的下一个元素相同
        	X = dp[X][pat.charAt(j)];
        }
    }

    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++) {
            // 计算 pat 的下⼀个状态
            j = dp[j][txt.charAt(i)];
            // 到达终⽌态， 返回结果 在txt串中开始匹配的位置
            if (j == M) return i - M + 1;
        }
        //没到达终⽌态，匹配失败
        return -1;
    }
}
```



#### [1013. 将数组分成和相等的三个部分](https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/)

给你一个整数数组 `A`，只有可以将其划分为三个和相等的非空部分时才返回 `true`，否则返回 `false`。

形式上，如果可以找出索引 `i+1 < j` 且满足 `(A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])` 就可以将数组三等分。

 

**示例 1：**

```
输出：[0,2,1,-6,6,-7,9,1,2,0,1]
输出：true
解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
```

**示例 2：**

```
输入：[0,2,1,-6,6,7,9,-1,2,0,1]
输出：false
```

**示例 3：**

```
输入：[3,3,6,5,-2,2,5,1,-9,4]
输出：true
解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
```

 

**提示：**

1. `3 <= A.length <= 50000`
2. `-10^4 <= A[i] <= 10^4`

```java
class Solution {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for(int num : A){
            sum += num;
        }
        if(sum % 3 != 0) return false;
        sum /= 3;
        int partition = 0;
        int curSum = 0;
        int index = 0;
        for(int i = 0; i < A.length; i++){
            curSum += A[i];
            if(curSum == sum && partition <= 1){
                partition++;
                curSum = 0;
                if(partition == 2)
                    index = i;
            }
            
        }

        return (partition == 2) && (curSum == sum) && (index < A.length - 1);
    }
}
```



#### [面试题03. 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)


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

#### [面试题22. 链表中倒数第k个节点(滑动窗口)](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

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
public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSame(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        
        if (s.val != t.val) return false;
        
        return isSame(s.left, t.left) && isSame(s.right, t.right);
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
        // when find a number i, flip the number at position i-1 to negative. 
    	// if the number at position i-1 is already negative, i is the number that occurs twice.
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

ASCII可显示字符：ASCII码对应的十进制从32-126，说明都在128之内。





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
输出:4
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
//快速排序，性能最好，时间复杂度为o(n),因为不用每次搜索完，是一个类似二分的形式
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

#### [面试题40. 最小的k个数](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)

难度简单18收藏分享切换为英文关注反馈

输入整数数组 `arr` ，找出其中最小的 `k` 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

 

**示例 1：**

```
输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
```

**示例 2：**

```
输入：arr = [0,1,2,1], k = 1
输出：[0]
```

 

**限制：**

- `0 <= k <= arr.length <= 10000`
- `0 <= arr[i] <= 10000`

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if(arr == null || k == 0 || arr.length == 0)  return new int[0];

        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSelect(int[] arr, int left, int right, int desPos){
        int pivot_index = partition(arr, left, right);

        if(desPos == pivot_index)
            return Arrays.copyOf(arr, pivot_index + 1);
        
        return pivot_index > desPos ? quickSelect(arr, left, pivot_index - 1, desPos)
                                    :   quickSelect(arr, pivot_index + 1, right, desPos);
    }

    private int partition(int[] arr, int start, int end){
        int pivot_index = start;

        for(int i = start; i < end; i++){
            if(arr[i] < arr[end]){
                int tmp = arr[i];
                arr[i] = arr[pivot_index];
                arr[pivot_index] = tmp;
                pivot_index++;
            }
        }

        int tmp = arr[end];
        arr[end] = arr[pivot_index];
        arr[pivot_index] = tmp;

        return pivot_index;
    }
}
```

```java
//klogk 时间复杂度更高
public class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((v1, v2)->(v2 - v1));

        for (int val : arr) {
            priorityQueue.offer(val);
            if (priorityQueue.size() > k)
                priorityQueue.poll();
        }
        
        int[] res = new int[k];
        for(int i = 0; i < k; i++)
            res[i] =  priorityQueue.poll();
        
        return res;
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







#### [LintCode532逆序对](https://www.lintcode.com/problem/reverse-pairs/description)

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

```java
package 剑指offer.N51数组中的逆序对;

public class Solution {
    public int reversePairs(int[] nums) {
        int len = nums.length;

        if (len < 2) {
            return 0;
        }

        int[] copy = new int[len];
        for (int i = 0; i < len; i++) {
            copy[i] = nums[i];
        }

        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    /**
     * 在nums[left...right]之间计算逆序对的个数
     *
     * @param nums
     * @param left
     * @param right
     * @param temp
     * @return
     */
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }

        int mid = left + ((right - left) >>> 1);
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        if (nums[mid] <= nums[mid + 1]) {
            return leftPairs + rightPairs;
        }

        int crossPairs = mergeAndCount(nums, left, mid, right, temp);

        return leftPairs + rightPairs + crossPairs;
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        //全局使用一个辅助数组，为了避免反复创建数组增加空间开销，创建销毁需要占用资源
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {

            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) { //这里相等保证的是归并排序的稳定
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                count += (mid - i + 1);
            }
        }

        return count;
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



#### [240. 搜索二维矩阵 II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)

编写一个高效的算法来搜索 *m* x *n* 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

- 每行的元素从左到右升序排列。
- 每列的元素从上到下升序排列。

**示例:**

现有矩阵 matrix 如下：

```
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

给定 target = `5`，返回 `true`。

给定 target = `20`，返回 `false`。

```java
//本题从右上、左下开始皆可
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //选择从左下开始
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col <= matrix[0].length - 1){
            if(target == matrix[row][col]) return true;
            else if(target < matrix[row][col])  row--;
            else    col++;
        }
        return false;
    }
}
```

#### [153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 `[0,1,2,4,5,6,7]` 可能变为 `[4,5,6,7,0,1,2]` )。

请找出其中最小的元素。

你可以假设数组中**不存在重复元素。**

**示例 1:**

```
输入: [3,4,5,1,2]
输出: 1
```

**示例 2:**

```
输入: [4,5,6,7,0,1,2]
输出: 0
```

```java
public class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high){
            int mid = low + ((high - low) >>> 1);

            //nums[mid] < nums[high]   太妙了
            if (nums[mid] < nums[high]) high = mid;
            else low = mid + 1;
        }

        return nums[low];
    }
```

#### [154. 寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 `[0,1,2,4,5,6,7]` 可能变为 `[4,5,6,7,0,1,2]` )。

请找出其中最小的元素。

注意数组中**可能存在重复的元素。**

**示例 1：**

```
输入: [1,3,5]
输出: 1
```

**示例 2：**

```
输入: [2,2,2,0,1]
输出: 0
```

**说明：**

- 这道题是 [寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/) 的延伸题目。
- 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？

```java
class Solution {
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;

        while(lo < hi){
            int mid = lo + ((hi -lo) >>> 1);
            
            if(nums[mid] < nums[hi])    hi = mid;
            else if(nums[mid] > nums[hi])   lo = mid + 1;
            else    //this case is nums[mid] == nums[hi]
                hi--;
        }

        return nums[lo];
    }
}
```

#### [33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 `[0,1,2,4,5,6,7]` 可能变为 `[4,5,6,7,0,1,2]` )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 `-1` 。

你可以假设数组中**不存在重复的元素**。

你的算法**时间复杂度**必须是 ***O*(log *n*)** 级别。

**示例 1:**

```
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
```

**示例 2:**

```
输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1
```

```java
class Solution {
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while(lo <= hi){//可能出现target不在数组中的时候，需要加上等号
            int mid = lo + ((hi -lo) >>> 1);
            
            if(target == nums[mid]) return mid;
            else if(nums[mid] < nums[hi]) {//中间小于右边，说明右半边有序
                if(target > nums[mid] && target <= nums[hi])    lo = mid + 1;
                else    hi = mid - 1;
            }
            else{//说明左边有序
                if(target < nums[mid] && target >= nums[lo])    hi = mid - 1;
                else    lo = mid + 1;
            }
        }

        return -1;
    }
}
```

#### [81. 搜索旋转排序数组 II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 `[0,0,1,2,2,5,6]` 可能变为 `[2,5,6,0,0,1,2]` )。

编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 `true`，否则返回 `false`。

**示例 1:**

```
输入: nums = [2,5,6,0,0,1,2], target = 0
输出: true
```

**示例 2:**

```
输入: nums = [2,5,6,0,0,1,2], target = 3
输出: false
```

**进阶:**

- 这是 [搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/) 的延伸题目，本题中的 `nums` 可能包含重复元素。
- 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？

```java
class Solution {
    public boolean search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while(lo <= hi){//可能出现target不在数组中的时候，需要加上等号
            int mid = lo + ((hi -lo) >>> 1);
            
            if(target == nums[mid]) return true;
            else if(nums[mid] < nums[hi]) {//中间小于右边，说明右半边有序
                if(target > nums[mid] && target <= nums[hi])    lo = mid + 1;
                else    hi = mid - 1;
            }
            else if(nums[mid] > nums[hi]){//说明左边有序
                if(target < nums[mid] && target >= nums[lo])    hi = mid - 1;
                else    lo = mid + 1;
            }else{
                //太牛皮了，无法判断的情况转化为可判断的情况即可
                hi--;
            }
        }

        return false;
    }
}
```

#### [378. 有序矩阵中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/)

给定一个 *n x n* 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
请注意，它是排序后的第k小元素，而不是第k个元素。

**示例:**

```
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

返回 13。
```

**说明:**你可以假设 k 的值永远是有效的,  `1 ≤ k ≤ n^2`

```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = getLessEqual(matrix, mid);
            if (count < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }
    
    //从左下的位置出发，找小于目标数的数量
    private int getLessEqual(int[][] matrix, int val) {
        int res = 0;
        int n = matrix.length, i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] > val) i--;
            else {
                //关键 
                res += i + 1;
                j++;
            }
        }
        return res;
    }
}
```

#### [4. 寻找两个有序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/)

给定两个大小为 m 和 n 的有序数组 `nums1` 和 `nums2`。

请你找出这两个有序数组的中位数，并且**要求算法的时间复杂度为 O(log(m + n))**。

你可以假设 `nums1` 和 `nums2` 不会同时为空。

**示例 1:**

```
nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
```

**示例 2:**

```
nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5
```

[参考链接,看视频解答就可以理解了](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/)

```java
//时间复杂度要求为o(log(m+n))，只能用二分查找
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    int left = (n + m + 1) / 2;
    int right = (n + m + 2) / 2;
    //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
    return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;  
}
    
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }
```





# JAVA程序设计题目



#### [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)

难度中等282收藏分享切换为英文关注反馈

实现一个 Trie (前缀树)，包含 `insert`, `search`, 和 `startsWith` 这三个操作。

**示例:**

```
Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // 返回 true
trie.search("app");     // 返回 false
trie.startsWith("app"); // 返回 true
trie.insert("app");   
trie.search("app");     // 返回 true
```

**说明:**

- 你可以假设所有的输入都是由小写字母 `a-z` 构成的。
- 保证所有输入均为非空字符串。

```java
class Trie {
    private boolean isString = false;
    private Trie next[] = new Trie[26];

    public Trie() {
    }

    public void insert(String word) {
        Trie root = this;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (root.next[c - 'a'] == null) root.next[c - 'a'] = new Trie();
            root = root.next[c - 'a'];
        }

        root.isString = true;
    }

    public boolean search(String word) {
        Trie root = this;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (root.next[c - 'a'] == null) return false;
            root = root.next[c - 'a'];
        }

        return root.isString;
    }

    public boolean startsWith(String prefix) {
        Trie root = this;
        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            if (root.next[c - 'a'] == null) return false;
            root = root.next[c - 'a'];
        }

        return true;
    }
}
```



#### [211. 添加与搜索单词 - 数据结构设计](https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/)

难度中等116收藏分享切换为英文关注反馈

设计一个支持以下两种操作的数据结构：

```
void addWord(word)
bool search(word)
```

search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 `.` 或 `a-z` 。 `.` 可以表示任何一个字母。

**示例:**

```
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
```

**说明:**

你可以假设所有单词都是由小写字母 `a-z` 组成的。

```java
class WordDictionary {

    private class Trie{
        private boolean isString = false;
        private Trie[] next = new Trie[26];
    }
    Trie root = new Trie();

    public WordDictionary() {
    }

    public void addWord(String word) {
        Trie node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c -'a'] == null) node.next[c - 'a'] = new Trie();
            node = node.next[c - 'a'];
        }
        node.isString = true;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    private boolean match(char[] chars, int index, Trie node) {
        if (index == chars.length) return node.isString;
        if (chars[index] != '.') {
            return node.next[chars[index] - 'a'] != null &&
                    match(chars, index + 1, node.next[chars[index] - 'a']);
        }else {
            for (int j = 0; j < node.next.length; j++) {
                if (node.next[j] != null){
                    if (match(chars, index + 1, node.next[j]))
                        return true;
                }
            }
        }


        return false;
    }
}
```



#### [212. 单词搜索 II](https://leetcode-cn.com/problems/word-search-ii/)

难度困难150收藏分享切换为英文关注反馈

给定一个二维网格 **board** 和一个字典中的单词列表 **words**，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

**示例:**

```
输入: 
words = ["oath","pea","eat","rain"] and board =
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

输出: ["eat","oath"]
```

**说明:**
你可以假设所有输入都由小写字母 `a-z` 组成。

**提示:**

- 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
- 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： [实现Trie（前缀树）](https://leetcode-cn.com/problems/implement-trie-prefix-tree/description/)。

```java
public class Solution {
    class TrieNode {
        private String val = "";
        private TrieNode[] next = new TrieNode[26];
    }

    int rows, cols;
    List<String> res = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        rows = board.length;
        cols = board[0].length;
        if (rows == 0 || cols == 0) return res;

        //建立字典树的模板
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curr = root;
            for (char ch : word.toCharArray()) {
                if (curr.next[ch - 'a'] == null) curr.next[ch - 'a'] = new TrieNode();
                curr = curr.next[ch - 'a'];
            }
            curr.val = word;
        }

        //DFS模板
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                dfs(board, root, i, j);
            }
        }

        return res;
    }

    private void dfs(char[][] board, TrieNode root, int x, int y) {
        if (x < 0 || y < 0 || x >= rows || y >= cols)   return;

        char c = board[x][y];

        if (c == '.' || root.next[c - 'a'] == null) return;

        root = root.next[c - 'a'];
        if (root.val != ""){
            res.add(root.val);
            root.val = "";          //防止结果出现相同集合
        }

        board[x][y] = '.'; //表示已经访问

        dfs(board, root, x - 1, y);
        dfs(board, root, x + 1, y);
        dfs(board, root, x, y - 1);
        dfs(board, root, x, y + 1);

        board[x][y] = c;    //dfs做完之后恢复原值
    }

}
```





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

#### [面试题59 - II. 队列的最大值](https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/)

请定义一个队列并实现函数 `max_value` 得到队列里的最大值，要求函数`max_value`、`push_back` 和 `pop_front` 的**均摊**时间复杂度都是O(1)。

若队列为空，`pop_front` 和 `max_value` 需要返回 -1

**示例 1：**

```
输入: 
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
```

**示例 2：**

```
输入: 
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出: [null,-1,-1]
```

 

**限制：**

- `1 <= push_back,pop_front,max_value的总操作数 <= 10000`
- `1 <= value <= 10^5`

```java
class MaxQueue {

    private Queue<Integer> q;   //q用来进出栈
    private Deque<Integer> helpDq; //helpDq用来存最大
    public MaxQueue() {
        q = new LinkedList<>();
        helpDq = new LinkedList<>();
    }
    
    //最大值的实现原理，将当前队列中最大的元素放在helpDq的头---
    //每次队列进来一个元素，在helpDq中从队尾看，前面只能比其值大
    public int max_value() {
        return helpDq.isEmpty() ? -1 : helpDq.peek();
    }
    
    public void push_back(int value) {
        q.offer(value);
        while(!helpDq.isEmpty() && value > helpDq.peekLast()){
            helpDq.pollLast();
        }
        helpDq.offer(value);
    }
    
    public int pop_front() {
        if(q.isEmpty()) return -1;

        int val = q.poll();
        if(helpDq.peek() == val)
            helpDq.poll();
        return val;
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

    Queue<Integer> q;
    /** Initialize your data structure here. */
    public MyStack() {
        q = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q.offer(x);
        //size - 1 次的交换刚好可以交换顺序
        for (int i = 1; i < q.size(); i++) {
            q.add(q.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q.poll();
    }

    /** Get the top element. */
    public int top() {
        return q.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
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

#### [84. 柱状图中最大的矩形（栈）](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/)

给定 *n* 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

 

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/histogram.png)

以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 `[2,1,5,6,2,3]`。

 

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/histogram_area.png)

图中阴影部分为所能勾勒出的最大矩形面积，其面积为 `10` 个单位。

 

**示例:**

```
输入: [2,1,5,6,2,3]
输出: 10
```

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        s.push(-1);
        //注意stack中存的值是0——len
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : heights[i]);
            if (s.peek() == -1 || h >= heights[s.peek()]){
                s.push(i);
            }else {
                if(s.isEmpty()) return maxArea;

                int top = s.pop();
                //这里计算的是以heightsp[top]为高度，可以得到的最大面积
                //i - s.peek() - 1 代表满足height是最小值的范围
                //s.peek()代表上一个小于当前值的位置，只能从其后面开始算
                //i代表右边第一个不满足的情况
                maxArea = Math.max(maxArea, heights[top] * (i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }
}
```

#### [85. 最大矩形(栈)](https://leetcode-cn.com/problems/maximal-rectangle/)

给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

**示例:**

```
输入:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
输出: 6
```

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1')
                height[i] = 1;
        }
        int result = largestInline(height);
        for (int row = 1; row < matrix.length; row++) {
            updateHeight(matrix, height, row);
            result = Math.max(result, largestInline(height));
        }
        return result;
    }

    //如果位置上是0就置为0，如果是1就height + 1
    private void updateHeight(char[][] matrix, int[] height, int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[row][i] == '1')  height[i] += 1;
            else height[i] = 0;
        }
    }

    private int largestInline(int[] heights) {
        int len = heights.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        s.push(-1);
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : heights[i]);
            if (s.peek() == -1 || h >= heights[s.peek()]){
                s.push(i);
            }else {
                if(s.isEmpty()) return maxArea;
                int top = s.pop();
                maxArea = Math.max(maxArea, heights[top] * (i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }

}
```

#### [394. 字符串解码(栈)](https://leetcode-cn.com/problems/decode-string/)

给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: `k[encoded_string]`，表示其中方括号内部的 *encoded_string* 正好重复 *k* 次。注意 *k* 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 *k* ，例如不会出现像 `3a` 或 `2[4]` 的输入。

**示例:**

```
s = "3[a]2[bc]", 返回 "aaabcbc".
s = "3[a2[c]]", 返回 "accaccacc".
s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
```

```java
class Solution {
    public String decodeString(String s) {
        Stack<Integer> inStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()){
            if (Character.isDigit(ch)){
                k = k * 10 + ch - '0';
            }else if (ch == '['){
                inStack.push(k);
                strStack.push(curr);
                curr = new StringBuilder();
                k = 0;
            }else if (ch == ']'){
                StringBuilder tmp = curr;
                curr = strStack.pop();
                for (k = inStack.pop(); k > 0; k--) curr.append(tmp);
            }else curr.append(ch);
        }

        return curr.toString();
    }
}
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

# 图

#### [802. 找到最终的安全状态](https://leetcode-cn.com/problems/find-eventual-safe-states/)

难度中等47

在有向图中, 我们从某个节点和每个转向处开始, 沿着图的有向边走。 如果我们到达的节点是终点 (即它没有连出的有向边), 我们停止。

现在, 如果我们最后能走到终点，那么我们的起始节点是*最终安全*的。 更具体地说, 存在一个自然数 `K`, 无论选择从哪里开始行走, 我们走了不到 `K` 步后必能停止在一个终点。

哪些节点最终是安全的？ 结果返回一个有序的数组。

该有向图有 `N` 个节点，标签为 `0, 1, ..., N-1`, 其中 `N` 是 `graph` 的节点数. 图以以下的形式给出: `graph[i]` 是节点 `j` 的一个列表，满足 `(i, j)` 是图的一条有向边。

```
示例：
输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
输出：[2,4,5,6]
这里是上图的示意图。
```

![Illustration of graph](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/03/17/picture1.png)

**提示：**

- `graph` 节点数不超过 `10000`.
- 图的边数不会超过 `32000`.
- 每个 `graph[i]` 被排序为不同的整数列表， 在区间 `[0, graph.length - 1]` 中选取。

```java
public class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        boolean[] safe = new boolean[n];
        List<HashSet<Integer>> copyG = new ArrayList<>();
        List<HashSet<Integer>> reverseG = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            copyG.add(new HashSet<>());
            reverseG.add(new HashSet<>());
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (graph[i].length == 0) {
                queue.offer(i);
            }
            for (int j : graph[i]) {
                copyG.get(i).add(j);
                reverseG.get(j).add(i);
            }
        }

        while (!queue.isEmpty()) {
            int j = queue.poll();
            safe[j] = true;
            for (int i : reverseG.get(j)) {
                copyG.get(i).remove(j);
                if (copyG.get(i).isEmpty()) {
                    queue.offer(i);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (safe[i]){
                res.add(i);
            }
        }

        return res;
    }
}
```

```java
//package LeetcodeAlgorithm.N801_N900.N802找到最终安全的状态;
public class Solution2 {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int len = graph.length;
        int[] color = new int[len];

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (dfs(i, color, graph)){
                res.add(i);
            }
        }

        return res;
    }

    private boolean dfs(int node, int[] color, int[][] graph) {
        if (color[node] > 0){
            return color[node] == 2;
        }

        color[node] = 1;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == 2){
                continue;
            }
            if (color[neighbor] == 1 || !dfs(neighbor, color, graph)){
                return false;
            }
        }

        color[node] = 2;
        return true;
    }
}
```

#### [133. 克隆图](https://leetcode-cn.com/problems/clone-graph/)

难度中等148

给你无向 **[连通](https://baike.baidu.com/item/连通图/6460995?fr=aladdin)** 图中一个节点的引用，请你返回该图的 [**深拷贝**](https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin)（克隆）。

图中的每个节点都包含它的值 `val`（`int`） 和其邻居的列表（`list[Node]`）。

```
class Node {
    public int val;
    public List<Node> neighbors;
}
```

 

**测试用例格式：**

简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（`val = 1`），第二个节点值为 2（`val = 2`），以此类推。该图在测试用例中使用邻接列表表示。

**邻接列表** 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。

给定节点将始终是图中的第一个节点（值为 1）。你必须将 **给定节点的拷贝** 作为对克隆图的引用返回。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/02/01/133_clone_graph_question.png)

```
输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
输出：[[2,4],[1,3],[2,4],[1,3]]
解释：
图中有 4 个节点。
节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/02/01/graph.png)

```
输入：adjList = [[]]
输出：[[]]
解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
```

**示例 3：**

```
输入：adjList = []
输出：[]
解释：这个图是空的，它不含任何节点。
```

**示例 4：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/02/01/graph-1.png)

```
输入：adjList = [[2],[1]]
输出：[[2],[1]]
```

 

**提示：**

1. 节点数不超过 100 。
2. 每个节点值 `Node.val` 都是唯一的，`1 <= Node.val <= 100`。
3. 无向图是一个[简单图](https://baike.baidu.com/item/简单图/1680528?fr=aladdin)，这意味着图中没有重复的边，也没有自环。
4. 由于图是无向的，如果节点 *p* 是节点 *q* 的邻居，那么节点 *q* 也必须是节点 *p* 的邻居。
5. 图是连通图，你可以从给定节点访问到所有节点。

```JAVA
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Solution {
    public Node cloneGraph(Node node) {
        Map<Node, Node> visited = new HashMap<>();
        return dfs(node, visited);
    }

    //DFS 遍历图
    private Node dfs(Node node, Map<Node, Node> visited) {
        if (node == null) return node;

        if (visited.containsKey(node)) return visited.get(node);

        // Create a clone for the given node.
        // Note that we don't have cloned neighbors as of now, hence [].
        Node clone = new Node(node.val, new ArrayList<>());

        // The key is original node and value being the clone node.
        visited.put(node, clone);

        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node n : node.neighbors) {
            clone.neighbors.add(dfs(n, visited));
        }

        return clone;
    }
}
```

```JAVA
//BFS
public class Solution2 {
    public Node cloneGraph(Node node) {
        if (node == null) return node;

        Map<Node, Node> visited = new HashMap<>();
        Node clone = new Node(node.val, new ArrayList<>());
        visited.put(node, clone);

        Deque<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            for (Node neighbor : tmp.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.offer(neighbor);
                }
                visited.get(tmp).neighbors.add(visited.get(neighbor));
            }
        }

        return clone;
    }
}
```

#### [417. 太平洋大西洋水流问题](https://leetcode-cn.com/problems/pacific-atlantic-water-flow/)

给定一个 `m x n` 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。

规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。

请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。

 **提示：**

1. 输出坐标的顺序不重要
2. *m* 和 *n* 都小于150

 **示例：**

 

```
给定下面的 5x5 矩阵:

  太平洋 ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * 大西洋

返回:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
```

```java
//DFS 解法
public class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return res;
        }

        int n = matrix[0].length;
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1 ,0}};
        for (int i = 0; i < m; i++) {
            dfs(matrix, dir, pac, i, 0);    //第一列
            dfs(matrix, dir, atl, i, n - 1);    //最后一列
        }

        for (int i = 0; i < n; i++) {
            dfs(matrix, dir, pac, 0, i);        //  第一行
            dfs(matrix, dir, atl, m - 1, i);    //  最后一行
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int[][] matrix, int[][] dir, boolean[][] visited, int i, int j) {
        int m = matrix.length, n = matrix[0].length;
        visited[i][j] = true;
        for (int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];
            if (x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || matrix[i][j] > matrix[x][y]) {
                continue;
            }
            dfs(matrix, dir, visited, x, y);
        }
    }
}
```

```java
//BFS
public class Solution2 {
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;

        int m = matrix.length, n = matrix[0].length;
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, n - 1});
            pac[i][0] = true;
            atl[i][n - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{m - 1, i});
            pac[0][i] = true;
            atl[m - 1][i] = true;
        }
        bfs(matrix, pQueue, pac);
        bfs(matrix, aQueue, atl);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j])
                    res.add(Arrays.asList(i, j));
            }
        }

        return res;
    }

    private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        int m = matrix.length, n = matrix[0].length;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] d : dir) {
                int x = curr[0] + d[0];
                int y = curr[1] + d[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] ||
                       matrix[x][y] < matrix[curr[0]][curr[1]]) {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }
}
```



# 中位数/滑动窗口



#### [76. 最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/)

难度困难553收藏分享切换为英文关注反馈

给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。

**示例：**

```
输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
```

**说明：**

- 如果 S 中不存这样的子串，则返回空字符串 `""`。
- 如果 S 中存在这样的子串，我们保证它是唯一的答案。

```java
package LeetcodeAlgorithm.N0___100.N76_MinimumWindowSubstring;

public class Solution2 {
    public String minWindow(String s, String t) {
        //winFreq   表示S的字串词频数组  会变化
        //tFreq     表示T词频数组
        //distance  表示滑动窗口内部包含T中的字符的个数
        int sLen = s.length();
        int tLen = t.length();

        if (sLen == 0 || tLen == 0 || sLen < tLen) return "";

        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();

        //ascii('z') = 122
        int[] winFreq = new int[128];
        int[] tFreq = new int[128];

        for (char c : charArrayT) {
            tFreq[c]++;
        }

        //滑动窗口内部包含多少T中的字符，对应字符频数超过不重复计算
        int count = 0;
        int minLen = sLen + 1;
        int begin = 0;

        int left = 0;
        int right = 0;
        // [left, right)
        while (right < sLen) {
            char r = charArrayS[right];
            if (tFreq[r] == 0) {    //当前字符在t中没有出现过，右移
                right++;
                continue;   //不执行后面的逻辑, 直接开始下一次
            }

            if (winFreq[r] < tFreq[r]){
                count++;
            }
            winFreq[r]++;
            right++;

            while (count == tLen){

                if (right - left < minLen){
                     minLen = right - left;
                     begin = left;
                }

                char l = charArrayS[left];
                if (tFreq[l] == 0){
                    left++;
                    continue;
                }

                //执行这里相当于left在里面
                if (winFreq[l] == tFreq[l]){
                    count--;
                }
                winFreq[l]--;
                left++;
            }
        }

        if (minLen == sLen + 1) return "";

        return s.substring(begin, begin + minLen);
    }
}
```





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

# BFS|DFS|

#### [102. 二叉树的层次遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

例如:
给定二叉树: `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回其层次遍历结果：

```
[
  [3],
  [9,20],
  [15,7]
]
```

```java
//BFS
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();

        if(root == null)    return res;

        q.offer(root);
        while(!q.isEmpty()){
            int levelCount = q.size();
            List<Integer> tmp = new ArrayList<>();

            for(int i = 0; i< levelCount; i++){
                TreeNode node = q.poll();
                if(node.left != null)   q.offer(node.left);
                if(node.right != null)  q.offer(node.right);
                tmp.add(node.val);
            }
            res.add(tmp);
        }

        return res;
    }
}
```

#### [107. 二叉树的层次遍历 II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/)

给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回其自底向上的层次遍历为：

```
[
  [15,7],
  [9,20],
  [3]
]
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
         List<List<Integer>> res = new LinkedList<>();
         Queue<TreeNode> q = new LinkedList<>();

        if(root == null)    return res;

        q.offer(root);
        while(!q.isEmpty()){
            int levelCount = q.size();
            List<Integer> tmp = new ArrayList<>();

            for(int i = 0; i< levelCount; i++){
                TreeNode node = q.poll();
                if(node.left != null)   q.offer(node.left);
                if(node.right != null)  q.offer(node.right);
                tmp.add(node.val);
            }
            res.add(0, tmp);//和上一题相比只是改变了这里
        }

        return res;
    }
}
```

#### [103. 二叉树的锯齿形层次遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)

给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回锯齿形层次遍历如下：

```
[
  [3],
  [20,9],
  [15,7]
]
```

```jAVA
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        
        if(root == null)    return res;

        q.offer(root);
        boolean order = true;
        while(!q.isEmpty()){
            int levelSize = q.size();
            List<Integer> tmp = new ArrayList<>();
            for(int i = 0; i < levelSize; i++){
                TreeNode node = q.poll();
                if(node.left != null)  q.offer(node.left);
                if(node.right != null) q.offer(node.right);
                
                //只是在这里做了手脚，其他的地方一点没变
                if(order)   tmp.add(node.val);
                else    tmp.add(0, node.val);
            }
            order = order == true ? false : true;
            res.add(tmp);
        }

        return res;
    }
}
```



#### [987. 二叉树的垂序遍历](https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree/)

给定二叉树，按***垂序*遍历**返回其结点值。

对位于 `(X, Y)` 的每个结点而言，其左右子结点分别位于 `(X-1, Y-1)` 和 `(X+1, Y-1)`。

把一条垂线从 `X = -infinity` 移动到 `X = +infinity` ，每当该垂线与结点接触时，我们按从上到下的顺序报告结点的值（ `Y` 坐标递减）。

如果两个结点位置相同，则首先报告的结点值较小。

按 `X` 坐标顺序返回非空报告的列表。每个报告都有一个结点值列表。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/02/1236_example_1.PNG)

```
输入：[3,9,20,null,null,15,7]
输出：[[9],[3,15],[20],[7]]
解释： 
在不丧失其普遍性的情况下，我们可以假设根结点位于 (0, 0)：
然后，值为 9 的结点出现在 (-1, -1)；
值为 3 和 15 的两个结点分别出现在 (0, 0) 和 (0, -2)；
值为 20 的结点出现在 (1, -1)；
值为 7 的结点出现在 (2, -2)。
```

**示例 2：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/02/23/tree2.png)**

```
输入：[1,2,3,4,5,6,7]
输出：[[4],[2],[1,5,6],[3],[7]]
解释：
根据给定的方案，值为 5 和 6 的两个结点出现在同一位置。
然而，在报告 "[1,5,6]" 中，结点值 5 排在前面，因为 5 小于 6。
```

 

**提示：**

1. 树的结点数介于 `1` 和 `1000` 之间。
2. 每个结点值介于 `0` 和 `1000` 之间。

```java
public class Solution {
    List<int[]> list = new ArrayList<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        DFS(root, 0, 0);

        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] != b[1]){
                    //根据x排序
                    return a[1] - b[1];
                }else if (a[2] != b[2]){
                    //根据y递减的速度
                    return -a[2] + b[2];
                }else {
                    //在同一位置按照值从小到大排序
                    return a[0] - b[0];
                }
            }
        });

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int j = i; //找到可以加到一起的边界,i代表左边界，j代表右边界的下一个

            while(j < list.size() && list.get(i)[1] == list.get(j)[1]) j++;

            List<Integer> tmp = new ArrayList<>();
            for (int k = i; k < j; k++) tmp.add(list.get(k)[0]);
            res.add(tmp);

            i = j - 1;
        }
        
        return res;
    }

    private void DFS(TreeNode node, int x, int y){
        if(node == null)    return;
        list.add(new int[]{node.val, x, y});
        DFS(node.left, x - 1, y - 1);
        DFS(node.right, x + 1, y - 1);
    }
}
```

#### [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)

给定一个由 `'1'`（陆地）和 `'0'`（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

**示例 1:**

```
输入:
11110
11010
11000
00000

输出: 1
```

**示例 2:**

```
输入:
11000
11000
00100
00011

输出: 3
```

```java
public class Solution {
    private int m;//行数
    private int n;//列数
    public int numIslands(char[][] grid) {
        m = grid.length;
        if (m == 0) return 0;
        n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1'){
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] != '1') return;

        grid[i][j] = '0';

        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
    
}
```

#### [144. 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)

难度中等213

给定一个二叉树，返回它的 *前序* 遍历。

 **示例:**

```
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [1,2,3]
```

```java
//递归
class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null) return res;

        res.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);

        return res;
    }
}
```

```java
//迭代

//其他知识：stack是类不需要实现类就可以，queue是接口需要实现类
//java中Deque双端队列中实现了栈的方法
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //Stack<TreeNode> stack = new Stack<>(); 
        Deque<TreeNode> stack = new LinkedList<>();
        
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            if(node != null){
                res.add(node.val);
                stack.push(node.right);
                node = node.left;
            }else{
                node = stack.pop();
            }
        }
        
        return res;
    }
}
```



#### [94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

给定一个二叉树，返回它的*中序* 遍历。

**示例:**

```
输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
```

**进阶:** 递归算法很简单，你可以通过迭代算法完成吗？

```java
//递归
class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return res;

        inorderTraversal(root.left);
        res.add(root.val);
        inorderTraversal(root.right);

        return res;
    }
}
```

```java
//迭代
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
       List<Integer> res = new ArrayList<>();
       Deque<TreeNode> stack = new LinkedList<>();

        if(root == null)    return res;

        TreeNode node = root;
        //注意不能先将root加入stack
        while(node != null || !stack.isEmpty()){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else{
                node = stack.pop();
                res.add(node.val);
                node = node.right;
            }
        }

        return res;
    }
}
```

#### [145. 二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

给定一个二叉树，返回它的 *后序* 遍历。

**示例:**

```
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
```

**进阶:** 递归算法很简单，你可以通过迭代算法完成吗？

```java
class Solution {
    
    public List<Integer> postorderTraversal(TreeNode root) {
        
        List<Integer> res = new ArrayList<>();
        
        return  postOrder(root, res);
    }

    private List<Integer> postOrder(TreeNode node, List<Integer> res){
        if(node == null) return res;

        postOrder(node.left, res);
        postOrder(node.right, res);
        res.add(node.val);

        return res;
    }
}
```

```java
//迭代
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        //List<Integer> res = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>(); //这样才可以使用循环链表的方法
        Deque<TreeNode> stack = new LinkedList<>();

        if(root == null)   return res;

        TreeNode node = root;
        stack.push(node);
        while(!stack.isEmpty()){
            node = stack.pop();
            res.addFirst(node.val);
            if(node.left != null) stack.push(node.left);
            if(node.right != null) stack.push(node.right);
        }

        return res;
    }
}
```



####  [255. 验证前序遍历序列二叉搜索树](https://leetcode-cn.com/problems/verify-preorder-sequence-in-binary-search-tree/)

难度中等21收藏分享切换为英文关注反馈

给定一个整数数组，你需要验证它是否是一个二叉搜索树正确的先序遍历序列。

你可以假定该序列中的数都是不相同的。

参考以下这颗二叉搜索树：

```
     5
    / \
   2   6
  / \
 1   3
```

**示例 1：**

```
输入: [5,2,6,1,3]
输出: false
```

**示例 2：**

```
输入: [5,2,1,3,6]
输出: true
```

**进阶挑战：**

您能否使用恒定的空间复杂度来完成此题？

这个题需要理解如下知识点：

- 先序遍历，如果递减，一定是左子树；
- 如果出现非递减的值，意味着到了某个节点的右子树；
- 利用栈来寻找该节点，最后一个比当前元素小的从栈弹出的元素即为该节点的父亲节点，而且当前元素父节点即为新的下限值；
- 后续的元素一定是比当前的下限值要大的，否则return false；

```java
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack = new Stack();
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < preorder.length; i++) {
            if (preorder[i] < min) return false;
            while (!stack.isEmpty() && preorder[i]>stack.peek()){
                min = stack.pop();
            }
            stack.push(preorder[i]);
        }
        return true;
    }
}
```

#### [面试题33. 二叉搜索树的后序遍历序列](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 `true`，否则返回 `false`。假设输入的数组的任意两个数字都互不相同。

 

参考以下这颗二叉搜索树：

```
     5
    / \
   2   6
  / \
 1   3
```

**示例 1：**

```
输入: [1,6,3,2,5]
输出: false
```

**示例 2：**

```
输入: [1,3,2,6,5]
输出: true
```

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        // 单调栈使用，单调递增的单调栈
        Deque<Integer> stack = new LinkedList<>();
        int pervElem = Integer.MAX_VALUE;
        // 逆向遍历，就是翻转的先序遍历
        for (int i = postorder.length - 1;i>=0;i--){
            // 左子树元素必须要小于递增栈被peek访问的元素，否则就不是二叉搜索树
            if (postorder[i] > pervElem){
                return false;
            }
            while (!stack.isEmpty() && postorder[i] < stack.peek()){
                // 数组元素小于单调栈的元素了，表示往左子树走了，记录下上个根节点
                // 找到这个左子树对应的根节点，之前右子树全部弹出，不再记录，因为不可能在往根节点的右子树走了
                pervElem = stack.pop();
            }
            // 这个新元素入栈
            stack.push(postorder[i]);
        }
        return true;
    }
}






//解题思路
//后序遍历，最后一个为根节点，遍历数组找到第一个大于根节点的，然后判断后面的全部都是大于根节点的，然后分为两部分，左右子树，左子树为lefr到temp-1，右子树为temp到right-1，递归。
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        if(postorder.length == 1){
            return true;
        }
        if(postorder.length == 0){
            return true;
        }
        boolean flag = verify(postorder,0,postorder.length-1);
        return flag;

    }
    public boolean verify(int [] postorder,int left, int right){
        if(right <= left){
            return true;
        }
        int temp = left;
        //找到第一个大于根节点的值
        while((temp < right)&&(postorder[temp] < postorder[right])){
            temp++;
        }
        //判断后面是否都大于根节点
        for(int i = temp; i < right; i++){
            if(postorder[i] < postorder[right]){
                return false;
            }
        }
        
        return verify(postorder,left,temp - 1) && verify(postorder,temp,right - 1);
    }
}
```

#### [面试题07. 重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

 例如，给出

```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
```

返回如下的二叉树：

```
    3
   / \
  9  20
    /  \
   15   7
```

```java
package 剑指offer.N07根据前序中序重建二叉树;

public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > inorder.length - 1 || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        for (int i = 0; i <= inEnd; i++) {
            if (preorder[preStart] == inorder[i])
                inIndex = i;
        }

        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd,preorder, inorder);
        return root;
    }
}

```

#### [297. 二叉树的序列化与反序列化](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/)

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

**示例:** 

```
你可以将以下二叉树：

    1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"
```

**提示:** 这与 LeetCode 目前使用的方式一致，详情请参阅 [LeetCode 序列化二叉树的格式](https://leetcode-cn.com/faq/#binary-tree)。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

**说明:** 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。动态规划套路详解

```java
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serial(new StringBuilder(), root).toString();
    }
    
    // Generate preorder string
    private StringBuilder serial(StringBuilder str, TreeNode root) {
        if (root == null) return str.append("#");
        str.append(root.val).append(",");
        serial(str, root.left).append(",");
        serial(str, root.right);
        return str;
    }

    public TreeNode deserialize(String data) {
        return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
    }
    
    // Use queue to simplify position move
    private TreeNode deserial(Queue<String> q) {
        String val = q.poll();
        if ("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserial(q);
        root.right = deserial(q);
        return root;
    }
}
```



#### [110. 平衡二叉树](https://leetcode-cn.com/problems/balanced-binary-tree/)

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

> 一个二叉树*每个节点* 的左右两个子树的高度差的绝对值不超过1。

**示例 1:**

给定二叉树 `[3,9,20,null,null,15,7]`

```
    3
   / \
  9  20
    /  \
   15   7
```

返回 `true` 。

**示例 2:**

给定二叉树 `[1,2,2,3,3,null,null,4,4]`

```
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
```

返回 `false` 。

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }

    private int dfs(TreeNode node){
        if(node == null)    return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        if(left == -1 || right == -1 || Math.abs(left - right) > 1) 
            return -1;
        
        return Math.max(left, right) + 1;
    }
}
```

#### [98. 验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/)

给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

- 节点的左子树只包含**小于**当前节点的数。
- 节点的右子树只包含**大于**当前节点的数。
- 所有左子树和右子树自身必须也是二叉搜索树。

**示例 1:**

```
输入:
    2
   / \
  1   3
输出: true
```

**示例 2:**

```
输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
```

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode root, TreeNode min, TreeNode max){
        if(root == null)    return true;

        if((min != null && root.val <= min.val) || (max != null && root.val >= max.val))
            return  false;

        return  helper(root.left, min, root) && helper(root.right, root, max);
    }
}
```

#### [543. 二叉树的直径](https://leetcode-cn.com/problems/diameter-of-binary-tree/)

给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。

**示例 :**
给定二叉树

```
          1
         / \
        2   3
       / \     
      4   5    
```

返回 **3**, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

**注意：**两结点之间的路径长度是以它们之间边的数目表示。

```java
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res);

        return res[0];
    }

    private int dfs(TreeNode node, int[] res){
        if(node == null)    return 0;

        int left = dfs(node.left, res);
        int right = dfs(node.right, res);

        res[0] = Math.max(res[0], left + right);

        return Math.max(left, right) + 1; 
    }
}
```



#### [236. 二叉树的最近公共祖先（面试题68）](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

[百度百科](https://baike.baidu.com/item/最近公共祖先/8918834?fr=aladdin)中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（**一个节点也可以是它自己的祖先**）。”

例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/binarytree.png)

 

**示例 1:**

```
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
```

**示例 2:**

```
输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
```

**说明:**

- 所有节点的值都是唯一的。
- p、q 为不同节点且均存在于给定的二叉树中。



如果每个节点都有父指针，那么我们可以从 p 和 q 返回以获取它们的祖先。在这个遍历过程中，我们得到的第一个公共节点是 LCA 节点。我们可以在遍历树时将父指针保存在字典中。

算法：

- 从根节点开始遍历树。
- 在找到 p 和 q 之前，将父指针存储在字典中。
- 一旦我们找到了 p 和 q，我们就可以使用父亲字典获得 p 的所有祖先，并添加到一个称为祖先的集合中。
  同样，我们遍历节点 q 的祖先。如果祖先存在于为 p 设置的祖先中，这意味着这是 p 和 q 之间的第一个共同祖先（同时向上遍历），因此这是 LCA 节点。

```java
//迭代
class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();

        // HashMap for parent pointers
        Map<TreeNode, TreeNode> parent = new HashMap<>();

        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {

            TreeNode node = stack.pop();

            // While traversing the tree, keep saving the parent pointers.
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();

        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }

}
```

复杂度分析

时间复杂度：O(N),其中 N 是二进制树中的节点数。在最坏的情况下，我们可能会访问二叉树的所有节点。
空间复杂度：O(N),在堆栈使用的最坏情况下，每个节点的父指针字典和祖先集的空间为 N，斜二叉树的高度可能为 N。

```java
//DFS 递归
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // 递归结束条件为找到了 p、q 节点，或者后续没有节点了
    if (root == null || root == p || root == q) return root;
      
    // 递归遍历左右子树
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    // 如果 root 节点左右子树中，都查找出了 p、q 节点，那么说明 root 节点就是最近的公共节点
    if (left != null && right != null) return root;
    // 否则，只要不为 null 的一方就是最近的公共节点，因为它是最先被查出来的
    return left != null ? left : right;    
  }
}
```





#### [111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

**说明:** 叶子节点是指没有子节点的节点。

**示例:**

给定二叉树 `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最小深度  2.

```java
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if(left == 0 || right == 0) return Math.max(left, right) + 1;

        return Math.min(left, right) + 1;
    }
}
```

#### [257. 二叉树的所有路径](https://leetcode-cn.com/problems/binary-tree-paths/)

给定一个二叉树，返回所有从根节点到叶子节点的路径。

**说明:** 叶子节点是指没有子节点的节点。

**示例:**

```
输入:

   1
 /   \
2     3
 \
  5

输出: ["1->2->5", "1->3"]

解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
```



方法一：递归
最直观的方法是使用递归。在递归遍历二叉树时，需要考虑当前的节点和它的孩子节点。如果当前的节点不是叶子节点，则在当前的路径末尾添加该节点，并递归遍历该节点的每一个孩子节点。如果当前的节点是叶子节点，则在当前的路径末尾添加该节点后，就得到了一条从根节点到叶子节点的路径，可以把该路径加入到答案中。

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //递归
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, root, sb);
        return res;
    }

    private void helper(List<String> res, TreeNode node, StringBuilder sb){
        if(node == null)    return;

        int len = sb.length();

        if(node.left == null && node.right == null) res.add(sb.append(node.val).toString());
        if(node.left != null || node.right != null){
            sb.append(node.val).append("->");
            helper(res, node.left, sb);
            helper(res, node.right, sb);
        }

        sb.setLength(len);//very trick... hard to think 可以画个图理解
            
    }
}
```



```java
class Solution {
    //BFS solution
    //如果到一个节点左右子节点都是空说明遍历到了末尾
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<String> strQueue = new LinkedList<>();
        if(root == null)    return res;
        queue.add(root);
        strQueue.add("");

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            String curStr = strQueue.poll();
            if(node.left == null && node.right == null) res.add(curStr + node.val);
            if(node.left != null){
                queue.add(node.left);
                strQueue.add(curStr + node.val + "->");
            }
            if(node.right != null){
                queue.add(node.right);
                strQueue.add(curStr + node.val + "->");
            }
        }

        return res;
    }
}
```

```java
public class Solution {
//DFS - Stack
public List<String> binaryTreePaths(TreeNode root) {
    List<String> list=new ArrayList<String>();
    Stack<TreeNode> sNode=new Stack<TreeNode>();
    Stack<String> sStr=new Stack<String>();
    
    if(root==null) return list;
    sNode.push(root);
    sStr.push("");
    while(!sNode.isEmpty()) {
        TreeNode curNode=sNode.pop();
        String curStr=sStr.pop();
        
        if(curNode.left==null && curNode.right==null) list.add(curStr+curNode.val);
        if(curNode.left!=null) {
            sNode.push(curNode.left);
            sStr.push(curStr+curNode.val+"->");
        }
        if(curNode.right!=null) {
            sNode.push(curNode.right);
            sStr.push(curStr+curNode.val+"->");
        }
    }
    return list;
}
```

#### [112. 路径总和](https://leetcode-cn.com/problems/path-sum/)

给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

**说明:** 叶子节点是指没有子节点的节点。

**示例:** 
给定如下二叉树，以及目标和 `sum = 22`，

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
```

返回 `true`, 因为存在目标和为 22 的根节点到叶子节点的路径 `5->4->11->2`。

```java
//方法一：递归
class Solution {
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
      return false;

    sum -= root.val;
    if ((root.left == null) && (root.right == null))  	return (sum == 0);
     
    return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
  }
}
//复杂度分析
//时间复杂度：我们访问每个节点一次，时间复杂度为O(N) ，其中 N 是节点个数。
//空间复杂度：最坏情况下，整棵树是非平衡的，例如每个节点都只有一个孩子，递归会调用 N 次（树的高度），因此栈的空间开销是 O(N) 。但在最好情况下，树是完全平衡的，高度只有 log(N)，因此在这种情况下空间复杂度只有 O(log(N)) 。
```

```java
//方法二：迭代DFS
class Solution {
  public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
      return false;

    LinkedList<TreeNode> node_stack = new LinkedList();
    LinkedList<Integer> sum_stack = new LinkedList();
    node_stack.add(root);
    sum_stack.add(sum - root.val);

    TreeNode node;
    int curr_sum;
    while ( !node_stack.isEmpty() ) {
      node = node_stack.pollLast();
      curr_sum = sum_stack.pollLast();
      if ((node.right == null) && (node.left == null) && (curr_sum == 0))
        return true;

      if (node.right != null) {
        node_stack.add(node.right);
        sum_stack.add(curr_sum - node.right.val);
      }
      if (node.left != null) {
        node_stack.add(node.left);
        sum_stack.add(curr_sum - node.left.val);
      }
    }
    return false;
  }
}
//复杂度分析

//时间复杂度：和递归方法相同是 O(N)。
//空间复杂度：当树不平衡的最坏情况下是 O(N) 。在最好情况（树是平衡的）下是O(logN)。
```

#### [113. 路径总和 II（回溯法）](https://leetcode-cn.com/problems/path-sum-ii/)

给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

**说明:** 叶子节点是指没有子节点的节点。

**示例:**
给定如下二叉树，以及目标和 `sum = 22`，

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
```

返回:

```
[
   [5,4,11,2],
   [5,8,4,5]
]
```



```java
/**
 *回溯法
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        pathSum(root, sum, tmp, res);
        return res;
    }

    private void pathSum(TreeNode node, int sum, List<Integer> tmp, List<List<Integer>> res) {
        if (node == null) return;

        tmp.add(node.val);
        if (node.left == null && node.right == null && node.val == sum)
            res.add(new ArrayList(tmp));
        else{
            pathSum(node.left, sum - node.val, tmp, res);
            pathSum(node.right, sum - node.val, tmp, res);
        }

        tmp.remove(tmp.size() - 1);//移除最后一个元素
    }
    
}
```

#### [437. 路径总和 III(回溯，难)](https://leetcode-cn.com/problems/path-sum-iii/)

给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

**示例：**

```
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

返回 3。和等于 8 的路径有:

1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11
```

```java
class Solution {
    public int pathSum(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    /**
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     * @param node 树节点
     * @param prefixSumCount 前缀和Map
     * @param target 目标值
     * @param currSum 当前路径和
     * @return 满足题意的解
     */
    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
}
```

#### [114. 二叉树展开为链表](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/)

前序展开

给定一个二叉树，[原地](https://baike.baidu.com/item/原地算法/8010757)将它展开为链表。

例如，给定二叉树

```
    1
   / \
  2   5
 / \   \
3   4   6
```

将其展开为：

```
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```

```java
public class Solution{
    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode curr = root;
        while (curr.right != null) curr = curr.right;
        curr.right = right;
    }
}
```

#### [595.二叉树最长连续序列](https://www.lintcode.com/problem/binary-tree-longest-consecutive-sequence/description)

给一棵二叉树，找到最长连续路径的长度。
这条路径是指 任何的节点序列中的起始节点到树中的任一节点都必须遵循 父-子 联系。最长的连续路径必须是从父亲节点到孩子节点（`不能逆序`）。

您在真实的面试中是否遇到过这个题？ 是

题目纠错

**样例1:**

```
输入:
{1,#,3,2,4,#,#,#,5}
输出:3
说明:
这棵树如图所示
   1
    \
     3
    / \
   2   4
        \
         5
最长连续序列是3-4-5，所以返回3.
```

**样例2:**

```
输入:
{2,#,3,2,#,1,#}
输出:2
说明:
这棵树如图所示：
   2
    \
     3
    / 
   2    
  / 
 1
最长连续序列是2-3，而不是3-2-1，所以返回2.
```

```java

public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
    
        // write your code here
        return helper(root, null, 0);
    }

    private int helper(TreeNode root, TreeNode parent, int lengthWithoutRoot) {
        if (root == null) return 0;

        int length = (parent != null && parent.val + 1 == root.val)
                ? lengthWithoutRoot + 1 : 1;

        int left = helper(root.left, root, length);
        int right = helper(root.right, root, length);

        return Math.max(length, Math.max(left, right));
    }

}
```

#### [101. 对称二叉树](https://leetcode-cn.com/problems/symmetric-tree/)

给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 `[1,2,2,3,4,4,3]` 是对称的。

```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

但是下面这个 `[1,2,2,null,3,null,3]` 则不是镜像对称的:

```
    1
   / \
  2   2
   \   \
   3    3
```

**说明:**

如果你可以运用递归和迭代两种方法解决这个问题，会很加分。

```java
//递归
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;

        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode l, TreeNode r){
        if(l == null && r == null) return true;
        if(l == null || r == null) return false;

        return l.val == r.val && isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
    }
}
```

```java
//迭代
public boolean isSymmetric(TreeNode root) {
    if (root == null) return true;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root.left);
    stack.push(root.right);
    while (!stack.empty()) {
        TreeNode n1 = stack.pop(), n2 = stack.pop();
        if (n1 == null && n2 == null) continue;
        if (n1 == null || n2 == null || n1.val != n2.val) return false;
        stack.push(n1.left);
        stack.push(n2.right);
        stack.push(n1.right);
        stack.push(n2.left);
    }
    return true;
}
```

#### [572. 另一个树的子树](https://leetcode-cn.com/problems/subtree-of-another-tree/)

给定两个非空二叉树 **s** 和 **t**，检验 **s** 中是否包含和 **t** 具有相同结构和节点值的子树。**s** 的一个子树包括 **s** 的一个节点和这个节点的所有子孙。**s** 也可以看做它自身的一棵子树。

**示例 1:**
给定的树 s:

```
     3
    / \
   4   5
  / \
 1   2
```

给定的树 t：

```
   4 
  / \
 1   2
```

返回 **true**，因为 t 与 s 的一个子树拥有相同的结构和节点值。

**示例 2:**
给定的树 s：

```
     3
    / \
   4   5
  / \
 1   2
    /
   0
```

给定的树 t：

```
   4
  / \
 1   2
```

返回 **false**。

```java
public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSame(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        
        if (s.val != t.val) return false;
        
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
```

#### [226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)

翻转一棵二叉树。

**示例：**

输入：

```
     4
   /   \
  2     7
 / \   / \
1   3 6   9
```

输出：

```
     4
   /   \
  7     2
 / \   / \
9   6 3   1
```

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        root.left = right;
        root.right = left;
        
        return root;
    }
}
```

#### [124. 二叉树中的最大路径和](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/)

给定一个**非空**二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径**至少包含一个**节点，且不一定经过根节点。

**示例 1:**

```
输入: [1,2,3]

       1
      / \
     2   3

输出: 6
```

**示例 2:**

```
输入: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出: 42
```

```java
class Solution {

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
         /**
        对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
        1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
        2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
        **/
        helper(root);
        return max;
    }

    private int helper(TreeNode node){
        if(node == null) return 0;

        // 如果子树路径和为负则应当置0表示最大路径不包含子树
        int left = Math.max(helper(node.left), 0);
        int right = Math.max(helper(node.right), 0);

        // 判断在该节点包含左右子树的路径和是否大于当前最大路径和
        max = Math.max(max, left + right + node.val);

        return node.val + Math.max(left, right);
    }

}
```

#### [109. 有序链表转换二叉搜索树（双指针）](https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/)

给定一个单链表，其中的元素**按升序排序**，将其转换为高度平衡的二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树*每个节点* 的左右两个子树的高度差的绝对值不超过 1。

**示例:**

```
给定的有序链表： [-10, -3, 0, 5, 9],

一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
```

[答案以及时间空间复杂度分析+附有动图](https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/solution/you-xu-lian-biao-zhuan-huan-er-cha-sou-suo-shu-by-/)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        ListNode slow = head, fast = head, pre_slow = null;
        while(fast != null && fast.next != null){
            pre_slow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode node = new TreeNode(slow.val);
        if(pre_slow != null)   pre_slow.next = null;
        if(slow != head)
            node.left = sortedListToBST(head);
        node.right = sortedListToBST(slow.next);

        return node;    
    }
}
```

#### [面试题36. 二叉搜索树与双向链表](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/)

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

 

为了让您更好地理解问题，以下面的二叉搜索树为例：

 

![img](https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png)

 

我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

 

![img](https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png)

 

特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
//中序遍历的思想
class Solution {
    public Node treeToDoublyList(Node root) {
        if(root == null) return null;
        Deque<Node> stack = new LinkedList<>();
        Node curr = root;
        Node head = null, tail = null, pre = null;
        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                curr = stack.pop();
                //do
                tail = curr;
                if(pre == null){//处理头结点
                    head = curr;
                }else{//不是头结点
                    pre.right = curr;
                    curr.left = pre;
                }
                pre = curr;
                //do end
                curr = curr.right;
            }
        }
        //头尾相连
        tail.right = head;
        head.left = tail;

        return head;
    }
}
```

#### [230. 二叉搜索树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)

给定一个二叉搜索树，编写一个函数 `kthSmallest` 来查找其中第 **k** 个最小的元素。

**说明：**
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

**示例 1:**

```
输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 1
```

**示例 2:**

```
输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 3
```

**进阶：**
如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 `kthSmallest` 函数？

```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int cnt = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                curr = stack.pop();
                if(++cnt == k)    return curr.val;
                curr = curr.right;
            }
        }

        return Integer.MIN_VALUE;
    }
}
```

#### [450. 删除二叉搜索树中的节点](https://leetcode-cn.com/problems/delete-node-in-a-bst/)

给定一个二叉搜索树的根节点 **root** 和一个值 **key**，删除二叉搜索树中的 **key** 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。

一般来说，删除节点可分为两个步骤：

1. 首先找到需要删除的节点；
2. 如果找到了，删除它。

**说明：** 要求算法时间复杂度为 O(h)，h 为树的高度。

**示例:**

```
root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。

一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。

    5
   / \
  4   6
 /     \
2       7

另一个正确答案是 [5,2,6,null,4,null,7]。

    5
   / \
  2   6
   \   \
    4   7
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(root.val < key)
            root.right = deleteNode(root.right, key);
        else if(root.val > key)
            root.left = deleteNode(root.left, key);
        else{
            if(root.left == null)   return root.right;
            else if(root.right == null) return root.left;
            else{
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
        }

        return root;
    }

    private int successor(TreeNode node){
        node = node.right;
        while(node.left != null)    node = node.left;
        return node.val;
    }

}
```

#### [307. 区域和检索 - 数组可修改](https://leetcode-cn.com/problems/range-sum-query-mutable/)

难度中等131收藏分享切换为英文关注反馈

给定一个整数数组  *nums*，求出数组从索引 *i* 到 *j* (*i* ≤ *j*) 范围内元素的总和，包含 *i, j* 两点。

*update(i, val)* 函数可以通过将下标为 *i* 的数值更新为 *val*，从而对数列进行修改。

**示例:**

```
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
```

**说明:**

1. 数组仅可以在 *update* 函数下进行修改。
2. 你可以假设 *update* 函数与 *sumRange* 函数的调用次数是均匀分布的。

```java
//方法一：使用树状数组
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
```

```java
//方法二：线段树方法
public class NumArray {

    private int[] nums;
    private int len;    //nums的长度
    private int[] segmentTree;

    public NumArray(int[] nums) {
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
```



# 回溯法

#### [494. 目标和](https://leetcode-cn.com/problems/target-sum/)

给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 `+` 和 `-`。对于数组中的任意一个整数，你都可以从 `+` 或 `-`中选择一个符号添加在前面。

返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

**示例 1:**

```
输入: nums: [1, 1, 1, 1, 1], S: 3
输出: 5
解释: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

一共有5种方法让最终目标和为3。
```

**注意:**

1. 数组非空，且长度不会超过20。
2. 初始的数组的和不会超过1000。
3. 保证返回的最终结果能被32位整数存下。

```java
class Solution {
    
    int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return 0;
        
        helper(nums, S, 0);
        return count;
    }

    private void helper(int[] nums, int target, int index){
        if(index == nums.length){
            if(target == 0)
                count++;
            return;
        }

        helper(nums, target - nums[index], index + 1);
        helper(nums, target + nums[index], index + 1);
    }

}
```

#### [78. 子集](https://leetcode-cn.com/problems/subsets/)

给定一组**不含重复元素**的整数数组 *nums*，返回该数组所有可能的子集（幂集）。

**说明：**解集不能包含重复的子集。

**示例:**

```
输入: nums = [1,2,3]
输出:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index){
        res.add(new ArrayList<>(tmp));
        for(int i = index; i < nums.length; i++){
            tmp.add(nums[i]);
            backtrack(res, tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
```

```java
//可以运用二进制来
public class Solution2 {
    public List<List<Integer>> subsets(int[] nums) {
        int totalNum = 1<< nums.length;
        List<List<Integer>> res = new ArrayList<>(totalNum);

        //取值的可能性 000， 001， 010，...，111；如果集合有三个元素，就是8种
        for (int i = 0; i < totalNum; i++) {
            List<Integer> tempList = new ArrayList<>();
            //前面给出的000、001这些数，和这些数的每一位去比较，有1就加
            for (int j = 0; j < nums.length; j++) {
                if ((i & 1<<j) != 0){//    “<< ” 大于 “！=” 大于 “&与”
                    tempList.add(nums[j]);
                }
            }
            res.add(tempList);
        }
        return res;
    }
}
```

#### [90. 子集 II](https://leetcode-cn.com/problems/subsets-ii/)

给定一个可能包含重复元素的整数数组 ***nums***，返回该数组所有可能的子集（幂集）。

**说明：**解集不能包含重复的子集。

**示例:**

```
输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tmp, int[] nums, int index){
        res.add(new ArrayList<>(tmp));
        for(int i = index; i < nums.length; i++){
            if(i > index && nums[i] == nums[i - 1])
                continue;
            tmp.add(nums[i]);
            backtrack(res, tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

}
```

#### [39. 组合总和](https://leetcode-cn.com/problems/combination-sum/)

给定一个**无重复元素**的数组 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。

`candidates` 中的数字可以无限制重复被选取。

**说明：**

- 所有数字（包括 `target`）都是正整数。
- 解集不能包含重复的组合。 

**示例 1:**

```
输入: candidates = [2,3,6,7], target = 7,
所求解集为:
[
  [7],
  [2,2,3]
]
```

**示例 2:**

```
输入: candidates = [2,3,5], target = 8,
所求解集为:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

```java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tmp, int[] nums, int target,  int index){
        if(target < 0) return;
        if(target == 0) 
            res.add(new ArrayList<>(tmp));
        
        for(int i = index; i < nums.length; i++){
            tmp.add(nums[i]);
            backtrack(res, tmp, nums, target - nums[i], i);
            tmp.remove(tmp.size() - 1);
        }
    }
}
```

#### [40. 组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/)

难度中等219收藏分享切换为英文关注反馈

给定一个数组 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。

`candidates` 中的每个数字在每个组合中只能使用一次。

**说明：**

- 所有数字（包括目标数）都是正整数。
- 解集不能包含重复的组合。 

**示例 1:**

```
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

**示例 2:**

```
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```

```java
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tmp, int[] nums, int target,  int index){
        if(target < 0) return;
        if(target == 0) 
            res.add(new ArrayList<>(tmp));
        
        for(int i = index; i < nums.length; i++){
            //注意：这里是i > index 代表第一次可以进，第二次就不能进，画图方便理解
            if(i > index && nums[i] == nums[i - 1])   continue;
            tmp.add(nums[i]);
            backtrack(res, tmp, nums, target - nums[i], i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
```

#### [46. 全排列](https://leetcode-cn.com/problems/permutations/)

难度中等566收藏分享切换为英文关注反馈

给定一个 **没有重复** 数字的序列，返回其所有可能的全排列。

**示例:**

```
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), nums);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tmp, int[] nums){
        if(tmp.size() == nums.length){
            res.add(new ArrayList<>(tmp));
            return;
        }
            
        for(int i = 0; i < nums.length; i++){
            if(tmp.contains(nums[i]))   continue;
            tmp.add(nums[i]);
            backtrack(res, tmp, nums);
            tmp.remove(tmp.size() - 1);
        }
    }
}
```

#### [47. 全排列 II](https://leetcode-cn.com/problems/permutations-ii/)

难度中等245收藏分享切换为英文关注反馈

给定一个可包含重复数字的序列，返回所有不重复的全排列。

**示例:**

```
输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

[题解，最好画图理解](https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/)

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> tmp, int[] nums, boolean[] used){
        if(tmp.size() == nums.length){
            res.add(new ArrayList<>(tmp));
            return;
        }
            
        for(int i = 0; i < nums.length; i++){
            //1.第i个元素已经使用，则跳过
            if(used[i]) continue;
            //2.第i个元素还未被使用,且遇到i和i-1的数字相等,且i-1已未被使用，此时递归树剪枝
            if(i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) continue;
            
            //标记为使用
            used[i] = true;
            tmp.add(nums[i]);
            backtrack(res, tmp, nums, used);
            tmp.remove(tmp.size() - 1);
            used[i] = false;
            //使用完标记为false
        }
    }
}
```

#### [131. 分割回文串](https://leetcode-cn.com/problems/palindrome-partitioning/)

难度中等235收藏分享切换为英文关注反馈

给定一个字符串 *s*，将 *s* 分割成一些子串，使每个子串都是回文串。

返回 *s* 所有可能的分割方案。

**示例:**

```
输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]
```

```java
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
        if(start == s.length()){
            list.add(new ArrayList<>(tempList));
            return;
        }
            
        for(int i = start; i < s.length(); i++){
            if(isPalindrome(s, start, i)){
                tempList.add(s.substring(start, i + 1));
                backtrack(list, tempList, s, i + 1);
                tempList.remove(tempList.size() - 1);//除去的是当前行
            }
        }
    }

    public boolean isPalindrome(String s, int low, int high){
        while(low < high)
            if(s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    } 
}
```

#### [79. 单词搜索](https://leetcode-cn.com/problems/word-search/)

难度中等344收藏分享切换为英文关注反馈

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

**示例:**

```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false
```

```java
public class Solution {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (existRecursive(board, word, i, j, 0, visited)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existRecursive(char[][] board, String word,int row, int col, int index, boolean[][] visited) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return false;
        if (visited[row][col] || word.charAt(index) != board[row][col])
            return false;
        if (index == word.length() - 1)
            return true;
        visited[row][col] = true;

        boolean up = existRecursive(board, word, row + 1, col, index + 1, visited);
        boolean down = existRecursive(board, word, row - 1, col, index + 1, visited);
        boolean left = existRecursive(board, word, row, col - 1, index + 1, visited);
        boolean rigth = existRecursive(board, word, row, col + 1, index + 1, visited);
        if (up || down || left || rigth){
            return true;
        }
        visited[row][col] = false;
        return false;
    }
}
```

#### [面试题13. 机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

难度中等18收藏分享切换为英文关注反馈

地上有一个m行n列的方格，从坐标 `[0,0]` 到坐标 `[m-1,n-1]` 。一个机器人从坐标 `[0, 0] `的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 

**示例 1：**

```
输入：m = 2, n = 3, k = 1
输出：3
```

**示例 1：**

```
输入：m = 3, n = 1, k = 0
输出：1
```

**提示：**

- `1 <= n,m <= 100`
- `0 <= k <= 20`

```java
package 剑指offer.N13机器人的运动范围;

public class GoodSolution {
    //带有递归的DFS,不用借助栈的数据结构来完成，，，，记住 Stack 和 DFS 的关联，从本题目中多总结
    int count;
    boolean[][] visited;

    public int movingCount(int m, int n, int k) {
        if (m <= 0 || n <= 0 || k < 0) return 0;
        visited = new boolean[m][n];
        dfs(0, 0, m, n, k);
        return count;
    }

    public void dfs(int i, int j, int m, int n, int k) {
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] == true) return;

        if (!canK(i, j, k)) return;
        visited[i][j] = true;
        count++;
        dfs(i + 1, j, m, n, k);
        dfs(i - 1, j, m, n, k);
        dfs(i, j + 1, m, n, k);
        dfs(i, j - 1, m, n, k);
    }

    public boolean canK(int i, int j, int k) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum <= k;
    }
}
```



# 动态规划

[labuladong](https://leetcode-cn.com/u/labuladong/)发布于 9 个月前33.6k动态规划递归记忆化C++

我润色了本文，并添加了更多干货内容，希望本文成为解决动态规划的一部「指导方针」。再说句题外话，我们的公众号开号至今写了起码十几篇文章拆解动态规划问题，我都整理到了公众号菜单的「文章目录」中，**它们都提到了动态规划的解题框架思维，本文就系统总结一下**。这段时间本人也从非科班小白成长到刷通半个 LeetCode，所以我总结的套路可能不适合各路大神，但是应该适合大众，毕竟我自己也是一路摸爬滚打过来的。

算法技巧就那几个套路，如果你心里有数，就会轻松很多，本文就来扒一扒动态规划的裤子，形成一套解决这类问题的思维框架。废话不多说了，上干货。

**动态规划问题的一般形式就是求最值**。动态规划其实是运筹学的一种最优化方法，只不过在计算机问题上应用比较多，比如说让你求**最长**递增子序列呀，**最小**编辑距离呀等等。

既然是要求最值，核心问题是什么呢？**求解动态规划的核心问题是穷举**。因为要求最值，肯定要把所有可行的答案穷举出来，然后在其中找最值呗。

动态规划就这么简单，就是穷举就完事了？我看到的动态规划问题都很难啊！

首先，动态规划的穷举有点特别，因为这类问题**存在「重叠子问题」**，如果暴力穷举的话效率会极其低下，所以需要「备忘录」或者「DP table」来优化穷举过程，避免不必要的计算。

而且，动态规划问题一定会**具备「最优子结构」**，才能通过子问题的最值得到原问题的最值。

另外，虽然动态规划的核心思想就是穷举求最值，但是问题可以千变万化，穷举所有可行解其实并不是一件容易的事，只有列出**正确的「状态转移方程**」才能正确地穷举。

以上提到的重叠子问题、最优子结构、状态转移方程就是动态规划三要素。具体什么意思等会会举例详解，但是在实际的算法问题中，**写出状态转移方程是最困难的**，这也就是为什么很多朋友觉得动态规划问题困难的原因，我来提供我研究出来的一个思维框架，辅助你思考状态转移方程：

明确「状态」 -> 定义 dp 数组/函数的含义 -> 明确「选择」-> 明确 base case。

下面通过斐波那契数列问题和凑零钱问题来详解动态规划的基本原理。前者主要是让你明白什么是重叠子问题（斐波那契数列严格来说不是动态规划问题），后者主要举集中于如何列出状态转移方程。

请读者不要嫌弃这个例子简单，**只有简单的例子才能让你把精力充分集中在算法背后的通用思想和技巧上，而不会被那些隐晦的细节问题搞的莫名其妙**。想要困难的例子，历史文章里有的是。

### 一、斐波那契数列

**1、暴力递归**

斐波那契数列的数学形式就是递归的，写成代码就是这样：

```
int fib(int N) {
    if (N == 1 || N == 2) return 1;
    return fib(N - 1) + fib(N - 2);
}
```

这个不用多说了，学校老师讲递归的时候似乎都是拿这个举例。我们也知道这样写代码虽然简洁易懂，但是十分低效，低效在哪里？假设 n = 20，请画出递归树。

PS：但凡遇到需要递归的问题，最好都画出递归树，这对你分析算法的复杂度，寻找算法低效的原因都有巨大帮助。

![img](https://pic.leetcode-cn.com/27c9f7e7b100243c8a0b1e3cb664988cc3f0456abb89d01e5625382c48ac7dd1.jpg)

这个递归树怎么理解？就是说想要计算原问题 `f(20)`，我就得先计算出子问题 `f(19)` 和 `f(18)`，然后要计算 `f(19)`，我就要先算出子问题 `f(18)` 和 `f(17)`，以此类推。最后遇到 `f(1)` 或者 `f(2)` 的时候，结果已知，就能直接返回结果，递归树不再向下生长了。

**递归算法的时间复杂度怎么计算？子问题个数乘以解决一个子问题需要的时间。**

子问题个数，即递归树中节点的总数。显然二叉树节点总数为指数级别，所以子问题个数为 O(2^n)。

解决一个子问题的时间，在本算法中，没有循环，只有 f(n - 1) + f(n - 2) 一个加法操作，时间为 O(1)。

所以，这个算法的时间复杂度为 O(2^n)，指数级别，爆炸。

观察递归树，很明显发现了算法低效的原因：存在大量重复计算，比如 `f(18)` 被计算了两次，而且你可以看到，以 `f(18)` 为根的这个递归树体量巨大，多算一遍，会耗费巨大的时间。更何况，还不止 `f(18)` 这一个节点被重复计算，所以这个算法及其低效。

这就是动态规划问题的第一个性质：**重叠子问题**。下面，我们想办法解决这个问题。

**2、带备忘录的递归解法**

明确了问题，其实就已经把问题解决了一半。即然耗时的原因是重复计算，那么我们可以造一个「备忘录」，每次算出某个子问题的答案后别急着返回，先记到「备忘录」里再返回；每次遇到一个子问题先去「备忘录」里查一查，如果发现之前已经解决过这个问题了，直接把答案拿出来用，不要再耗时去计算了。

一般使用一个数组充当这个「备忘录」，当然你也可以使用哈希表（字典），思想都是一样的。

```
int fib(int N) {
    if (N < 1) return 0;
    // 备忘录全初始化为 0
    vector<int> memo(N + 1, 0);
    // 初始化最简情况
    return helper(memo, N);
}
 
int helper(vector<int>& memo, int n) {
    // base case 
    if (n == 1 || n == 2) return 1;
    // 已经计算过
    if (memo[n] != 0) return memo[n];
    memo[n] = helper(memo, n - 1) + 
                helper(memo, n - 2);
    return memo[n];
}
```

现在，画出递归树，你就知道「备忘录」到底做了什么。

![img](https://pic.leetcode-cn.com/259446b44cb809bf270ec26b6367ce4d1f32c6c65a30ae9cd0848d471939c886.jpg)

实际上，带「备忘录」的递归算法，把一棵存在巨量冗余的递归树通过「剪枝」，改造成了一幅不存在冗余的递归图，极大减少了子问题（即递归图中节点）的个数。

![img](https://pic.leetcode-cn.com/0a5bcfd19a01f6b310f3933c46ed316e41ec61a5311e24f006be2c22bbbb14cc.jpg)

递归算法的时间复杂度怎么算？子问题个数乘以解决一个子问题需要的时间。

子问题个数，即图中节点的总数，由于本算法不存在冗余计算，子问题就是 `f(1)`, `f(2)`, `f(3)` ... `f(20)`，数量和输入规模 n = 20 成正比，所以子问题个数为 O(n)。

解决一个子问题的时间，同上，没有什么循环，时间为 O(1)。

所以，本算法的时间复杂度是 O(n)。比起暴力算法，是降维打击。

至此，带备忘录的递归解法的效率已经和迭代的动态规划解法一样了。实际上，这种解法和迭代的动态规划已经差不多了，只不过这种方法叫做「自顶向下」，动态规划叫做「自底向上」。

啥叫「自顶向下」？注意我们刚才画的递归树（或者说图），是从上向下延伸，都是从一个规模较大的原问题比如说 `f(20)`，向下逐渐分解规模，直到 `f(1)` 和 `f(2)` 触底，然后逐层返回答案，这就叫「自顶向下」。

啥叫「自底向上」？反过来，我们直接从最底下，最简单，问题规模最小的 `f(1)` 和 `f(2)` 开始往上推，直到推到我们想要的答案 `f(20)`，这就是动态规划的思路，这也是为什么动态规划一般都脱离了递归，而是由循环迭代完成计算。

**3、dp 数组的迭代解法**

有了上一步「备忘录」的启发，我们可以把这个「备忘录」独立出来成为一张表，就叫做 DP table 吧，在这张表上完成「自底向上」的推算岂不美哉！

```
int fib(int N) {
    vector<int> dp(N + 1, 0);
    // base case
    dp[1] = dp[2] = 1;
    for (int i = 3; i <= N; i++)
        dp[i] = dp[i - 1] + dp[i - 2];
    return dp[N];
}
```

![img](https://pic.leetcode-cn.com/1b4485704cf963fd49d597040ffbb3b5d4d2ca923752c8c78d00042df66343a9.jpg)

画个图就很好理解了，而且你发现这个 DP table 特别像之前那个「剪枝」后的结果，只是反过来算而已。实际上，带备忘录的递归解法中的「备忘录」，最终完成后就是这个 DP table，所以说这两种解法其实是差不多的，大部分情况下，效率也基本相同。

这里，引出「状态转移方程」这个名词，实际上就是描述问题结构的数学形式：
$$
f(n) = \begin{cases} 1, n = 1, 2 \\ f(n - 1) + f(n - 2), n > 2 \end{cases}
$$
为啥叫「状态转移方程」？为了听起来高端。你把 f(n) 想做一个状态 n，这个状态 n 是由状态 n - 1 和状态 n - 2 相加转移而来，这就叫状态转移，仅此而已。

你会发现，上面的几种解法中的所有操作，例如 return f(n - 1) + f(n - 2)，dp[i] = dp[i - 1] + dp[i - 2]，以及对备忘录或 DP table 的初始化操作，都是围绕这个方程式的不同表现形式。可见列出「状态转移方程」的重要性，它是解决问题的核心。很容易发现，其实状态转移方程直接代表着暴力解法。

**千万不要看不起暴力解，动态规划问题最困难的就是写出状态转移方程**，即这个暴力解。优化方法无非是用备忘录或者 DP table，再无奥妙可言。

这个例子的最后，讲一个细节优化。细心的读者会发现，根据斐波那契数列的状态转移方程，当前状态只和之前的两个状态有关，其实并不需要那么长的一个 DP table 来存储所有的状态，只要想办法存储之前的两个状态就行了。所以，可以进一步优化，把空间复杂度降为 O(1)：

```
int fib(int n) {
    if (n == 2 || n == 1) 
        return 1;
    int prev = 1, curr = 1;
    for (int i = 3; i <= n; i++) {
        int sum = prev + curr;
        prev = curr;
        curr = sum;
    }
    return curr;
}
```

有人会问，动态规划的另一个重要特性「最优子结构」，怎么没有涉及？下面会涉及。斐波那契数列的例子严格来说不算动态规划，因为没有涉及求最值，以上旨在演示算法设计螺旋上升的过程。下面，看第二个例子，凑零钱问题。

### 二、凑零钱问题

先看下题目：给你 `k` 种面值的硬币，面值分别为 `c1, c2 ... ck`，每种硬币的数量无限，再给一个总金额 `amount`，问你**最少**需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1 。算法的函数签名如下：

```
// coins 中是可选硬币面值，amount 是目标金额
int coinChange(int[] coins, int amount);
```

比如说 `k = 3`，面值分别为 1，2，5，总金额 `amount = 11`。那么最少需要 3 枚硬币凑出，即 11 = 5 + 5 + 1。

你认为计算机应该如何解决这个问题？显然，就是把所有肯能的凑硬币方法都穷举出来，然后找找看最少需要多少枚硬币。

**1、暴力递归**

首先，这个问题是动态规划问题，因为它具有「最优子结构」的。**要符合「最优子结构」，子问题间必须互相独立**。啥叫相互独立？你肯定不想看数学证明，我用一个直观的例子来讲解。

比如说，你的原问题是考出最高的总成绩，那么你的子问题就是要把语文考到最高，数学考到最高…… 为了每门课考到最高，你要把每门课相应的选择题分数拿到最高，填空题分数拿到最高…… 当然，最终就是你每门课都是满分，这就是最高的总成绩。

得到了正确的结果：最高的总成绩就是总分。因为这个过程符合最优子结构，“每门科目考到最高”这些子问题是互相独立，互不干扰的。

但是，如果加一个条件：你的语文成绩和数学成绩会互相制约，此消彼长。这样的话，显然你能考到的最高总成绩就达不到总分了，按刚才那个思路就会得到错误的结果。因为子问题并不独立，语文数学成绩无法同时最优，所以最优子结构被破坏。

回到凑零钱问题，为什么说它符合最优子结构呢？比如你想求 `amount = 11` 时的最少硬币数（原问题），如果你知道凑出 `amount = 10` 的最少硬币数（子问题），你只需要把子问题的答案加一（再选一枚面值为 1 的硬币）就是原问题的答案，因为硬币的数量是没有限制的，子问题之间没有相互制，是互相独立的。

那么，既然知道了这是个动态规划问题，就要思考**如何列出正确的状态转移方程**？

**先确定「状态」**，也就是原问题和子问题中变化的变量。由于硬币数量无限，所以唯一的状态就是目标金额 `amount`。

**然后确定 `dp` 函数的定义**：当前的目标金额是 `n`，至少需要 `dp(n)` 个硬币凑出该金额。

**然后确定「选择」并择优**，也就是对于每个状态，可以做出什么选择改变当前状态。具体到这个问题，无论当的目标金额是多少，选择就是从面额列表 `coins` 中选择一个硬币，然后目标金额就会减少：

```python
# 伪码框架
def coinChange(coins: List[int], amount: int):
    # 定义：要凑出金额 n，至少要 dp(n) 个硬币
    def dp(n):
        # 做选择，选择需要硬币最少的那个结果
        for coin in coins:
            res = min(res, 1 + dp(n - coin))
        return res
    # 我们要求的问题是 dp(amount)
    return dp(amount)
```

**最后明确 base case**，显然目标金额为 0 时，所需硬币数量为 0；当目标金额小于 0 时，无解，返回 -1：

```python
def coinChange(coins: List[int], amount: int):

    def dp(n):
        # base case
        if n == 0: return 0
        if n < 0: return -1
        # 求最小值，所以初始化为正无穷
        res = float('INF')
        for coin in coins:
            subproblem = dp(n - coin)
            # 子问题无解，跳过
            if subproblem == -1: continue
            res = min(res, 1 + subproblem)

        return res if res != float('INF') else -1
    
    return dp(amount)
```

至此，状态转移方程其实已经完成了，以上算法已经是暴力解法了，以上代码的数学形式就是状态转移方程：
$$
dp(n) = \begin{cases} 0, n = 0 \\ -1, n < 0\\ min\{dp(n - coin) + 1 | coin \in coins\}, n > 0 \end{cases}
$$
至此，这个问题其实就解决了，只不过需要消除一下重叠子问题，比如 `amount = 11, coins = {1,2,5}` 时画出递归树看看：

![img](https://pic.leetcode-cn.com/7db5a80dbffaf2337c0e0323437442d007987bce16b7fa86affbc5ca0e1132d0.jpg)

**时间复杂度分析：子问题总数 x 每个子问题的时间**。

子问题总数为递归树节点个数，这个比较难看出来，是 O(n^k)，总之是指数级别的。每个子问题中含有一个 for 循环，复杂度为 O(k)。所以总时间复杂度为 O(k * n^k)，指数级别。

**2、带备忘录的递归**

只需要稍加修改，就可以通过备忘录消除子问题：

```python
def coinChange(coins: List[int], amount: int):
    # 备忘录
    memo = dict()
    def dp(n):
        # 查备忘录，避免重复计算
        if n in memo: return memo[n]

        if n == 0: return 0
        if n < 0: return -1
        res = float('INF')
        for coin in coins:
            subproblem = dp(n - coin)
            if subproblem == -1: continue
            res = min(res, 1 + subproblem)
        
        # 记入备忘录
        memo[n] = res if res != float('INF') else -1
        return memo[n]
    
    return dp(amount)
```

不画图了，很显然「备忘录」大大减小了子问题数目，完全消除了子问题的冗余，所以子问题总数不会超过金额数 n，即子问题数目为 O(n)。处理一个子问题的时间不变，仍是 O(k)，所以总的时间复杂度是 O(kn)。

**3、dp 数组的迭代解法**

当然，我们也可以自底向上使用 dp table 来消除重叠子问题，`dp` 数组的定义和刚才 `dp` 函数类似，定义也是一样的：

**`dp[i] = x` 表示，当目标金额为 `i` 时，至少需要 `x` 枚硬币**。

```java
int coinChange(vector<int>& coins, int amount) {
    // 数组大小为 amount + 1，初始值也为 amount + 1
    vector<int> dp(amount + 1, amount + 1);
    // base case
    dp[0] = 0;
    for (int i = 0; i < dp.size(); i++) {
        // 内层 for 在求所有子问题 + 1 的最小值
        for (int coin : coins) {
            // 子问题无解，跳过
            if (i - coin < 0) continue;
            dp[i] = min(dp[i], 1 + dp[i - coin]);
        }
    }
    return (dp[amount] == amount + 1) ? -1 : dp[amount];
}
```

![img](https://pic.leetcode-cn.com/b4e6cf1bb8e2284bfc01dfef6c1a60c19f9c78238061b65370ccc01822161e83.jpg)

PS：为啥 `dp` 数组初始化为 `amount + 1` 呢，因为凑成 `amount` 金额的硬币数最多只可能等于 `amount`（全用 1 元面值的硬币），所以初始化为 `amount + 1` 就相当于初始化为正无穷，便于后续取最小值。

### 三、最后总结

第一个斐波那契数列的问题，解释了如何通过「备忘录」或者「dp table」的方法来优化递归树，并且明确了这两种方法本质上是一样的，只是自顶向下和自底向上的不同而已。

第二个凑零钱的问题，展示了如何流程化确定「状态转移方程」，只要通过状态转移方程写出暴力递归解，剩下的也就是优化递归树，消除重叠子问题而已。

如果你不太了解动态规划，还能看到这里，真得给你鼓掌，相信你已经掌握了这个算法的设计技巧。

**计算机解决问题其实没有任何奇技淫巧，它唯一的解决办法就是穷举**，穷举所有可能性。算法设计无非就是先思考“如何穷举”，然后再追求“如何聪明地穷举”。

列出动态转移方程，就是在解决“如何穷举”的问题。之所以说它难，一是因为很多穷举需要递归实现，二是因为有的问题本身的解空间复杂，不那么容易穷举完整。

备忘录、DP table 就是在追求“如何聪明地穷举”。用空间换时间的思路，是降低时间复杂度的不二法门，除此之外，试问，还能玩出啥花活？

最后，点击我的头像可以查看更多详细题解，希望读者多多点赞，让我感受到你的认可～

PS：**我的所有算法文章都已经上传到了 Github 仓库**：[**fucking-algorithm**](https://github.com/labuladong/fucking-algorithm)，共 60 多篇，绝对精品，肯定让你收获满满，**求个 star 不过分吧**～

PPS：我最近精心制作了一份电子书《labuladong的算法小抄》，分为「动态规划」「数据结构」「算法思维」「高频面试」四个章节，目录如下，限时开放下载，如有需要可扫码到我的公众号 **labuladong** 后台回复关键词「pdf」下载：

![目录](https://pic.leetcode-cn.com/66b9e7466b8c493b67a709f3ebd87c88daa7ca0b85d960288dec10aff8c7fc3e.jpg)

# 买卖股票（dp）

二、状态转移框架
现在，我们完成了「状态」的穷举，我们开始思考每种「状态」有哪些「选择」，应该如何更新「状态」。只看「持有状态」，可以画个状态转移图。

通过这个图可以很清楚地看到，每种状态（0 和 1）是如何转移而来的。根据这个图，我们来写一下状态转移方程：

```
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
              max(   选择 rest  ,           选择 sell      )

解释：今天我没有持有股票，有两种可能：
要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。

dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
              max(   选择 rest  ,           选择 buy         )

解释：今天我持有着股票，有两种可能：
要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
```


这个解释应该很清楚了，如果 buy，就要从利润中减去 prices[i]，如果 sell，就要给利润增加 prices[i]。今天的最大利润就是这两种可能选择中较大的那个。而且注意 k 的限制，我们在选择 buy 的时候，把 k 减小了 1，很好理解吧，当然你也可以在 sell 的时候减 1，一样的。

现在，我们已经完成了动态规划中最困难的一步：状态转移方程。如果之前的内容你都可以理解，那么你已经可以秒杀所有问题了，只要套这个框架就行了。不过还差最后一点点，就是定义 base case，即最简单的情况。

```
dp[-1][k][0] = 0
解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
dp[-1][k][1] = -infinity
解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
dp[i][0][0] = 0
解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
dp[i][0][1] = -infinity
解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
```


把上面的状态转移方程总结一下：

```
base case：
dp[-1][k][0] = dp[i][0][0] = 0
dp[-1][k][1] = dp[i][0][1] = -infinity

状态转移方程：
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
```


读者可能会问，这个数组索引是 -1 怎么编程表示出来呢，负无穷怎么表示呢？这都是细节问题，有很多方法实现。现在完整的框架已经完成，下面开始具体化。

#### [121. 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

给定一个数组，它的第 *i* 个元素是一支给定股票第 *i* 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。

注意你不能在买入股票前卖出股票。

**示例 1:**

```
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
```

**示例 2:**

```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

```java
//普通解法  推荐
public class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxprofit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice)
                minPrice = prices[i];
            else if (prices[i] - minPrice > maxprofit) {
                maxprofit = prices[i] - minPrice;
            }
        }
        return maxprofit;
    }
}
```

```java
//dp解法
public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0)  return 0;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1){
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        
        return dp[n - 1][0];
    }
}

//dp解法优化
public class Solution {
     public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0)  return 0;
        int dp_i_0 = 0, dp_i_1 = -prices[0];

        for (int i = 1; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);	
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }

        return dp_i_0;
    }
}
```

#### [122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)

给定一个数组，它的第 *i* 个元素是一支给定股票第 *i* 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

**注意：**你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

**示例 1:**

```
输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
```

**示例 2:**

```
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```

**示例 3:**

```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

```java
//推荐普通解法
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        for(int i = 0; i < prices.length - 1; i++){
            if(prices[i + 1] > prices[i])
                maxProfit += prices[i + 1] - prices[i];
        }

        return maxProfit;
    }
}
```





# 动态规划



#### [面试题60. n个骰子的点数](https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/)

把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

 **示例 1:**

```
输入: 1
输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
```

**示例 2:**

```
输入: 2
输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
```

 **限制：**

```
1 <= n <= 11
```

```java
class Solution {
    public double[] twoSum(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];

        for (int s = 1; s <= 6; s++) {
            dp[1][s] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int s = i; s <= 6 * i; s++) {
                for (int d = 1; d <= 6; d++) {
                    if (s - d < i - 1)  break;
                    dp[i][s] += dp[i - 1][s - d];
                }
            }
        }

        double total = Math.pow(6.0, n);
        double[] ans = new double[5 * n + 1];
        for (int i = n; i <= 6 * n; i++) {
            ans[i - n] = (double)dp[n][i] / total;
        }

        return ans;
    }
}
```



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

#### [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/)

给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 `-1`。

**示例 1:**

```
输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1
```

**示例 2:**

```
输入: coins = [2], amount = 3
输出: -1
```

**说明**:
你可以认为每种硬币的数量是无限的。

```java
//自顶向下的动态规划
public class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount + 1]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0)   return 0;

        if (count[rem] != 0)    return count[rem];
        int min = Integer.MAX_VALUE;
        for (int coin : coins){
            int res = coinChange(coins, rem - coin, count);
            if (res < min && res >= 0)
                min = 1 + res;
        }
        count[rem] = min == Integer.MAX_VALUE ? -1 : min;

        return count[rem];
    }
}
// 复杂度分析

//时间复杂度：O(Sn)，其中 S 是金额，n 是面额数。我们一共需要计算 S 个状态的答案，且每个状态 F(S) 由于上面的记忆化的措施只计算了一次，而计算一个状态的答案需要枚举 n 个面额值，所以一共需要 O(Sn) 的时间复杂度。
//空间复杂度：O(S)，我们需要额外开一个长为 S 的数组来存储计算出来的答案 F(S)。
```



```java
//自底向上
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];

        //方便排除没有合适金钱匹配的情况
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i)
                    dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```





#### [62. 不同路径](https://leetcode-cn.com/problems/unique-paths/)

难度中等456收藏分享切换为英文关注反馈

一个机器人位于一个 *m x n* 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/robot_maze.png)

例如，上图是一个7 x 3 的网格。有多少可能的路径？

 

**示例 1:**

```
输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
```

**示例 2:**

```
输入: m = 7, n = 3
输出: 28
```

 

**提示：**

- `1 <= m, n <= 100`
- 题目数据保证答案小于等于 `2 * 10 ^ 9`

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int N = m + n -2;
        int k = m - 1;
        double res = 1;

        for(int i = 1; i <= k; i++){
            res = res * (N - k + i) / i;
        }

        return (int)res;
    }
}
```

#### [63. 不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/)

难度中等241收藏分享切换为英文关注反馈

一个机器人位于一个 *m x n* 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/robot_maze.png)

网格中的障碍物和空位置分别用 `1` 和 `0` 来表示。

**说明：***m* 和 *n* 的值均不超过 100。

**示例 1:**

```
输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
```

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0)
            return 0;

        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        int[][] dp = new int[rows][cols];

        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    if(i == 0 && j == 0) dp[i][j] = 1;
                    else if(i == 0) dp[i][j] = dp[i][j - 1];
                    else if(j == 0) dp[i][j] = dp[i - 1][j];
                    else    dp[i][j] = dp[i - 1][j] + dp[i][j- 1]; 
                }
            }
        }

        return dp[rows - 1][cols - 1];
    }
}
```

#### [64. 最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)

难度中等404收藏分享切换为英文关注反馈

给定一个包含非负整数的 *m* x *n* 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

**说明：**每次只能向下或者向右移动一步。

**示例:**

```
输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
```

```java
class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0)    return 0;

        int rows = grid.length, cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(i == 0 && j == 0)
                    dp[i][j] = grid[i][j];
                else{
                    if(i == 0)  dp[i][j] = dp[i][j - 1] + grid[i][j];
                    else if(j == 0) dp[i][j] = dp[i - 1][j] + grid[i][j];    
                    else{
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                    }
                }
            }
        }

        return dp[rows - 1][cols - 1];
    }
}
//思路2
//在原来的数组上进行迭代，这样会改变原来数组的值
```



#### [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/)

难度中等335收藏分享切换为英文关注反馈

给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

例如，给定三角形：

```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
```

自顶向下的最小路径和为 `11`（即，**2** + **3** + **5** + **1** = 11）。

**说明：**

如果你可以只使用 *O*(*n*) 的额外空间（*n* 为三角形的总行数）来解决这个问题，那么你的算法会很加分。

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int row = triangle.size();
        int column = triangle.get(row - 1).size();

        int[][] dp = new int[row][column];
        dp[0][0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;

        for(int i = 1; i < row; i++){
            for(int j = 0; j <= i; j ++){
                if(j == 0)  dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                else if(j == i) dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                }
            }
        }

        for(int j = 0; j < row; j++){
            res = Math.min(dp[column - 1][j], res);
        }

        return res;
    }
}
```

```java
//dp[]一维数组的方法
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
            if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int row = triangle.size();
        int column = triangle.get(row - 1).size();

        int[] dp = new int[column];
        for(int i = 0; i < column; i++){
            dp[i] = triangle.get(row - 1).get(i);
        }

        for(int i = row - 2; i >= 0; i--){
            for(int j = 0; j <= i; j ++){
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }

        return dp[0];
    }
}
```

#### [198. 打家劫舍](https://leetcode-cn.com/problems/house-robber/)

难度简单674收藏分享切换为英文关注反馈

你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，**如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警**。

给定一个代表每个房屋存放金额的非负整数数组，计算你**在不触动警报装置的情况下，**能够偷窃到的最高金额。

**示例 1:**

```
输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
```

**示例 2:**

```
输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
```

```java
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0)    return 0;

        int n = nums.length;
        int[] dp = new int[n + 2];
        for(int i = n - 1; i >= 0; i--){
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }

        return dp[0];
    }
}
```

#### [213. 打家劫舍 II](https://leetcode-cn.com/problems/house-robber-ii/)

难度中等208收藏分享切换为英文关注反馈

你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都**围成一圈，**这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，**如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警**。

给定一个代表每个房屋存放金额的非负整数数组，计算你**在不触动警报装置的情况下，**能够偷窃到的最高金额。

**示例 1:**

```
输入: [2,3,2]
输出: 3
解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
```

**示例 2:**

```
输入: [1,2,3,1]
输出: 4
解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。
```

```java
//其实就是把环拆成两个队列，一个是从0到n-1，另一个是从1到n，然后返回两个结果最大的。
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0)    return 0;
        //此边界条件需要注意
        if(nums.length == 1)    return nums[0];

        int n = nums.length;
        int[] dp = new int[n + 2];
        for(int i = n - 1; i >= 1; i--){
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }
        int res = dp[1];

        dp[n - 1] = 0;
        for(int i = n - 2; i >= 0; i--){
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }

        return Math.max(res, dp[0]);
    }
}
```

#### [279. 完全平方数](https://leetcode-cn.com/problems/perfect-squares/)

难度中等335收藏分享切换为英文关注反馈

给定正整数 *n*，找到若干个完全平方数（比如 `1, 4, 9, 16, ...`）使得它们的和等于 *n*。你需要让组成和的完全平方数的个数最少。

**示例 1:**

```
输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
```

**示例 2:**

```
输入: n = 13
输出: 2
解释: 13 = 4 + 9.
```

```java
dp[0] = 0 
dp[1] = dp[0]+1 = 1
dp[2] = dp[1]+1 = 2
dp[3] = dp[2]+1 = 3
dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 } 
      = Min{ dp[3]+1, dp[0]+1 } 
      = 1				
dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 } 
      = Min{ dp[4]+1, dp[1]+1 } 
      = 2
						.
						.
						.
dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 } 
       = Min{ dp[12]+1, dp[9]+1, dp[4]+1 } 
       = 2
						.
						.
						.
dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
```

```java
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];

        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            dp[i] = i;
            for(int j = 1; j * j <= i; j++){
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}
```

#### [221. 最大正方形](https://leetcode-cn.com/problems/maximal-square/)

在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

**示例:**

```
输入: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

输出: 4
```

**理解 三者取最小+1**

**理解 min(上, 左, 左上) + 1**

如题，动态规划方法的题解中，都会涉及到下列形式的代码：

```java
if (grid(i, j) == 1) {
    dp(i, j) = min(dp(i-1, j), dp(i, j-1), dp(i-1, j-1)) + 1;
}
```

翻译成中文

> 若某格子值为 `1` ，则以此为**右下角**的正方形的、最大边长为：上面的正方形、左面的正方形或左上的正方形中，最小的那个，再加上此格。

先来阐述简单共识

- 若形成正方形（非单 `1`），以当前为右下角的视角看，则需要：当前格、上、左、左上都是 `1`
- 可以换个角度：当前格、上、左、左上都不能受 `0` 的限制，才能成为正方形

![image.png](https://pic.leetcode-cn.com/8c4bf78cf6396c40291e40c25d34ef56bd524313c2aa863f3a20c1f004f32ab0-image.png)

上面详解了 三者取最小 的含义：

- 图1：受限于左上的0
- 图2：受限于上边的0
- 图3：受限于左边的0
- 数字表示：以此为正方形右下角的最大边长
- 黄色表示：格子 `?` 作为右下角的正方形区域

就像 **[木桶的短板理论](https://baike.baidu.com/item/短板理论)** 那样——附近的最小边长，才与 `?` 的最长边长有关。
此时已可得到递推公式 `if (grid[i][j] == 1) f[i][j] = min(f[i-1][j-1], f[i-1][j], f[i][j-1]) + 1;`

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0)    return 0;

        int row = matrix.length, col = matrix[0].length;
        
        //相当于预处理，第一行，第一列为0
        int[][] dp = new int[row + 1][col + 1];
        
        int maxlen = 0;
        for(int i = 1; i <= row; i++){
            for(int j = 1; j <= col; j++){
                if(matrix[i - 1][j - 1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j])
                                            , dp[i][j - 1]) + 1;
                    maxlen = Math.max(maxlen, dp[i][j]);
                }
            }
        }

        return maxlen * maxlen;
    }
}
```

#### [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

难度中等559收藏分享切换为英文关注反馈

给定一个无序的整数数组，找到其中最长上升子序列的长度。

**示例:**

```
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
```

**说明:**

- 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
- 你算法的时间复杂度应该为 O(*n2*) 。

**进阶:** 你能将算法的时间复杂度降低到 O(*n* log *n*) 吗?

```java
//dp思路
//https://leetcode-cn.com/problems/longest-increasing-subsequence/comments/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0)    return 0;

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int res = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(dp[i], res);
        }

        return res;
    }
}
```

```java
//此算法时间复杂度nlogn
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0)    return 0;

        int[] dp = new int[nums.length];
        int res = 0;

        for(int num : nums){
            int lo = 0, hi = res;
            while(lo < hi){
                int mid = lo + ((hi - lo) >>> 1);
                if(dp[mid] < num)	lo = mid + 1;
                else	hi = mid;
            }
            //lo代表插入点 
            //res代表下一次新的有序元素进来应该插入的index位置，也代表当前集合里有几个元素
            dp[lo] = num;
            if(res == lo)   res++;
        }

        return res;
    }
}
```

#### [304. 二维区域和检索 - 矩阵不可变](https://leetcode-cn.com/problems/range-sum-query-2d-immutable/)

难度中等82收藏分享切换为英文关注反馈

给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (*row*1, *col*1) ，右下角为 (*row*2, *col*2)。

![Range Sum Query 2D](https://assets.leetcode-cn.com/aliyun-lc-upload/images/304.png)
上图子矩阵左上角 (row1, col1) = **(2, 1)** ，右下角(row2, col2) = **(4, 3)，**该子矩形内元素的总和为 8。

**示例:**

```
给定 matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
```

**说明:**

1. 你可以假设矩阵不可变。
2. 会多次调用 *sumRegion* 方法*。*
3. 你可以假设 *row*1 ≤ *row*2 且 *col*1 ≤ *col*2。

```java
package LeetcodeAlgorithm.N304RangeSumQuery2D;

class NumMatrix {
    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;

        int m = matrix.length;
        int n = matrix[0].length;
        dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row2 + 1][col1] - dp[row1][col2 + 1] + dp[row1][col1];
    }
}
```





#### [354. 俄罗斯套娃信封问题(最长上升子序问题延伸)](https://leetcode-cn.com/problems/russian-doll-envelopes/)

难度困难108收藏分享切换为英文关注反馈

给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 `(w, h)` 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

**说明:**
不允许旋转信封。

**示例:**

```
输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
输出: 3 
解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
```

```java
public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]){
                    return b[1] - a[1];
                }else {
                    return a[0] - b[0];
                }
            }
        });
        int[] secNums = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            secNums[i] = envelopes[i][1];
        }

        return lengthOfLIS(secNums);
    }

    private int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;

        int[] dp = new int[nums.length];
        int res = 0;

        for (int num : nums){
            int lo = 0, hi = res;
            while (lo < hi){
                int mid = lo + ((hi - lo) >>> 1);
                if (num > dp[mid]) lo = mid + 1;
                else    hi = mid;
            }
            dp[lo] = num;
            //res是下一个插入的点，也是上升子序列的个数
            if (lo == res)  res++;
        }
        
        return res;
    }
    
}
```

#### [368. 最大整除子集（类似于最大上升子串）](https://leetcode-cn.com/problems/largest-divisible-subset/)

给出一个由**无重复的**正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。

如果有多个目标子集，返回其中任何一个均可。

 

**示例 1:**

```
输入: [1,2,3]
输出: [1,2] (当然, [1,3] 也正确)
```

**示例 2:**

```
输入: [1,2,4,8]
输出: [1,2,4,8]
```

```java
//dp 类似于最大的上升子串
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        int[] count = new int[n]; //dp核心,表示第i个位置有几个
        int[] pre = new int[n]; //在第i个位置,记住上一个num的位置
        Arrays.sort(nums);
        Arrays.fill(count, 1);//初始化每个位置的数量
        Arrays.fill(pre, -1);//初始化每个位置的上一个元素

        int max = 0, index = -1; // index存最大值count 的 index
        for(int i = 0; i < n; i++){
            for(int j = i - 1; j >= 0; j--){
                if(nums[i] % nums[j] == 0){
                    if(count[j] + 1 > count[i]){
                        count[i] = count[j] + 1;
                        pre[i] = j;
                    }
                }  
            }
            
            if(count[i] > max){
                max = count[i];
                index = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        while(index != -1){
            res.add(nums[index]);
            index = pre[index];
        }

        return res;
    }
}
```



#### [115. 不同的子序列](https://leetcode-cn.com/problems/distinct-subsequences/)

难度困难150收藏分享切换为英文关注反馈

给定一个字符串 **S** 和一个字符串 **T**，计算在 **S** 的子序列中 **T** 出现的个数。

一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，`"ACE"` 是 `"ABCDE"` 的一个子序列，而 `"AEC"` 不是）

**示例 1:**

```
输入: S = "rabbbit", T = "rabbit"
输出: 3
解释:

如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
(上箭头符号 ^ 表示选取的字母)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
```

**示例 2:**

```
输入: S = "babgbag", T = "bag"
输出: 5
解释:

如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。 
(上箭头符号 ^ 表示选取的字母)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
```

动态规划

`dp[i][j]` 代表 `T` 前 `i` 字符串可以由 `S` `j` 字符串组成最多个数.

所以动态方程:

当 `S[j] == T[i]` , `dp[i][j] = dp[i-1][j-1] + dp[i][j-1]`;

当 `S[j] != T[i]` , `dp[i][j] = dp[i][j-1]`

举个例子,如示例的

![1561970400084.png](https://pic.leetcode-cn.com/a3a1d30700be05cad2e60666f20ab261e7a04b85ed88b854dd1d8cb484909983-1561970400084.png)

对于第一行, `T` 为空,因为空集是所有字符串子集, 所以我们第一行都是 `1`

对于第一列, `S` 为空,这样组成 `T` 个数当然为 0` 了

```java
class Solution {
    //dp[i][j]表示目标串t的前i个元素，与字符串s在前j个位置为止，匹配到了几个可能性的组合
    public int numDistinct(String s, String t) {
        int S = s.length(), T = t.length();
        int[][] dp = new int[S + 1][T + 1];
        
        for(int i = 0; i < S; i ++){
            dp[i][0] = 1;
        }
        for(int i = 1; i <= S; i ++){
            for(int j = 1; j <= T; j ++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[S][T];
    }
}
```

#### [5. 最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)

难度中等1885

给定一个字符串 `s`，找到 `s` 中最长的回文子串。你可以假设 `s` 的最大长度为 1000。

**示例 1：**

```
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
```

**示例 2：**

```
输入: "cbbd"
输出: "bb"
```

//暴力法、dp、中心扩展算法

```java
//dp 算法
class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for(int i = n - 1; i >= 0; i--){
            for(int j = i; j < n; j++){
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]);
                if(dp[i][j] && j - i + 1 > res.length()){
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }
}
```

```java
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
```

#### [72. 编辑距离](https://leetcode-cn.com/problems/edit-distance/)

难度困难586收藏分享切换为英文关注反馈

给定两个单词 *word1* 和 *word2*，计算出将 *word1* 转换成 *word2* 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

1. 插入一个字符
2. 删除一个字符
3. 替换一个字符

**示例 1:**

```
输入: word1 = "horse", word2 = "ros"
输出: 3
解释: 
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
```

**示例 2:**

```
输入: word1 = "intention", word2 = "execution"
输出: 5
解释: 
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
```

```java
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null) return -1;
        if(word1.length() == 0)   return word2.length();
        if(word2.length() == 0)   return word1.length();

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i <= word1.length(); i++){
            dp[i][0] = i;
        }
        for(int j = 0; j <= word2.length(); j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= word1.length(); i++){
            for(int j = 1; j <= word2.length(); j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                //插入dp[i][j-1]+1 在第i个元素后插入一个元素，使得匹配，所以j-1，i不变
                //删除dp[i-1][j]+1 在s1第i个位置删除元素--删除之后不一定会匹配，所以j不变
                //替换dp[i-1][j-1] 替换s1的第i个位置元素使得匹配，所以i-1,j-1            
                    dp[i][j] = Math.min(
                    					Math.min(dp[i][j - 1], dp[i - 1][j]),
                                        dp[i - 1][j - 1]) + 1;
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
```

#### [10. 正则表达式匹配](https://leetcode-cn.com/problems/regular-expression-matching/)

难度困难1028收藏分享切换为英文关注反馈

给你一个字符串 `s` 和一个字符规律 `p`，请你来实现一个支持 `'.'` 和 `'*'` 的正则表达式匹配。

```
'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
```

所谓匹配，是要涵盖 **整个** 字符串 `s`的，而不是部分字符串。

**说明:**

- `s` 可能为空，且只包含从 `a-z` 的小写字母。
- `p` 可能为空，且只包含从 `a-z` 的小写字母，以及字符 `.` 和 `*`。

**示例 1:**

```
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```

**示例 2:**

```
输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
```

**示例 3:**

```
输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
```

**示例 4:**

```
输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
```

**示例 5:**

```
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
```
```java
//回溯
class Solution {
    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        
        boolean isFirstMatch = (s.isEmpty() == false) && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        
        if (p.length()>=2 && p.charAt(1) == '*'){
            return (isFirstMatch && isMatch(s.substring(1), p)) || isMatch(s, p.substring(2));
        }
        
        return isFirstMatch && isMatch(s.substring(1), p.substring(1));
    }
}
```

```
//dp的思路
1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
3, If p.charAt(j) == '*': 
   here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
```

```java
//dp
public class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        
        boolean[][] state = new boolean[s.length() + 1][p.length() + 1];
        state[0][0] = true;
        // no need to initialize state[i][0] as false
        // initialize state[0][j]
        for (int j = 1; j < state[0].length; j++) {
            if (p.charAt(j - 1) == '*') {
                if (state[0][j - 1] || (j > 1 && state[0][j - 2])) {
                    state[0][j] = true;
                }
            } 
        }
        for (int i = 1; i < state.length; i++) {
            for (int j = 1; j < state[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    state[i][j] = state[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    if (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.') {
                        state[i][j] = state[i][j - 2];
                    } else {
                        state[i][j] = state[i - 1][j] || state[i][j - 1] || state[i][j - 2];
                    }
                }
            }
        }
        return state[s.length()][p.length()];
    }
}
```

#### [97. 交错字符串](https://leetcode-cn.com/problems/interleaving-string/)

难度困难144收藏分享切换为英文关注反馈

给定三个字符串 *s1*, *s2*, *s3*, 验证 *s3* 是否是由 *s1* 和 *s2* 交错组成的。

**示例 1:**

```
输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
输出: true
```

**示例 2:**

```
输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
输出: false
```

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length();
        if(s3.length() != len1 + len2){
            return false;
        }
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for(int i = 1; i <= len1; i++){ //base case, go down
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        for(int i = 1; i <= len2; i++){  //base case, go right
            dp[0][i] = dp[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                //case 1, special case, up and left has the same character.
                if(s1.charAt(i - 1) == s3.charAt(i + j - 1) && s2.charAt(j - 1) == s3.charAt(i + j - 1)){
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                //case2, normal case, only from left
                }else if(s1.charAt(i - 1) == s3.charAt(i + j - 1)){
                    dp[i][j] = dp[i - 1][j];
                //case3, normal case, only from up
                }else if(s2.charAt(j - 1) == s3.charAt(i + j - 1)){
                    dp[i][j] = dp[i][j - 1];
                }
                /*pay attention, no other cases here,think of: s1 = abc, s2 = def, s3 = abcdef,
                while dp[2][1] will be one of "abd,adb, bad, bda, dab, dba", and we try to match "abc" no match!
                Think of this problem as a matrix, we are tring to find out if there is a path from [0,0]  to [len1 - 1, len2 - 1], 
                so dp[i][j] == false, only mean dp[i][j] is not on the valid path, does not mean there is no such path exists!
                   a  b  c
                   ☑️ ☑️ ☑️
            d               ☑️
            e               ☑️
            f               ☑️(valid path)
            */
            }
        }
        return dp[len1][len2];
    }
}
```

#### [91. 解码方法](https://leetcode-cn.com/problems/decode-ways/)

难度中等315收藏分享切换为英文关注反馈

一条包含字母 `A-Z` 的消息通过以下方式进行了编码：

```
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

给定一个只包含数字的**非空**字符串，请计算解码方法的总数。

**示例 1:**

```
输入: "12"
输出: 2
解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
```

**示例 2:**

```
输入: "226"
输出: 3
解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
```

```java
class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0)  return 0;
        int n = s.length();

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;

        //i代表第i个元素，其实index为i-1
        for(int i = 2; i <= n; i++){
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if(first >= 1 && first <= 9)
                dp[i] += dp[i - 1];
            if(second >= 10 && second <= 26)
                dp[i] += dp[i - 2];
        }

        return dp[n];
    }
}
```

#### [1143. 最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)

难度中等124收藏分享切换为英文关注反馈

给定两个字符串 `text1` 和 `text2`，返回这两个字符串的最长公共子序列的长度。

一个字符串的 *子序列* 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

若这两个字符串没有公共子序列，则返回 0。

 

**示例 1:**

```
输入：text1 = "abcde", text2 = "ace" 
输出：3  
解释：最长公共子序列是 "ace"，它的长度为 3。
```

**示例 2:**

```
输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc"，它的长度为 3。
```

**示例 3:**

```
输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0。
```

 

**提示:**

- `1 <= text1.length <= 1000`
- `1 <= text2.length <= 1000`
- 输入的字符串只含有小写英文字符。

```java
// 未优化之前
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                if(text1.charAt(i-1) == text2.charAt(j - 1))    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j - 1]);
            }
        }

        return dp[len1][len2];
    }
}
```

```java
//优化后可以进一步降低空间复杂度
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();

        int[] dp = new int[len2 + 1];

        for(int i = 1; i <= len1; i++){
            int tmp = 0;
            for(int j = 1; j <= len2; j++){
                int pre = tmp;  //每一行开始的时候，pre为0
                tmp = dp[j];    //每次dp[j]会参与运算的时候，记录老的值
                //需要排查左边的dp[j-1]发生变化的情况，这种情况需要记录dp[j-1]的old版本
                if(text1.charAt(i-1) == text2.charAt(j - 1))    dp[j] = pre + 1;

                else    dp[j] = Math.max(dp[j - 1], dp[j]);
            }
        }

        return dp[len2];
    }
}
```

#### [1444. 切披萨的方案数](https://leetcode-cn.com/problems/number-of-ways-of-cutting-a-pizza/)

难度困难18收藏分享切换为英文关注反馈

给你一个 `rows x cols` 大小的矩形披萨和一个整数 `k` ，矩形包含两种字符： `'A'` （表示苹果）和 `'.'` （表示空白格子）。你需要切披萨 `k-1` 次，得到 `k` 块披萨并送给别人。

切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。

请你返回确保每一块披萨包含 **至少** 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。

 

**示例 1：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/05/10/ways_to_cut_apple_1.png)**

```
输入：pizza = ["A..","AAA","..."], k = 3
输出：3 
解释：上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。
```

**示例 2：**

```
输入：pizza = ["A..","AA.","..."], k = 3
输出：1
```

**示例 3：**

```
输入：pizza = ["A..","A..","..."], k = 1
输出：1
```

 

**提示：**

- `1 <= rows, cols <= 50`
- `rows == pizza.length`
- `cols == pizza[i].length`
- `1 <= k <= 10`
- `pizza` 只包含字符 `'A'` 和 `'.'` 。

```java
package LeetcodeAlgorithm.N1444NumberofWaysofCuttingaPizza;

public class Solution {
    private int MOD = 1_000_000_007, m, n;
    private Integer dp[][][];
    private int preSum[][];

    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        dp = new Integer[k][m][n];
        preSum = new int[m + 1][n + 1];

        //这个直接就是以为i,j为左上角的时候，其和为多少
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                preSum[i][j] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }

        return dfs(0, 0, k - 1);
    }

    private int dfs(int x, int y, int cut) {
        if (cut == 0 && preSum[x][y] > 0)   return 1;
        if (preSum[x][y] == 0)  return 0;

        if (dp[cut][x][y] != null)  return dp[cut][x][y];

        int ans = 0;
        //Cut horizontal
        for (int i = x + 1; i < m; i++) {
            if (preSum[x][y] - preSum[i][y] > 0){
                ans = (ans + dfs(i, y, cut - 1)) % MOD;
            }
        }

        //Cut vertical
        for (int j = y + 1; j < n; j++) {
            if (preSum[x][y] - preSum[x][j] > 0){
                ans = (ans + dfs(x, j, cut - 1)) % MOD;
            }
        }

        dp[cut][x][y] = ans;
        return ans;
    }
}
```

#### [1449. 数位成本和为目标值的最大数字](https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target/)

难度困难11收藏分享切换为英文关注反馈

给你一个整数数组 `cost` 和一个整数 `target` 。请你返回满足如下规则可以得到的 **最大** 整数：

- 给当前结果添加一个数位（`i + 1`）的成本为 `cost[i]` （`cost` 数组下标从 0 开始）。
- 总成本必须恰好等于 `target` 。
- 添加的数位中没有数字 0 。

由于答案可能会很大，请你以字符串形式返回。

如果按照上述要求无法得到任何整数，请你返回 "0" 。

 

**示例 1：**

```
输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
输出："7772"
解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "997" 也是满足要求的数字，但 "7772" 是较大的数字。
 数字     成本
  1  ->   4
  2  ->   3
  3  ->   2
  4  ->   5
  5  ->   6
  6  ->   7
  7  ->   2
  8  ->   5
  9  ->   5
```

**示例 2：**

```
输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
输出："85"
解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
```

**示例 3：**

```
输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
输出："0"
解释：总成本是 target 的条件下，无法生成任何整数。
```

**示例 4：**

```
输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
输出："32211"
```

 **提示：**

- `cost.length == 9`
- `1 <= cost[i] <= 5000`
- `1 <= target <= 5000`

```java
package LeetcodeAlgorithm.N1449FormLargestIntegerWithDigitsThatAdduptoTarget;

public class Solution {
    public String largestNumber(int[] cost, int target) {
        if(target == 0) return "";
        int[] dp = new int[target + 1];

        //baseCase dp[0] = 0, 其他地方的值，应该从dp[0]开始
        //如果不是从dp[0]开始，那么其值总会为负数
        for (int t = 1; t <= target; t++) {
            dp[t] = Integer.MIN_VALUE;      //这里很关键，思考下为-1会出现什么情况？
            for (int i = 0; i < 9; i++) {
                if (t >= cost[i]){
                    dp[t] = Math.max(dp[t], 1 + dp[t - cost[i]]);
                }
            }
        }

        if (dp[target] < 0){
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 8; i >= 0; i--) {
            while (target >= cost[i] && dp[target] == dp[target - cost[i]] + 1){
                sb.append(i + 1);
                target -= cost[i];
            }
        }

        return sb.toString();
    }
}
```

#### [1458. 两个子序列的最大点积](https://leetcode-cn.com/problems/max-dot-product-of-two-subsequences/)

难度困难17收藏分享切换为英文关注反馈

给你两个数组 `nums1` 和 `nums2` 。

请你返回 `nums1` 和 `nums2` 中两个长度相同的 **非空** 子序列的最大点积。

数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，但不能改变数字间相对顺序。比方说，`[2,3,5]` 是 `[1,2,3,4,5]` 的一个子序列而 `[1,5,3]` 不是。

 

**示例 1：**

```
输入：nums1 = [2,1,-2,5], nums2 = [3,0,-6]
输出：18
解释：从 nums1 中得到子序列 [2,-2] ，从 nums2 中得到子序列 [3,-6] 。
它们的点积为 (2*3 + (-2)*(-6)) = 18 。
```

**示例 2：**

```
输入：nums1 = [3,-2], nums2 = [2,-6,7]
输出：21
解释：从 nums1 中得到子序列 [3] ，从 nums2 中得到子序列 [7] 。
它们的点积为 (3*7) = 21 。
```

**示例 3：**

```
输入：nums1 = [-1,-1], nums2 = [1,1]
输出：-1
解释：从 nums1 中得到子序列 [-1] ，从 nums2 中得到子序列 [1] 。
它们的点积为 -1 。
```

 

**提示：**

- `1 <= nums1.length, nums2.length <= 500`
- `-1000 <= nums1[i], nums2[i] <= 100`

 

```java
package LeetcodeAlgorithm.N1458MaxDotProductofTwoSubsequences;

public class Solution2 {
    public int maxDotProduct(int[] nums1, int[] nums2) {

        //注意：本道题 maxDotProduct 最大点乘，必须是自己算出来的值。
        //所以[-1, -1], [1, 1]这个题算出来的值为 -1; 而不能是 0

        int len1 = nums1.length;
        int len2 = nums2.length;

        int[][] dp = new int[len1][len2];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                dp[i][j] = nums1[i] * nums2[j];
                if (i > 0 && j > 0) dp[i][j] += Math.max(0, dp[i - 1][j - 1]);
                if (i > 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                if (j > 0) dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
            }
        }

        return dp[len1 - 1][len2 - 1];
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

[谷歌面试题：又双叒叕是位运算，最详细的自动机推导过程](https://mp.weixin.qq.com/s/FQ22S0aR_J7uLnRRU0S96w)

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = (ones ^ num) & ~twos;//把数看成二级制，ones记录的所有位出现1的次数在状态1
            twos = (twos ^ num) & ~ones;
        }
        return ones;
    }
}
// 			^ 优先级为 9
//			& 优先级为 8
//			~ 优先级为 2
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



# 贪心

#### [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/)

难度中等510收藏分享切换为英文关注反馈

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。

**示例 1:**

```
输入: [2,3,1,1,4]
输出: true
解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
```

**示例 2:**

```
输入: [3,2,1,0,4]
输出: false
解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
```

```java
class Solution {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0 ; i--) {
            if (i + nums[i] >= lastPos) lastPos = i;
        }
        return lastPos == 0;
    }
}
```

```java
//	解题思路：
//	1.如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
//	2.可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
//	3.如果可以一直跳到最后，就成功了。

class Solution {
    public boolean canJump(int[] nums) {
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > k) return false;
            k = Math.max(i+nums[i], k);
        }

        return true;
    }
}
```

#### [45. 跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/)

难度困难392收藏分享切换为英文关注反馈

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

**示例:**

```
输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
```

**说明:**

假设你总是可以到达数组的最后一个位置。

```java
class Solution {
    public int jump(int[] nums) {
        int steps = 0;
        int end = 0; //当前能够跳跃边界
        int maxEnd = 0; //本次跳跃可以达到的最远的位置

        for(int i = 0; i < nums.length - 1; i++){
            //找能跳的最远的
            maxEnd = Math.max(i + nums[i], maxEnd);

            //遇到边界，就更新边界，并且步数加一
            if(i == end){//一进来就会加1
                end = maxEnd;
                steps++;

                if(maxEnd >= nums.length - 1)  
                    break;
            }
        }

        return steps;
    }
}
```



#### [134. 加油站](https://leetcode-cn.com/problems/gas-station/)

难度中等241收藏分享切换为英文关注反馈

在一条环路上有 *N* 个加油站，其中第 *i* 个加油站有汽油 `gas[i]` 升。

你有一辆油箱容量无限的的汽车，从第 *i* 个加油站开往第 *i+1* 个加油站需要消耗汽油 `cost[i]` 升。你从其中的一个加油站出发，开始时油箱为空。

如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

**说明:** 

- 如果题目有解，该答案即为唯一答案。
- 输入数组均为非空数组，且长度相同。
- 输入数组中的元素均为非负数。

**示例 1:**

```
输入: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

输出: 3

解释:
从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。
```

**示例 2:**

```
输入: 
gas  = [2,3,4]
cost = [3,4,3]

输出: -1

解释:
你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
因此，无论怎样，你都不可能绕环路行驶一周。
```

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //determine if we have a solution
        int total = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
        }
        if (total < 0) {
            return -1;
        }
   
        // find out where to start
        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length;i++) {
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        return start;
    }
}
```

#### [135. 分发糖果](https://leetcode-cn.com/problems/candy/)

难度困难179收藏分享切换为英文关注反馈

老师想给孩子们分发糖果，有 *N* 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。

你需要按照以下要求，帮助老师给这些孩子分发糖果：

- 每个孩子至少分配到 1 个糖果。
- 相邻的孩子中，评分高的孩子必须获得更多的糖果。

那么这样下来，老师至少需要准备多少颗糖果呢？

**示例 1:**

```
输入: [1,0,2]
输出: 5
解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
```

**示例 2:**

```
输入: [1,2,2]
输出: 4
解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
```

```java
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] res = new int[n];

        Arrays.fill(res, 1);

        for(int i = 1; i < n; i++){
            if(ratings[i] > ratings[i - 1])
                res[i] = res[i - 1] + 1;
        }

        for(int i = n - 1; i >= 1; i--){
            if(ratings[i - 1] > ratings[i])
                res[i - 1] = Math.max(res[i - 1], res[i] + 1);
        }

        int sum = 0;
        for(int r : res)   sum += r;

        return sum;
    }
}
```



# 数学方法

#### [836. 矩形重叠](https://leetcode-cn.com/problems/rectangle-overlap/)

矩形以列表 `[x1, y1, x2, y2]` 的形式表示，其中 `(x1, y1)` 为左下角的坐标，`(x2, y2)` 是右上角的坐标。

如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。

给出两个矩形，判断它们是否重叠并返回结果。

**示例 1：**

```
输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
输出：true
```

**示例 2：**

```
输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
输出：false
```

**说明：**

1. 两个矩形 `rec1` 和 `rec2` 都以含有四个整数的列表的形式给出。
2. 矩形中的所有坐标都处于 `-10^9` 和 `10^9` 之间。

```java
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        //直接根据坐标可以判断是否可能重叠，找边界条件
        return !(rec1[2] <= rec2[0] ||      //rec1 on the left
                 rec1[0] >= rec2[2] ||      //right
                 rec1[1] >= rec2[3] ||      //up
                 rec1[3] <= rec2[1]         //down
                );
    }
}
```

#### [面试题65. 不用加减乘除做加法](https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/)

难度简单15收藏分享切换为英文关注反馈

写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。

 

**示例:**

```
输入: a = 1, b = 1
输出: 2
```

 

**提示：**

- `a`, `b` 均可能是负数或 0
- 结果不会溢出 32 位整数

```java
class Solution {
    public int add(int a, int b) {
        
        while(b != 0){
            int plus = a ^ b;// 求和（不计进位）. 相同位置0，相反位置1
            b = (a & b) << 1;// 计算进位. 先保留同为1的位，都为1的位要向左进位，因此左移1位
            a = plus;
        }

        return a;
    }
}
```

#### [365. 水壶问题](https://leetcode-cn.com/problems/water-and-jug-problem/)

难度中等167收藏分享切换为英文关注反馈

有两个容量分别为 *x*升 和 *y*升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 *z*升 的水？

如果可以，最后请用以上水壶中的一或两个来盛放取得的 *z升* 水。

你允许：

- 装满任意一个水壶
- 清空任意一个水壶
- 从一个水壶向另外一个水壶倒水，直到装满或者倒空

**示例 1:** (From the famous [*"Die Hard"* example](https://www.youtube.com/watch?v=BVtQNK_ZUJg))

```
输入: x = 3, y = 5, z = 4
输出: True
```

**示例 2:**

```
输入: x = 2, y = 6, z = 5
输出: False
```

[视频详解，为啥用GCD](https://www.youtube.com/watch?v=0Oef3MHYEC0)

```java
class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if(x + y < z)   return false;
        
        if(z == x || z == y || z == x + y)  return true;

        return z % GCD(x, y) == 0;// % level 3  + level 4  == level 7
    }

    private int GCD(int a, int b){
        while(b != 0){
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
}
```

#### [233. 数字 1 的个数](https://leetcode-cn.com/problems/number-of-digit-one/)

难度困难104收藏分享切换为英文关注反馈

给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。

**示例:**

```
输入: 13
输出: 6 
解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。
```

```java
class Solution {
     /**
    * Calculate occurance on every digit, from
    * least important digit to most important digit
    * number = 1432
    * One's digit: n/10=143 143*1+1
    * Ten's digit: n/100=14 14*10+10
    * Hun's digit: n/1000=1 1*100+100
    * Tho's digit: 1432%1000+1=433
    * Sum all occurance on digits together
    */
    public int countDigitOne(int n) {
        int count = 0;
            
        for (long k = 1; k <= n; k *= 10) {
            long r = n / k, m = n % k;
            // sum up the count of ones on every place k
            count += (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0);
        }
            
        return count;
    }
}
```

#### [面试题44. 数字序列中某一位的数字](https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/)

难度中等8收藏分享切换为英文关注反馈

数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。

请写一个函数，求任意第n位对应的数字。

 

**示例 1：**

```
输入：n = 3
输出：3
```

**示例 2：**

```
输入：n = 11
输出：0
```

 

**限制：**

- `0 <= n < 2^31`

```java
public  static int findNthDigit(int n) {
        int digit = 1;//digit level, we start at one digit.
        long counts = 9;//the number counts of current digit level,we start at one digit,there are 9 numbers(1-9)which is at one digit.
        /**
         * number [1-9] (there are 9 numbers)is of one digit,number[10-99](there are 90 numbers) is
         * of two digits,number[100-999](there are 900 numbers) is of three digits,so first we should
         * find what level(i mean which digits(one digit,two digit or so on)  by level) the nth digit locate,
         * once we find the digit level, we achieve half the process,
         */
        /**
         *if n - digit * counts > 0,it means the nth digit is not at the current digit level,we should
         * increase digit level to pass more number
         */

        while (n - digit * counts > 0) {
            //every time we pass the number at current digit level
            n -= digit * counts;

            digit++;
            //counts are grow as follows,9,90,900,9000.....since the counts maybe overflow so i use long type
            counts *= 10;
        }
        //after loop,the n means nth digits from the current baseNumber

        //the base number is 1，10，100，1000，10000 and so on.
        int baseNumber = (int)Math.pow(10, digit - 1);
        //find the number where nth digit locate
        int number  = (n -1) / digit + baseNumber;
        //find the digit where nth digit locate at the number above
        int mod = (n - 1 ) % digit;
        return String.valueOf(number).charAt(mod) - '0';
    }
```

#### [面试题15. 二进制中1的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)

难度简单11收藏分享切换为英文关注反馈

请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

**示例 1：**

```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```

**示例 2：**

```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```

**示例 3：**

```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum = 0;

        while (n != 0){
            sum += (n & 1);
            //   >>>  无符号右移，右移后最左边的位用0补齐；  
            //	>> 有符号右移，右移后最左边负数补1，否则补0
            n = n >>> 1;  
        }
        return sum;
    }
}
```

#### [面试题62. 圆圈中最后剩下的数字](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/)

难度简单26收藏分享切换为英文关注反馈

0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

 

**示例 1：**

```
输入: n = 5, m = 3
输出: 3
```

**示例 2：**

```
输入: n = 10, m = 17
输出: 2
```

 

**限制：**

- `1 <= n <= 10^5`
- `1 <= m <= 10^6`

```JAVA
class Solution {
    //常规解法
    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            list.add(i);    
        }

        int c = (m - 1) % list.size();
        while(list.size() != 1){
            list.remove(c);
            c = (c + m - 1) % list.size();
        }

        return list.get(0);
    }
}
```

```Java
//数学方法
问题：N个人编号为1，2，……，N，依次报数，每报到M时，杀掉那个人，求最后胜利者的编号。

递推公式： f(N,M)=(f(N−1,M)+M)%N
    
class Solution {
    //f(N,M)=(f(N−1,M)+M)%N
    public int lastRemaining(int n, int m) {
        if(n <= 0 || m <= 0) return -1;

        return n == 1 ? 0 : (lastRemaining(n - 1, m) + m) % n;
    }
}
```

#### [945. 使数组唯一的最小增量](https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/)

难度中等84收藏分享切换为英文关注反馈

给定整数数组 A，每次 *move* 操作将会选择任意 `A[i]`，并将其递增 `1`。

返回使 `A` 中的每个值都是唯一的最少操作次数。

**示例 1:**

```
输入：[1,2,2]
输出：1
解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
```

**示例 2:**

```
输入：[3,2,1,2,1,7]
输出：6
解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
```

```java
class Solution {
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        
        //need表示下一个位置应该出现的数字
        int need = 0, res = 0;
        for(int a : A){
            res += Math.max(need - a, 0);
            need = Math.max(a, need) + 1;
        }

        return res;
    }
}
```

#### [892. 三维形体的表面积](https://leetcode-cn.com/problems/surface-area-of-3d-shapes/)

难度简单100收藏分享切换为英文关注反馈

在 `N * N` 的网格上，我们放置一些 `1 * 1 * 1 ` 的立方体。

每个值 `v = grid[i][j]` 表示 `v` 个正方体叠放在对应单元格 `(i, j)` 上。

请你返回最终形体的表面积。

 



**示例 1：**

```
输入：[[2]]
输出：10
```

**示例 2：**

```
输入：[[1,2],[3,4]]
输出：34
```

**示例 3：**

```
输入：[[1,0],[0,2]]
输出：16
```

**示例 4：**

```
输入：[[1,1,1],[1,0,1],[1,1,1]]
输出：32
```

**示例 5：**

```
输入：[[2,2,2],[2,1,2],[2,2,2]]
输出：46
```

 

**提示：**

- `1 <= N <= 50`
- `0 <= grid[i][j] <= 50`

```java
class Solution {
    public int surfaceArea(int[][] grid) {
        int n = grid.length, area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 先把grid[i][j]赋值给level，省掉了bound check，可以略微略微略微优化一下耗时。。。
                int level = grid[i][j];
                if (level > 0) {
                    // 一个柱体中：2个底面 + 所有的正方体都贡献了4个侧表面积 
                    area += (level << 2) + 2;
                    // 减掉 i 与 i-1 相贴的两份表面积
                    area -= i > 0? Math.min(level, grid[i - 1][j]) << 1: 0; 
                    // 减掉 j 与 j-1 相贴的两份表面积
                    area -= j > 0? Math.min(level, grid[i][j - 1]) << 1: 0;
                }  
            }
        }
        return area;
    }
}
//	补充运算符优先级   -=  level  14   
//					？： level  13
//					>   level  6
//					<<  level  5
```

#### [914. 卡牌分组(找最大公约数)](https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/)

难度简单94收藏分享切换为英文关注反馈

给定一副牌，每张牌上都写着一个整数。

此时，你需要选定一个数字 `X`，使我们可以将整副牌按下述规则分成 1 组或更多组：

- 每组都有 `X` 张牌。
- 组内所有的牌上都写着相同的整数。

仅当你可选的 `X >= 2` 时返回 `true`。

**示例 1：**

```
输入：[1,2,3,4,4,3,2,1]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
```

**示例 2：**

```
输入：[1,1,1,2,2,2,3,3]
输出：false
解释：没有满足要求的分组。
```

**示例 3：**

```
输入：[1]
输出：false
解释：没有满足要求的分组。
```

**示例 4：**

```
输入：[1,1]
输出：true
解释：可行的分组是 [1,1]
```

**示例 5：**

```
输入：[1,1,2,2,2,2]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[2,2]
```

**提示：**

1. `1 <= deck.length <= 10000`
2. `0 <= deck[i] < 10000`

```java
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        //表示最小公倍数，初始化为0
        int res = 0; 

        Map<Integer, Integer> count = new HashMap<>();
        for(int i = 0; i < deck.length; i++)
            count.put(deck[i], count.getOrDefault(deck[i], 0) + 1);

        for(int value : count.values())   res = GCD(value, res);

        return res > 1;
    }

    private int GCD(int a, int b){
        return b > 0 ? GCD(b, a % b) : a;
    }
}
```

# TOP K问题

[6天通吃树结构—— 第五天 Trie树](https://www.cnblogs.com/huangxincheng/archive/2012/11/25/2788268.html)

[【动画】看动画轻松理解「Trie树」](https://juejin.im/post/5c2c096251882579717db3d2)

[经典面试问题: Top K 之 ---- 海量数据找出现次数最多或，不重复的。](https://www.cnblogs.com/linguanh/p/8532641.html)

[教你如何迅速秒杀掉99%的海量数据处理面试题](https://juejin.im/entry/5a27cb796fb9a045104a5e8c)

# 多线程

#### [1114. 按序打印](https://leetcode-cn.com/problems/print-in-order/)

我们提供了一个类：

```
public class Foo {
  public void one() { print("one"); }
  public void two() { print("two"); }
  public void three() { print("three"); }
}
```

**三个不同的线程将会共用一个 `Foo` 实例。**

- 线程 A 将会调用 `one()` 方法
- 线程 B 将会调用 `two()` 方法
- 线程 C 将会调用 `three()` 方法

请设计修改程序，以确保 `two()` 方法在 `one()` 方法之后被执行，`three()` 方法在 `two()` 方法之后被执行。

 

**示例 1:**

```
输入: [1,2,3]
输出: "onetwothree"
解释: 
有三个线程会被异步启动。
输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
正确的输出是 "onetwothree"。
```

**示例 2:**

```
输入: [1,3,2]
输出: "onetwothree"
解释: 
输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
正确的输出是 "onetwothree"。
```

 

**注意:**

尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。

你看到的输入格式主要是为了确保测试的全面性。

**解题思路：**

这是一个典型的执行屏障的问题，可以通过构造屏障来实现。

```java
class Foo {
    CountDownLatch cd1;
    CountDownLatch cd2;
    public Foo() {
        cd1 = new CountDownLatch(1);
        cd2 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        cd1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        cd1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        cd2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        cd2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
```

#### [1115. 交替打印FooBar](https://leetcode-cn.com/problems/print-foobar-alternately/)

我们提供一个类：

```
class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
```

**两个不同的线程将会共用一个 `FooBar` 实例**。其中一个线程将会调用 `foo()` 方法，另一个线程将会调用 `bar()` 方法。

请设计修改程序，以确保 "foobar" 被输出 n 次。

**示例 1:**

```
输入: n = 1
输出: "foobar"
解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
```

**示例 2:**

```
输入: n = 2
输出: "foobarfoobar"
解释: "foobar" 将被输出两次。
```

```java
class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    Semaphore foo = new Semaphore(1);
    Semaphore bar = new Semaphore(0);

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
        }
    }
}
```

```java
//synchronized方法
public class FooBar {
    
    private int n;
    //flag 0->foo to be print  1->foo has been printed
    private int flag = 0;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (flag == 1) {
                    this.wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = 1;
                this.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (flag == 0) {
                    this.wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                flag = 0;
                this.notifyAll();
            }
        }
    }
}
```

```java
//使用Volatile的方法
public class FooBarVolatile {

    private int n;
   //flag 0->foo to be print  1->foo has been printed  using volatile
    private volatile int flag=0;


    public FooBarVolatile(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (true){
                if(flag==0){
                    printFoo.run();
                    flag=1;
                    break;
                }
                Thread.sleep(1);
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (true){
                if(flag==1){
                    printBar.run();
                    flag=0;
                    break;
                }
                Thread.sleep(1);
            }
        }
    }
}
```

```java
//cas 方法
public class FooBarCAS {

        private int n;
        ////flag 0->foo to be print  1->foo has been printed
        private AtomicInteger flag=new AtomicInteger(0);

        public FooBarCAS(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (!flag.compareAndSet(0,1)){//如果当前值等于预期值，设为目标值
                    Thread.sleep(1);
                }
                printFoo.run();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (!flag.compareAndSet(1,0)){
                    Thread.sleep(1);
                }
                printBar.run();
            }
        }

}
```

#### [1116. 打印零与奇偶数](https://leetcode-cn.com/problems/print-zero-even-odd/)

假设有这么一个类：

```
class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // 构造函数
  public void zero(printNumber) { ... }  // 仅打印出 0
  public void even(printNumber) { ... }  // 仅打印出 偶数
  public void odd(printNumber) { ... }   // 仅打印出 奇数
}
```

相同的一个 `ZeroEvenOdd` 类实例将会传递给三个不同的线程：

1. 线程 A 将调用 `zero()`，它只输出 0 。
2. 线程 B 将调用 `even()`，它只输出偶数。
3. 线程 C 将调用 `odd()`，它只输出奇数。

每个线程都有一个 `printNumber` 方法来输出一个整数。请修改给出的代码以输出整数序列 `010203040506`... ，其中序列的长度必须为 2*n*。

 

**示例 1：**

```
输入：n = 2
输出："0102"
说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
```

**示例 2：**

```
输入：n = 5
输出："0102030405"
```

```java
public class ZeroEvenOdd {
    private int n;

    Semaphore zero = new Semaphore(1);
    Semaphore even = new Semaphore(0);
    Semaphore odd = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if (i % 2 == 1)
                odd.release();
            else 
                even.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }
}

```

#### [1117. H2O 生成](https://leetcode-cn.com/problems/building-h2o/)

现在有两种线程，氢 `oxygen` 和氧 `hydrogen`，你的目标是组织这两种线程来产生水分子。

存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。

氢和氧线程会被分别给予 `releaseHydrogen` 和 `releaseOxygen` 方法来允许它们突破屏障。

这些线程应该三三成组突破屏障并能立即组合产生一个水分子。

你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。

换句话说:

- 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
- 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。

书写满足这些限制条件的氢、氧线程同步代码。

 

**示例 1:**

```
输入: "HOH"
输出: "HHO"
解释: "HOH" 和 "OHH" 依然都是有效解。
```

**示例 2:**

```
输入: "OOHHHH"
输出: "HHOHHO"
解释: "HOHHHO", "OHHHHO", "HHOHOH", "HOHHOH", "OHHHOH", "HHOOHH", "HOHOHH" 和 "OHHOHH" 依然都是有效解。
```

 

**限制条件:**

- 输入字符串的总长将会是 3*n*, 1 ≤ *n* ≤ 50；
- 输入字符串中的 “H” 总数将会是 2n；
- 输入字符串中的 “O” 总数将会是 n。

```java
class H2O {

    Semaphore h;
    Semaphore o ;
    public H2O() {
        h = new Semaphore(2);
        o = new Semaphore(2);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
		releaseOxygen.run();
        h.release(2);
    }
}
```

#### [1195. 交替打印字符串](https://leetcode-cn.com/problems/fizz-buzz-multithreaded/)

编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：

- 如果这个数字可以被 3 整除，输出 "fizz"。
- 如果这个数字可以被 5 整除，输出 "buzz"。
- 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。

例如，当 `n = 15`，输出： `1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz`。

假设有这么一个类：

```
class FizzBuzz {
  public FizzBuzz(int n) { ... }               // constructor
  public void fizz(printFizz) { ... }          // only output "fizz"
  public void buzz(printBuzz) { ... }          // only output "buzz"
  public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
  public void number(printNumber) { ... }      // only output the numbers
}
```

请你实现一个有四个线程的多线程版 `FizzBuzz`， 同一个 `FizzBuzz` 实例会被如下四个线程使用：

1. 线程A将调用 `fizz()` 来判断是否能被 3 整除，如果可以，则输出 `fizz`。
2. 线程B将调用 `buzz()` 来判断是否能被 5 整除，如果可以，则输出 `buzz`。
3. 线程C将调用 `fizzbuzz()` 来判断是否同时能被 3 和 5 整除，如果可以，则输出 `fizzbuzz`。
4. 线程D将调用 `number()` 来实现输出既不能被 3 整除也不能被 5 整除的数字。

```java
class FizzBuzz {
    private int n;

    Semaphore sem, sem3, sem5, sem15;
    public FizzBuzz(int n) {
        this.n = n;
        sem = new Semaphore(1);
        sem3 = new Semaphore(0);
        sem5 = new Semaphore(0);
        sem15 = new Semaphore(0);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i = 3; i <= n; i += 3){
            sem3.acquire();
            printFizz.run();
            if((i + 3) % 5 == 0)
                i += 3;
            sem.release();
        }        
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i = 5; i <= n; i += 5){
            sem5.acquire();
            printBuzz.run();
            if((i + 5) % 3 == 0)
                i += 5;
            sem.release();
        }    
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i = 15; i <= n; i += 15){
            sem15.acquire();
            printFizzBuzz.run();
            sem.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++){
            sem.acquire();
            if(i % 15 == 0)   sem15.release();
            else if(i % 5 == 0)   sem5.release();
            else if(i % 3 == 0)   sem3.release();
            else{
                    printNumber.accept(i);
                    sem.release();
                }
        }
    }
}
```

```java
//使用cyclicBarrier
class FizzBuzz {
    private int n;

    private static CyclicBarrier barrier = new CyclicBarrier(4);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                printFizz.run();
            }
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                printBuzz.run();
            }
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                printFizzBuzz.run();
            }
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
            }
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
```

