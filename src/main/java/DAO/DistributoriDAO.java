package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Entities.Distributore;

public class DistributoriDAO {
	private final EntityManager em;

	public DistributoriDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Distributore distributore) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(distributore);

		t.commit();

		System.out.println("Elemento salvato correttamente");
	}

}
