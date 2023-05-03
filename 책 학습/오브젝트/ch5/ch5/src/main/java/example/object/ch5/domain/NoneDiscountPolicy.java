package example.object.ch5.domain;

public class NoneDiscountPolicy implements DiscountPolicy{
	@Override
	public Money calculateMovieFee(Money fee) {
		return Money.ZERO;
	}
}
