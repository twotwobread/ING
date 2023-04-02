class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int result = 0;
        int zeroCnt = 0;
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) zeroCnt += 1;
            for (int j = 0; j < win_nums.length; j++) {
                if (lottos[i] == win_nums[j]) {
                    result += 1;
                    break;
                }
            }
        }
        int maxValue = Math.max(0, zeroCnt);
        int minValue = Math.min(0, zeroCnt);
        int maxRank = 7 - ((result + maxValue) > 6 ? 6 : (result + maxValue)) ;
        int minRank = 7 - ((result + minValue) > 6 ? 6 : (result + minValue));
        
        if (maxRank > 6) maxRank = 6;
        if (minRank <= 0) minRank = 1;
        System.out.println(String.format("최소 최대 : %s %s", minValue, maxValue));
        System.out.println(String.format("제로 결과 : %s %s", zeroCnt, result));
        return new int[] {maxRank <= 0 ? 1 : maxRank, minRank > 6 ? 6 : minRank};
    }
}