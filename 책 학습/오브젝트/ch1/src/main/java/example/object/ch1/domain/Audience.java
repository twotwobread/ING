package example.object.ch1.domain;

public class Audience {
	private Bag bag;

	public Audience(Bag bag) {
		this.bag = bag;
	}

	//:2 public Bag getBag() {
	//:2 	return bag;
	//:2 }

	public Long buy(Ticket ticket) {
		// 3. Bag을 자율적인 객체로 만들기.
		//:3 if (bag.hasInvitation()) {
		//:3 	bag.setTicket(ticket);
		//:3 	return 0L;
		//:3 } else {
		//:3 	bag.setTicket(ticket);
		//:3 	bag.minusAmount(ticket.getFee());
		//:3 	return ticket.getFee();
		//:3 }
		return bag.hold(ticket);
		// 가방안에 초대권이 있는지 없는지에 대한 부분을 알 수 없음.
		// 가방의 세부 구현 변경에도 영향이 없음.
	}
}
