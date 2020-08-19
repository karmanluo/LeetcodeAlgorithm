package AcWing.基础算法.数据结构.N835Trie字符串统计;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static class TrieNode {
        private int cnt;
        private TrieNode[] next;

        public TrieNode() {
            cnt = 0;
            next = new TrieNode[26];
        }

        public void insert(String word) {
            TrieNode root = this;
            for (char ch : word.toCharArray()) {
                if (root.next[ch - 'a'] == null) root.next[ch - 'a'] = new TrieNode();
                root = root.next[ch - 'a'];
            }

            root.cnt++;
        }

        public int search(String word) {
            TrieNode root = this;
            for (char ch : word.toCharArray()) {
                if (root.next[ch - 'a'] == null) return 0;
                root = root.next[ch - 'a'];
            }

            return root.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        TrieNode root = new TrieNode();

        while (n-- > 0) {
            String[] s = br.readLine().split(" ");
            if (s[0].equals("I")) root.insert(s[1]);
            else System.out.println(root.search(s[1]) + " ");
        }
    }
}
