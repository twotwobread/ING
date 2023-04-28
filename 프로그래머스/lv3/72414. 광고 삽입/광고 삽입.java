// 특정 구간에 하나하나 값을 채우면 무조건 터짐. -> 시간 초과
// 누적합 이용하기 -> 2번 누적합을 진행해야함 -> 최적의 위치를 찾기 위해선
class Solution {
    private static final Time EMPTY = new Time(-1, -1, -1);
    
    private long[] time;
    private Time playTime, advTime;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        initialize(play_time, adv_time, logs); // 여기서 logs에 대해서 시작 값에 1 삽입. -> 300,000
        prefixSumTwice(); // 여기서 누적합 수행. -> 2회 진행하기 최대 360,000 * 2 => 720,000회
        return findBestStartTime().toString(); // 최적의 시간 찾기. => 360,000회
        // 이거 위해서 초를 HH:MM:SS 형식으로 바꾼는 메서드.
        // HH:MM:SS 형식에서 초로 바꾸는 메서드 필요.
    }
    
    private Time findBestStartTime() {
        long maxValue = -1;
        Time bestTime = EMPTY;
        for (int i = 0; i <= playTime.getTotalSec(); i++) {
            long start = i == 0 ? 0 : time[i-1];
            Time tmpTime = Time.convertIntToTime(i);
            tmpTime.plus(advTime);
            
            if (playTime.isLater(tmpTime)) break;
            long end = time[tmpTime.getTotalSec()-1];
            
            if (maxValue < end - start) {
                maxValue = end - start;
                bestTime = Time.convertIntToTime(i);
            }
        }
        return bestTime;
    }
    
    private void prefixSumTwice() {
        for (int i = 0; i < 2; i++) {
            prefixSum();
        }
    }
    private void prefixSum() {
        for (int i = 1; i <= playTime.getTotalSec(); i++) {
            time[i] = (time[i - 1] + time[i]);
        }
    }
    
    private void initialize(String play_time, String adv_time, String[] logs) {
        playTime = Time.convertStringToTime(play_time);
        advTime = Time.convertStringToTime(adv_time);
        
        time = new long[playTime.getTotalSec() + 2];
        for (String log : logs) {
            String[] tmp = log.split("-");
            Time startTime = Time.convertStringToTime(tmp[0]);
            Time endTime = Time.convertStringToTime(tmp[1]);
            
            time[startTime.getTotalSec()] += 1;
            time[endTime.getTotalSec()] += -1;
        }
    }
}

class Time {
    private static final int BASE = 60, HOUR = 3600, MIN = 60;
    
    int hour;
    int min;
    int sec;
    
    Time (int hour, int min, int sec) {
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }
    
    public static Time convertStringToTime(String time) {
        String[] tmp = time.split(":");
        return new Time(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
    }
    
    public static Time convertIntToTime(int time) {
        int hour = time / HOUR;
        time = (time % HOUR); 
        int min = time / MIN;
        time = (time % MIN);
        return new Time(hour, min, time);
    }
    
    public int getTotalSec() {
        return hour * 3600 + min * 60 + sec;
    }
    
    public void plus(Time addTime) {
        sec += addTime.sec;
        if (sec >= BASE) {
            min += (sec / BASE);
            sec = (sec % BASE);
        }
        
        min += addTime.min;
        if (min >= BASE) {
            hour += (min / BASE);
            min = (min % BASE);
        }
        
        hour += addTime.hour;
    }
    
    public boolean isLater(Time time) {
        if (hour == time.hour) {
            if (min == time.min) {
                return sec < time.sec;
            }
            return min < time.min;
        }
        return hour < time.hour;
    }
    
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}