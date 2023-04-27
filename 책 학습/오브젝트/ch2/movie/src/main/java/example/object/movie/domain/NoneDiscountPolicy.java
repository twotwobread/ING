package example.object.movie.domain;

public class NoneDiscountPolicy implements DiscountPolicy {
	@Override
	public Money calculateDiscountAmount(Screening screening) {
		return Money.ZERO;
	}
	// @Override
	// protected Money getDiscountAmount(Screening screening) {
	// 	return Money.ZERO;
	// }
}

// 중요한 것은 Movie와 DsicountPolicy는 수정하지 않고 NoneDiscountPolicy를 추가한 것만으로 애플리케이션의 기능을 확장했다는 것이다. -> 추상화를 중심으로 코드 구조를 설계하면 유연하고 확장 가능한 설계를 만들 수 있다.
// 추상화가 유연한 설계를 가능하게 하는 이유는 설계가 구체적인 상황에 결합되는 것을 방지하기 때문이다. -> Movie는 특정 할인 정책에 묶이지 않는다. 할인 정책을 구현한 클래스가 DiscountPolicy를 상속받고 있으면 어떤 클래스와도 협력이 가능하다.
// 컨텍스트 독립성이라 불리고 프레임워크와 같은 유연한 설계가 필수적인 분야에서 그 진가를 발휘한다. -> 유연성이 필요한 곳에 추상화를 사용해라.

