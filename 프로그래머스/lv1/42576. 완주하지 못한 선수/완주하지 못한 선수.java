import java.util.*;

class Solution {
    
    Map<String, Integer> map = new HashMap<>();
    
    public String solution(String[] participant, String[] completion) {
        String result = "none";
        for (String name : participant) {
            if (map.containsKey(name)) {
                map.put(name, map.get(name) + 1);
            } else {
                map.put(name, 1);
            }
        }
        
        
        for (String name : completion) {
            if (map.containsKey(name) && map.get(name) > 0) {
                map.put(name, map.get(name) - 1);
            }
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                result = entry.getKey();
            }
        }
        
        return result;
    }
}