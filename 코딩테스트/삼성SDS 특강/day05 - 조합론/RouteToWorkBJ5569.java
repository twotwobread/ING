package day05;

// 내 x 좌표
// 내 y 좌표
// 교차로를 돌았는지 여부
// 현재 위치까지 올 수 있는 경로의 수
// => dp[r][x][y] = 어느 방향을 보면서(r) x, y좌표에 도착할 수 있는 경로의 수
// => dp[0][x][y] += (dp[0][x - 1][y] + dp[0][x][y - 1]);
// => dp[1][x][y] += dp[0][x-1][y-1];

import java.io.*;

public class RouteToWorkBJ5569 {
	
	static final int START_X = 1, START_Y = 1;
	
	static int w, h;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		w = Integer.parseInt(tmp[0]);
		h = Integer.parseInt(tmp[1]);
		
		initialize();
		move();
		System.out.println(dp[1][w][h] + dp[0][w][h]);
		
		System.out.println("회전 안한 상태 : ");
		for (int x = 1; x <= w; x++) {
			for (int y = 1; y <= h; y++) {
				System.out.print(dp[0][x][y] + " ");
			}
			System.out.println();
		}
		
		System.out.println("회전 한 상태 : ");
		for (int x = 1; x <= w; x++) {
			for (int y = 1; y <= h; y++) {
				System.out.print(dp[1][x][y] + " ");
			}
			System.out.println();
		}
	}
	
	public static void move() {
		for (int x = START_X + 1; x <= w; x++) {
			for (int y = START_Y + 1; y <= h; y++) {
				dp[0][x][y] += (dp[1][x-1][y] + dp[1][x][y-1]);
				dp[1][x][y] += (dp[0][x-1][y] + dp[0][x][y-1]);
			}
		}
	}
	
	public static void initialize() {
		dp = new int[2][w + 1][h + 1];
		for (int i = 1; i <= w; i++) {
			dp[0][i][START_Y] = 1;
		}
		for (int j = 1; j <= h; j++) {
			dp[0][START_X][j] = 1;
		}
	}
}
