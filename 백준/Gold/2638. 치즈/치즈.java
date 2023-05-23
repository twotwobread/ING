// 5 <= n, m <= 100
// 치즈 = 1, X = 0

// 1. 빈 공간에서 bfs 조져서 내부 공간 외에 -1로 처만들기
// 2. 그리고 격자 전부다 돌면서 -1이랑 2면이 접해있는 치즈 녹이기
// 3. 녹이면서 옆에 0(내부 공기) 있으면 bfs 돌리면서 -1로 만들어버리기
// 4. 2~3번 반복하다 치즈 다 녹으면 종료

import java.io.*;
import java.util.*;

public class Main {
    private static final int DIR = 4;
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    
    private static int n, m;
    private static int[][] grid;
    
    public static void main(String args[]) throws IOException {
        initialize();
        checkOutAir(new LinkedList<>(Arrays.asList(new Point(0, 0))));
        // printArr(grid);
        System.out.println(simulate());
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        
        grid = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                grid[i][j] = Integer.parseInt(tmp[j-1]);
            }
        }
    }
    
    private static void checkOutAir(Queue<Point> q) {
        for (Point p : q) {
            grid[p.x][p.y] = -1;
        }
        
        while(!q.isEmpty()) {
            Point now = q.remove();
            
            for (int i = 0; i < DIR; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (isOutAir(nx, ny)) {
                    grid[nx][ny] = -1;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }
    
    private static boolean isOutAir(int x, int y) {
        if (isOutOfGrid(x, y)) return false;
        if (grid[x][y] != 0) return false;
        return true;
    }
    
    private static boolean isOutOfGrid(int x, int y) {
        if (0 > x || x > n || 0 > y || y > m) return true;
        return false;
    }
    
    private static int simulate() {
        int result = 0;
        while(!isMeltAllCheese()) {
            result += 1;
            meltCheese();
        }
        return result;
    }
    
    private static boolean isMeltAllCheese() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (grid[i][j] == 1) return false;
            }
        }
        return true;
    }
    
    private static void meltCheese() {
        Queue<Point> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (canMelt(i, j)) {
                    q.add(new Point(i, j));
                }
            }
        }
        
        checkOutAir(q);
    }
    
    private static boolean canMelt(int x, int y) {
        if (grid[x][y] == 1 && meetMoreThan2PlanOutAir(x, y)) return true;
        return false;
    }
    
    private static boolean meetMoreThan2PlanOutAir(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < DIR; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isOutOfGrid(nx, ny) && grid[nx][ny] == -1) {
                cnt += 1;
            }
        }
        return cnt >= 2 ? true : false;
    }
    
    private static void printArr(int[][] grid) {
        System.out.println("Array 출력 : ");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(String.format("%s ", grid[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Point {
    int x;
    int y;
    
    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
}