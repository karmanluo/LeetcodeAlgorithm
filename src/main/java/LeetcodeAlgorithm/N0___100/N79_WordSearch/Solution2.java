package LeetcodeAlgorithm.N0___100.N79_WordSearch;

public class Solution2 {
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (isExist(board, y, x, w, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isExist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y < 0 || x < 0 || y == board.length || x == board[0].length) return false;
        if (board[y][x] != word[i]) return false;
        //做了异或之后对应的ASCII码值变化到A-Z之外，所以不可能往回走了
        board[y][x] ^= 256;
        boolean exist = isExist(board, y + 1, x, word, i + 1)
                || isExist(board, y - 1, x, word, i + 1)
                || isExist(board, y, x - 1, word, i + 1)
                || isExist(board, y, x + 1, word, i + 1);
        //再异或一次恢复为最开始的那个数
        board[y][x] ^= 256;
        return exist;
    }
}
