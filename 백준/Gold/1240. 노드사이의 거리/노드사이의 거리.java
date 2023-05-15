import java.io.*;
import java.util.*;

public class Main {
    private static int n, m;
    private static List<Pair> pairs = new ArrayList<>();
    private static List<List<Edge>> edges = new ArrayList<>();
    
    public static void main(String args[]) throws IOException {
        initialize();
        simulate();
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        
        for (int i = 0; i < n - 1; i++) {
            tmp = br.readLine().split(" ");
            int s = Integer.parseInt(tmp[0]) - 1;
            int e = Integer.parseInt(tmp[1]) - 1;
            int d = Integer.parseInt(tmp[2]);
            edges.get(s).add(new Edge(e, d));
            edges.get(e).add(new Edge(s, d));
        }
        
        for (int i = 0; i < m; i++) {
            tmp = br.readLine().split(" ");
            int s = Integer.parseInt(tmp[0]) - 1;
            int e = Integer.parseInt(tmp[1]) - 1;
            pairs.add(new Pair(s, e));
        }
    }
    
    private static void simulate() {
        StringBuilder sb = new StringBuilder();
        for (Pair p : pairs) {
            sb.append(bfs(p));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
    private static int bfs(Pair pair) {
        Deque<Edge> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int result = 0;
        
        int goal = pair.end;
        
        q.add(new Edge(pair.start, 0));
        visited[pair.start] = true;
        while(!q.isEmpty()) {
            Edge now = q.remove();
            
            if (now.node == goal) {
                result = now.dist;
            }
            
            for (Edge adj : edges.get(now.node)) {
                if (!visited[adj.node]) {
                    q.add(new Edge(adj.node, now.dist + adj.dist));
                    visited[adj.node] = true;
                }
            }
        }
        return result;
    }
}

class Edge {
    int node;
    int dist;
    
    public Edge(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}

class Pair {
    int start;
    int end;
    
    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }
}