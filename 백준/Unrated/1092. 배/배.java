import java.io.*;
import java.util.*;

class Main {

    private static int n, m;
    private static List<Integer> crains = new ArrayList<>();
    private static List<Integer> boxes = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		initialize();
        Collections.sort(crains, Collections.reverseOrder());
        Collections.sort(boxes, Collections.reverseOrder());
        System.out.println(carryBoxes());
	}

    private static int carryBoxes() {
        int cnt = 0;
        
        if (crains.get(0) < boxes.get(0)) return -1;
        while(!boxes.isEmpty()) {
            boolean[] visited = new boolean[n];
            int crainIndex = 0, boxIndex = 0;
            while(true) {
                int nowBox = boxes.get(boxIndex);
                if (visited[crainIndex] == false && crains.get(crainIndex) >= nowBox) {
                    boxes.remove(boxIndex);
                    visited[crainIndex] = true;
                    crainIndex = 0;
                    boxIndex = 0;
                } 
                else if (visited[crainIndex] == true && crains.get(crainIndex) >= nowBox) {
                    crainIndex += 1;
                } else {
                    boxIndex += 1;
                }
                
                if (crainIndex >= crains.size() || boxIndex >= boxes.size()) break;
            }
            cnt += 1;
        }

        return cnt;
    }

    private static void initialize() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");
        for (int i = 0; i < arr.length; i++) {
            crains.add(Integer.parseInt(arr[i]));
        }

        m = Integer.parseInt(br.readLine());
        arr = br.readLine().split(" ");
        for (int i = 0; i < arr.length; i++) {
            boxes.add(Integer.parseInt(arr[i]));
        }
    }
}