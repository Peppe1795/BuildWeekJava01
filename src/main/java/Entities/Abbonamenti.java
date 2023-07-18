package Entities;

<<<<<<< HEAD
public class Abbonamenti {

=======
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
public class Abbonamenti {

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
	
	public Abbonamenti(Periodicita periodicita) {
		super();
		this.periodicita = periodicita;
	}
>>>>>>> Davide
}
