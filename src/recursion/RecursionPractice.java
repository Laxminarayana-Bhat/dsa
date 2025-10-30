package recursion;

import java.util.ArrayList;
import java.util.List;

public class RecursionPractice {

    RecursionPractice() {
    }

    public static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    public static int fibonacciSeries(int n) {//0 based index so 4 - 0,1,1,2,3 , for 1 based index pass n-1
        if (n <= 1) return n;
        return fibonacciSeries(n - 1) + fibonacciSeries(n - 2);
    }

    // Helper function to start recursion
    public static void fibonacciSequence(int n) {//1 indexed if 0 index needed pass n+1
        printFibonacciRecursive(0, 1, n);
    }

    // Actual recursive function
    private static void printFibonacciRecursive(int a, int b, int count) {
        if (count == 0) return;
        System.out.print(a + " ");
        printFibonacciRecursive(b, a + b, count - 1);


    }

    static class Solution {
        //https://www.lavivienpost.com/combinations-of-adding-parentheses/
        static List<String> ans = new ArrayList<>();

        public static void generateParenthesis(int n) {
            rec(n, 0, new StringBuilder());
            ans.forEach(System.out::println);
        }

        public static void main(String[] args) {
            generateParenthesis(3);
        }

        public static void rec(int open, int close, StringBuilder sb) {
            // Print current recursion state
            System.out.println("rec(open=" + open + ", close=" + close + ", sb=\"" + sb + "\")");

            if (open == 0 && close == 0) {
                System.out.println("✅ Found: " + sb.toString());
                ans.add(sb.toString());
                return;
            }

            if (open > 0) {
                System.out.println("Adding '(' -> " + sb + "(");
                sb.append("(");
                rec(open - 1, close + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
                System.out.println("Backtracked after '(' -> " + sb);
            }

            if (close > 0) {
                System.out.println("Adding ')' -> " + sb + ")");
                sb.append(")");
                rec(open, close - 1, sb);
                sb.deleteCharAt(sb.length() - 1);
                System.out.println("Backtracked after ')' -> " + sb);
            }
        }
    }
}
