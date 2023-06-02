// 피보나치 0th = 0, 1th = 1, 2th = 1
// n번째 피보나치 수 구하기
// 0 <= n <= 10^18 => O(logn)
// DP를 이용해도 불가능.
// 분할 정복 형식으로 풀면 되는데 이전 정보를 저장하지 않고 어떻게 이후에 다시 이용하지?

// 풀이 찾아보기
// - 분할 정복을 해야하는데 어떤 수식을 기반으로 분할 정복을 할 것인가?
//     - 이 수식 찾기가 어려움.
//     - 간단한 피보나치 수열의 점화식을 써보면 => F(n) = F(n-1) + F(n-2)
//     - 위 식은 수제한을 커버할 수 없기에 다른 수식을 떠올려야함.
// - 엄청 큰 수에 대해서 피보나치 문제를 해결하기 위해선 피보나치 수를 행렬을 이용한 수식으로 나타내보자

import java.io.*;

public class Main {
    private static final long MOD = 1_000_000_007;
    
    private static long n;
    private static long[][] A = {{1, 1}, {1, 0}};
    
    public static void main(String ... args) throws IOException {
        initialize();
        System.out.println(fibonachi(A, n));
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
    }
    
    private static long fibonachi(long[][] A, long num) {
        long[][] matrix = {{1,0}, {0,1}};
        
        if (num == 1 || num == 0) { // 0, 1인 경우 값 바로 반환
            return num;
        }
        
        while (num > 0) {
            if (num % 2 == 1) { 
                // num이 3이라고 가정하면
                // A^n = A^1*A^1 이렇게 됨. 2로 나누면서 풀기 때문에
                // 이걸 해결하기 위해서 A를 한번더 곱해서 A^1 * A^2가 되도록 구현
                matrix = matrixMultiply(matrix, A);
            }
            A = matrixMultiply(A, A);
            num /= 2;
        }
        
        return matrix[0][1];
    }
    
    private static long[][] matrixMultiply(long[][] A, long[][] B) {
        // 행렬 곱 구현
        int size = A.length;
        long[][] result = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
                result[i][j] %= MOD;
            }
        }
        return result;
    }
}