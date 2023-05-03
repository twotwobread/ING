package example.object.ch5.domain;

import java.time.Duration;
import java.util.List;

import javax.management.Query;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Movie {
	private String title;
	private Duration runningTime;
	private Money fee;
	private List<DiscountCondition> discountConditions;
	private DiscountPolicy discountPolicy;

	// private MovieType movieType;
	// private Money discountAmount;
	// private double discountPercent;

	public Money calculateMovieFee(Screening screening) {
		if (isDiscountable(screening)) {
			return fee.minus(discountPolicy.calculateMovieFee(fee));
		}

		return fee;
	}

	private boolean isDiscountable(Screening screening) {
		return discountConditions.stream()
			.anyMatch(condition -> condition.isSatisfiedBy(screening));
	}

	// private Money calculateDiscountAmount() {
	// 	switch (movieType) {
	// 		case AMOUNT_DISCOUNT -> { return calculateAmountDiscountAmount(); }
	// 		case PERCENT_DISCOUNT -> { return calculatePercentDiscountAmount(); }
	// 		case NONE_DISCOUNT -> { return calculateNoneDiscountAmount(); }
	// 	}
	//
	// 	throw new IllegalStateException();
	// }
	//
	// private Money calculateAmountDiscountAmount() {
	// 	return discountAmount;
	// }
	//
	// private Money calculatePercentDiscountAmount() {
	// 	return fee.times(discountPercent);
	// }
	//
	// private Money calculateNoneDiscountAmount() {
	// 	return Money.ZERO;
	// }
}

// Movie 클래스 개선하기
//	- 이것도 똑같은 이유로 문제가 있다.
//	- 금액 할인 정책 영화와 비율 할인 정책 영화라는 두 가지 타입을 하나의 클래스 안에 구현하고 있어 하나 이상의 이유로 변경될 수 있다. -> 응집도가 낮다.
// 	- Polymorphism 패턴을 이용해서 변경해보자.