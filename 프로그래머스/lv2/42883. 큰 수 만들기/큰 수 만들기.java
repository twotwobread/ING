import java.util.*;
import java.util.stream.Collectors;

class Solution {
    
    public String solution(String number, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] seq = number.toCharArray();
        
        int index = 0;
        while(index < number.length()) {
            stack.add(seq[index++]);
            while(index < number.length() && stack.size() > 0 && stack.peekLast() < seq[index] && k > 0) {
                stack.removeLast();
                k -= 1;
            }
        }
        
        if (k > 0) {
            while(k > 0) {
                stack.removeLast();
                k -= 1;
            }
        }
        
        return stack.stream()
            .map(String::valueOf)
            .collect(Collectors.joining());
    }
}