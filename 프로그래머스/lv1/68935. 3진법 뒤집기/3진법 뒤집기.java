import java.io.*;

class Solution {
    public int solution(int n) {
        String reverseTNum = changeReverseThirdString(n);
        String num = changeNumber(reverseTNum);
        return changeTenNumber(num);
    }

    private String changeReverseThirdString(int n) {
        StringBuilder sb = new StringBuilder();
        while(n != 0) {
            int tmp = n % 3;
            sb.append(Integer.toString(tmp));
            n /= 3;
        }

        return sb.toString();
    }

    private String changeNumber(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) != '0') {
                return num.substring(i); 
            }
        }
        return "0";
    }

    private int changeTenNumber(String num) {
        int result = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int tmp = num.charAt(i) - 48;
            result += tmp * setDigit(num.length() - (i+1), 3);
        }
        return result;
    }

    private int setDigit(int num, int base) {
        int result = 1;
        for (int i = 0; i < num; i++) {
            result *= base;
        }
        return result;
    }
}