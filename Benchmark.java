import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class Benchmark {

    public static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quicksort(arr, low, pi - 1);
            quicksort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static double[][] matrixMultiply(double[][] A, double[][] B, int n) {
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double s = 0.0;
                for (int k = 0; k < n; k++) {
                    s += A[i][k] * B[k][j];
                }
                C[i][j] = s;
            }
        }
        return C;
    }

    public static int sieve(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; (long) i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int fibN = 35;
        int sortN = 1000000;
        int matrixN = 300;
        int sieveLimit = 10000000;

        // 1. Fibonacci
        long t0 = System.nanoTime();
        long fibResult = fibonacci(fibN);
        long t1 = System.nanoTime();
        System.out.printf(Locale.US, "fibonacci,%.6f,%d%n", (t1 - t0) / 1_000_000.0, fibResult);

        // 2. Quicksort
        Random rng = new Random(42);
        int[] arr = new int[sortN];
        for (int i = 0; i < sortN; i++) {
            arr[i] = rng.nextInt(sortN);
        }
        t0 = System.nanoTime();
        quicksort(arr, 0, sortN - 1);
        t1 = System.nanoTime();
        System.out.printf(Locale.US, "quicksort,%.6f,%d%n", (t1 - t0) / 1_000_000.0, arr[sortN / 2]);

        // 3. Matrix Multiplication
        rng = new Random(42);
        double[][] A = new double[matrixN][matrixN];
        double[][] B = new double[matrixN][matrixN];
        for (int i = 0; i < matrixN; i++) {
            for (int j = 0; j < matrixN; j++) {
                A[i][j] = rng.nextInt(100) / 10.0;
            }
        }
        for (int i = 0; i < matrixN; i++) {
            for (int j = 0; j < matrixN; j++) {
                B[i][j] = rng.nextInt(100) / 10.0;
            }
        }
        t0 = System.nanoTime();
        double[][] C = matrixMultiply(A, B, matrixN);
        t1 = System.nanoTime();
        System.out.printf(Locale.US, "matrix,%.6f,%.6f%n", (t1 - t0) / 1_000_000.0, C[0][0]);

        // 4. Sieve of Eratosthenes
        t0 = System.nanoTime();
        int primeCount = sieve(sieveLimit);
        t1 = System.nanoTime();
        System.out.printf(Locale.US, "sieve,%.6f,%d%n", (t1 - t0) / 1_000_000.0, primeCount);
    }
}

