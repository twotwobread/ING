package example.object.ch5.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PeriodCondition implements DiscountCondition {
	private DayOfWeek dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;

	public boolean isSatisfiedBy(Screening screening) {
		return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
			startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0&&
			endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
	}

}
