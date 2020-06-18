package LeetcodeAlgorithm.N1013将数组分成相等的三部分;

public class Solutoion {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int num : A) sum += num;
        if (A.length < 3 || sum % 3 != 0) return false;
        int target = sum / 3;
        int index = 0, partition = 0, curSum = 0;
        for (int i = 0; i < A.length; i++) {
            curSum += A[i];
            if (curSum == target && partition <= 1) {
                partition++;
                curSum = 0;
                if (partition == 2)
                    index = i;
            }
        }

        return curSum == target && partition == 2 && index < A.length - 1;
    }
}
