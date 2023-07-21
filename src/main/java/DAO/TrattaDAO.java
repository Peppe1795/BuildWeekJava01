package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Entities.Tratta;

public class TrattaDAO {
	private final EntityManager em;

	public TrattaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Tratta tratta) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(tratta);

		t.commit();

		System.out.println("Elemento salvato correttamente");

	}

	public List<Tratta> ricercaTratteDaId(Long id) {
		return em.createQuery("SELECT r FROM Tratta r", Tratta.class).getResultList();
	}

	public Tratta ricercaTrattaDaId(Long id) {
		return em.find(Tratta.class, id);
	}

}
