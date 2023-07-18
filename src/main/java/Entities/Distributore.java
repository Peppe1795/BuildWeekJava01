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

public class Distributore extends Vendita{
	
	@Enumerated(EnumType.STRING)
    private StatoDistributore stato;
	private 
	
	public Distributore(Biglietti biglietti, Abbonamenti abbonamenti, StatoDistributore stato) {
		super( biglietti, abbonamenti);
		
		this.stato = stato;	
	}


}
