package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Entities.ParcoMezzi;
import Entities.Utente;

public class ParcoMezziDAO {
	private final EntityManager em;

	public ParcoMezziDAO(EntityManager em) {
		this.em = em;
	}

	public void save(ParcoMezzi parcoMezzi) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(parcoMezzi);

		t.commit();

		System.out.println("Elemento salvato correttamente");

	}
	
	public List<ParcoMezzi> visualizzaParcoMezzi() {
	    return em.createQuery("SELECT * FROM parcomezzi", ParcoMezzi.class)    
	            .getResultList();
	}
	public ParcoMezzi ricercaMezziDaId(Long id) {
        return em.find(ParcoMezzi.class, id);
    }
	
}
