package LeetcodeAlgorithm.N79_WordSearch;
//Given a 2D board and a word, find if the word exists in the grid.
//
// The word can be constructed from letters of sequentially adjacent cell,
// where "adjacent" cells are those horizontally or vertically neighboring.
// The same letter cell may not be used more than once.
//
// Example:
//board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//Given word = "ABCCED", return true.
//Given word = "SEE", return true.
//Given word = "ABCB", return false.
//
// Related Topics Array Backtracking

class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        if (rows == 0) {
            return false;
        }
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        //从不同位置开始
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //从当前位置开始符合就返回 true
                if (existRecursive(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existRecursive(char[][] board, int row, int col, String word, int index, boolean[][] visited) {
        //判断是否越界
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        //当前元素访问过或者和当前 word 不匹配返回 false
        if (visited[row][col] || board[row][col] != word.charAt(index)) {
            return false;
        }
        //已经匹配到了最后一个字母，返回 true
        if (index == word.length() - 1) {
            return true;
        }
        //将当前位置标记位已访问
        visited[row][col] = true;
        //对四个位置分别进行尝试
        boolean up = existRecursive(board, row - 1, col, word, index + 1, visited);
        if (up) {
            return true;
        }
        boolean down = existRecursive(board, row + 1, col, word, index + 1, visited);
        if (down) {
            return true;
        }
        boolean left = existRecursive(board, row, col - 1, word, index + 1, visited);
        if (left) {
            return true;
        }
        boolean right = existRecursive(board, row, col + 1, word, index + 1, visited);
        if (right) {
            return true;
        }
        //当前位置没有选进来，恢复标记为 false
        visited[row][col] = false;
        return false;
    }
}
