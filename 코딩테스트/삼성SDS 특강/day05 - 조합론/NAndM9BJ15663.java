package day05;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NAndM9BJ15663 {
	
	static int n, m;
	
	static int[] seq;
	static boolean[] duplicateCheck;
	
	static List<Integer> list;
	static Map<String, Boolean> map;
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		inputValue();
		initializeVariable();
		backtracking();
		printResultSet();
	}
	
	public static void inputValue() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		seq = new int[n];
		duplicateCheck = new boolean[n];
		tmp = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			seq[i] = Integer.parseInt(tmp[i]);
		}
	}
	
	public static void initializeVariable() {
		Arrays.sort(seq);
		list = new ArrayList<>();
		map = new HashMap<>();
	}
	
	public static void backtracking() {
		if (isListSizeEquals(m)) {
			addSequenceResultSet();
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (isDuplicate(i))
				continue;
			nextBackTracking(i);
		}
	}
	
	public static void printResultSet() {
		System.out.println(result.toString());
	}
	
	private static boolean isListSizeEquals(int value) {
		if (list.size() == value)
			return true;
		return false;
	}
	
	private static void addSequenceResultSet() {
		String convertSeqListToStr = list.stream()
				.map(String::valueOf)
				.collect(Collectors.joining(" "));
		
		if (isPresentSequence(convertSeqListToStr))
			return;
		
		map.put(convertSeqListToStr, true);
		result.append(convertSeqListToStr + "\n");
	}
	
	private static void nextBackTracking(int index) {
		duplicateCheck[index] = true;
		list.add(seq[index]);
		backtracking();
		list.remove(list.size() - 1);
		duplicateCheck[index] = false;
	}
	
	private static boolean isPresentSequence(String sequenceString) {
		return map.containsKey(sequenceString);
	}
	
	private static boolean isDuplicate(int index) {
		return duplicateCheck[index];
	}
}
