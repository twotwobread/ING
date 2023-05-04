import java.io.*;
import java.util.*;

public class Main {
    private static int n;
    private static int maxDist = 0, farNode = -1;
    private static List<List<Vertex>> grid = new ArrayList<>();
    private static boolean[] visited;
    
    public static void main(String args[]) throws IOException {
        initialize();
        dfs(0, 0);
        dfs(0, farNode);
        System.out.println(maxDist);
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            grid.add(new ArrayList<>());
        }
        
        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            int loop = 0;
            int node = Integer.parseInt(tmp[loop++]) - 1;
            while(Integer.parseInt(tmp[loop]) != -1) {
                int dest = Integer.parseInt(tmp[loop++]) - 1;
                int dist = Integer.parseInt(tmp[loop++]);
                
                grid.get(node).add(new Vertex(dest, dist));
            }
        }
    }
    
    private static void dfs(int dist, int nowNode) {
        if (maxDist < dist) {
            maxDist = dist;
            farNode = nowNode;
        }
        
        visited[nowNode] = true;
        for (Vertex adj : grid.get(nowNode)) {
            if (visited[adj.destNode]) continue;
            dfs(dist + adj.dist, adj.destNode);
        }
        visited[nowNode] = false;
    }
}

class Vertex {
    int destNode;
    int dist;
    
    Vertex(int destNode, int dist) {
        this.destNode = destNode;
        this.dist = dist;
    }
}