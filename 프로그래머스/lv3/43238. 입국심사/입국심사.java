// 제한이 엄청 큰 값임. -> 완전 탐색은 불가능.
// 문제에서 요구하는 것은 단순하게 모든 사람이 심사를 받는데 걸리는 시간 최소화.
// 파라메트리 서치를 이용
//  - 내가 하고 싶은 행동은 심사를 받는데 걸리는 시간 최소화.
//  - 발생 가능한 가장 최소 시간 = 1, 최대 시간 = 1,000,000,000 ^ 2 = 100경 -> long 타입 이용
//  - 특정 mid 값을 선택 -> 이 시간안에 입국심사 기다리는 사람을 다 심사할 수 있는지 체크
//    - 할 수 있으면 시간을 더 작게, 할 수 없으면 시간을 더 크게

class Solution {

    private long result;

    public long solution(int n, int[] times) {
        long maxTime = (long)1000000000 * (long)1000000000;
        long minTime = 1;
        result = maxTime;
        search(times, n, minTime, maxTime);
        return result;
    }

    private void search(int[] times, int goal, long start, long end) {
        while(start <= end) {
            long mid = (start + end) / 2;

            long timeCnt = 0;
            for (int time : times) {
                timeCnt += (mid / time);
            }

            if (goal <= timeCnt) {
                result = Math.min(result, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }
}