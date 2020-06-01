package LeetcodeAlgorithm.N401_500.N412_FizzBuzz;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a program that outputs the string representation of numbers from 1 to n.
 * <p>
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”.
 * For numbers which are multiples of both three and five output “FizzBuzz”.
 * <p>
 * Example:
 * <p>
 * n = 15,
 * <p>
 * Return:
 * [
 * "1",
 * "2",
 * "Fizz",
 * "4",
 * "Buzz",
 * "Fizz",
 * "7",
 * "8",
 * "Fizz",
 * "Buzz",
 * "11",
 * "Fizz",
 * "13",
 * "14",
 * "FizzBuzz"
 * ]
 */
public class Solution2 {
    public static List<String> fizzBuzz(int n) {

        List<String> res = new ArrayList<>();
        int Fizz = 0, Buzz = 0;

        for (int i = 1; i <= n; i++) {
            Fizz++;
            Buzz++;
            if (Fizz == 3 && Buzz == 5) {
                res.add("FizzBuzz");
                Fizz = 0;
                Buzz = 0;
            } else if (Fizz == 3) {
                res.add("Fizz");
                Fizz = 0;
            } else if (Buzz == 5) {
                res.add("Buzz");
                Buzz = 0;
            } else {
                res.add(Integer.toString(i));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        List<String> list = fizzBuzz(15);
        list.forEach(System.out::println);
    }
}
