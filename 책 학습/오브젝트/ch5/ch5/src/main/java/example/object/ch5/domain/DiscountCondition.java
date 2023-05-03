package example.object.ch5.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.springframework.cglib.core.Local;

public interface DiscountCondition {
	// 할인 조건의 종류를 위함.
	// DiscountConditionType type;

	// 1. 순번 조건과 기간 조건이라는 두 개의 독립적인 타입이 하나의 클래스 안에 공존 -> 두 개의 클래스(SequenceCondition, PeriodCondition)로 분리. -> Movie에서 각각의 리스트를 갖게 설계 -> 결합도가 올라감.
	// 2. 이를 해결하기 위해서 다형성 이용하기. -> 역할의 개념.


	// // 순번 조건을 위함.
	// private int sequence;
	// // 기간 조건을 위함.
	// private DayOfWeek dayOfWeek;
	// private LocalTime startTime;
	// private LocalTime endTime;

	boolean isSatisfiedBy(Screening screening);

	// private boolean isSatisfiedByPeriod(Screening screening) {
	// 	return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek()) &&
	// 		startTime.compareTo(screening.getWhenScreened().toLocalTime()) <= 0&&
	// 		endTime.compareTo(screening.getWhenScreened().toLocalTime()) >= 0;
	// }
	//
	//
	// private boolean isSatisfiedBySequence(Screening screening) {
	// 	return sequence == screening.getSequence();
	// }
}

// 해당 클래스는 변경에 취약한 클래스이다. 그 이유는 아래와 같다.
//	1. 새로운 할인 조건 추가.
//		- isSatisfiedBy 메서드 안의 if-else 구문을 수정해야함.
//		- 물론 새로운 할인 조건이 새로운 데이터를 요구하면 DiscountCondition에 속성을 추가하는 작업도 필요.
//	2. 순번 조건을 판단하는 로직 변경.
//		- isSatisfiedBySequence 메서드 내부 구현을 수정해야함.
//		- 물론 순번 조건을 판단하는 데 필요한 데이터가 변경 -> DiscountCondition sequence 속성 역시 변경해야함.
//	3. 기간 조건을 판단하는 로직 변경.
//		- isSatisfiedByPeriod 메서드 내부 구현을 수정해야함.
//		- 물론 기간 조건을 판단하는 데 필요한 데이터가 변경 -> DiscountCondition 기간 조건 속성 역시 변경해야함.
// - 이렇듯 하나 이상의 변경 이유를 가져 응집도가 낮음. -> 서로 연관성이 없는 기능이나 데이터가 하나의 클래스안에 뭉쳐져 있다는 것을 의미.
//	=> 변경의 이유에 따라 클래스를 분리하자.

//	- 일반적으로 설계 개선 작업은 변경의 이우가 하나 이상인 클래스를 찾는 것으로부터 시작하는 것이 좋음.
//	- 이런 변경의 이유가 하나 이상인 클래스는 위험 징후를 또렷하게 드러내는 몇 가지 패턴이 존재.
//		- 코드를 통해 변경의 이유를 파악할 수 있는 방법.
//			1. 인스턴스 변수가 초기화되는 시점 살펴보기.
//				- 응집도 높은 클래스는 인스턴스를 생성 시 모든 속성을 함께 초기화함.
//				- 반면 응집도가 낮은 클래스는 객체의 속성 중 일부만 초기화하고 일부는 초기화되지 않은 상태로 남겨짐.
//				=> DiscountCondition이 초기화 될 때 dayOfWeek, startTime, endTime은 초기화 되지 않고 sequence만 초기화 되거나 그 반대의 경우 존재.
//				=> 함께 초기화되는 속성을 기준으로 코드를 분리.
//			2. 메서드들이 인스턴스 변수를 사용하는 방식 살펴보기.
//				- 모든 메서드가 객체의 모든 속성을 사용한다면 클래스의 응집도는 높은 것.
//				- 반면 메서드들이 사용하는 속성에 따라 그룹이 나뉜다면 클래스의 응집도가 낮은 것.
//				=> isSatisfiedBySequence 메서드와 isSatisfiedByPeriod 메서드가 이 경우에 해당함.
//				=> 응집도를 높이기 위해선 속성 그룹과 해당 그룹에 접근하는 메서드 그룹을 기준으로 코드를 분리.
//	- 클래스 응집도 판단하기
//		- 클래스가 하나 이상의 이유로 변경 -> 응집도 낮음. -> 변경의 이유를 기준으로 클래스 분리하기.
//		- 클래스의 인스턴스를 초기화하는 시점에 경우에 따라 서로 다른 속성 초기화 -> 응집도 낮음 -> 초기화 속성의 그룹을 기준으로 클래스 분리하기.
//		- 메서드 그룹이 속성 그룹을 사용하는지 여부로 나뉨. -> 응집도 낮음. -> 이들 그룹을 기준으로 클래스 분리하기.

// 다형성을 통해 분리하기 (Polymorphism 패턴)
//	- 역할을 대체할 클래스들 사이에서 구현을 공유해야 할 필요가 있다면 추상 클래스를 사용
//	- 구현 공유 필요 없이 역할을 대체하는 객체들의 책임만 정의하고 싶다면 인터페이스 사용.
//	- 객체의 암시적인 타입에 따라 행동을 분기해야 한다면 암시적인 타입을 명시적인 클래스로 정의하고 행동을 나눔으로써 응집도 문제를 해결할 수 있다.
//	- 객체의 타입에 따라 변하는 행동이 있다면 타입을 분리하고 변화하는 행동을 각 타입의 책임으로 할당하라는 것이다.
//	- GRASP에서는 이를 다형성 패턴이라고 한다.
// 변경으로부터 보호하기 (Protected Variations 패턴)
//	- 변경을 캡슐화하도록 책임을 할당하는 것 -> DiscountCondition에서 각 구현체들은 구현체의 변경에 있어 독립적이고 새로운 조건이 들어와도 확장하기 쉽다.
// => 위의 이 두가지 패턴을 조합하면 코드 수정의 파급 효과를 조절할 수 있고 변경과 확장에 유연하게 대처할 수 있는 설계를 얻을 수 있을 것이다.
