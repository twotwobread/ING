package day03;

import java.io.*;

public class TreeTraversal {
	
	static final int ALPHABET_SIZE = 26;
	
	static int n;
	static char[][] tree = new char[ALPHABET_SIZE + 1][3];
	
	public static void preOrder(char now) {
		if (now == '.') return;
		System.out.print(now);
		preOrder(tree[now - 'A'][1]);
		preOrder(tree[now - 'A'][2]);
	}
	
	public static void inOrder(char now) {
		if (now == '.') return;
		inOrder(tree[now - 'A'][1]);
		System.out.print(now);
		inOrder(tree[now - 'A'][2]);
	}
	
	public static void postOrder(char now) {
		if (now == '.') return;
		postOrder(tree[now - 'A'][1]);
		postOrder(tree[now - 'A'][2]);
		System.out.print(now);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++) {
			String[] tmp = br.readLine().split(" ");
			int row = tmp[0].charAt(0) - 'A';
			tree[row][0] = tmp[0].charAt(0);
			tree[row][1] = tmp[1].charAt(0);
			tree[row][2] = tmp[2].charAt(0);
		}
		
		preOrder('A');
		System.out.println();
		inOrder('A');
		System.out.println();
		postOrder('A');
	}

}
