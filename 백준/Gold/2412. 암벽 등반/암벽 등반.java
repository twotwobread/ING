import java.io.*;
import java.util.*;

public class Main {
    private static final int FAIL = -1;
    private static final Set<Point> points = new HashSet<>();
    private static final Deque<Point> q = new LinkedList<>();
    private static int n, T;
    
    public static void main(String args[]) throws IOException {
      initialize();
      System.out.println(simulate());
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        T = Integer.parseInt(tmp[1]);
        
        for (int i = 0; i < n; i++) {
            tmp = br.readLine().split(" ");
            int x = Integer.parseInt(tmp[0]);
            int y = Integer.parseInt(tmp[1]);
            points.add(new Point(x, y, -1));
        }
    }
    
    private static int simulate() {
        q.add(new Point(0, 0, 0));
        while(!q.isEmpty()) {
            Point now = q.poll();
            if (now.y == T) return now.value;
            
            now.findPossibleMovePoints(T);
        }
        return FAIL;
    }
    
    public static boolean containsKey(Point p) {
        return points.contains(p);
    }
    
    public static void delete(Point p) {
        points.remove(p);
    }
    
    public static void addQueue(Point p, int value) {
        q.add(new Point(p.x, p.y, value + 1));
    }
}

class Point {
    int x;
    int y;
    int value;
    
    Point(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    public void findPossibleMovePoints(int t) {
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                int nx = x + i;
                int ny = y + j;
                if (canMove(nx, ny, t)) {
                    Main.addQueue(new Point(nx, ny, -1), value);
                    Main.delete(new Point(nx, ny, -1));
                }
            }
        }
    }
    
    private boolean canMove(int x, int y, int t) {
        return x >= 0 && y >= 0 && y <= t && Main.containsKey(new Point(x, y, -1));
    }
}

