package Entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@NoArgsConstructor
public class Utente {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(mappedBy = "utente")
	private Tessera tessera;
	@OneToMany(mappedBy = "utente")
	List<Biglietto> biglietti;
	private String nome;
	private String cognome;
	private LocalDate dataDiNascita;

	public Utente(String nome, String cognome, LocalDate dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
	}

	@Override
	public String toString() {
		return "Utente [tessera=" + tessera + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita="
				+ dataDiNascita + "]";
	}
}
