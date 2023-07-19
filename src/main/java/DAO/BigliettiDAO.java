package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
