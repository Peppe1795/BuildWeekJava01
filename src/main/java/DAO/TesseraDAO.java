package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Entities.Tessera;
import Entities.Utente;

public class TesseraDAO {
	private final EntityManager em;

	public TesseraDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Tessera tessera) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(tessera);

		t.commit();

		System.out.println("Elemento salvato correttamente");

	}
	
	public Tessera ricercaTesseraDaId(Long id) {
        return em.find(Tessera.class, id);
    }
}
