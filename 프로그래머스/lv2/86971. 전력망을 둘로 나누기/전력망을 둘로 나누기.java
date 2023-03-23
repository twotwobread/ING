import java.util.*;

class Solution {

    private static final int MAX_NUM = 100;

    private int size;

    private int minValue = Integer.MAX_VALUE;
    private int[][] nowWires = new int[MAX_NUM + 1][MAX_NUM + 1];
    private boolean[] visited = new boolean[MAX_NUM + 1];

    public int solution(int n, int[][] wires) {
        size = n;
        for (int i = 0; i < size-1; i++) {
            initialize(wires, i);
            minValue = Math.min(minValue, calculate());
        }

        return minValue;
    }

    private int calculate() {
        Deque<Integer> q = new ArrayDeque<>();

        int cnt = 0;
        for (int i = 1; i <= size; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                q.add(i);
                int tmp = bfs(q);

                cnt = Math.abs(cnt - tmp);
            }
        }

        return cnt;
    }

    private int bfs(Deque<Integer> q) {
        int cnt = 0;
        while(!q.isEmpty()) {
            cnt++;
            Integer node = q.remove();

            for (int i = 1; i <= size; i++) {
                if (nowWires[node][i] == 1 && visited[i] == false) {
                    visited[i] = true;
                    q.add(i);
                }
            } 
        }

        return cnt;
    }

    private void initialize(int[][] wires, int num) {
        for (int i = 0; i < size-1; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            nowWires[a][b] = 0;
            nowWires[b][a] = 0;

            if (i == num) continue;

            nowWires[a][b] = 1;
            nowWires[b][a] = 1;
        }

        for (int i = 0; i <= size; i++) {
            visited[i] = false;
        }
    }
}