import java.util.*;
import java.io.*;

public class Main {
    private static final int ARCHER_CNT = 3;
    private static final Point EMPTY = new Point(-1, -1);
    
    private static int n, m, d;
    private static int[][] grid;
    
    private static List<List<Point>> allArchers = new ArrayList<>();
    private static List<Point> archers = new ArrayList<>();
    private static boolean[] visited;
    
    public static void main(String args[]) throws IOException {
      initialize(); // 값 입력 받기 및 초기화
      backtracking(0); // 궁수를 둘 수 있는 모든 경우의 수 구하기
      System.out.println(simulate()); // 모든 경우의 수마다 시뮬레이션 돌리고 최댓값 도출
    }
    
    
    public static void backtracking(int index) {
        if (archers.size() == ARCHER_CNT) {
            List<Point> tmp = new ArrayList<>();
            for (Point p : archers) {
                tmp.add(new Point(p.x, p.y));
            }
            allArchers.add(tmp);
            return;
        }
        
        for (int i = index; i < m; i++) {
            if (visited[i]) continue;
            
            visited[i] = true;
            archers.add(new Point(n, i));
            backtracking(i + 1);
            archers.remove(archers.size() - 1);
            visited[i] = false;
        }
    }
    
    
    public static int simulate() {
        int maxKillCnt = 0;
        for (List<Point> archers : allArchers) {
            int cnt = 0;
            int[][] perGrid = new int[n][m];
            copyGrid(perGrid);
            while(!isEndGame(perGrid)) {
                cnt += attack(archers, perGrid);
                move(perGrid);
            }
            maxKillCnt = Math.max(maxKillCnt, cnt);
        }
        return maxKillCnt;
    }
    
    
    private static void copyGrid (int[][] perGrid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                perGrid[i][j] = grid[i][j];
            }
        }
    }
    
    
    private static boolean isEndGame(int[][] grid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) return false;
            }
        }
        return true;
    }
    
    
    private static int attack(List<Point> archers, int[][] grid) {
        int cnt = 0;
        List<Point> killEnemies = new ArrayList<>();
        
        for (Point archer : archers) {
            killEnemies.add(getArcherTarget(grid, archer));
        }

        for (Point enemy : killEnemies) {
            if (enemy.equals(EMPTY)) continue;
            
            if (grid[enemy.x][enemy.y] == 1) {
                grid[enemy.x][enemy.y] = 0;
                cnt += 1;
            }
        }
        
        return cnt;
    }
    private static Point getArcherTarget(int[][] grid, Point archer) {
        Point target = EMPTY;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && archer.canAttack(i, j, d)) {
                    if (archer.getDist(target.x, target.y) > archer.getDist(i, j)) {
                        target = new Point(i, j);
                    } else if (archer.getDist(target.x, target.y) == archer.getDist(i, j)) {
                        if (!target.isHigherPriority(j)) {
                            target = new Point(i, j);
                        }
                    }
                }
            }
        }
        return target;
    }
    
    
    private static void move(int[][] grid) {
        int[][] newGrid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int nx = i + 1;
                    int ny = j;
                    
                    if (inRange(nx, ny)) {
                        newGrid[nx][ny] = grid[i][j];
                    }
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = newGrid[i][j];
            }
        }
    }
    private static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
    
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        d = Integer.parseInt(tmp[2]);
        
        grid = new int[n][m];
        visited = new boolean[m];
        for (int i = 0; i < n; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(tmp[j]);
            }
        }
    }
    private static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(String.format("%s ", arr[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
    private static void printArchers(List<Point> archers) {
        System.out.println("아처 정보 : ");
        for (Point a : archers) {
            System.out.print(a);
        }
        System.out.println();
    }
}


class Point {
    int x;
    int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return String.format("{%s %s}, ", x, y);
    }
    
    public int getDist(int x, int y) {
        return Math.abs(this.x - x) + Math.abs(this.y - y);
    }
    
    public boolean equals(Point p) {
        return x == p.x && y == p.y;
    }
    
    public boolean isHigherPriority(int y) {
        return this.y <= y;
    }
    
    public boolean canAttack(int x, int y, int d) {
        return getDist(x, y) <= d;
    }
}