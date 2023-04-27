class Solution {
    private static final int MAX_SIZE = 1000000, EMPTY = Integer.MAX_VALUE;
    private int[] dp = new int[MAX_SIZE + 1];
    
    public int solution(int x, int y, int n) {
        initialize(x, y);
        countMinCalculate(x, y, n);
        return dp[y] == EMPTY ? -1 : dp[y];
    }
    
    private void countMinCalculate(int x, int y, int n) {
        for (int i = x; i < y; i++) {
            if (dp[i] == EMPTY) continue;
            
            if (i + n <= y) dp[i + n] = Math.min(dp[i + n], dp[i] + 1);
            if (i * 2 <= y) dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            if (i * 3 <= y) dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
        }
    }
    
    private void initialize(int x, int y) {
        for (int i = x; i <= y; i++) {
            if (i == x) {
                dp[i] = 0;
                continue;
            }
            dp[i] = EMPTY;
        }
    }
}