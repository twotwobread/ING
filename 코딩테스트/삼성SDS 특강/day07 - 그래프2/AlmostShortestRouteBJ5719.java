package day08;

import java.io.*;
import java.util.*;

class Move implements Comparable<Move>{
	int node;
	int dist;
	
	public Move(int node, int dist) {
		this.node = node;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Move m) {
		return dist - m.dist;
	}
}

public class AlmostShortestRouteBJ5719 {
	
	static final int INF = 10000001;
	
	static int n, m;
	static int start, dest;
	
	static List<Move>[] edges;
	static List<Integer>[] tracking;
	static int[] dist;
	static boolean[][] isShortest;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String[] tmp = br.readLine().split(" ");
			n = Integer.parseInt(tmp[0]);
			m = Integer.parseInt(tmp[1]);
			
			if (isExit()) break;
			
			tmp = br.readLine().split(" ");
			start = Integer.parseInt(tmp[0]);
			dest = Integer.parseInt(tmp[1]);
			
			initialize();
			for (int i = 0; i < m; i++) {
				tmp = br.readLine().split(" ");
				int u = Integer.parseInt(tmp[0]);
				int v = Integer.parseInt(tmp[1]);
				int p = Integer.parseInt(tmp[2]);
				
				edges[u].add(new Move(v, p));
			}
			
			dijkstra(start);
			if (dist[dest] == INF)
				System.out.println(-1);
			else {
				findShortestEdges(dest, start);
				dijkstra(start);
				
				if (dist[dest] == INF)
					System.out.println(-1);
				else
					System.out.println(dist[dest]);
			}	
		}
	}
	
	private static boolean isExit() {
		if (n == 0 && m == 0) 
			return true;
		return false;
	}
	
	private static void initialize() {
		dist = new int[n+1];
		isShortest = new boolean[n+1][n+1];
		edges = new ArrayList[n+1];
		for (int i=0; i<=n; i++) {
			edges[i] = new ArrayList<>();
		}
	}
	
	private static void dijkstra(int start) {
		tracking = new ArrayList[n+1];
		for (int i=0; i<=n; i++) {
			tracking[i] = new ArrayList<>();
		}
		
		Arrays.fill(dist, INF);
		Queue<Move> prior = new PriorityQueue<>();
		
		dist[start] = 0;
		prior.add(new Move(start, dist[start]));
		while(!prior.isEmpty()) {
			Move now = prior.poll();
			
			if (now.dist > dist[now.node]) continue;
			for (Move next : edges[now.node]) {
				if (isShortest[now.node][next.node]) continue;
				
				if (dist[next.node] == dist[now.node] + next.dist) {
					tracking[next.node].add(now.node);
				} else if (dist[next.node] > dist[now.node] + next.dist){
					tracking[next.node].clear();
					tracking[next.node].add(now.node);
					dist[next.node] = dist[now.node] + next.dist;
					prior.add(new Move(next.node, dist[next.node]));
				}
			}
		}
	}
	
	private static void findShortestEdges(int now, int end) {
		if (now == end) return;
		
		for (int next : tracking[now]) {
			if (isShortest[next][now]) continue;
			isShortest[next][now] = true;
			findShortestEdges(next, end);
		}
	}
}
