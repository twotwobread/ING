package example.object.ch1.domain.strategy.enter;

import example.object.ch1.domain.Audience;
import example.object.ch1.domain.Ticket;
import example.object.ch1.domain.TicketSeller;

public class InvitationEnterStrategy implements EnterStrategy{
	@Override
	public void enter(Audience audience, TicketSeller ticketSeller) {
		// Ticket invitationTicket = ticketSeller.getTicketOffice().getTicket();
		// audience.getBag().setTicket(invitationTicket);
	}
}
