package day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaxHeapBJ11279 {
	
	static int n;
	static Queue<Integer> mxq = new PriorityQueue<>(Collections.reverseOrder());
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (mxq.isEmpty())
					System.out.println(0);
				else
					System.out.println(mxq.poll());
			} else {
				mxq.add(x);
			}
		}
	}

}
