package example.object.ch4.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reservation {
	private Customer customer;
	private Screening screening;
	private Money fee;
	private int audienceCount;
}
