import java.io.*;

public class Main {
    private static int n, k;
    
    public static void main(String args[]) throws IOException {
        initialize(); // 입력 받기.
        System.out.println(getKNumber());
    }
    
    private static long getKNumber() {
        long start = 1;
        long end = k;
        while (start < end) {
            long mid = (start + end) / 2;
            long numberLowerThanMid = countLowerThan(mid);
            
            if (numberLowerThanMid < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
    
    private static long countLowerThan(long mid) {
        long cnt = 0;
        for (int i = 1; i <= n; i++) {
            cnt += (Math.min(n, mid / i));
        }
        return cnt;
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
    }
}