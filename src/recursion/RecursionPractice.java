package recursion;

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
}
