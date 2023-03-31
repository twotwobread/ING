import java.io.*;

class Solution {
    
    private int loop = 0, zeroCnt = 0;

    public int[] solution(String s) {
        String str = s;
        while(!str.equals("1")) {
            str = deleteZero(str);
            str = convertBinary(str);
            loop += 1;
        }
        return new int[] {loop, zeroCnt};
    }

    private String convertBinary(String str) {
        StringBuilder sb = new StringBuilder();
        int goal = str.length();
        while(goal != 0) {
            int tmp = goal % 2;
            sb.append(String.valueOf(tmp));
            goal /= 2;
        }
        return sb.reverse().toString();
    }

    private String deleteZero(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                zeroCnt += 1;
                continue;
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}