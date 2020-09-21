package 笔试.练习.day0919;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int r = method(arr, k);
        System.out.println(r);
    }

    private static int method(int[] arr, int k) {
        Map<Integer, Integer> preSumFreq = new HashMap<>();

        preSumFreq.put(0,1);
        int preSum = 0;
        int cnt = 0;
        for (int num : arr) {
            preSum += num;

            if (preSumFreq.containsKey(preSum - k)) cnt += preSumFreq.get(preSum - k);
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }

        return cnt;
    }
}
/*
    第一题，解答思路：可以采用异或的方式进行计算；利用异或的特性，相同为0，相异为1；那么同一个数异或两次就会回到一个初始的状态
        比如数组是【a1,a2,....,an】,目标结果是 res = a1 异或 a2 异或 a3 。。。。 异或 an； 那么得到的结果就是只出现一次的数字。

    第二题，解答思路：
        LSM              优化写，在内存中追加写操作，不需要就地写。
        哈希表            数据压缩，优化读o（1）可以找到对应key位置
        布隆过滤器         数据压缩，在lsm下层优化读性能
        ART              数据压缩
        跳表              优化读
 */