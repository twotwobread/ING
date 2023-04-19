import java.util.*;

class Info implements Comparable<Info> {
    boolean isWanho;
    int workPoint;
    int peerPoint;
    
    public Info(boolean isWanho, int workPoint, int peerPoint) {
        this.isWanho = isWanho;
        this.workPoint = workPoint;
        this.peerPoint = peerPoint;
    }
    
    public int getSum() {
        return workPoint + peerPoint;
    }
    
    public boolean isBiggerAllPoint(Info info) {
        return workPoint > info.workPoint && peerPoint > info.peerPoint;
    }
    
    @Override
    public int compareTo(Info info) {
        return info.getSum() - getSum();
    }
    
    @Override
    public String toString() {
        return String.format("완호:%s, 태도점수:%s, 동료점수:%s", isWanho, workPoint, peerPoint);
    }
}

class Solution {
    private List<Info> infoArr = new ArrayList<>();
    
    public int solution(int[][] scores) {
        initialize(scores);
        Collections.sort(infoArr);
        int index = getFindWanho();
        if (isPossibleGetIncentive(index)) {
            return getWanhoRank(index);
        } else {
            return -1;
        }
    }
    
    private int getWanhoRank(int index) {
        Info wanhoInfo = infoArr.get(index);
        int higherFailerCnt = countHigherPointFailer(index, wanhoInfo);
        return (index+1) - higherFailerCnt;
    }
    
    private int countHigherPointFailer(int index, Info goal) {
        int cnt = 0;
        for (int i = index - 1; i >= 0; i--) {
            Info nowInfo = infoArr.get(i);
            if (goal.getSum() == nowInfo.getSum()) {
                cnt += 1;
                continue;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (infoArr.get(j).isBiggerAllPoint(nowInfo)) {
                    cnt += 1;
                    break;
                }
            }
        }
        return cnt;
    }
    
    private boolean isPossibleGetIncentive(int index) {
        Info wanhoInfo = infoArr.get(index);
        for (int i = index - 1; i >= 0; i--) {
            if (infoArr.get(i).isBiggerAllPoint(wanhoInfo)) {
                return false;
            }
        }
        return true;
    }
    
    private int getFindWanho() {
        for (int i = 0; i < infoArr.size(); i++) {
            if (infoArr.get(i).isWanho)
                return i;
        }
        return -1;
    }
    
    private void initialize(int[][] scores) {
        for (int i = 0; i < scores.length; i++) {
            if (i == 0) {
                infoArr.add(new Info(true, scores[i][0], scores[i][1]));
            } else {
                infoArr.add(new Info(false, scores[i][0], scores[i][1]));
            }
        }
    }
    
    private void printArr() {
        System.out.println("infoArr 출력하기");
        for (int i = 0; i < infoArr.size(); i++) {
            System.out.println(infoArr.get(i));    
        }
        System.out.println();
    }
}