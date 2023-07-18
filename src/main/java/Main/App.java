package Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import DAO.UtenteDAO;
import Entities.Utente;
import Util.JpaUtil;

public class App {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		UtenteDAO sd = new UtenteDAO(em);

		List<Utente> listaUtenti = new ArrayList<>();

		listaUtenti.add(new Utente("Giuseppe", "Petrucci", LocalDate.of(1995, 2, 17)));
		listaUtenti.add(new Utente("Luca", "Iannive", LocalDate.of(1980, 9, 23)));
		listaUtenti.add(new Utente("Ciccio", "Ciccio", LocalDate.of(1990, 2, 27)));
		listaUtenti.add(new Utente("Arianna", "Ciliegia", LocalDate.of(1986, 11, 14)));

		for (Utente utente : listaUtenti) {
			sd.save(utente);

		}

		em.close();

	}
}
