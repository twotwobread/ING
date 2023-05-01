package example.object.ch4.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Getter;

@Getter
public class DiscountCondition {
	private DiscountConditionType type;

	private int sequence;

	private DayOfWeek dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;
}
