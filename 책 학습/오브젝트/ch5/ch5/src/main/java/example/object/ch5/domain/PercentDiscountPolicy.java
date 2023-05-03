package example.object.ch5.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PercentDiscountPolicy implements DiscountPolicy{
	private double discountPercent;
	@Override
	public Money calculateMovieFee(Money fee) {
		return fee.times(discountPercent);
	}
}
