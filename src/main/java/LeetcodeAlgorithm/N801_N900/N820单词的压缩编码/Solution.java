package LeetcodeAlgorithm.N801_N900.N820单词的压缩编码;

import java.util.Arrays;

public class Solution {
    class TireNode{
        private TireNode[] child = new TireNode[26];
        TireNode(){}
    }

    class Tire {
        TireNode root;
        public Tire() {
            root = new TireNode();
        }

        public boolean isExistOrInsert(String s) {
            TireNode curr = root;
            boolean isNew = false;
            for (int i = s.length() - 1; i >= 0; i--) {
                int c = s.charAt(i);
                if (curr.child[c - 'a'] == null) {
                    isNew = true;
                    curr.child[c - 'a'] = new TireNode();
                }
                curr = curr.child[c - 'a'];
            }

            return isNew;
        }
    }

    public int minimumLengthEncoding(String[] words) {
        int res = 0;

        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());

        Tire tire = new Tire();
        for (String word : words) {
            if (tire.isExistOrInsert(word)) res += word.length() + 1;
        }

        return res;
    }
}
