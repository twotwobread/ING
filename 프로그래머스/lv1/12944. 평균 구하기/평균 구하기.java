class Solution {
    public double solution(int[] arr) {
        int len = arr.length;
        
        int result = 0;
        for (int value : arr) {
            result += value;
        }
        return (double) result / len;
    }
}