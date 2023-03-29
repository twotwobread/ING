// 내구도 <= 0 -> 파괴
// 공격 및 회복 항상 직사각형 모양
// 내구도가 0 이하가 된 이미 파괴된 건물도 공격을 받아면 계속해서 내구도 하락.
// 파괴되지 않은 건물의 개수 반환.

// 1 <= n, m <= 1000
// 1 <= skill <= 250,000
// type = 1 = 공격, type = 2 = 회복. -> degree만큼 높이거나 낮춤.

// 내 생각
//  - 격자가 1000 x 1000 이 될 수 있는데 스킬이 250,000번 쓸 수 있으면 완탐하면 최악의 경우로
//    250,000 * 1000 * 1000 = 250,000,000,000 = 2500억임....
//  - 스킬은 무조건 다 돌아야하고 줄일 수 있는 부분이 그럼 내구도를 낮추거나 높이는 부분을 줄여야 하는데
//    줄일 수 있는 방법이 구간합일꺼 같은데 어떤 식으로 적용해야할지 모르겠네,,,
//  - 누적합, 구간을 공부해보고 풀어보자.

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {

    private int n, m;
    private int[][] update;

    public int solution(int[][] board, int[][] skill) {
        initialize(board);
        getUpdateValue(skill);
        prefix();
        return getCnt(board);
    } 

    private void getUpdateValue(int[][] skills) { // O(K)
        for (int[] skill : skills) {
            Point start = new Point(skill[1], skill[2]);
            Point end = new Point(skill[3], skill[4]);
            int degree = skill[5];

            if (skill[0] == 1) { // 공격
                update[start.x][start.y] -= degree;
                update[end.x + 1][start.y] += degree;
                update[start.x][end.y + 1] += degree;
                update[end.x + 1][end.y + 1] -= degree;
            } else { // 회복
                update[start.x][start.y] += degree;
                update[end.x + 1][start.y] -= degree;
                update[start.x][end.y + 1] -= degree;
                update[end.x + 1][end.y + 1] += degree;
            }
        }
    } 

    private void prefix() { // O(N * M)
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j == 0) {
                    update[i][j] = update[i][j];
                } else {
                    update[i][j] = update[i][j] + update[i][j - 1];
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    update[i][j] = update[i][j];
                } else {
                    update[i][j] = update[i][j] + update[i-1][j];
                }
            }
        }
    }

    private int getCnt(int[][] board) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + update[i][j] > 0) result += 1;
            }
        }
        return result;
    }

    private void initialize(int[][] board) {
        n = board.length;
        m = board[0].length;
        update = new int[n + 1][m + 1];
    }
}