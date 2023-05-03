package example.object.ch5.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Screening {
	// 2. 책임을 수행하기 위해 필요한 상태를 결정해라.
	private Movie movie; // 가격을 계산하라는 메시지를 전송해야 하기에
	private int sequence; // 상영 순번
	private LocalDateTime whenScreened; // 상영 시간

	// 1. Screening은 외부로 부터 '예매해라'는 메시지를 받을 것이다.
	public Reservation reserve(Customer customer, int audienceCount) {
		return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
	}

	public int getSequence() {
		return sequence;
	}

	public LocalDateTime getWhenScreened() {
		return whenScreened;
	}

	private Money calculateFee(int audienceCount) {
		return movie.calculateMovieFee(this).times(audienceCount);
	}
}
