import java.io.*;
import java.util.*;

public class NQueenBJ9663{
	static final int MAX_NUM = 15;
	
	static int n, result;
	
	static int[] xArr = new int[MAX_NUM];
	static int[] yArr = new int[MAX_NUM];
	static int[] rDiagonal = new int[MAX_NUM * 2];
	static int[] lDiagonal = new int[MAX_NUM * 2];
	
	static boolean canSelect(int x, int y) {
		if (xArr[x] == 1)
			return false;
		if (yArr[y] == 1)
			return false;
		if (rDiagonal[x - y + n - 1] == 1)
			return false;
		if (lDiagonal[x + y] == 1)
			return false;
		return true;
	}
	
	static void n_queen(int num, int temp) {
		if (num >= n) {
			if (temp == n)
				result += 1;
			return;
		}
		
		for (int j = 0; j < n; j++) {
			if (canSelect(num, j)) {
				xArr[num] = 1;
				yArr[j] = 1;
				rDiagonal[num - j + n - 1] = 1;
				lDiagonal[num + j] = 1;
				
				n_queen(num + 1, temp + 1);
				
				xArr[num] = 0;
				yArr[j] = 0;
				rDiagonal[num - j + n - 1] = 0;
				lDiagonal[num + j] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		n_queen(0, 0);
		
		System.out.println(result);
	}
}