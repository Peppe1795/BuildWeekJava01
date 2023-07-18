package Entities;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.*;

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
	//@ManyToOne
	//private PuntoVendita puntoVendita;
	@ManyToOne
	private Utente utente;
    //@ManyToOne
    //private ParcoMezzi puntoVidimazione;

}
