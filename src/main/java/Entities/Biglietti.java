package Entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "biglietti")
@Getter
@Setter
@NoArgsConstructor
public class Biglietti {

	@Id
	@GeneratedValue
	private Long id;
	private LocalDate dataEmissione = LocalDate.now();
	private LocalDate dataVidimazione;
	private boolean vidimato = false;
	@ManyToOne
	private Rivenditore puntoVendita;
	@ManyToOne
	private Utente utente;

	// @ManyToOne
	// private ParcoMezzi puntoVidimazione;

	public Biglietti(Rivenditore puntoVendita, Utente utente) {
		this.puntoVendita = puntoVendita;
		this.utente = utente;
	}

}
