package day03;

import java.io.*;
import java.util.*;

public class FireflyBJ3020 {

	static int n, h, startIndex;
	static int[] indexedTree;
	
	public static void checkSumOfSection(int l, int r, int gL, int gR, int index) {
		if(l > gR || r < gL) return;
		if(l >= gL && r <= gR) {
			indexedTree[index] += 1;
			return;
		}
		
		int mid = (l + r) / 2;
		checkSumOfSection(l, mid, gL, gR, index * 2);
		checkSumOfSection(mid + 1, r, gL, gR, index * 2 + 1);
	}
	
	public static int getSumOfSection(int index) {
		int sum = 0;
		while (index / 2 > 0) {
			sum += indexedTree[index];
			index /= 2;
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		h = Integer.parseInt(tmp[1]);
		startIndex = 1;
		while(startIndex < h) {
			startIndex *= 2;
		}
		
		indexedTree = new int[startIndex * 2];
		for (int i = 0; i < n/2; i++) {
			// 석순 (밑에서 위로)
			int range = Integer.parseInt(br.readLine());
			checkSumOfSection(1, startIndex, 1, range, 1);
			// 종유석 (위에서 밑으로)
			range = Integer.parseInt(br.readLine());
			checkSumOfSection(1, startIndex, h - range + 1, h, 1);
		}
		
		int minValue = Integer.MAX_VALUE, cnt = 0;
		for (int i = startIndex; i < startIndex + h; i++) {
			int result = getSumOfSection(i);
			if (minValue > result) {
				cnt = 1;
				minValue = result;
			} else if (minValue == result) {
				cnt++;
			}
		}
		
		System.out.println(minValue + " " + cnt);
	}
}
