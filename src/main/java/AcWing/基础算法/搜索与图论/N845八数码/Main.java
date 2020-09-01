package AcWing.基础算法.搜索与图论.N845八数码;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split("\\s+"); //匹配多个空格

        String start = "", end = "12345678x";
        for (int i = 0; i < 9; i++) start += s[i];

        System.out.println(bfs(start, end));
    }

    private static int bfs(String start, String end) {
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Map<String, Integer> map = new HashMap<>(); //记录每个字符串操作的次数
        Queue<String> q = new LinkedList<>();

        q.offer(start);
        map.put(start, 0);
        while (!q.isEmpty()) {
            String s = q.poll();
            if (s.equals(end)) return map.get(s);

            int k = s.indexOf('x');
            int x = k / 3, y = k % 3;
            for (int[] dir : dirs) {
                int a = x + dir[0], b = y + dir[1];
                if (a < 0 || a >= 3 || b < 0 || b >= 3) continue;
                char[] arr = s.toCharArray();
                swap(arr, k, a * 3 + b);
                String str = String.valueOf(arr);
                if (!map.containsKey(str)) {
                    map.put(str, map.get(s) + 1);
                    q.offer(str);
                }
            }
        }

        return -1;
    }

    public static void swap(char[] arr, int i, int j) {
        char t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }
}