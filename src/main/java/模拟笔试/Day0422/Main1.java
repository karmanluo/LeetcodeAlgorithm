package 模拟笔试.Day0422;


import java.util.*;

/**
 * 牛牛找工作， 通过 40%
 */
public class Main1{
    public static void main(String[] args){
        int[][] job;
        int[] person;

        Scanner sc = new Scanner(System.in);

        int jobNum = sc.nextInt();
        int personNum = sc.nextInt();

        job = new int[jobNum][2];
        person = new int[personNum];

        for(int i = 0; i < jobNum; i++){
            job[i][0] = sc.nextInt();
            job[i][1] = sc.nextInt();
        }
        for(int i = 0; i < personNum; i++){
            person[i] = sc.nextInt();
        }
        /*Arrays.sort(job, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] < o2[0] ? -1 : o1[0] == o2[0] ? 0 : 1;
            }
        });*/
        Arrays.sort(job, (int[] o1, int[] o2)->o1[0] < o2[0] ? -1 : o1[0] == o2[0] ? 0 : 1);
        for(int p : person){
            int maxPay = 0,  i = 0;
            while(i < jobNum){
                if(p >= job[i][0] && job[i][1] > maxPay){
                    maxPay = job[i][1];
                }
                if(p < job[i][0])
                    break;
                i++;
            }
            System.out.println(maxPay);
        }
    }
}