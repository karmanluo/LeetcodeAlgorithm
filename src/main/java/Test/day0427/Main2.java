package Test.day0427;

import java.io.*;

public class Main2{
    public static int lampSum(int l,String r){
        int sum = 0;
        for(int i=0; i<l; i++){
            if(r.charAt(i) == '.'){
                sum ++;
                i+=2;
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String sa = bf.readLine();
        int ca = Integer.valueOf(sa);
        for(int i=0; i<ca; i++){
            int len = Integer.valueOf(bf.readLine());
            String s = bf.readLine();
            System.out.println(String.valueOf(lampSum(len,s)));
        }
    }
}
