import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	long maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE;
	long minX = Long.MAX_VALUE, minY= Long.MAX_VALUE;

	public String[] solution(int[][] line) {
		Map<String, Integer> points = new HashMap<>();
		for (int i = 0; i < line.length; i++) {
			for (int j = i + 1; j < line.length; j++) {
				long xChild = (long)line[i][1]*line[j][2] - line[i][2]*line[j][1];
				long yChild = (long)line[i][2]*line[j][0] - line[i][0]*line[j][2];
				long parent = (long)line[i][0]*line[j][1] - line[i][1]*line[j][0];
				
                if (parent == 0)
                    continue;
                
				double x = (double) xChild / parent;
				double y = (double) yChild / parent;
                
                
                if (!isInteger(x, y))
                    continue;

				long intX = (long) x;
				long intY = (long) y;
				
				points.put(String.format("%s %s", intX, intY), 1);

				maxX = Math.max(maxX, intX);
				minX = Math.min(minX, intX);
				maxY = Math.max(maxY, intY);
				minY = Math.min(minY, intY);
			}
		}

		return draw(points);
	}

	private String[] draw(Map<String, Integer> points) {
		List<String> result = new ArrayList<>();
        
		for (long i = maxY; i >= minY; i--) {
			StringBuilder sb = new StringBuilder();
			for (long j = minX; j <= maxX; j++) {
				if (points.containsKey(String.format("%s %s", j, i))) {
					sb.append("*");
				} else {
					sb.append(".");
				}
			}
			result.add(sb.toString());
		}

		return result.toArray(new String[result.size()]);
	}
    
    private boolean isInteger(double x, double y) {
        return (x % 1.0 == 0.0) && (y % 1.0 == 0.0); 
    }
}