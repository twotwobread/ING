package example.object.ch4.domain;

import java.time.Duration;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
	private String title;
	private Duration duration;
	private Money fee;
	private List<DiscountCondition> discountConditions;

	private MovieType movieType;
	private Money dicountAmount;
	private double discountPercent;

	public void setTitle(String title) {
		this.title = title;
	}
}

// 가장 큰 차이점은 할인 조건의 목록이 인스턴스 변수로 Movie에 직접 포함되어 있다는 것이다. 또한 할인 정책을 별도의 클래스로 변경했던 이전 예제와 달리 Movie 안에서 할인 금액과 정책을 정한다.
// 해당 설계에서는 객체에 포함되어야 하는 데이터에 집중한다.
