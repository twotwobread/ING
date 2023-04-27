package example.object.ch1.domain.strategy.enter;

import example.object.ch1.domain.Audience;
import example.object.ch1.domain.Ticket;
import example.object.ch1.domain.TicketSeller;

public interface EnterStrategy {
	void enter(Audience audience, TicketSeller ticketSeller);
}
