import java.io.*;
import java.util.*;

public class Main {
    private static final int INF = 987654321;
    
    private static int n, m, w, tc;
    private static List<List<Edge>> edges;
    
    public static void main(String args[]) throws IOException {
        printResultAfterSimulate();
    }
    
    private static void printResultAfterSimulate() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            initialize(br);
            sb.append(bellmanFord(0));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
    private static void initialize(BufferedReader br) throws IOException {
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        w = Integer.parseInt(tmp[2]);
        
        edges = new ArrayList<>();
        for (int i = 0 ; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            tmp = br.readLine().split(" ");
            int s = Integer.parseInt(tmp[0]) - 1;
            int e = Integer.parseInt(tmp[1]) - 1;
            int t = Integer.parseInt(tmp[2]);
            edges.get(s).add(new Edge(e, t));
            edges.get(e).add(new Edge(s, t));
        }
        
        for (int i = 0; i < w; i++) {
            tmp = br.readLine().split(" ");
            int s = Integer.parseInt(tmp[0]) - 1;
            int e = Integer.parseInt(tmp[1]) - 1;
            int t = Integer.parseInt(tmp[2]);
            edges.get(s).add(new Edge(e, -t));
        }
    }
    
    private static String bellmanFord(int start) {
        int[] distance = new int[n];
        Arrays.fill(distance, INF);
        
        distance[start] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                for (Edge e : edges.get(j)) {
                    if (distance[e.node] > distance[j] + e.dist) {
                        distance[e.node] = distance[j] + e.dist;
                    }
                }
            }
        }
        
        if (checkNegativeCycle(distance)) {
            return String.format("YES");
        }
        return String.format("NO");
    }
    
    private static boolean checkNegativeCycle(int[] distance) {
        for (int j = 0; j < n; j++) {
            for (Edge e : edges.get(j)) {
                if (distance[e.node] > distance[j] + e.dist) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Edge {
    int node;
    int dist;
    
    Edge(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}