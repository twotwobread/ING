package example.object.movie.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmountDiscountPolicy implements DiscountPolicy {

	private Money discoundAmount;
	private List<DiscountCondition> conditions = new ArrayList<>();

	public AmountDiscountPolicy(Money discoundAmount, DiscountCondition... discountConditions) {
		this.conditions = Arrays.asList(discountConditions);
		this.discoundAmount = discoundAmount;
	}

	@Override
	public Money calculateDiscountAmount(Screening screening) {
		return discoundAmount;
	}
}
