package example.object.ch5.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AmountDiscountPolicy implements DiscountPolicy{
	Money discountAmount;

	@Override
	public Money calculateMovieFee(Money fee) {
		return discountAmount;
	}
}
