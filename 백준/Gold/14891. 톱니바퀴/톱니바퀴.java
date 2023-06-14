// 톱니바퀴 상태와 회전 정보가 주어질 때
// 최종 톱니바퀴 상태에 따른 점수 구하기.
// A라는 톱니가 시계 방향 회전 시 맞닿아 있는 B와 극이 다르면 반대 방향 회전, 같으면 가만히
// N극 : 0, S극 : 1, K (1<=K<=100, 회전횟수)
// 시계방향 : 1, 반시계 : -1
// N극이면 0점, S극이면 1, 2, 4, 8점
// 인덱스 2번, 6번이 맞닿음.

// 모든 회전을 돌리면서 회전 정보를 큐에 삽입하고 (큐에 들어간 회전은 무조건 돈다)
// 그리고 조건문 이용해서 극이 다르고 번호가 0 ~ 3번 내에 있으면 큐에 삽입해서
// 큐가 빌때까지 돌리기.
// => 이렇게 처리하면 순차적으로 돌리는 느낌 -> 그래서 회전 후의 상태로 비교하게 됨.
// => 그래서 먼저 상태를 다 확인하고 돌릴 수 있는 애들을 확인하고 방향도 정해놓고 순차적으로 돌려줘야함.
import java.io.*;
import java.util.*;

public class Main {
    private static final int GEAR_CNT = 4;
    private static final int SAW_TOOTH_CNT = 8;
    
    private static int k;
    private static Gear[] gears = new Gear[GEAR_CNT];
    private static List<Rotation> rotations = new ArrayList<>();
    
    public static void main(String args[]) throws IOException {
        initialize();
        doRotatations();
        System.out.println(getScore());
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < GEAR_CNT; i++) {
            String[] tmp = br.readLine().split("");
            List<Integer> arr = new ArrayList<>();
            for (int j = 0; j < SAW_TOOTH_CNT; j++) {
                int num = Integer.parseInt(tmp[j]);
                arr.add(num);
            }
            gears[i] = new Gear(arr);
        }
        
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            String[] tmp = br.readLine().split(" ");
            int gearNum = Integer.parseInt(tmp[0]) - 1;
            int rotationDirection = Integer.parseInt(tmp[1]);
            
            Rotation rotation;
            if (rotationDirection == 1) {
                rotation = new ClockwiseRotation(gearNum);
            } else {
                rotation = new CounterClockwiseRotation(gearNum);
            }
            rotations.add(rotation);
        }
    }
    
    private static void doRotatations() {
        for (int i = 0; i < k; i++) {
            rotate(rotations.get(i));
        }
    }
    
    private static void rotate(Rotation rotation) {
        Rotation[] arrRotation = new Rotation[GEAR_CNT];
        for (int i = 0; i < GEAR_CNT; i++) {
            arrRotation[i] = new NoneRotation(i);
        }
        arrRotation[rotation.getTargetGearNum()] = rotation;
        
        // left
        for (int i = rotation.getTargetGearNum() - 1; i >= 0; i--) {
            if (gears[i].getRightContactTooth() == gears[i + 1].getLeftContactTooth()) {
                break;
            }
            arrRotation[i] = makeReverseRotation(i, arrRotation[i + 1]);
        }
        // right
        for (int i = rotation.getTargetGearNum() + 1; i < GEAR_CNT; i++) {
            if (gears[i].getLeftContactTooth() == gears[i - 1].getRightContactTooth()) {
                break;
            }
            arrRotation[i] = makeReverseRotation(i, arrRotation[i - 1]);
        }
        
        for (int i = 0; i < GEAR_CNT; i++) {
            arrRotation[i].rotate(gears[i]);
        }
    }
    
    private static Rotation makeReverseRotation(int gearNum, Rotation rotation) {
        if (rotation.getDirection() == -1) {
            return new ClockwiseRotation(gearNum);
        } else {
            return new CounterClockwiseRotation(gearNum);
        }
    }
    
    private static int getScore() {
        int result = 0;
        for (int i = 0; i < GEAR_CNT; i++) {
            int score = gears[i].getScore();
            result += calculateScore(score, i);
        }
        return result;
    }
    
    private static int calculateScore(int score, int gearNum) {
        int result = score;
        for (int i = 0; i < gearNum; i++) {
            result *= 2;
        }
        return result;
    }
}

class Gear {
    public static final int TWELVE_INDEX = 0;
    public static final int LEFT_CONTACT_INDEX = 6;
    public static final int RIGHT_CONTACT_INDEX = 2;
    
    private List<Integer> gear;
    
    public Gear(List<Integer> gear) {
        this.gear = gear;
    }
    
    public List<Integer> getGear() {
        return gear;
    }
    
    public int getLeftContactTooth() {
        return gear.get(LEFT_CONTACT_INDEX);
    }
    
    public int getRightContactTooth() {
        return gear.get(RIGHT_CONTACT_INDEX);
    }
    
    public int getScore() {
        return gear.get(TWELVE_INDEX);
    }
}

abstract class Rotation {
    private int targetGearNum;
    private int direction;
    
    public Rotation(int targetGearNum, int direction) {
        this.targetGearNum = targetGearNum;
        this.direction = direction;
    }
    
    public int getTargetGearNum() {
        return targetGearNum;
    }
    
    public int getDirection() {
        return direction;
    }
    
    abstract void rotate(Gear gear);
}

class NoneRotation extends Rotation {
    public NoneRotation(int targetGearNum) {
        super(targetGearNum, 0);
    }
    
    @Override
    public void rotate(Gear gear) {}   
} 

class ClockwiseRotation extends Rotation {
    public ClockwiseRotation(int targetGearNum) {
        super(targetGearNum, 1);
    }
    
    @Override
    public void rotate(Gear gear) {
        List<Integer> gearList = gear.getGear();
        int tmp = gearList.remove(gearList.size() - 1);
        gearList.add(0, tmp);
    }
}

class CounterClockwiseRotation extends Rotation {
    public CounterClockwiseRotation(int targetGearNum) {
        super(targetGearNum, -1);
    }
    
    @Override
    public void rotate(Gear gear) {
        List<Integer> gearList = gear.getGear();
        int tmp = gearList.remove(0);
        gearList.add(tmp);
    }   
} 
