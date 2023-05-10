import java.io.*;
import java.util.*;

public class Main {
    private static int n, k;
    private static Integer[] sensors, diff;
    
    public static void main(String args[]) throws IOException {
        initialize();
        Arrays.sort(sensors);
        makeDiffArray();
        System.out.println(getReceiveDiff());
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        sensors = new Integer[n];
        
        String[] tmp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(tmp[i]);
        }
    }
    
    private static void makeDiffArray() {
        diff = new Integer[n-1];
        for (int i = 1; i < n; i++) {
            diff[i-1] = sensors[i] - sensors[i-1];
        }
        Arrays.sort(diff, Collections.reverseOrder());
    }
    
    private static int getReceiveDiff() {
        int result = 0;
        for (int i = k-1; i < diff.length; i++) {
            result += diff[i];
        }
        return result;
    }
}