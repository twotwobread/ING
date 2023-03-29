class Result {
    boolean isEnded;
    int cnt;

    Result(boolean isEnded, int cnt) {
        this.isEnded = isEnded;
        this.cnt = cnt;
    }
}

class Solution {

    private String[] alpabet = {"A", "E", "I", "O", "U"};
    private StringBuilder tmp = new StringBuilder();

    private int cnt = 0;

    public int solution(String word) {
       return dfs(word).cnt;
    }

    private Result dfs(String goal) {
        if (tmp.toString().equals(goal)) {
            return new Result(true, cnt);
        }

        if (tmp.length() == 5) {
            return new Result(false, cnt);
        }

        for (int i = 0; i < alpabet.length; i++) {
            cnt += 1;
            tmp.append(alpabet[i]);
            Result result = dfs(goal);
            if (result.isEnded) {
                return new Result(true, cnt);
            }
            tmp.deleteCharAt(tmp.length() - 1);
        }

        return new Result(false, cnt);
    }
}