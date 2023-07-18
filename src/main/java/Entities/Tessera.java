package Entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tessere")
@Getter
@Setter
@NoArgsConstructor
public class Tessera {

	@Id
	@SequenceGenerator(name = "my_sequence", sequenceName = "my_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
	private Long id;

	@OneToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;
	@OneToMany(mappedBy = "tessera")
	private List<Abbonamenti> abbonamenti;
	private LocalDate dataEmissione = LocalDate.now();
	private LocalDate dataScadenza = dataEmissione.plusYears(1);

	public Tessera(Utente utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "Tessera [id=" + id + ", dataEmissione=" + dataEmissione + ", dataScadenza=" + dataScadenza + "]";
	}
}