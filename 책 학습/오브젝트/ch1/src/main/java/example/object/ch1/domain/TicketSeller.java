package example.object.ch1.domain;

public class TicketSeller {
	private TicketOffice ticketOffice;

	public TicketSeller(TicketOffice ticketOffice) {
		this.ticketOffice = ticketOffice;
	}

	//:1 public TicketOffice getTicketOffice() {
	//:1 	return ticketOffice;
	//:1 }

	// 캡슐화를 수행한 것이다. -> 세부적인 사항을 내부로 숨겼다.
	public void sellTo(Audience audience) {
		// 2. Audience에 대한 캡슐화를 수행해보자. -> 관람객의 세부적인 부분(getBag)을 내부로 옮기자.
		//:2 if (audience.getBag().hasInvitation()) {
		//:2 	Ticket ticket = ticketOffice.getTicket();
		//:2 	audience.getBag().setTicket(ticket);
		//:2 } else {
		//:2 	Ticket ticket = ticketOffice.getTicket();
		//:2 	audience.getBag().minusAmount(ticket.getFee());
		//:2 	ticketOffice.plusAmount(ticket.getFee());
		//:2 	audience.getBag().setTicket(ticket);
		//:2 }

		// 4. TicketOffice를 자율적인 객체로 만들기.
		//:4 Ticket ticket = ticketOffice.getTicket();
		//:4 audience.buy(ticket);
		// 마찬가지로 audience가 가방을 가지고 있는지 뭘로 계산을 했는지 세부적인 내용을 알 수 없다.

		ticketOffice.sellTicketTo(audience);
		// ticketOffice에서 관리하는 보유한 금액이나 이런 세부 사항을 모르기에 변경에 영향을 받지 않음.
		// 근데 이 변경은 사실 만족스럽지 않다 -> 기존엔 없었던 TicketOffice와 Audience 간의 의존이 생김.
		// TicketOffice가 자율적인 존재가 되었지만 전체 설계 관점에서의 결합도가 상승했다. -> 트레이드오프의 시점이 왔다.
		// TicketOffice에 대한 자율도 보단 Audience의 결합도를 나누는게 좋겠다와 같이 트레이드오프를 고려해야함. -> 훌륭한 설계는 적절한 트레이드오프의 결과물.

	}
}
