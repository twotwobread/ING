package example.object.ch1.domain;

import java.util.ArrayList;
import java.util.List;

public class TicketOffice {
	private Long amount;
	private List<Ticket> tickets = new ArrayList<>();

	public TicketOffice(Long amount, Ticket... tickets) {
		this.amount = amount;
		this.tickets = List.of(tickets);
	}

	public void sellTicketTo(Audience audience) {
		Ticket ticket = getTicket();
		audience.buy(ticket);
		plusAmount(ticket.getFee());
	}

	private Ticket getTicket() {
		return tickets.remove(0);
	}

	private void plusAmount(Long amount) {
		this.amount += amount;
	}
}
