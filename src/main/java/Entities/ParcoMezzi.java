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

	@OneToMany(mappedBy = "parcoMezzi")
	private List<Tratta> tratte = new ArrayList<>();

	@OneToMany(mappedBy = "puntoVidimazione")
	private List<Biglietti> biglietti = new ArrayList<>();;

	private LocalDate dataInizioManutenzione;
	private LocalDate dataFineManutenzione;
	private int giorniInServizio;
	private int giorniInManutenzione;

	public ParcoMezzi(TipoMezzo tipoMezzo, StatoMezzi statoMezzi, LocalDate inizioStatoMezzo, int capienza) {
		this.tipoMezzo = tipoMezzo;
		this.statoMezzi = statoMezzi;
		this.inizioStatoMezzo = inizioStatoMezzo;
		this.capienza = capienza;
	}

	public void avviaManutenzione(LocalDate dataInizioManutenzione, LocalDate dataFineManutenzione) {
		this.dataInizioManutenzione = dataInizioManutenzione;
		this.dataFineManutenzione = dataFineManutenzione;
		this.giorniInServizio = 0; // Resetta il conteggio dei giorni di servizio
		this.statoMezzi = StatoMezzi.IN_MANUTENZIONE;
	}

	public void completamentoManutenzione() {
		this.dataInizioManutenzione = null;
		this.dataFineManutenzione = null;
		this.statoMezzi = StatoMezzi.IN_SERVIZIO;
	}

	public boolean isInManutenzione() {
		return dataInizioManutenzione != null && dataFineManutenzione != null
				&& LocalDate.now().isAfter(dataInizioManutenzione) && LocalDate.now().isBefore(dataFineManutenzione);
	}

	// Metodo per verificare se il mezzo è attualmente in servizio
	public boolean isInServizio() {
		return !isInManutenzione();
	}

	// Metodo per aggiornare il conteggio dei giorni di servizio
	public void aggiornaGiorniServizio() {
		if (isInServizio()) {
			giorniInServizio++;
		}
	}

	// Metodo per aggiornare il conteggio dei giorni di manutenzione
	public void aggiornaGiorniManutenzione() {
		if (isInManutenzione()) {
			giorniInManutenzione++;
		}
	}

	// Metodo per ottenere il numero totale di giorni di servizio
	public int getGiorniTotaleServizio() {
		return giorniInServizio;
	}

	// Metodo per ottenere il numero totale di giorni di manutenzione
	public int getGiorniTotaleManutenzione() {
		return giorniInManutenzione;
	}

	public void addTratta(Tratta tratta) {
		if (tratta != null) {
			tratta.setParcoMezzi(this);
			tratte.add(tratta);
		}
	}

	public void addBiglietto(Biglietti biglietto) {
		if (biglietto != null) {
			biglietto.setDataVidimazione(LocalDate.now());
			biglietto.setVidimato(true);
			biglietto.setPuntoVidimazione(this);
			biglietti.add(biglietto);
		}
	}

	public Periodo getPeriodoServizio() {
		LocalDate dataInizio = inizioStatoMezzo;
		LocalDate dataFine = dataInizioManutenzione != null ? dataInizioManutenzione.minusDays(1) : LocalDate.now();
		return new Periodo(dataInizio, dataFine);
	}

	public Periodo getPeriodoManutenzione() {
		return new Periodo(dataInizioManutenzione, dataFineManutenzione);
	}

	@Override

	public String toString() {
		return "tipo mezzo:" + tipoMezzo + ", id:" + id + ", stato mezzi:" + statoMezzi + "Tratte: "
				+ tratte.toString();
	}
}
