package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Entities.PuntoVendita;

public class VenditaDAO {
	private final EntityManager em;

	public VenditaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(PuntoVendita vendita) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(vendita);

		t.commit();

		System.out.println("Elemento salvato correttamente");

	}
}
