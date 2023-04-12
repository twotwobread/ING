import java.io.*;
import java.util.*;

class Solution {
    
    private Map<Integer, Boolean> map = new HashMap<>();
    private int size;
    
    
    public int solution(int[] elements) {
        size = elements.length;
        return getSubSequence(elements);
    }
    
    private int getSubSequence(int[] elements) {
        int cnt = 0;
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < size; j++) {
                int tmp = 0;
                for (int k = 0; k < i; k++) {
                    tmp += elements[(j + k) % size];
                }
                
                if (!map.containsKey(tmp)) {
                    cnt += 1;
                    map.put(tmp, true);
                }    
            }
        }
        return cnt;
    }
}