package AcWing.基础算法.数据结构.N154滑动窗口;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        int[] arr = new int[n];
        line = br.readLine().split(" ");
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(line[i]);

        bw.flush();

        int[] res = getWindowMax(arr, k);
        for (int num : res) bw.write(num + " ");

        br.close();
        bw.close();
    }

    private static int[] getWindowMax(int[] arr, int k) {
        int[] res = new int[arr.length - k + 1];
        int r = 0;

        int hh = 0, tt = -1;
        int[] q = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (hh <= tt && i - q[hh] == k) hh++;
            while (hh <= tt && arr[i] > arr[q[tt]]) tt--;
            q[++tt] = i;
            if (i + 1 >= k) res[r++] = arr[q[hh]];
        }

        return res;
    }
}
