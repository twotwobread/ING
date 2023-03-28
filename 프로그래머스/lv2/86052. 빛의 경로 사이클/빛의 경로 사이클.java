import java.util.*;

class Solution {
    
    private static final int DIR_NUM = 4, MAX_SIZE = 500;
    private int r, c;
    
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    private boolean[][][] visited = new boolean[MAX_SIZE][MAX_SIZE][DIR_NUM];
    private int[][] value = new int[MAX_SIZE][MAX_SIZE];
    
    private List<Integer> result = new ArrayList<>();
    
    public int[] solution(String[] grid) {
        initialize(grid);
        move();
        int[] answer = result.stream()
            .mapToInt(i -> i)
            .toArray();
        Arrays.sort(answer);
        return answer;
    }
    
    private void initialize(String[] grid) {
        r = grid.length;
        c = grid[0].length();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i].charAt(j) == 'S') {
                    value[i][j] = 0;
                } else if(grid[i].charAt(j) == 'L') {
                    value[i][j] = 1;
                } else {
                    value[i][j] = -1;
                }
            }
        }
    }
    
    private void move() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < DIR_NUM; k++) {
                    if (!visited[i][j][k]) {
                        result.add(moveRoute(i, j, k));
                    }
                }
            }
        }
    }
    
    private int moveRoute(int x, int y, int d) {
        int cnt = 0;
        int nowX = x;
        int nowY = y;
        int nowD = d;
        while(true) {
            
            if (visited[nowX][nowY][nowD])
                break;
            
            cnt++;
            visited[nowX][nowY][nowD] = true;
            
            int nx = (nowX + dx[nowD] + r) % r;
            int ny = (nowY + dy[nowD] + c) % c;
            
            int nd = (DIR_NUM + nowD + value[nx][ny]) % DIR_NUM;
            
            nowX = nx;
            nowY = ny;
            nowD = nd;
        }
        
        return cnt;
    }
}