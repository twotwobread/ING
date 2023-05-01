package example.object.ch4.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Screening {
	private Movie movie;
	private int sequence;
	private LocalDateTime whenScreened;
}
