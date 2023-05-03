package example.object.ch5.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SequenceCondition implements DiscountCondition{
	private int sequence;

	public boolean isSatisfiedBy(Screening screening) {
		return sequence == screening.getSequence();
	}
}
