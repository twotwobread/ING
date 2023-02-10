package day07;

import java.io.*;
import java.util.*;

class Dist implements Comparable<Dist>{
	int dest;
	int dist;
	
	public Dist(int dest, int dist) {
		this.dest = dest;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Dist d) {
		return Integer.compare(dist, d.dist);
	}
}

public class ShortestDistanceBJ1753 {
	
	static int v, e, start;
	static List<Dist>[] edges;
	static int[] shortest;
	static PriorityQueue<Dist> prior = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] tmp = br.readLine().split(" ");
		v = Integer.parseInt(tmp[0]);
		e = Integer.parseInt(tmp[1]);
		
		start = Integer.parseInt(br.readLine());
		initialize();
		for (int i = 0; i < e; i++) {
			tmp = br.readLine().split(" ");
			int u = Integer.parseInt(tmp[0]);
			int v_v = Integer.parseInt(tmp[1]);
			int w = Integer.parseInt(tmp[2]);
			
			edges[u].add(new Dist(v_v, w));
		}
		
		dijkstra();
		for (int i=1; i<=v; i++) {
			if (shortest[i] == Integer.MAX_VALUE) {
				sb.append("INF\n");
				continue;
			}
			sb.append(shortest[i]+"\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void dijkstra() {
		prior.add(new Dist(start, 0));
		shortest[start] = 0;
		while(!prior.isEmpty()) {
			Dist now = prior.poll();
			if (shortest[now.dest] < now.dist)
				continue;
			for (Dist d : edges[now.dest]) {
				if (shortest[d.dest] > shortest[now.dest] + d.dist) {
					shortest[d.dest] = shortest[now.dest] + d.dist;
					prior.add(new Dist(d.dest, shortest[d.dest]));
				}
			}
		}
	}
	
	public static void initialize() {
		shortest = new int[v+1];
		edges = new ArrayList[v+1];
		for (int i = 1; i <= v; i++) {
			shortest[i] = Integer.MAX_VALUE;
			edges[i] = new ArrayList<>();
		}
	}
}
