package Entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo punto vendita")
@Table(name = "Punti vendita")
@NoArgsConstructor
public class PuntoVendita {

	@Id
	@SequenceGenerator(name = "my_sequence", sequenceName = "my_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
	private long id;

	private Biglietti biglietti;
	private Abbonamenti abbonamenti;
	static int numeroBiglietti;
	static int numeroAbbonamenti;

	public PuntoVendita(Biglietti biglietti, Abbonamenti abbonamenti) {
		super();
		this.biglietti = biglietti;
		this.abbonamenti = abbonamenti;
	}

}
