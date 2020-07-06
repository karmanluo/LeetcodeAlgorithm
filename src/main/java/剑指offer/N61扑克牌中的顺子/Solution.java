package 剑指offer.N61扑克牌中的顺子;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();

        int max = 0, min = 14;
        for (int num : nums) {
            if (num == 0) continue;

            if (set.contains(num)) return false;
            max = Math.max(max, num);
            min = Math.min(min, num);
            set.add(num);
        }

        return max - min < 5;
    }
}
