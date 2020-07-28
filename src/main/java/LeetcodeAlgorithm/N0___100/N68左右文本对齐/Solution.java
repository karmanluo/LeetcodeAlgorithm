package LeetcodeAlgorithm.N0___100.N68左右文本对齐;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int i = 0;
        List<String> res = new ArrayList<>();
        while (i < words.length) {
            int j = i + 1, s = words[i].length(), rs = words[i].length(); // rs是为了算空格数
            while (j < words.length && s + 1 + words[j].length() <= maxWidth) {
                s += words[j].length() + 1;
                rs += words[j].length();
                j++;
            }
            rs = maxWidth - rs;
            StringBuilder line = new StringBuilder(words[i]);
            if (j == words.length) { // 表示最后一行
                for (int k = i + 1; k < j; k++) line.append(" ").append(words[k]);
                line.append(getSpace(maxWidth - line.length()));
            } else if (j - i == 1) { // 单词只有一个
                line.append(getSpace(maxWidth - line.length()));
            } else { // 表示这里要均匀分一下空格
                int base = rs / (j - i - 1);
                int rem = rs % (j - i - 1);
                for (int k = i + 1, t = 0; k < j; k++, t++)
                    line.append(getSpace(base + (t < rem ? 1 : 0))).append(words[k]);
            }
            i = j;
            res.add(line.toString());
        }

        return res;
    }

    public String getSpace(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
}