package day03;

import java.io.*;
import java.util.*;

public class SumOfSectionBJ2042 {

	static int n, m, k;
	static int startLeafNode;
	static long[] indexedTree;
	
	public static void changeLeafNodeValue(int changeIndex, long changeValue) {
		int index = changeIndex + startLeafNode - 1;
		indexedTree[index] = changeValue;
		while(index / 2 > 0) {
			index /= 2;
			indexedTree[index] = (indexedTree[index*2]+indexedTree[index*2+1]);
		}
	}
	
	public static long sumOfSection(int left, int right, int goalLeft, int goalRight, int index) {
		if (right < goalLeft || left > goalRight) return 0;
		if (left >= goalLeft && right <= goalRight) return indexedTree[index];
		
		int mid = (left + right) / 2;
		return sumOfSection(left, mid, goalLeft, goalRight, index * 2) +
				sumOfSection(mid + 1, right, goalLeft, goalRight, index * 2 + 1);
	}
	
	public static int log2(int x) {
	    return (int) (Math.log((double) x) / Math.log(2.0));
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		k = Integer.parseInt(tmp[2]);
		
		long[] temp = new long[n];
		for (int i = 0; i < n; i++) {
			temp[i] = Long.parseLong(br.readLine());
		}
		int cmp = log2(n);
		int t = (Math.pow(2, cmp) == n) ? cmp : cmp + 1;
		startLeafNode = (int) Math.pow(2, t);
        
		indexedTree = new long[startLeafNode * 2];
		for (int i = startLeafNode; i < 2*startLeafNode; i++){
			long input = 0;
			if (i < startLeafNode + n) {
				input = temp[i - startLeafNode];
			}
			indexedTree[i] = input;
		}
		
		for (int i = startLeafNode - 1; i > 0; i--) {
			indexedTree[i] = (indexedTree[i * 2] + indexedTree[i * 2 + 1]);
		}
		
		for (int i = 0; i < m + k; i++) {
			tmp = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			long c = Long.parseLong(tmp[2]);
			
			if (a == 1) { // ���� ��� �� ����
				changeLeafNodeValue(b, c);
			} else { // �� ���
				System.out.println(sumOfSection(1, startLeafNode, b, (int)c, 1));
			}
		}
	}
	
}
