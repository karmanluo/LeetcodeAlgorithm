package LeetcodeAlgorithm.N401_500.N412_FizzBuzz;

import java.util.ArrayList;
import java.util.List;
/**
 * Write a program that outputs the string representation of numbers from 1 to n.
 *
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”.
 * For numbers which are multiples of both three and five output “FizzBuzz”.
 *
 * Example:
 *
 * n = 15,
 *
 * Return:
 * [
 *     "1",
 *     "2",
 *     "Fizz",
 *     "4",
 *     "Buzz",
 *     "Fizz",
 *     "7",
 *     "8",
 *     "Fizz",
 *     "Buzz",
 *     "11",
 *     "Fizz",
 *     "13",
 *     "14",
 *     "FizzBuzz"
 * ]
 */
public class Solution {
    public static List<String> fizzBuzz(int n) {

        List<String> res = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0){
                res.add("FizzBuzz");
                continue;
            }
            if (i % 3 == 0){
                res.add("Fizz");
                continue;
            }
            if (i % 5 == 0){
                res.add("Buzz");
                continue;
            }
            res.add(Integer.toString(i));
        }

        return res;
    }

    public static void main(String[] args) {
        List<String> list = fizzBuzz(15);
        list.forEach(System.out::println);
    }
}
