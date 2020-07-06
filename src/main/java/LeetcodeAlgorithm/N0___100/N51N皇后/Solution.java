package LeetcodeAlgorithm.N0___100.N51N皇后;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(board, 0);
        return res;
    }

    private void dfs(char[][] board, int row) {
        if (row == board.length) {
            res.add(bulidString(board));
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (isValidate(board, row, i)){
                board[row][i] = 'Q';
                dfs(board, row + 1);
                board[row][i] = '.';
            }
        }
    }

    private boolean isValidate(char[][] board, int row, int col) {
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'Q' && (i - j == row - col || i + j == row + col || col == j))
                    return false;
            }
        }

        return true;
    }

    private List<String> bulidString(char[][] board) {
        int len = board.length;
        List<String> res = new LinkedList<String>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}