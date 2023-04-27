package example.object.movie.domain;

public interface DiscountPolicy {
	Money calculateDiscountAmount(Screening screening);
}

// public abstract class DiscountPolicy {
// 	private List<DiscountCondition> conditions = new ArrayList<>();
//
// 	public DiscountPolicy(DiscountCondition... conditions) {
// 		this.conditions = Arrays.asList(conditions);
// 	}
//
// 	public Money calculateDiscountAmount(Screening screening) {
// 		for (DiscountCondition each : conditions) {
// 			if (each.isSatisfiedBy(screening)) {
// 				return getDiscountAmount(screening);
// 			}
// 		}
//
// 		// 여기서 할인 정책이 없는 경우 ZERO를 반환하게 구현해서 NoneDiscountPolicy가 이용되지 않는다.
// 		// 이 부분에서 DiscountPolicy와 NoneDiscountPolicy가 개념적으로 결합된다. -> getDiscountAmount가 호출되지 않는다면 0원을 반환할 것이라는 사실을 가정하고 있다.
// 		// DiscountPolicy를 인터페이스로 변경하자.
// 		return Money.ZERO;
// 	}
//
// 	abstract protected Money getDiscountAmount(Screening screening);
// }

// 구현 정책 클래스들은 대부분의 코드가 유사하고 할인 요금을 계산하는 방식만 조금 다르기에 중복 코드를 줄이기 위해서 공통 코드를 보관.
// 인터페이스와 추상 클래스 차이는 추상 클래스에서는 인터페이스와 달리 객체변수, 생성자, private를 이용할 수 있음.

// DiscountPolicy는 할인 여부와 요금 계산에 필요한 전체적인 흐름은 정의하지만 실제로 요금을 계산하는 부분은 추상 메서드인 getDiscountAmount 메서드에게 위임한다.
// 실제로는 구현체의 오버라이딩한 메서드가 실행된다. -> 부모 클래스에 기본적인 알고리즘의 흐름을 구현하고 중간에 필요한 처리를 자식 클래스에게 위임하는 디자인 패턴을 템플릿 메서드 패턴이라고 부름.

// 이전에 구현된 방식이 사실 더 명확하게 할인 금액이 0원이라는 것을 말해준다. 하지만 이렇게 바꾸면 종속되는 부분이 사라진다.
// 결국 이런 부분들은 모두 트레이드 오프이고 트레이드오프를 통해 얻어진 결론과 그렇지 않은 결론 사이의 차이는 크다 -> 고민하고 트레이드오프하라.

