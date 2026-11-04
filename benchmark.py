import time
import random
import sys

sys.setrecursionlimit(10000)


def fibonacci(n):
    if n <= 1:
        return n
    return fibonacci(n - 1) + fibonacci(n - 2)


def quicksort(arr, low, high):
    if low < high:
        pi = partition(arr, low, high)
        quicksort(arr, low, pi - 1)
        quicksort(arr, pi + 1, high)


def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] <= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1


def matrix_multiply(A, B, n):
    C = [[0.0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            s = 0.0
            for k in range(n):
                s += A[i][k] * B[k][j]
            C[i][j] = s
    return C


def sieve(limit):
    is_prime = [True] * (limit + 1)
    is_prime[0] = is_prime[1] = False
    i = 2
    while i * i <= limit:
        if is_prime[i]:
            for j in range(i * i, limit + 1, i):
                is_prime[j] = False
        i += 1
    return sum(is_prime)


if __name__ == '__main__':
    fib_n = 35
    sort_n = 1000000
    matrix_n = 300
    sieve_limit = 10000000

    t0 = time.perf_counter()
    fib_result = fibonacci(fib_n)
    t1 = time.perf_counter()
    print(f"fibonacci,{(t1 - t0) * 1000:.6f},{fib_result}")

    random.seed(42)
    arr = [random.randint(0, sort_n - 1) for _ in range(sort_n)]
    t0 = time.perf_counter()
    quicksort(arr, 0, sort_n - 1)
    t1 = time.perf_counter()
    print(f"quicksort,{(t1 - t0) * 1000:.6f},{arr[sort_n // 2]}")

    random.seed(42)
    A = [[random.randint(0, 99) / 10.0 for _ in range(matrix_n)] for _ in range(matrix_n)]
    B = [[random.randint(0, 99) / 10.0 for _ in range(matrix_n)] for _ in range(matrix_n)]
    t0 = time.perf_counter()
    C = matrix_multiply(A, B, matrix_n)
    t1 = time.perf_counter()
    print(f"matrix,{(t1 - t0) * 1000:.6f},{C[0][0]:.6f}")

    t0 = time.perf_counter()
    prime_count = sieve(sieve_limit)
    t1 = time.perf_counter()
    print(f"sieve,{(t1 - t0) * 1000:.6f},{prime_count}")
