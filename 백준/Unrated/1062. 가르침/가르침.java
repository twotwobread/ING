import java.io.*;
import java.util.*;

public class Main {
    private static final int ALPHA_SIZE = 26, INIT_CNT = 5;
    private static final char[] INIT_CHAR = {'a', 'n', 't', 'i', 'c'};
    
    private static int n, k, result = 0;
    private static boolean[][] words;
    private static boolean[] cmpWord = new boolean[ALPHA_SIZE];
    
    public static void main(String args[]) throws IOException {
        initialize();
        if (k < 5) {
            System.out.println(0);
            return;
        }
        if (k == 26) { 
            System.out.println(n);
            return;
        }
        
        backtracking(0, 0);
        System.out.println(result);
    }
    
    private static void backtracking(int cnt, int index) {
        if (k - INIT_CNT == cnt) {
            int tmp = countContainsWord();
            result = Math.max(result, tmp);
            return;
        }
        
        for (int i = index; i < ALPHA_SIZE; i++) {
            if (cmpWord[i]) continue;
            
            cmpWord[i] = true;
            backtracking(cnt + 1, i+1);
            cmpWord[i] = false;
        }
    }
    
    private static int countContainsWord() {
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            boolean check = true;
            for (int j = 0; j < ALPHA_SIZE; j++) {
                if (!words[i][j]) continue;
                if (!cmpWord[j]) {
                    check = false;
                    break;
                } 
            }
            if (check) tmp += 1;
        }
        return tmp;
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        k = Integer.parseInt(str[1]);
        
        words = new boolean[n][ALPHA_SIZE];
        for (int i = 0; i < n; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (char c : tmp) {
                words[i][c-'a'] = true;
            }
        }
        
        for (char c : INIT_CHAR) {
            cmpWord[c-'a'] = true;
        }
    }
}