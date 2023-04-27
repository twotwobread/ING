
import java.io.*;

public class Main {
	
	static int n, m;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
	
		initialize();
		
		for (int i = 0; i < m; i++) {
			tmp = br.readLine().split(" ");
			int cmd = Integer.parseInt(tmp[0]);
			int a = Integer.parseInt(tmp[1]);
			int b = Integer.parseInt(tmp[2]);
			
			if (cmd == 0) {
				union(a, b);
			} else {
				if (find(a) != find(b))
					System.out.println("NO");
				else
					System.out.println("YES");
			}
		}
	}
	
	public static void initialize() {
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
	}
	
	public static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if (parentA < parentB) {
			parent[parentB] = parentA;
		} else {
			parent[parentA] = parentB;
		}
	}
	
	public static int find(int a) {
		if (parent[a] != a)
			parent[a] = find(parent[a]);
		return parent[a];
	}
}
