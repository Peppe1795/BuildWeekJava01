package Entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tessera {
	@Id
	@SequenceGenerator(name = "my_sequence", sequenceName = "my_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
	@OneToOne(mappedBy = "tessera")
	private long id;

	private Utente utente;
	private LocalDate dataEmissione;
	private LocalDate dataScadenza = dataEmissione.plusYears(1);

	public Tessera(Utente utente, LocalDate dataEmissione) {
		this.utente = utente;
		this.dataEmissione = dataEmissione;
	}

}
