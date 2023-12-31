package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne
	@JoinColumn(name = "mezzo_id")
	private ParcoMezzi parcoMezzi;

	public Tratta(String zonaDiPartenza, String capolinea, double tempoMedioDiPercorrenza) {
		this.zonaDiPartenza = zonaDiPartenza;
		this.capolinea = capolinea;
		this.tempoMedioDiPercorrenza = tempoMedioDiPercorrenza;
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Zona di partenza: " + zonaDiPartenza + ", capolinea: " + capolinea
				+ ", tempo medio di percorrenza: " + tempoMedioDiPercorrenza;
	}

}
