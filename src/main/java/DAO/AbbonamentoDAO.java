package DAO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Entities.Abbonamenti;

public class AbbonamentoDAO {
	private final EntityManager em;

	public AbbonamentoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Abbonamenti abbonamento) {
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(abbonamento);

		t.commit();

		System.out.println("Elemento salvato correttamente");

	}

	public Abbonamenti ricercaAbbonamentoDaId(Long id) {
		return em.find(Abbonamenti.class, id);
	}

	public List<Abbonamenti> ricercaAbbonamentiPerData(LocalDate data) {
		return em.createQuery("SELECT p FROM Abbonamenti p WHERE p.dataEmissione =:dataemissione", Abbonamenti.class)
				.setParameter("dataemissione", data).getResultList();
	}
}
