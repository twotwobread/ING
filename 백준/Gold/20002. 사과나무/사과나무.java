// 13:23 시작
// N x N 크기 격자 -> K x K 크기만 수확 가능 ( 1 <= K <= N) (더 작은거도 안된다는 의미.)
// 수확 시 이익과 손해 동시에 발생. -> 나무 위치를 좌표로 사과를 통해 얻은 이익과 노동비를 더한 총 이익 정리.
// 최대 총이익 출력
// 1 <= N <= 300
// -1000 <= 총이익 <= 1000

// 완전히 브루트 포스로 그냥 생각해보면
// N * N * (K * K) => 300 * 300 * 300 * 300 => 90,000 * 90,000 => 8,100,000,000 (81억) => 불가능.
// 그럼 구간합 이런거 쓰면 -> N * N 돌면서 2차원 구간합 만들고 N * N 돌면서 구간마다의 합을 더하면 되니까
// N^2 + N^2 => 90,000 + 90,000 => 180,000 요렇게 진행해보자.

// 아 K도 내가 찾아야 하는거구나. 그럼 180,000 * 300 => 54,000,000 요렇게도 되네네

import java.io.*;
import java.util.*;

public class Main {
    private static final int MIN = Integer.MIN_VALUE;
    private static final int MAX_SIZE = 300;
    
    private static int n;
    private static int[][] grid = new int[MAX_SIZE][MAX_SIZE];
    private static int[][] prefixSum = new int[MAX_SIZE][MAX_SIZE];
    
    public static void main(String args[]) throws IOException {
        initialize();
        System.out.println(harvest());
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(tmp[j]);
            }
        }
    }
    
    private static int harvest() {
        prefixSumOfMatrix();
        return getMaxHarvestQuantity();
    }
    
    private static void printArr(int[][] arr) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(String.format("%s ", arr[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private static void prefixSumOfMatrix() {
        copyMatrix(prefixSum, grid);
        for (int i = 1; i < n; i++) {
            prefixSum[i][0] += prefixSum[i-1][0];
        }
        
        for (int i = 1; i < n; i++) {
            prefixSum[0][i] += prefixSum[0][i-1];
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                prefixSum[i][j] += (prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1]);
            }
        }
    }
    
    private static void copyMatrix(int[][] a, int[][] b) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = b[i][j];
            }
        }
    }
    
    private static int getMaxHarvestQuantity() {
        int result = MIN;
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n-k; i++) {
                for (int j = 0; j < n-k; j++) {
                    int rangeSum = prefixSum[i + k][j + k];
                    if (i - 1 >= 0) {
                        rangeSum -= prefixSum[i - 1][j + k];
                    }
                    if (j - 1 >= 0) {
                        rangeSum -= prefixSum[i + k][j - 1];
                    }
                    if (inRange(i - 1, j - 1)) {
                        rangeSum += prefixSum[i - 1][j - 1];
                    }
                    
                    result = Math.max(result, rangeSum);
                }
            }
        }
        
        return result;
    }
    
    private static boolean inRange(int i, int j) {
        return 0 <= i && i < n && 0 <= j && j < n;
    }
}