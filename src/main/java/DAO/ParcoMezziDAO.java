package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Entities.ParcoMezzi;
import Entities.StatoMezzi;

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
	
	public void update(ParcoMezzi parcoMezzi) {
		EntityTransaction t = em.getTransaction();
		t.begin();

        
        em.merge(parcoMezzi);

        t.commit();
	}

	public List<ParcoMezzi> visualizzaParcoMezzi() {
		return em.createQuery("SELECT r FROM ParcoMezzi r", ParcoMezzi.class).getResultList();
	}

	public ParcoMezzi ricercaMezziDaId(Long id) {
		return em.find(ParcoMezzi.class, id);
	}

	public void findMezzoById(Long id) {
		ParcoMezzi found = em.find(ParcoMezzi.class, id);

		if (found != null) {
			System.out.println(found);
		} else {
			System.out.println("Elemento con id " + id + " non trovato");
		}

	}

	public void updateStatoMezzo(Long id) {
		ParcoMezzi found = em.find(ParcoMezzi.class, id);
		EntityTransaction t = em.getTransaction();
		if (found != null) {
			t.begin();
			if (found.getStatoMezzi() == StatoMezzi.IN_MANUTENZIONE) {
				found.setStatoMezzi(StatoMezzi.IN_SERVIZIO);
			} else if (found.getStatoMezzi() == StatoMezzi.IN_SERVIZIO) {
				found.setStatoMezzi(StatoMezzi.IN_MANUTENZIONE);
			}
			t.commit();
			System.out.println(found);
		}

	}
}
