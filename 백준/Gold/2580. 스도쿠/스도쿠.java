// 9x9 그리드
// 1. 가로줄 1 ~ 9
// 2. 세로줄 1 ~ 9
// 3. 3x3 정사각형 1 ~ 9 (범위가 존재)
// 결과 : 최종 모습 출력
import java.io.*;
import java.util.*;

public class Main {
    private static final int GRID_SIZE = 9;
    
    private static int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    
    public static void main(String args[]) throws IOException {
        initialize();
        sudoku();
        printAnswerGrid();
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < GRID_SIZE; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = Integer.parseInt(tmp[j]);
            }
        }
    }
    
    private static void sudoku() {
        List<Point> blank = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == 0) blank.add(new Point(i, j));
            }
        }
        
        simulate(0, blank, new ArrayList<>());
    }
    
    
    private static boolean simulate(int index, List<Point> blank, List<Point> cmp) {
        if (blank.size() == cmp.size()) return true;
        
        boolean[] check = new boolean[GRID_SIZE + 1];
        
        Point now = blank.get(index);
        
        checkRow(check, now.x);
        checkColumn(check, now.y);
        checkBox(check, now.x, now.y);
        
        
        for (int i = 1; i <= GRID_SIZE; i++) {
            if (!check[i]) {
                grid[now.x][now.y] = i;
                cmp.add(new Point(now.x, now.y));
                if (simulate(index + 1, blank, cmp)) return true;
                cmp.remove(cmp.size() - 1);
                grid[now.x][now.y] = 0;
            }
        }
        
        return false;
    }
    
    private static void checkRow(boolean[] check, int x) {
        for (int i = 0; i < GRID_SIZE; i++) {
            int num = grid[x][i];
            check[num] = true;
        }
    }
    
    private static void checkColumn(boolean[] check, int y) {
        for (int i = 0; i < GRID_SIZE; i++) {
            int num = grid[i][y];
            check[num] = true;
        }
    }
    
    private static void checkBox(boolean[] check, int x, int y) {
        int startX = x - (x % (GRID_SIZE / 3));
        int startY = y - (y % (GRID_SIZE / 3));
        
        for (int i = startX; i < startX + GRID_SIZE / 3; i++) {
            for (int j = startY; j < startY + GRID_SIZE / 3; j++) {
                int num = grid[i][j];
                check[num] = true;
            }
        }
    }
    
    private static void insertNumber(boolean[] check, int x, int y) {
        for (int i = 1; i <= GRID_SIZE; i++) {
            if (!check[i]) {
                grid[x][y] = i;
                return;
            }
        }
    }
    
    private static void printAnswerGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(String.format("%d ", grid[i][j]));
            }
            System.out.println();
        }
    }
}

class Range {
    Point start;
    Point end;
    
    Range(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
}

class Point {
    int x;
    int y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}