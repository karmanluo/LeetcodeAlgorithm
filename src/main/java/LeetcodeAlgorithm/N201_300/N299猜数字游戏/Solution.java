package LeetcodeAlgorithm.N201_300.N299猜数字游戏;

public class Solution {
    public String getHint(String secret, String guess) {
        StringBuilder res = new StringBuilder();
        int[] ds = new int[10];
        int[] dg = new int[10];
        int samePos = 0, sameAll = 0;

        for (int i = 0; i < secret.length(); i++) {
            int x = secret.charAt(i) - '0', y = guess.charAt(i) - '0';
            if (x == y) samePos++;
            ds[x]++;
            dg[y]++;
        }

        for (int i = 0; i < 10; i++) sameAll += Math.min(ds[i], dg[i]);

        return res.append(samePos).append("A").append(sameAll - samePos).append("B").toString();
    }
}
