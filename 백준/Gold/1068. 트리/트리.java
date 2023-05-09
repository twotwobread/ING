import java.io.*;
import java.util.*;

public class Main {
    private static int n, goal;
    private static boolean[] disabled;
    private static List<List<Integer>> tree = new ArrayList<>();
    
    public static void main(String args[]) throws IOException {
        initialize();
        deleteNodeWithDescendantsNode(goal);
        System.out.println(countLeafNode());
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        disabled = new boolean[n];
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        
        String[] tmp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(tmp[i]);
            if (num == -1) continue;
            
            tree.get(num).add(i);
        }
        goal = Integer.parseInt(br.readLine());
    }
    
    private static void deleteNodeWithDescendantsNode(int deleteNode) {
        disabled[deleteNode] = true;
        for (Integer descendant : tree.get(deleteNode)) {
            deleteNodeWithDescendantsNode(descendant);
        }
    }
    
    private static int countLeafNode() {
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (!disabled[i] && isLeafNode(i)) result += 1;
        }
        
        return result;
    }
    
    private static boolean isLeafNode(int node) {
        return tree.get(node).size() == 0 || isAllDisabled(node);
    }
    
    private static boolean isAllDisabled(int node) {
        for (Integer descendant : tree.get(node)) {
            if (!disabled[descendant]) return false;
        }
        return true;
    }
}