package Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ParcoMezzi {

	@Id
	@SequenceGenerator(name = "my_sequence3", sequenceName = "my_sequence3", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence3")
	@Column(name = "Id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoMezzo tipoMezzo;

	@Enumerated(EnumType.STRING)
	private StatoMezzi statoMezzi;

	private LocalDate inizioStatoMezzo;
	private int capienza;

	@OneToOne(mappedBy = "parcoMezzi")
	private Manutenzione manutenzione;

	@OneToOne(mappedBy = "parcoMezzi")
	private Tratta tratta;

	@OneToMany(mappedBy = "puntoVidimazione")
	private List<Biglietti> biglietti = new ArrayList<>();

	public ParcoMezzi(TipoMezzo tipoMezzo, StatoMezzi statoMezzi, LocalDate inizioStatoMezzo, int capienza,
			Tratta tratta) {
		this.tipoMezzo = tipoMezzo;
		this.statoMezzi = statoMezzi;
		this.capienza = capienza;
		this.tratta = tratta;
	}

	@Override
	public String toString() {
		return "tipo mezzo:" + tipoMezzo + ", id:" + id + ", stato mezzi:" + statoMezzi + ", tratta:" + tratta;
	}

}
