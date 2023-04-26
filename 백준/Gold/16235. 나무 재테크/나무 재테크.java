// 처음 각 grid 값 = 5
// 나무 m개 심기 -> 하나의 칸에 여러 개 나무 심기 가능.
// 1. 봄 -> 나무 나이만큼 양분먹기(자기가 있는 칸) 후 나이 1증가
//      a. 하나의 칸에 여러 나무 존재 시 가장 어린 나무부터 양분먹기.
//      b. 땅 양분이 자신의 나이만큼 존재 X -> 해당 나무 죽음.
// 2. 여름 -> 죽은 나무가 해당 칸의의 양분으로 변험 -> 나이 / 2 (소수점 버림)
// 3. 가을 -> 나이가 5의 배수인 나무 번식 (인점한 8개 칸에 나이 1인 나무 생성).
// 4. 겨울 -> 기계가 움직이며 땅에 양분 추가. (A[r][c]만큼 -> 입력 주어짐.)
// 이때, K년 지난 후 상도의 살아있는 나무의 개수 구하기.
import java.io.*;
import java.util.*;


public class Main {
    private static final int MAX_N = 10, DIR = 8;
    
    private static final int[] dx = {1, 0, -1, 0, 1, 1, -1, -1};
    private static final int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};
    
    private static int n, m, k;
    private static int result = 0;
    private static int[][] A = new int[MAX_N][MAX_N];
    private static int[][] energy = new int[MAX_N][MAX_N];
    private static List<List<List<Tree>>> tree = new ArrayList<>();
    
    public static void main(String args[]) throws IOException {
        initialize();
        simulate();
        result = countAliveTree();
        System.out.println(result);
    }
    
    private static void simulate() {
        for(int i = 0; i < k; i++) {
            spring();
            summer();
            fall();
            winter();
        }
    }
    
    private static int countAliveTree() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cnt += tree.get(i).get(j).size(); 
            }
        }
        return cnt;
    }
    
    private static void spring() {
        // 1. 봄 -> 나무 나이만큼 양분먹기(자기가 있는 칸) 후 나이 1증가
        //      a. 하나의 칸에 여러 나무 존재 시 가장 어린 나무부터 양분먹기.
        //      b. 땅 양분이 자신의 나이만큼 존재 X -> 해당 나무 죽음.
        sortTree();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int e = energy[i][j];
                for (int k = 0; k < tree.get(i).get(j).size(); k++) {
                    Tree now = tree.get(i).get(j).get(k);
                    if (e >= now.age) {
                        e -= now.age;
                        now.grow();
                    } else {
                        now.kill();
                    }
                }
                energy[i][j] = e;
            }
        }
    }
    
    private static void summer() {
        // 2. 여름 -> 죽은 나무가 해당 칸의 양분으로 변함 -> 나이 / 2 (소수점 버림)
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                List<Tree> tmp = new ArrayList<>();
                for (int k = 0; k < tree.get(i).get(j).size(); k++) {
                    Tree now = tree.get(i).get(j).get(k);
                    if (now.isDead) {
                        energy[i][j] += (now.age / 2);
                    } else {
                        tmp.add(now);
                    }
                }
                tree.get(i).get(j).clear();
                
                for (int k = 0; k < tmp.size(); k++) {
                    tree.get(i).get(j).add(new Tree(tmp.get(k).age));
                }
            }
        }
    }
    
    private static void fall() {
        // 3. 가을 -> 나이가 5의 배수인 나무 번식 (인점한 8개 칸에 나이 1인 나무 생성).
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < tree.get(i).get(j).size(); k++) {
                    Tree now = tree.get(i).get(j).get(k);
                    if (now.canBreeding()) {
                        breedTree(i, j);
                    }
                }
            }
        }
    }
    private static void breedTree(int x, int y) {
        for (int i = 0; i < DIR; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (inRange(nx, ny)) {
                tree.get(nx).get(ny).add(new Tree(1));
            }
        }
    }
    private static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    private static void winter() {
        // 4. 겨울 -> 기계가 움직이며 땅에 양분 추가. (A[r][c]만큼 -> 입력 주어짐.)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                energy[i][j] += A[i][j];
            }
        }
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        k = Integer.parseInt(tmp[2]);
        
        for (int i = 0; i < n; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                A[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                tree.get(i).add(new ArrayList<>(new ArrayList<>()));
                energy[i][j] = 5;
            }
        }
        
        for (int i = 0; i < m; i++) {
            tmp = br.readLine().split(" ");
            
            int x = Integer.parseInt(tmp[0]) - 1;
            int y = Integer.parseInt(tmp[1]) - 1;
            int z = Integer.parseInt(tmp[2]);
            
            tree.get(x).get(y).add(new Tree(z));
        }
    }
    
    private static void sortTree() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Collections.sort(tree.get(i).get(j));
            }
        }
    }
}

class Tree implements Comparable<Tree> {
    int age;
    boolean isDead;
    
    Tree (int age) {
        this.age = age;
        this.isDead = false;
    }
    
    @Override
    public int compareTo(Tree t) {
        return age - t.age;
    }
    
    public void kill() {
        isDead = true;
    }
    
    public void grow() {
        age += 1;
    }
    
    public boolean canBreeding() {
        return age % 5 == 0;
    }
}