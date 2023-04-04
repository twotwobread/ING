import java.util.*;

class Solution {

    private static final int SIZE = 5, DIR_SIZE = 4, MAX_DIST = 2;
    private int[][] visited = new int[SIZE][SIZE];

    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0 ,-1};

    public int[] solution(String[][] places) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < places.length; i++) {
            initialize();
            result.add(checkSeat(places[i]));
        }
        return result.stream()
            .mapToInt(i -> i)
            .toArray();
    }

    private int checkSeat(String[] grid) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i].charAt(j) != 'P') continue;
                if (checkWrongSeatInRange(grid, i, j)) return 0; 
            }
        }
        return 1;
    }

    private boolean checkWrongSeatInRange(String[] grid, int x, int y) {
        for (int i = x - MAX_DIST; i <= x + MAX_DIST; i++) {
            for (int j = y - MAX_DIST; j <= y + MAX_DIST; j++) {
                if (!inRange(i, j) || (i == x && j == y)) continue;
                if (grid[i].charAt(j) != 'P' || getDist(i, j, x, y) > MAX_DIST) continue;
                if (!checkPartition(grid, i, j, x, y)) return true;
            }
        }
        return false;
    }

    private boolean checkPartition(String[] grid, int x, int y, int goalx, int goaly) {
        if (x > goalx) {
            if (y > goaly) {
                return grid[x - 1].charAt(y) == 'X' && grid[x].charAt(y - 1) == 'X';
            } else if (y < goaly) {
                return grid[x - 1].charAt(y) == 'X' && grid[x].charAt(y + 1) == 'X';
            } else {
                return grid[x - 1].charAt(y) == 'X';
            }    
        } else if (x < goalx) {
            if (y > goaly) {
                return grid[x + 1].charAt(y) == 'X' && grid[x].charAt(y - 1) == 'X';
            } else if (y < goaly) {
                return grid[x + 1].charAt(y) == 'X' && grid[x].charAt(y + 1) == 'X';
            } else {
                return grid[x + 1].charAt(y) == 'X';
            }
        } else {
            if (y > goaly) {
                return grid[x].charAt(y - 1) == 'X';
            } else if (y < goaly) {
                return grid[x].charAt(y + 1) == 'X';
            }
        }
        return true;
    }

    private int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    

    private boolean inRange(int x, int y) {
        return 0 <= x && x < SIZE && 0 <= y && y < SIZE;
    }

    private void initialize() {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                visited[i][j] = 0;
            }
        }
    }
}