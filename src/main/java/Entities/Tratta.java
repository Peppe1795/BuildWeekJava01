package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tratta")
@Getter
@Setter
@NoArgsConstructor

public class Tratta {
	@Id
	@GeneratedValue
	private Long id;
	private String zonaDiPartenza;
	private String capolinea;
	private double tempoMedioDiPercorrenza;
	@OneToOne
	@JoinColumn(name = "tratta_id")
	private ParcoMezzi parcoMezzi;

	public Tratta(String zonaDiPartenza, String capolinea, double tempoMedioDiPercorrenza) {
		this.zonaDiPartenza = zonaDiPartenza;
		this.capolinea = capolinea;
		this.tempoMedioDiPercorrenza = tempoMedioDiPercorrenza;
	}

}
