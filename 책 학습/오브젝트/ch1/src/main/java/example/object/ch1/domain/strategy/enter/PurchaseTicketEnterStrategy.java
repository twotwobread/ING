package example.object.ch1.domain.strategy.enter;

import example.object.ch1.domain.Audience;
import example.object.ch1.domain.Ticket;
import example.object.ch1.domain.TicketSeller;

public class PurchaseTicketEnterStrategy implements EnterStrategy{
	@Override
	public void enter(Audience audience, TicketSeller ticketSeller) {
		// Ticket purchasedTicket = ticketSeller.getTicketOffice().getTicket();
		// audience.getBag().minusAmount(purchasedTicket.getFee());
		// ticketSeller.getTicketOffice().plusAmount(purchasedTicket.getFee());
		// audience.getBag().setTicket(purchasedTicket);
	}
}
