package 剑指offer.N12矩阵中的路径;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 *
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 *
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 * 提示：
 *
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 *
 */
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
