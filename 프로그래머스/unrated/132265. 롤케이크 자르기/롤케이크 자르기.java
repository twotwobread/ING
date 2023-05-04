class Solution {
    private static final int MAX_ELE = 10000;
    
    private static int result = 0;
    private int leftCases = 0, rightCases = 0;
    private int[] leftCheck = new int[MAX_ELE + 1], rightCheck = new int[MAX_ELE + 1];
    
    public int solution(int[] topping) {
        initialize(topping); // check 배열 만들기.
        getNumberOfCases(topping); // 배열 체크하면서 경우의 수 구하기.
        return result;
    }
    
    private void initialize(int[] topping) {
        for (int t : topping) {
            if (rightCheck[t] == 0) rightCases += 1;
            rightCheck[t] += 1;
        }
    }
    
    private void getNumberOfCases(int[] topping) {
        for (int t : topping) {
            if (leftCheck[t] == 0) leftCases += 1;
            if (rightCheck[t] == 1) rightCases -= 1;
            if (leftCases == rightCases) result += 1;
            
            leftCheck[t] += 1;
            rightCheck[t] -= 1;      
        }
    }
}