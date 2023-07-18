package Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ParcoMezzi {
	@Id
	@GeneratedValue
	@Column(name = "Id")
	private int id;
	@Enumerated(EnumType.STRING)
	private TipoMezzo tipoMezzo;
	@Enumerated(EnumType.STRING)
	private StatoMezzi statoMezzi;
	private int capienza;
	@OneToOne(mappedBy = "parcoMezzi")
	private Tratta tratta;
	@OneToMany
	private List<Biglietti> biglietti;

	public ParcoMezzi(TipoMezzo tipoMezzo, StatoMezzi statoMezzi, int capienza, Tratta tratta) {
		super();
		this.tipoMezzo = tipoMezzo;
		this.statoMezzi = statoMezzi;
		this.capienza = capienza;
		this.tratta = tratta;
	}

}
