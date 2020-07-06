package LeetcodeAlgorithm.N201_300.N208前缀树;

/**
 * @Author:KunmingLuo
 * @Date: 2020/5/6 15:30
 */
class Trie {
    private boolean isString = false;
    private Trie next[] = new Trie[26];

    public Trie() {
    }

    public void insert(String word) {
        Trie root = this;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (root.next[c - 'a'] == null) root.next[c - 'a'] = new Trie();
            root = root.next[c - 'a'];
        }

        root.isString = true;
    }

    public boolean search(String word) {
        Trie root = this;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (root.next[c - 'a'] == null) return false;
            root = root.next[c - 'a'];
        }

        return root.isString;
    }

    public boolean startsWith(String prefix) {
        Trie root = this;
        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            if (root.next[c - 'a'] == null) return false;
            root = root.next[c - 'a'];
        }

        return true;
    }
}
