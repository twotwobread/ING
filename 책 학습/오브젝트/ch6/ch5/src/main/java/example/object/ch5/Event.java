package example.object.ch5;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event {
	private String subject;
	private LocalDateTime from;
	private Duration duration;

	public Event(String subject, LocalDateTime from, Duration duration) {
		this.subject = subject;
		this.from = from;
		this.duration = duration;
	}

	// 쿼리라는 것을 명확하게 알 수 있따.
	public boolean isSatisfied(RecurringSchedule schedule) {
		if (from.getDayOfWeek() != schedule.getDayOfWeek() ||
		!from.toLocalTime().equals(schedule.getFrom()) ||
		!duration.equals(schedule.getDuration())) {
			return false;
		}

		return true;
	}

	// 명령이라는 것을 확실하게 알 수 있따.
	public void reschedule(RecurringSchedule schedule) {
		from = LocalDateTime.of(from.toLocalDate().plusDays(daysDistance(schedule)),
			schedule.getFrom());
		duration = schedule.getDuration();
	}

	private long daysDistance(RecurringSchedule schedule) {
		return schedule.getDayOfWeek().getValue() - from.getDayOfWeek().getValue();
	}
}

// 일정 관리의 중요성을 인식한 개발팀은 반복되는 이벤트를 쉽게 관리할 수 있는 소프트 웨어를 개발하기로 결정.
//	- 이벤트 : 특정 일자에 실제로 발생하는 사건을 의미.
//	- 반복 일정 : 일주일 단위로 돌아오는 특정 시간 간격에 발생하는 사건 전체를 포관적으로 지칭하는 용어
//		- 반복 일정을 만족하는 특정 일자와 시간에 발생하는 사건이 바로 이벤트가 된다.
//		- 2019년 5월 8일 수요일 10시 30분부터 11시까지는 열리는 회의 이벤트는 매주 수요일 10시 30분부터 11시까지 열리는 회의라는 반복 일정을 만족시키는 하나의 사건이다.

// 이벤트 클래스는 현재 이벤트가 반복 일자가 정의한 반복 일정 조건을 만족하는지를 검사하는 isSatisfied 메서드를 제공한다.
//	이 메서드는 반복일자의 인스턴스를 인자로 받아 해당 이벤트가 일정 조건을 만족하면 true 아니면 false를 반환한다.

// isSatisfied 메서드는 이벤트가 반복 조건을 만족시키는지를 체크하기 위해서 반복 일정에 대한 인스턴스를 생성하고 이벤트 인스턴스를 생성한다.
// 5월 8일은 수요일이므로 반복 일정의 조건을 만족시키기에 true를 반환한다. 하지만 여기에 개발팀을 당혹하게 만든 버그가 숨겨져 있다.

// 이 메서드는 이 값들 중 하나라도 같지 않으면 false를 반환한다. 하지만 false를 반환하기 전에 reschedule 메서드를 호출하고 있다. -> 이는 이벤트 객체의 상태를 수정한다.
// reshedule에서는 조건을 만족하지 못하면 조건을 만족시키도록 변경한 후 false를 반환하고 이 때문에 두번 실행 시 결과가 달라진다.
// 버그를 찾기 어려웠던 이유는 isSatisfied가 명령과 쿼리의 두 가지 역할을 동시에 수행하고 있었기 때문이다.

// 대부분의 사랃들은 isSatisfied가 부수효과를 가질 것이라 예상할 수 없다.
// 명령과 쿼리를 뒤섞으면 실행 결과를 예측하기가 어려워진다. -> 버그를 양산할 수 있다. -> 명령과 쿼리를 명확하게 분리하자.

// 명령과 쿼리를 분리함으로써 참조 투명성의 장점을 제한적이나마 누릴 수 있따. -> 잘 활용한다면 버그가 적고 디버깅이 용이하며, 쿼리의 순서에 따라 실행 결과가 변하지 않는 코드를 작성할 수 있다.
// 컴퓨터의 세계와 수학의 세계를 나누는 가장 큰 특징은 부수효과의 유무다. -> 프로그램에서 부수효과를 발생시키는 두 가지 대표적 문법은 대입문과 (프로시저라고 부르는 것이 올바른) 함수다.
// 수학의 경우 x의 값을 초기화한 후에는 값을 변경하는 것이 불가능하지만 프로그램에서는 대입문을 통해 다른 값으로 변경하는 것이 가능하다. -> 부수효과에 의해 결과값이 매번 달라질 수 있다.
// 부수효과에서 빠질 수 없는 얘기가 참조 투명성이다. -> 이는 어떤 표현시 e가 있을 때 e의 값으로 e가 나타나는 모든 위치를 교체하더라도 결과가 달라지지 않는 특성을 의미한다.
// 수학에서 함수는 동일한 입력에 대해 항상 동일한 값을 반환하기에 참조 투명성을 지키는 이상적 예이다. -> 이렇게 말할 수 있는 이유는 f(1)의 값이 불변하기 때문이다. -> 이런 성질을 불변성이라고 부른다. -> 이는 부수효과가 발생하지 않는다는 말과 같다.
// 객체지향 패러다임은 객체의 상태 변경이라는 부수효과를 기반으로 하기에 참조 투명성은 예외에 가깝다. -> 하지만 명령-쿼리 분리 원칙을 사용하면 이 균열을 줄일 수 있따.
//	명령과 쿼리를 분리하여 이 참조 투명성을 제한적이나마 누릴 수 있따.

