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

	public List<Biglietti> ricercaBigliettiPerData(LocalDate data) {
		return em.createQuery("SELECT p FROM Biglietti p WHERE p.dataEmissione = :dataemissione", Biglietti.class)
				.setParameter("dataemissione", data).getResultList();
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

	public List<Long> getBigliettiByUtente(Utente utente) {
		TypedQuery<Long> query = em.createQuery("SELECT b.id FROM Biglietti b WHERE b.utente = :utente", Long.class);
		query.setParameter("utente", utente);
		return query.getResultList();
	}
}
