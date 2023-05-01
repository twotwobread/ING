package example.object.ch4.domain;

public class ReservationAgency {
	public Reservation reseve(Screening screening, Customer customer, int audienceCount) {
		Movie movie = screening.getMovie();

		boolean discountable = false;
		for (DiscountCondition condition : movie.getDiscountConditions()) {
			if (condition.getType() == DiscountConditionType.PERIOD) {
				discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek()) &&
				condition.getStartTime().compareTo(screening.getWhenScreened().toLocalTime()) <= 0 &&
				condition.getEndTime().compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
			} else {
				discountable = condition.getSequence() == screening.getSequence();
			}

			if (discountable) {
				break;
			}
		}

		Money fee;
		if (discountable) {
			Money discountAmount = null;
			switch(movie.getMovieType()) {
				case AMOUNT_DISCOUNT -> discountAmount = movie.getDicountAmount();
				case PERCENT_DISCOUNT -> discountAmount = movie.getFee().times(movie.getDiscountPercent());
				case NONE_DISCOUNT -> discountAmount = Money.ZERO;
			}
			fee = movie.getFee().minus(discountAmount).times(audienceCount);
		} else {
			fee = movie.getFee();
		}

		return new Reservation(customer, screening, fee, audienceCount);
	}
}

// 데이터 중심의 설계를 책임 중심의 설계와 비교하면서 각각의 장단점을 알아보자.

// 설계 트레이드 오프
//	- 데이터 중심, 책임 중심 설계의 장단점 비교를 위해서 캡슐화, 응집도, 결합도를 이용.
//	- 캡슐화 : 변경 가능성이 높은 부분은 내부로 숨기고 외부에는 상대적 안정적인 부분만 공개하는 추상화 기법.
//	- 응집도와 결합도
//		- 응집도 : 모듈에 포함된 내부 요소들이 연관돼 있는 정도 -> 얼마나 관련 높은 책임들을 할당했는가.
//		- 결합도 : 의존성의 정도를 나태내고 다른 모듈에 대해 얼마나 많은 지식을 갖고 있는지를 나타내는 척도.
//		=> 설계 변경 시 용이하게 하기 위해서 응집도는 높고 결합도는 낮은 설계를 추구해야함. -> 캡슐화의 정도가 이에 영향을 미침.
// 데이터 중심의 영화 예매 시스템의 문제점.
//	- 캡슐화 위반
//		- Getter, Setter -> Movie 내부에 Money 타입의 fee라는 이름의 인스턴스 변수가 존재한다는 사실을 퍼블릭 인터페이스에 노골적으로 드러냄.
//		=> 책임이 아닌 데이터에 초점을 맞췄기에 과도한 접근자와 수저장자를 가지게 됨. -> 이를 추측에 의한 설계 전략이라 부름. -> 다양한 상황에 사용된다는 막연한 추측을 기반으로 설계.
//	- 높은 결합도
//		- 여러 데이터 객체들을 사용하는 제어 로직 -> 특정 객체 안에 집중 -> 다수의 데이터 객체 강하게 결합. -> 변경 시 영향을 받고 줌.
//	- 낮은 응집도
//		- 서로 다른 이유로 변경되는 코드가 하나의 모듈 안에 공존 시 모듈의 응집도가 낮음. -> 수정하는 이유 살펴보기.
//		- ReservationAgency 경우, 정말 많은 이유로 코드가 수정되어야 함. -> 어떤 코드 수정 시 아무 상관 없던 코드에 문제 발생 -> 응집도가 낮다는 의미.
// 자율적인 객체를 향해
// 	- 캡슐화를 지켜라
//		- 속성의 가시성을 private로 설정해도 getter, setter 등을 이용하여 외부로 제공한다면 캡슐화를 위반. -> 객체 스스로가 자신의 상태를 책임지고 인터페이스를 통해 외부에서 접근.
//	- 스스로 자신의 데이터를 책임지는 객체
//		- 객체 내부에 저장되는 데이터보다 객체가 협력에 참여하면서 수행할 책임을 정의하는 오퍼레이션이 더 중요. -> 객체가 어떤 데이터 포함?, 객체가 데이터에 대해 수행해야할 오퍼레이션?
//		=> 객체 내부 상태를 저장하는 방식과 저장된 상태에 대해 호출하는 오퍼레이션의 집합 알 수 있음.
// 여전히 부족.
//	- DiscountCondition의 isDiscountable 메서드에서 LocalTIme 시간정보와 DayOfWeek 의 요일정보를 파라미터로 받는다. -> 이 두 타입이 내부에 존재한다는 것을 표현하는 것.
//	=> 파라미터가 바뀐다면?? -> 모든 클라이언트들이 수정해야 한다. -> 파급 효과
// 데이터 중심 설계의 문제점.
//	- 데이터 중심 설계는 본질적으로 너무 이른 시기에 데이터에 관해 결정하도록 강요.
//	- 데이터 중심의 설계에서는 협력이라는 문맥을 고려하지 않고 객체를 고립시킨 채 오퍼레이션을 결정.
//		- 올바른 객체지향 설계의 무게 중심은 항상 객체의 내부가 아닌 외부에 맞춰져 있어야 한다.
//			- 상태를 어떻게 관리할 지는 나중 문제이고 중요한 건 다른 객체와 협력하는 방법이다.
//		- 데이터 중심 설계에서 초점은 객체 내부로 향한다. -> 객체의 구현이 이미 결정된 상태 -> 다른 객체와의 협력 방법 고민 -> 억지로 인터페이스를 끼워맞춤.
//		- 객체의 인터페이스에 구현이 노출돼 있었기 때문에 협력이 구현 세부사항에 종속돼 있고 그에 따라 객체의 내부 구현이 변경됐을 때 협력하는 객체 모두가 영향을 받음.


