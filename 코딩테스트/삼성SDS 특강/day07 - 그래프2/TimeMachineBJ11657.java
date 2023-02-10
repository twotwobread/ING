package day07;

import java.io.*;
import java.util.*;

class Move {
	int from;
	int to;
	int timeTaken;
	
	public Move(int from, int to, int timeTaken) {
		this.from = from;
		this.to = to;
		this.timeTaken = timeTaken;
	}
}

public class TimeMachineBJ11657 {
	
	static final long INF = Long.MAX_VALUE;
	
	static int n, m;
	static List<Move> edges;
	static long[] elapsedTime;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		initialize();
		for (int i = 0; i < m; i++) {
			tmp = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			int c = Integer.parseInt(tmp[2]);
			edges.add(new Move(a, b, c));
		}
		
		findShortestPath(1);
		if (isCycling()) {
			System.out.println(-1);
		} else {
			printCloserCities();
		}
	}
	
	private static void initialize() {
		elapsedTime = new long[n+1];
		edges = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			elapsedTime[i] = INF;
		}
	}
	
	private static void findShortestPath(int start) {
		elapsedTime[start] = 0;
		for (int i = 1; i <= n-1; i++) {
			for (Move move : edges) {
				if (elapsedTime[move.from] == INF) continue;
				elapsedTime[move.to] = Math.min(elapsedTime[move.to], elapsedTime[move.from] + move.timeTaken);
			}
		}
	}
	
	private static boolean isCycling() {
		for (Move move : edges) {
			if (elapsedTime[move.from] == INF) continue;
			if (elapsedTime[move.to] > elapsedTime[move.from] + move.timeTaken)
				return true;
		}
		return false;
	}
	
	private static void printCloserCities() {
		for (int i = 2; i <= n; i++) {
			if (elapsedTime[i] == INF)
				System.out.println(-1);
			else
				System.out.println(elapsedTime[i]);
		}
	}
}
