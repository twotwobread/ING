package example.object.movie.domain;

public class Reservation {
	private Customer customer;
	private Screening screening;
	private Money fee;
	private int audienceCount;

	public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
		this.customer = customer;
		this.screening = screening;
		this.fee = fee;
		this.audienceCount = audienceCount;
	}
}

// 영화 예매를 위해서 각 조합 인스턴스들이 서로의 메서드를 호출하면서 상호작용. -> 협력
// OOP 작성 시 먼저 협력의 관점에서 어떤 객체가 필요한지를 결정 -> 객체들의 공통 상태와 행위를 구현하기 위해 클래스 작성.
// 객체 간 상호작용 가능한 유일한 방법은 메시지를 전송하는 것 -> 메시지를 수신한 객체가 수신된 메시지를 처리하기 위한 자신만의 방법은 메서드.
// 메시지와 메서드를 구분하는 것은 매우 중요 -> 이 구분에서부터 다형성의 개념이 출발.
