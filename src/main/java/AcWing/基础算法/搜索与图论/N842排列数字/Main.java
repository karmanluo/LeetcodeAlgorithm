package AcWing.基础算法.搜索与图论.N842排列数字;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i + 1;

        backtrack(nums, new int[n], new boolean[n], 0);
    }

    private static void backtrack(int[] nums, int[] path, boolean[] visited, int size) {
        if (size == nums.length) {
            for (int i = 0; i < size; i++) System.out.print(path[i] + " ");
            System.out.println();
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                path[size] = nums[i];
                visited[i] = true;
                backtrack(nums, path, visited, size + 1);
                visited[i] = false;
            }
        }
    }
}
