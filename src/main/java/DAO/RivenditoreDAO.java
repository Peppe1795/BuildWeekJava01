package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Entities.Rivenditore;

public class RivenditoreDAO {
	private final EntityManager em;

	public RivenditoreDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Rivenditore rivenditore) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(rivenditore);

		t.commit();

		System.out.println("Elemento salvato correttamente");

	}
}
