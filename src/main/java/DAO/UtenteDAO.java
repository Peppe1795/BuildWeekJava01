package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Entities.Utente;

public class UtenteDAO {

	private final EntityManager em;

	public UtenteDAO(EntityManager em) {

		this.em = em;
	}

	public void save(Utente s) {

		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(s);

		t.commit();

		System.out.println("Elemento Salvato correttamente!!!!!");
	}
}
