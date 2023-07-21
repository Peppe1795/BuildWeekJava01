package DAO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Entities.Biglietti;
import Entities.Utente;

public class BigliettiDAO {
	private final EntityManager em;

	public BigliettiDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Biglietti biglietti) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(biglietti);

		t.commit();

		System.out.println("Elemento salvato correttamente");

	}

	public Biglietti ricercaBigliettoDaId(Long id) {
		return em.find(Biglietti.class, id);
	}

	public void vidimazione(List<Long> listaId) {
		em.getTransaction().begin();

		for (Long bigliettoId : listaId) {
			Biglietti biglietto = em.find(Biglietti.class, bigliettoId);

			if (biglietto != null) {
				biglietto.setVidimato(true);
				System.out.println("Biglietto con id " + bigliettoId + " vidimato.");
			} else {
				System.out.println("Elemento con id " + bigliettoId + " non trovato");
			}
		}

		em.getTransaction().commit();
	}

	public List<Biglietti> ricercaBigliettiPerData(LocalDate data) {
		return em.createQuery("SELECT b FROM Biglietti b WHERE b.dataEmissione = :dataEmissione", Biglietti.class)
				.setParameter("dataEmissione", data).getResultList();
	}

	public int getNumeroBigliettiPerData(LocalDate data) {
		List<Biglietti> bigliettiPerData = ricercaBigliettiPerData(data);
		return bigliettiPerData.size();
	}

	public List<Biglietti> getBigliettiByUtente(Utente utente) {
		TypedQuery<Biglietti> query = em.createQuery("SELECT b FROM Biglietti b WHERE b.utente = :utente",
				Biglietti.class);
		query.setParameter("utente", utente);
		return query.getResultList();
	}

}
