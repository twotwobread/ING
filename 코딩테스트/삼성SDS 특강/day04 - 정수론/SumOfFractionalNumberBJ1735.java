package day04;

import java.io.*;

public class SumOfFractionalNumberBJ1735 {
	
	static int a, b, c, d;
	
	public static int gcd(int a, int b) {
		if (a % b == 0) return b;
		return gcd(b, a % b);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		int a = Integer.parseInt(tmp[0]);
		int b = Integer.parseInt(tmp[1]);
		
		tmp = br.readLine().split(" ");
		int c = Integer.parseInt(tmp[0]);
		int d = Integer.parseInt(tmp[1]);
		
		
		int child = a*d + b*c;
		int parent = b * d;
		int x = gcd(child, parent);
		System.out.println(child / x + " " + parent / x);
	}

}
