import java.io.*;
import java.util.*;

public class Main {
    private static int n, m;
    private static boolean[] knowFactPeople;
    private static List<List<Integer>> parties = new ArrayList<>();
    private static List<List<Integer>> participateParties = new ArrayList<>();
    
    public static void main(String args[]) throws IOException {
        initialize();
        checkKnowFactPeople();
        System.out.println(doParties());
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        n = Integer.parseInt(tmp[0]);
        m = Integer.parseInt(tmp[1]);
        
        for (int i = 0; i < n+1; i++) {
            participateParties.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            parties.add(new ArrayList<>());
        }
        knowFactPeople = new boolean[n + 1];
        
        String str = br.readLine();
        if (str.charAt(0) != '0') {
            int num = Integer.parseInt(String.valueOf(str.charAt(0)));
            if (num != 0) {
                tmp = str.split(" ");
                for (int i = 1; i <= num; i++) {
                    int person = Integer.parseInt(tmp[i]);
                    knowFactPeople[person] = true;
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            tmp = br.readLine().split(" ");
            for (int j = 1; j < tmp.length; j++) {
                int num = Integer.parseInt(tmp[j]);
                participateParties.get(num).add(i);
                parties.get(i).add(num);
            }
        }
    }
    
    private static void checkKnowFactPeople() {
        for (int i = 1; i <= n; i++) {
            if (knowFactPeople[i]) checkFactPeople(i);
        }
    }
    
    private static void checkFactPeople(int p) {
        knowFactPeople[p] = true;
        for (Integer party : participateParties.get(p)) {
            for (Integer person : parties.get(party)) {
                if (knowFactPeople[person]) continue;
                checkFactPeople(person);
            }
        }
    }
    
    private static int doParties() {
        int result = 0;
        for (int i = 0; i < m; i++) {
            if (canLie(i)) {
                result += 1;
            }
        }
        
        return result;
    }
    
    private static boolean canLie(int party) {
        for (Integer person : parties.get(party)) {
            if (knowFactPeople[person]) return false;
        }
        return true;
    }
}