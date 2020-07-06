package LeetcodeAlgorithm.N201_300.N212单词搜索;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:KunmingLuo
 * @Date: 2020/5/6 18:04
 */
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
