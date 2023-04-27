package example.object.ch1.domain;

import example.object.ch1.domain.strategy.enter.EnterStrategy;
import example.object.ch1.domain.strategy.enter.InvitationEnterStrategy;
import example.object.ch1.domain.strategy.enter.PurchaseTicketEnterStrategy;

public class Theater {
	private TicketSeller ticketSeller;

	public Theater(TicketSeller ticketSeller) {
		this.ticketSeller = ticketSeller;
	}

	public void enter(Audience audience) {
		// 1. Theater이 TicketOffice로의 접근을 모두 TicketSeller 내부로 숨김.
		//1: if (audience.getBag().hasInvitation()) {
			//:1 Ticket invitationTicket = ticketSeller.getTicketOffice().getTicket();
			//:1 audience.getBag().setTicket(invitationTicket);
		//1: } else {
			//:1 Ticket purchasedTicket = ticketSeller.getTicketOffice().getTicket();
			//:1 audience.getBag().minusAmount(purchasedTicket.getFee());
			//:1 ticketSeller.getTicketOffice().plusAmount(purchasedTicket.getFee());
			//:1 audience.getBag().setTicket(purchasedTicket);
		//1: }
		// => 이 방식은 절차지향적임 -> enter 메서드가 process이고 Audience, TicketSeller, Bag, TicketOffice가 데이터로 동작 -> 프로세스와 데이터가 별도의 모듈에 위치 -> 절차지향적.
		// => 모든 처리가 하나의 클래스(Theater)에 위치하고 나머지 클래스는 단지 데이터의 역할만 수행.

		ticketSeller.sellTo(audience);
		// 이렇게 하면 소극장 클래스 어디에서도 판매원이 TicketOffice에 접근한다는 사실을 알 수 없다.
		// 소극장은 단지 판매원이 sellTo 메시지를 이해하고 응답할 수 있다는 사실만 알고 있다. -> 판매원의 인터페이스에만 의존한다.
		// => 이 방법은 객체지향적임 -> process 과정이 객체 내부로 이동 -> 자신의 데이터를 스스로 처리.
		// => 데이터와 프로세스가 동일한 모듈 내부에 위치.

		// 훌륭한 객체지향 설계의 핵심은 캡슐화를 통해서 의존성을 적절히 관리함으로써 객체 사이의 결합도를 낮추는 것.

	}
}
