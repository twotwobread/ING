class Solution {
    public int solution(int[][] sizes) {
        
        int width = -1;
        int height = -1;
        
        for (int[] card : sizes) {
            int maxValue = Math.max(card[0], card[1]);
            int minValue = Math.min(card[0], card[1]);
            
            if (width < maxValue) {
                width = maxValue;
            }
            
            if (height < minValue) {
                height = minValue;
            }
        }
        
        return width * height;
    }
}