import java.io.*;
import java.util.*;

public class Main {
    
    private static char[] prefix;
    private static StringBuilder result = new StringBuilder();
    private static char[] priority = new char[300];
    
    public static void main(String args[]) throws IOException {
        initialize();
        convertPrefixToPostfix();
        System.out.println(result.toString());
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        prefix = br.readLine().toCharArray();
        priority['*'] = 1;
        priority['/'] = 1;
        priority['+'] = 2;
        priority['-'] = 2;
    }
    
    private static void convertPrefixToPostfix() {
        Deque<Character> stack = new LinkedList<>();
        
        for (char c : prefix) {
            convert(c, stack);
        }
        
        while (!stack.isEmpty()) {
            result.append(stack.pollLast());
        }
    }
    
    private static void convert(char c, Deque<Character> stack) {
        if (isAlphabet(c)) {
            result.append(c);                       
        } else if (isClosedBracket(c)) {
            while (!stack.isEmpty()) {
                char tmp = stack.pollLast();
                if (isOpenedBracket(tmp)) break;
                result.append(tmp);
            }
        } else if (isOpenedBracket(c)){
            stack.add(c);
        } else {
            while(!stack.isEmpty()) {
                char tmp = stack.peekLast();
                if (isOpenedBracket(tmp) || priority[tmp] > priority[c]) break;
                result.append(stack.pollLast());
            }
            stack.add(c);
        }
    }
    
    private static boolean isAlphabet(char c) {
        return Character.isAlphabetic(c);
    }
    
    private static boolean isOpenedBracket(char c) {
        return c == '(';
    }
    
    private static boolean isClosedBracket(char c) {
        return c == ')';
    }
}