class Result {
    private boolean isWin;
    private int moveCnt;
    
    public Result(boolean isWin, int moveCnt) {
        this.isWin = isWin;
        this.moveCnt = moveCnt;
    }
    
    public int getMoveCnt() {
        return moveCnt;
    }
    
    public boolean getIsWin() {
        return isWin;
    }
}

class Solution {
    
    private static final int DIR_SIZE = 4;
    
    private int boardX, boardY;
    
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        boardX = board.length;
        boardY = board[0].length;
        
        return move(board, aloc[0], aloc[1], bloc[0], bloc[1]).getMoveCnt(); // A부터 시작
    }
    
    private Result move(int[][] board, int ax, int ay, int bx, int by) {
        if (isEnded(board, ax, ay)) {
            return new Result(false, 0);
        }
        
        if (ax == bx && ay == by) {
            return new Result(true, 1);
        }
        
        boolean canWin = false;
        int maxValue = -1;
        int minValue = Integer.MAX_VALUE;
        board[ax][ay] = 0;
        
        for (int i = 0; i < DIR_SIZE; i++) {
            int nx = ax + dx[i];
            int ny = ay + dy[i];
            
            if (!inRange(nx, ny) || board[nx][ny] == 0)
                continue;
            
            Result nextResult = move(board, bx, by, nx, ny);
                    
            if (nextResult.getIsWin()) {
                maxValue = Math.max(maxValue, nextResult.getMoveCnt());
            } else {
                canWin = true;
                minValue = Math.min(minValue, nextResult.getMoveCnt());
            }
        }
        
        board[ax][ay] = 1;
        return new Result(canWin, (canWin ? minValue : maxValue) + 1);
    }
    
    private boolean isEnded(int[][] board, int x, int y) {
        for (int i = 0; i < DIR_SIZE; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inRange(nx, ny) && board[nx][ny] == 1)
                return false;
        }
        return true;
    }
    
    private boolean inRange(int x, int y) {
        return 0 <= x && x < boardX && 0 <= y && y < boardY;
    }
}