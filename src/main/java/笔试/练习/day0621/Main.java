package 笔试.练习.day0621;

import java.util.Scanner;

public class Main{
    public static int shortLengthOfStr(String A, String B) {
        char[] BChar = B.toCharArray();
        char[] AChar = A.toCharArray();
        int[] BMap = new int[256];


        /**
         * BMap中状态：BMap中状态：0初始值，>0欠缺的值, <0不欠缺的值
         */
        //将欠缺哪些值压入map中
        for (int i = 0; i < BChar.length; i++) {
            BMap[BChar[i]] ++;
        }

        int before = 0;
        int after = 0;
        int minLen  = Integer.MAX_VALUE;;
        int match = BChar.length;
        while (before < AChar.length){
            /**
             *  初始值 自减后 小于0，变成不欠缺的值
             *  欠缺的值 自减后，最小变成 0 初始值
             **/
            BMap[AChar[before]]--;
            if (BMap[AChar[before]] >= 0) {
                //找到欠的字符，match自减，直到match=0，说明BMap中大于0的值匹配到了
                match--;
            }

            if (match == 0){
                //全匹配上了，开始向右移动after，after也是从0开始
                //这里可以理解为找匹配字符的最左边的位置。
                //例如：abcsfdr
                //这里找：bcd
                //此时，before已经到了d即index=5的位置
                //要计算出长度，需要知道，最左边在{bcd}中字符的位置
                //所以移动after从index=0开始，直到找到{bcd}中任意字符
                //所以after移动到b即index=1，此时最短长度为 5 - 1 + 1 = 5
                while (BMap[AChar[after]] < 0){
                    /**
                     *小于0说明不是匹配到的数字，继续右移，直到找到最左边BChar值
                     因为匹配的数据是大于0,经过上面BMap[AChar[before]]--后
                     变成0了
                     **/
                    BMap[AChar[after]]++;
                    after++;
                }
                minLen = Math.min(minLen, before - after + 1);

                //继续往下走
                match++;
                //回补一个字符，告诉Map，哪个字符欠一个
                BMap[AChar[after]]++;
                after++;
            }


            //向右移动before
            before++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] data = new String[2];
        int count = 0;
        while(scan.hasNext()){
            data[count] = scan.nextLine();
            count++;
            if (count > 1){
                break;
            }
        }
        System.out.println(shortLengthOfStr(data[0], data[1]));
    }
}