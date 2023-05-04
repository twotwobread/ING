package example.object.ch5;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RecurringSchedule {
	private String subject;
	private DayOfWeek dayOfWeek;
	private LocalTime from;
	private Duration duration;
}
