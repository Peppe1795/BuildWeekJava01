package DAO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Entities.Abbonamenti;
import Entities.Rivenditore;
import Entities.Tratta;
import Entities.Utente;

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
		 return em.createQuery("SELECT r FROM Tratta r", Tratta.class)
	             .getResultList();
    }
}
