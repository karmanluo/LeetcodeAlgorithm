package LeetcodeAlgorithm.N301_400.N350两个数组的交集;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums1) map.put(n, map.getOrDefault(n, 0) + 1);

        int[] temp = new int[nums1.length];
        int count = 0;
        for (int n : nums2) {
            if (map.containsKey(n) && map.get(n) > 0) {
                map.put(n, map.get(n) - 1);
                temp[count++] = n;
            }
        }

        int[] res = new int[count];
        System.arraycopy(temp, 0, res, 0, count);

        return res;
    }
}
