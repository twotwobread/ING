import java.util.*;

class Solution {
    
    Map<Integer, Integer> result = new HashMap<>();
    
    public int solution(int[] nums) {
        int cnt = 0;
        int goal = nums.length/2;
        for (int num : nums) {
            if (result.containsKey(num))
                continue;
            result.put(num, 1);
            cnt++;
        }
        
        if (goal < cnt)
            return goal;
        else
            return cnt;
    }
}