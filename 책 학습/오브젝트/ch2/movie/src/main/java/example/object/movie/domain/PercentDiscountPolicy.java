package example.object.movie.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PercentDiscountPolicy implements DiscountPolicy {

	private double percent;
	private List<DiscountCondition> conditions = new ArrayList<>();

	public PercentDiscountPolicy(double percent, DiscountCondition... discountConditions) {
		this.conditions = Arrays.asList(discountConditions);
		this.percent = percent;
	}

	@Override
	public Money calculateDiscountAmount(Screening screening) {
		return screening.getMovieFee().times(percent);
	}
}
