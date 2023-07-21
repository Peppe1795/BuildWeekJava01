package Entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "abbonamenti")
@Getter
@Setter
@NoArgsConstructor
public class Abbonamenti {

	@Override
	public String toString() {
		return "Abbonamenti [id=" + id + ", periodicita=" + periodicita + ", dataEmissione=" + dataEmissione
				+ ", dataScadenza=" + dataScadenza + ", puntoVendita=" + puntoVendita + ", tessera=" + tessera + "]";
	}

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;

	private LocalDate dataEmissione = LocalDate.now();
	private LocalDate dataScadenza;

	@ManyToOne
	@JoinColumn(name = "punto_vendita_id")
	private Rivenditore puntoVendita;

	@ManyToOne
	private Tessera tessera;

	public Abbonamenti(Periodicita periodicita, LocalDate dataScadenza, Rivenditore puntoVendita, Tessera tessera) {
		this.periodicita = periodicita;
		this.dataScadenza = dataScadenza;
		this.puntoVendita = puntoVendita;
		this.tessera = tessera;
	}

}
