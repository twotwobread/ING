// 스코어를 근무 점수가 높은 순으로 정렬하고 같다면 동료 점수가 낮은 순으로 정렬한다면
// 앞에서부터 for문을 돌 때, 근무 점수는 낮아질 것이고 만약 근무 점수가 같다고 해도 동료 점수는 같거나 무조건 높아집니다.
// 이를 이용해서 해결해보면
// 1. 최대 동료 점수를 가지고 있고 이것보다 높거나 같은 경우라면 최대 동료 점수를 계속 갱신시킵니다.
// 2. 만약 최대 동료 점수보다 낮아진다면 이건 근무 점수가 더 낮은 수로 바뀐 경우가 되고 그 때 동료 점수도 낮아진 것이 되니까
//    그게 완호의 점수랑 같으면 완호는 인센티브를 못받게 되는 겁니다.
// 3. 그리고 위에서 최대 동료 점수를 갱신할 때마다 완호보다 점수가 높은지를 체크하고 높으면 갯수를 올리고 이게 완호보다 더 랭킹이 높은
//    사람을 의미하기에 이를 이용해서 처리를 해봅시다.

import java.util.*;

class Score implements Comparable<Score> {
    int attitude;
    int peer;
    
    public Score(int attitude, int peer) {
        this.attitude = attitude;
        this.peer = peer;
    }
    
    @Override
    public int compareTo(Score s) {
        if (attitude == s.attitude) {
            return peer - s.peer;
        }
        return s.attitude - attitude;
    }
    
    public boolean equals(Score s) {
        return attitude == s.attitude && peer == s.peer;
    }
    
    public int getSum() {
        return attitude + peer;
    }
}

class Solution {
    private List<Score> scoreList = new ArrayList<>();
    
    public int solution(int[][] scores) {
        initialize(scores);
        Collections.sort(scoreList);
        return getWanhoRank(new Score(scores[0][0], scores[0][1]));
    }
    
    private int getWanhoRank(Score wanho) {
        int maxPeerPoint = 0, ranking = 0;
        for (Score s : scoreList) {
            if (maxPeerPoint > s.peer) {
                if (s.equals(wanho)) {
                    return -1;
                }
            } else {
                maxPeerPoint = Math.max(maxPeerPoint, s.peer);
                if (wanho.getSum() < s.getSum()) {
                    ranking += 1;
                }
            }
        }
        return ranking + 1;
    }
    
    private void initialize(int[][] scores) {
        for (int[] score : scores) {
            scoreList.add(new Score(score[0], score[1]));
        }
    }
}