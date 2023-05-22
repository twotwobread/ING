import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    private static int n;
    private static int[] tp, time;
    
    private static List<List<Integer>> afterWorks = new ArrayList<>();
    
    public static void main(String args[]) throws IOException {
        initialize();
        System.out.println(topological());
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tp = new int[n + 1];
        time = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            afterWorks.add(new ArrayList<>());
        }
        
        for (int i = 1; i <= n; i++) {
            String[] tmp = br.readLine().split(" ");
            time[i] = Integer.parseInt(tmp[0]);
            tp[i] = Integer.parseInt(tmp[1]);
            
            for (int j = 2; j < tmp.length; j++) {
                int w = Integer.parseInt(tmp[j]);
                afterWorks.get(w).add(i);
            }
        }
    }
    
    private static int topological() {
        int[] workTime = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (tp[i] == 0) {
                q.add(i);
                workTime[i] = time[i];
            }
        }
        
        while(!q.isEmpty()) {
            int now = q.remove();
            
            for (int after : afterWorks.get(now)) {
                tp[after] -= 1;
                workTime[after] = Math.max(workTime[after], workTime[now] + time[after]);
                if (tp[after] == 0) {
                    q.add(after);
                }
            }
        }
        
        return getTime(workTime);
    }
    
    private static int getTime(int[] timeArray) {
        // for (int i = 0; i < timeArray.length; i++) {
        //     System.out.print(timeArray[i] + " ");
        // }
        // System.out.println();
        return IntStream.of(timeArray)
            .max()
            .orElse(0);
    }
}
