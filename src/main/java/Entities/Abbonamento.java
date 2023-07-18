package Entities;

import java.time.LocalDate;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "abbonamenti")
@Getter
@Setter
@NoArgsConstructor
public class Abbonamento {

	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;
	private LocalDate dataEmissione = LocalDate.now();
	private LocalDate dataScadenza;
	//@ManyToOne
	//private PuntoVendita puntoVendita;
	@ManyToOne
	private Tessera tessera;
	
	public Abbonamento(Periodicita periodicita) {
		super();
		this.periodicita = periodicita;
	}
}
