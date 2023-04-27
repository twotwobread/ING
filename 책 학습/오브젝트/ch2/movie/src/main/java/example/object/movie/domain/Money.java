package example.object.movie.domain;

import java.math.BigDecimal;

public class Money {
	public static final Money ZERO = Money.wons(0);

	private final BigDecimal amount;

	Money(BigDecimal amount) {
		this.amount = amount;
	}

	public static Money wons(long amount) {
		return new Money(BigDecimal.valueOf(amount));
	}

	public static Money wons(double amount) {
		return new Money(BigDecimal.valueOf(amount));
	}

	public Money plus(Money amount) {
		return new Money(this.amount.add(amount.amount));
	}

	public Money minus(Money amount) {
		return new Money(this.amount.subtract(amount.amount));
	}

	public Money times(double percent) {
		return new Money(this.amount.multiply(
			BigDecimal.valueOf(percent)));
	}

	public boolean isLessThan(Money other) {
		return amount.compareTo(other.amount) < 0;
	}

	public boolean isGreaterThanOrEqual(Money other) {
		return amount.compareTo(other.amount) >= 0;
	}
}

// 1장에서는 금액을 표현할 때 -> Long 타입을 이용. -> 변수 크기, 연산자의 종류와 관련된 구현 관점의 제약 표현은 가능 But, 해당 타입처럼 저장 값이 금액과 관련되었다는 의미 전달 불가.
// 또한 금액과 관련된 로직이 서로 다른 곳에 중복되어 구현되는 것을 막을 수 없음. -> 객체지향의 장점은 객체를 통해 도메인의 의미를 풍부하게 표현 가능.
// 따라서 의미를 좀 더 명시적이고 분명히 표현 가능하다면 하나의 인스턴스 변수만 포함하더라도 개념을 명시적으로 표현하는 것이 전체적 설계의 명확성과 유연성을 높이는 첫걸음임.
