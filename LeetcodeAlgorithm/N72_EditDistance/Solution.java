package LeetcodeAlgorithm.N72_EditDistance;
//Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
//
// You have the following 3 operations permitted on a word:
// Insert a character
// Delete a character
// Replace a character
//
// Example 1:
//
//Input: word1 = "horse", word2 = "ros"
//Output: 3
//Explanation:
//horse -> rorse (replace 'h' with 'r')
//rorse -> rose (remove 'r')
//rose -> ros (remove 'e')
//
// Example 2:

//Input: word1 = "intention", word2 = "execution"
//Output: 5
//Explanation:
//intention -> inention (remove 't')
//inention -> enention (replace 'i' with 'e')
//enention -> exention (replace 'n' with 'x')
//exention -> exection (replace 'n' with 'c')
//exection -> execution (insert 'u')
/**
 * Optimization using dynamic programming
 * Top-down solution
 * O(len1 * len2) time, O(len1 * len2) space
 *
 */
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return -1;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();

        int[][] cache = new int[c1.length][c2.length];
        for (int i = 0; i < c1.length; i++) {
            for (int j = 0; j < c2.length; j++) {
                cache[i][j] = -1;
            }
        }
        return match(c1, c2, 0, 0, cache);
    }

    private int match(char[] c1, char[] c2, int i, int j, int[][] cache) {
        if (c1.length == i) return c2.length - j;
        if (c2.length == j) return c1.length - i;
        //在执行insert的时候，会计算出一些基础的情况，这些基础的情况；这些基础的情况在delete的时候不需要再次重复计算
        if (cache[i][j] != -1) return cache[i][j];

        if (c1[i] == c2[j]) cache[i][j] = match(c1, c2, i + 1, j + 1, cache);
        else{
            //Case1: insert
            int insert = match(c1, c2, i, j + 1, cache);
            //Case2: delete
            int delete = match(c1, c2, i + 1, j, cache);
            //Case3: replace
            int replace = match(c1, c2, i + 1, j + 1, cache);
            cache[i][j] = Math.min(insert, Math.min(delete, replace)) + 1;
        }
        return cache[i][j];
    }
}
