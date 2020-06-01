package LeetcodeAlgorithm.N201_300.N263丑数;

public class Solution {
    public boolean isUgly(int num) {
        if (num<1) return false;
        while (num%5==0){
            num/=5;
        }
        while (num%3==0){
            num/=3;
        }
        while (num%2==0){
            num>>=1;
        }
        return num == 1;
    }
}
