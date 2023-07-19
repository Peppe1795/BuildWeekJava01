package Entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Manutenzione")
@Getter
@Setter
@NoArgsConstructor

public class Manutenzione {
	
	@Id
	@GeneratedValue
	@OneToOne
	@JoinColumn (name = "manutenzione_mezzo")
	private long id;
	
	private LocalDate dataInizioManutenzione;
	private LocalDate dataFineManutenzione;
	@Enumerated(EnumType.STRING)
	private Intervento tipoIntervento;
	
	public Manutenzione (Intervento tipoIntervento, LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione ) {
		
		this.tipoIntervento = tipoIntervento;
		this.dataInizioManutenzione = dataInizioManutenzione;
		this.dataFineManutenzione = dataFineManutenzione;
		
		
	}

}
