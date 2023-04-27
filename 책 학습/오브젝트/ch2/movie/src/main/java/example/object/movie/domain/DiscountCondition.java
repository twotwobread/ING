package example.object.movie.domain;

public interface DiscountCondition {
	boolean isSatisfiedBy(Screening screening);
}
