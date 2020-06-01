package LeetcodeAlgorithm.N201_300.N211添加与搜索单词;

/**
 * @Author:KunmingLuo
 * @Date: 2020/5/6 16:23
 */

class WordDictionary {

    private class Trie {
        private boolean isString = false;
        private Trie[] next = new Trie[26];
    }

    Trie root = new Trie();

    public WordDictionary() {
    }

    public void addWord(String word) {
        Trie node = root;
        for (char c : word.toCharArray()) {
            if (node.next[c - 'a'] == null) node.next[c - 'a'] = new Trie();
            node = node.next[c - 'a'];
        }
        node.isString = true;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

    private boolean match(char[] chars, int index, Trie node) {
        if (index == chars.length) return node.isString;
        if (chars[index] != '.') {
            return node.next[chars[index] - 'a'] != null &&
                    match(chars, index + 1, node.next[chars[index] - 'a']);
        } else {
            for (int j = 0; j < node.next.length; j++) {
                if (node.next[j] != null) {
                    if (match(chars, index + 1, node.next[j]))
                        return true;
                }
            }
        }


        return false;
    }
}
