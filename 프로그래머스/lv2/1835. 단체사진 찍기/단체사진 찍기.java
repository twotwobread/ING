import java.util.*;

class Solution {
    private static final int FRIENDS_CNT = 8;
    private int size, result = 0;
    private boolean[] check = new boolean[FRIENDS_CNT];
    private char[] friendList = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private Map<Character, Integer> friends = new HashMap<>();
    private List<Condition> conditions = new ArrayList<>();
    
    public int solution(int n, String[] data) {
        size = n;
        initialize(data);
        getAllSituation(0);
        return result;
    }
    
    private void getAllSituation(int cnt) {
        if (cnt == FRIENDS_CNT) {
            if (canLining()) {
                result += 1;
            }
            return;
        }
        
        for (int i = 0; i < FRIENDS_CNT; i++) {
            if (check[i]) continue;
            
            check[i] = true;
            friends.put(friendList[i], cnt);
            getAllSituation(cnt + 1);
            friends.put(friendList[i], -1);
            check[i] = false;
        }
    }
    
    private boolean canLining() {
        for (Condition c : conditions) {
            if (!c.meetCondition(friends)) return false;
        }
        return true;
    }
    
    private void initialize(String[] data) {
        for (String d : data) {
            String[] tmp = d.split("~");
            char presenter = tmp[0].charAt(0);
            char opposite = tmp[1].charAt(0);
            char cmd = tmp[1].charAt(1);
            int interval = Integer.parseInt(String.valueOf(tmp[1].charAt(2)));
            
            Condition condition;
            if (cmd == '=') {
                condition = new Condition(presenter, opposite, new Equal(), interval);
            } else if (cmd == '<') {
                condition = new Condition(presenter, opposite, new Smaller(), interval);
            } else {
                condition = new Condition(presenter, opposite, new Bigger(), interval);
            }
            conditions.add(condition);
        }
        
        for (Character c : friendList) {
            friends.put(c, -1);
        }
    }
}

class Condition {
    char presenter;
    char opposite;
    Comparator comparator;
    int interval;
    
    
    public Condition(char presenter, char opposite, Comparator comparator, int interval) {
        this.presenter = presenter;
        this.opposite = opposite;
        this.comparator = comparator;
        this.interval = interval;
    }
    
    @Override
    public String toString() {
        return String.format("%s ~ %s %s %s", presenter, opposite, comparator, interval);
    }
    
    
    public boolean meetCondition(Map<Character, Integer> friends) {
        int presenterOrder = friends.get(presenter);
        int oppositeOrder = friends.get(opposite);
        if (presenterOrder == -1 || oppositeOrder == -1) {
            return false;
        }
           
        return comparator.compare(Math.abs(presenterOrder - oppositeOrder)-1, interval);
    }
}

interface Comparator {
    boolean compare(int now, int opposite);
}

class Bigger implements Comparator {
    public Bigger() {}
    
    @Override
    public String toString() {
        return ">";
    }
    
    public boolean compare(int now, int opposite) {
        return now > opposite;
    }
}

class Equal implements Comparator {
    public Equal() {}
    
    @Override
    public String toString() {
        return "==";
    }
    
    public boolean compare(int now, int opposite) {
        return now == opposite;
    }
}

class Smaller implements Comparator {
    public Smaller() {}
    
    @Override
    public String toString() {
        return "<";
    }
    
    public boolean compare(int now, int opposite) {
        return now < opposite;
    }
}