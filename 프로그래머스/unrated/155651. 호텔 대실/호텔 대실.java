// 예약 시작 시간, 끝나는 시간이 주어지는데
// 이 때, 필요한 최소 객실 수를 반환해라. -> 사용 객실은 퇴실 시간 기준으로 10분간 청소하고 다음 손님이 사용할 수 있음.
import java.util.*;

class Solution {
    public static final int ALL_TIME = 2400;
    
    private List<Reservation> bookTimeList = new ArrayList<>();
    private int[] allTime = new int[ALL_TIME];
    
    public int solution(String[][] book_time) {
        convertBookTimeToReservation(book_time);
        reserve();  
        return getRoomCount();
    }
    
    private void convertBookTimeToReservation(String[][] book_time) {
        for (String[] reservation : book_time) {
            String[] startTimeStr = reservation[0].split(":");
            String[] endTimeStr = reservation[1].split(":");
            Time startTime = new Time(Integer.parseInt(startTimeStr[0]), Integer.parseInt(startTimeStr[1]));
            Time endTime = new Time(Integer.parseInt(endTimeStr[0]), Integer.parseInt(endTimeStr[1]));
            
            bookTimeList.add(new Reservation(startTime, endTime));
        }
    }
    
    private void reserve() {
        for (Reservation reservation : bookTimeList) {
            reservation.reserve(allTime);
        }
    }
    
    private int getRoomCount() {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < ALL_TIME; i++) {
            result = Math.max(result, allTime[i]);
        }
        return result;
    }
}

class Time {
    private static final int HOUR = 24;
    private static final int MIN = 60;
    int hour;
    int min;
    
    public Time(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }
    
    public void plusMin(int min) {
        this.min += min;
        if (this.min / MIN >= 1) {
            hour += (this.min / MIN);
            this.min %= MIN;
        }
    }
    
    public int getIntegerTime() {
        return hour * 100 + min;
    }
}

class Reservation { 
    private static final int CLEAN_UP_TIME = 10;
    Time startTime;
    Time endTime;
    
    public Reservation(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public void reserve(int[] allTime) {
        Time endPlusCleanUpTime = plusCleanUpTime(endTime);
        int startIntegerTime = startTime.getIntegerTime();
        int endPlusCleanUpIntegerTime = endPlusCleanUpTime.getIntegerTime();
        for (int i = startIntegerTime; i < endPlusCleanUpIntegerTime; i++) {
            if (i >= Solution.ALL_TIME) break;
            allTime[i] += 1;
        }
    }
    
    private Time plusCleanUpTime(Time time) {
        Time newTime = new Time(time.hour, time.min);
        newTime.plusMin(CLEAN_UP_TIME);
        return newTime;
    }
}