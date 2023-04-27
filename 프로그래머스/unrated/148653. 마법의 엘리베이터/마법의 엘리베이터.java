import java.util.*;

class Solution {
    private final List<Integer> moves = new ArrayList<>();
    
    public int solution(int storey) {
        getMove(storey);
        return bfs(storey);
    }
    
    private int bfs(int storey) {
        int minValue = Integer.MAX_VALUE;
        Deque<Move> q = new LinkedList<>();
        q.add(new Move(storey, 0));
        while(!q.isEmpty()) {
            Move now = q.remove();
            
            if (now.pos < 10) {
                minValue = Math.min(minValue, now.cnt + now.pos);
                minValue = Math.min(minValue, now.cnt + (10 - now.pos) + 1);
                continue;
            }
            
            if (now.pos % 10 == 0) {
                q.add(new Move(now.pos / 10, now.cnt));
            } else {
                q.add(new Move(now.pos / 10, now.cnt + (now.pos % 10)));
                q.add(new Move(now.pos / 10 + 1, now.cnt + (10 - now.pos % 10)));
            }
        }
        return minValue;
    }
    
    private void getMove(int s) {
        int result = 1;
        moves.add(result * -1);
        moves.add(result);
        while (result <= s) {
            result *= 10;
            moves.add(result * -1);
            moves.add(result);
        }
    }
}

class Move {
    int pos;
    int cnt;
    
    Move(int pos, int cnt) {
        this.pos = pos;
        this.cnt = cnt;
    }
}