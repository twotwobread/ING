package example.object.movie.domain;

import java.time.Duration;

public class Movie {
	private String title;
	private Duration runningTime;
	private Money fee;
	private DiscountPolicy discountPolicy;

	public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
		this.title = title;
		this.runningTime = runningTime;
		this.fee = fee;
		this.discountPolicy = discountPolicy;
	}

	public Money getFee() {
		return fee;
	}

	public Money calculateMovieFee(Screening screening) {
		// 1. discountPolicy가 없으면 그냥 가격을 그대로 측정해야 한다.
		// 하지만 이 방식의 문제점은 할인 정책이 없는 경우를 예외 케이스로 취급하기에 일관성 있던 협력 방식이 무너진다.
		// 기존 할인 정책의 경우엔 할인할 금액을 계산하는 책임이 DiscountPolicy의 자식 클래스에 있었지만 할인 정책의 경우엔 할인 금액이 0원이라는 사실을 결정하는 책임이 DiscountPolicy가 아닌 Movie 쪽에 있기 때문이다.
		// 따라서 책임의 위치를 결정하기 위해 조건문을 사용하는 것은 협력의 설계 측면에서 대부분 좋지 않은 선택이다. -> 항상 예외 케이스를 최소화하고 일관성을 유지할 수 있는 방법을 선택해라.
		// 일관성을 지킬 수 있는 방법은 0원이라는 할인 요금을 계산할 책임을 그대로 DiscountPolicy 계층에 유지시키는 것이다. -> NoneDiscountPolicy를 추가하자.
		// if (discountPolicy == null) {
		// 	return fee;
		// }

		return fee.minus(discountPolicy.calculateDiscountAmount(screening));
	}

	public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
		this.discountPolicy = discountPolicy;
	}
}

// 이 코드에서 어떤 할인정책을 이용할 것인지를 결정하는 코드가 없다. -> 상속과 다형성을 이용했고 그 기반에는 추상화라는 원리가 존재한다.