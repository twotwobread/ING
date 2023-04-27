import java.io.*;
import java.util.*;

public class Main {
    private static final String EMPTY = "FRULA";
    
    private static String bomb, explodeStr;
    private static StringBuilder result = new StringBuilder();
    
    public static void main(String args[]) throws IOException {
        initialize(); // 입력 및 초기화
        explode(); // 폭발 문자열 처리
        System.out.println(result.toString().isEmpty() ? EMPTY : result.toString()); // 더 이상 폭발되지 않는 문자열 출력.
    }
    
    private static void explode() {
        for (int i = 0; i < explodeStr.length(); i++) {
            result.append(explodeStr.charAt(i));
            if (result.length() >= bomb.length()) {
                if (isEqualsBomb()) {
                    int start = result.length() - bomb.length();
                    result.delete(start, result.length());
                }
            }
        }
    }
    
    private static boolean isEqualsBomb() {
        int index = result.length() - bomb.length();
        for (int i = 0; i < bomb.length(); i++) {
            if (result.charAt(index + i) != bomb.charAt(i)) { 
                return false;
            }
        }
        return true;
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        explodeStr = br.readLine();
        bomb = br.readLine();
    }
}
