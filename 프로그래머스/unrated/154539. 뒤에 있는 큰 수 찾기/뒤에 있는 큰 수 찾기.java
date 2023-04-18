import java.util.*;

class Pos {
    int value;
    int index;
    
    public Pos(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

class Solution {
    private List<Pos> stack = new ArrayList<>();
    private int[] result;
    
    public int[] solution(int[] numbers) {
        initialize(numbers);
        getBackBiggerNumber(numbers);
        return result;
    }
    
    private void getBackBiggerNumber(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int cmp = nums[i];
            if (stack.size() > 0) {
                while(stack.size() > 0 && stack.get(stack.size() - 1).value < cmp) {
                    Pos now = stack.remove(stack.size() - 1);
                    result[now.index] = cmp;
                }
                stack.add(new Pos(cmp, i));
            }
        }
        
        if (stack.size() > 0) {
            while(!stack.isEmpty()) {
                Pos now = stack.remove(stack.size() - 1);
                result[now.index] = -1;
            }
        }
    }
    
    private void initialize(int[] nums) {
        stack.add(new Pos(nums[0], 0));
        result = new int[nums.length];
    }
}