package Entities;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Rivenditore extends Vendita {

	public Rivenditore(Biglietti biglietti, Abbonamenti abbonamenti) {
		super( biglietti, abbonamenti);
	
	}

	
	
	
	
	

}
