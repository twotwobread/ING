import java.io.*;
import java.util.*;


public class Game {
	
	static final int MAX_SIZE = 50;
	
	static int n, m, maxCnt = 0;
	static char[][] arr = new char[MAX_SIZE][MAX_SIZE];
	
	static int[][] visited = new int[MAX_SIZE][MAX_SIZE];
	static int[][] arrCnt = new int[MAX_SIZE][MAX_SIZE];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static boolean isCycle = false;
	
	public static boolean canGo(int x, int y) {
		if (x < 0 || x >= n || y < 0 || y >= m)
			return false;
		if (arr[x][y] == 'H')
			return false;
		return true;
	}
	
	public static void back(int x, int y, int cnt) {
		maxCnt = Math.max(maxCnt, cnt);
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i] * (arr[x][y] - '0');
			int ny = y + dy[i] * (arr[x][y] - '0');
			
			if (canGo(nx, ny)) {
				if (visited[nx][ny] == 1) {
					isCycle = true;
					return;
				}
				visited[nx][ny] = 1;
				back(nx, ny, cnt + 1);
				visited[nx][ny] = 0;
				
				if (isCycle) return;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		visited[0][0] = 1;
		back(0, 0, 0);
		
		if (isCycle)
			System.out.println(-1);
		else
			System.out.println(maxCnt + 1);
	}
	
	public static void printArr() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
