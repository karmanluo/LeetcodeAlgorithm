package LeetcodeAlgorithm.N5_LongestPalindromicSubstring;
/*
* Approach 5: Manacher's Algorithm
There is even an O(n) algorithm called Manacher's algorithm, explained here in detail. However, it is a non-trivial
* algorithm, and no one expects you to come up with this algorithm in a 45 minutes coding session. But, please go ahead
* and understand it, I promise it will be a lot of fun.
*
* 其实是 O ( n )。不严谨的想一下，因为 while 循环访问 R 右边的数字用来扩展，也就是那些还未求出的节点，然后不断扩展，
* 而期间访问的节点下次就不会再进入 while 了，可以利用对称得到自己的解，所以每个节点访问都是常数次，所以是 O ( n )。

空间复杂度：O（n）。
* */
public class Solution4 {
}
