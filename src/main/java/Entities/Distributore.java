package Entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Distributore extends Rivenditore {

	@Enumerated(EnumType.STRING)
	private StatoDistributore stato;

	public Distributore(StatoDistributore stato) {
		this.stato = stato;
	}

}
