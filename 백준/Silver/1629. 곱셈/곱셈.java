import java.io.*;
import java.util.*;

public class Main {
    private static long a, b, c;
    private static long dp;
    
    public static void main(String args[]) throws IOException {
        initialize();
        System.out.println(calculate(a, b));
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        a = Long.parseLong(tmp[0]);
        b = Long.parseLong(tmp[1]);
        c = Long.parseLong(tmp[2]);
    }
    
    private static long calculate(long x, long n) {
        if (n == 1) {
            return x % c;
        }
        
        long left = n / 2;
        long leftValue = calculate(x, left);
        
        if (n % 2 == 1) {
            return leftValue * leftValue % c * x % c;
        }
        return leftValue * leftValue % c;
    }
}