import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class PasswordMaking {
	
	static int l, c;
	static List<String> result = new ArrayList<>();
	static int[] check = new int[15];
	static List<Character> seq = new ArrayList<>();
	
	public static void back(int num, int prev, String tmp) {
		if (num >= l) {
			int cnt = 0;
			for (int i = 0; i < tmp.length(); i++) {
				if (tmp.charAt(i) == 'a' || tmp.charAt(i) == 'e' || tmp.charAt(i) == 'i' || tmp.charAt(i) == 'o' || tmp.charAt(i) == 'u')
					cnt++;
			}
			
			if (cnt >= 1 && tmp.length() - cnt >= 2)
				result.add(tmp);
			return;
		}
		
		for (int i = prev; i < c; i++) {
			back(num + 1, i + 1, tmp + seq.get(i));
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		l = Integer.parseInt(tmp[0]);
		c = Integer.parseInt(tmp[1]);
		
		seq = Arrays.stream(br.readLine().split(" "))
		.map(v -> v.charAt(0))
		.collect(Collectors.toList());
		
		Collections.sort(seq);
		
		back(0, 0, "");
		
		for (int i = 0; i < result.size(); i++)
			System.out.println(result.get(i));
	}
}
