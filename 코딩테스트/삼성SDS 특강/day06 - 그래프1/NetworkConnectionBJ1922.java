package day06;

import java.io.*;
import java.util.*;

class Connection implements Comparable<Connection>{
	int start;
	int end;
	int value;
	
	public Connection(int start, int end, int value) {
		this.start = start;
		this.end = end;
		this.value = value;
	}
	
	@Override
	public int compareTo(Connection con) {
		return value - con.value;
	}
}

public class NetworkConnectionBJ1922 {

	static int n, m, cost;
	static Queue<Connection> prior;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		initialize();
		for (int i = 0; i < m; i++) {
			String[] tmp = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			int c = Integer.parseInt(tmp[2]);
			prior.add(new Connection(a, b, c));
		}
		
		connect();
		System.out.println(cost);
	}
	
	public static int find(int a) {
		if (parent[a] != a)
			parent[a] = find(parent[a]);
		return parent[a];
	}
	
	public static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		parent[parentA] = parentB;
	}
	
	public static void initialize() {
		parent = new int[n + 1];
		prior = new PriorityQueue<>();
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
	}
	
	public static void connect() {
		while(!prior.isEmpty() ) {
			Connection con = prior.poll();
			
			if (find(con.start) == find(con.end)) continue;
			
			cost += con.value;
			union(con.start, con.end);
		}
	}
}
