import java.io.*;

class Solution {

    private StringBuilder sb = new StringBuilder();

    public int solution(int n, int k) {
        String chgN = changeDigit(n, k);
        return countPrimeNumber(chgN);
    }

    private int countPrimeNumber(String num) {
        int cnt = 0;
        sb.setLength(0);
        for (int i = 0; i <= num.length(); i++) {
            if (i == num.length() || num.charAt(i) == '0') {
                while(sb.length() > 0) {
                    if (isRequired(sb.toString(), num, i)) {
                        cnt += 1;
                    }
                    sb.deleteCharAt(0);
                }
            } else {
                sb.append(num.charAt(i));
            }
        }
        return cnt;
    }

    private boolean isRequired(String now, String total, int index) {
        if (!checkPrime(Long.parseLong(now))) return false;

        if (index - now.length() == 0 && index == total.length()) return true; // P
        if (index - now.length() == 0 && (index < total.length() && total.charAt(index) == '0')) return true; // P0
        if (index == total.length() && (index - now.length() - 1 >= 0 && total.charAt(index - now.length() - 1) == '0')) return true; // 0P
        if ((index - now.length() - 1 >= 0 && total.charAt(index - now.length() - 1) == '0') && (index < total.length() && total.charAt(index) == '0')) return true; // 0P0

        return false;
    }

    private boolean checkPrime(long num) {
        if (num == 1) return false;
        for (int i = 2; i < (int)(Math.sqrt(num) + 1); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private String changeDigit(int num, int base) {
        while (num != 0) {
            String tmp = Integer.toString(num % base);
            sb.append(tmp);
            num /= base;
        }

        return sb.reverse().toString();
    }
}