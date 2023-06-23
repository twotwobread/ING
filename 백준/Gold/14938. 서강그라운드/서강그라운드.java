// 수색 가능한 아이템 최대 개수 찾기
// 각 지역 일정 길의 길로 다른 지역과 연결 ( 1 <= l <= 15 ) -> 양방향 통행 가능
// 거리가 m 이내 (1 <= m <= 15)면 아이템 습득 가능.

// 지역의 개수 n (1 <= n <= 100), 길의 개수 r (1 <= r <= 100)

// 플루이드 워셜 -> 100 ^ 3 => 1,000,000
// 먼저 각 노드부터 특정 노드까지의 최단 거리를 모두 구하고 최단거리를 통해서 갈 수 있는
// 지역을 찾고 거기서 가진 아이템의 개수를 더하는 형식으로 풀자.

import java.io.*;
import java.util.*;

public class Main {
    private static final int IMPOSSIBLE = 10001;
    
    private static int n, m, r;
    private static int[] items;
    private static int[][] road;
    
    public static void main(String args[]) throws IOException {
        initialize();
        findShortestRoad();
        System.out.println(getMaxItemQuantity());
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        r = Integer.parseInt(tmp[2]);
        
        items = new int[n];
        tmp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(tmp[i]);
        }
        
        road = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                road[i][j] = IMPOSSIBLE;
            }
        }
        
        for (int i = 0; i < r; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < tmp.length; j++) {
                int a = Integer.parseInt(tmp[0]) - 1;
                int b = Integer.parseInt(tmp[1]) - 1;
                int l = Integer.parseInt(tmp[2]);
                
                road[a][b] = l;
                road[b][a] = l;
            }
        }
    }
    
    private static void findShortestRoad() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    road[i][j] = Math.min(road[i][j], road[i][k] + road[k][j]);
                }
            }
        }
    }
    
    private static int getMaxItemQuantity() {
        int result = 0;
        for (int i = 0; i < n; i++) {
            int tmp = items[i];
            for (int j = 0; j < n; j++) {
                if (i == j || road[i][j] > m) continue;
                tmp += items[j];
            }
            result = Math.max(result, tmp);
        }
        return result;
    }
}