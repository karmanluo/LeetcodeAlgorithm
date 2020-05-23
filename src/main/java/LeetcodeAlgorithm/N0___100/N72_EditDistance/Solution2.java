package LeetcodeAlgorithm.N0___100.N72_EditDistance;
/*
* now we are considering bottom-up approach
*
* */
public class Solution2 {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return -1;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();
        //表示已经匹配好了的情况matched[0][0]表示还没有匹配
        int[][] matched = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i <= word1.length(); i++) {
            matched[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            matched[0][j] = j;
        }

        //从初始[0][0]的情况去推导[i][j]的情况,更具已知去推导未知的情况
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)){
                    matched[i + 1][j + 1] = matched[i][j];
                }else{
                    matched[i + 1][j + 1] = Math.min(matched[i][j], Math.min(matched[i][j+1], matched[i+1][j])) + 1;
                    //Since it is bottom up, we are considering in the ascending order of indexes.
                    //Insert means plus 1 to j, delete means plus 1 to i, replace means plus 1 to both i and j.
                    //above sequence is delete, insert and replace.
                }
            }
        }
        return matched[word1.length()][word2.length()];
    }
}
