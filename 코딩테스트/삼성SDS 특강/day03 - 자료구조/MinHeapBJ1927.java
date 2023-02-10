package day03;

import java.io.*;
import java.util.*;

public class MinHeapBJ1927 {
	
	static int n;
	static Queue<Integer> mnq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			int cmd = Integer.parseInt(br.readLine());
			if (cmd == 0) {
				if (mnq.isEmpty())
					System.out.println(0);
				else
					System.out.println(mnq.poll());
			} else {
				mnq.add(cmd);
			}
		}
	}

}
