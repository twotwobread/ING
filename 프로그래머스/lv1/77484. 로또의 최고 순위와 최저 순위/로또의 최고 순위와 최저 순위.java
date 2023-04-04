class Solution {

    private int[] ranking = {6, 6, 5, 4, 3, 2, 1, 1, 1, 1};

    public int[] solution(int[] lottos, int[] win_nums) {
        int nowCnt = 0;
        int zeroCnt = 0;
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) zeroCnt += 1;
            for (int j = 0; j < win_nums.length; j++) {
                if (win_nums[i] == lottos[j]) {
                    nowCnt += 1;
                    break;
                }
            }
        }

        int maxResult = Math.max(0, zeroCnt) + nowCnt;
        int minResult = Math.min(0, zeroCnt) + nowCnt;
        return new int[] {ranking[maxResult], ranking[minResult]};
    }
}