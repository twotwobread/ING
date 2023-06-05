// S(k) = S(k - 1) + o가 k+2개인 수열 "m o . . . o" + S(k - 1)
// 일단 그냥 분할 정복이용해서 N이 주어지면 k 값을 구하도록 구성하고.
// k값을 알면 전체 길이가 몇인지 알 수 있나?,,,
// 무조건 마지막
import java.io.*;
import java.util.*;

public class Main {
    private static Map<Long, Boolean> mMap = new LinkedHashMap<>();
    private static long n;
    
    public static void main(String args[]) throws IOException {
        initialize();
        simulate(n, 0, 0);
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
    }
    
    private static void simulate(long num, long k, long prevLen) {
        if (k == 0 && num <= 3) {
            System.out.println(num == 1 ? "m" : "o");
            return;
        }
        
        long nowLen = prevLen * 2 + (k + 3);
        if (nowLen < num) {
            simulate(num, k + 1, nowLen);
        } else { // 여기서 prevLen보다 짧은 경우는 없음 -> 즉, 2번째 공간 혹은 3번째 공간에 속함
            if (num <= prevLen) {
                simulate(num, k - 1, (prevLen - (k + 2)) / 2);
            } else if (prevLen < num  && num <= prevLen + (k + 3)) {// 2번째 영역
                System.out.println(num - prevLen == 1 ? "m" : "o");
                return;
            } else { //마지막 영역
                simulate(num - (prevLen + k + 3), k - 1, (prevLen - (k + 2)) / 2);
            }
        }
    }
}