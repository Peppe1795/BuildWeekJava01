package Main;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import DAO.AbbonamentoDAO;
import DAO.BigliettiDAO;
import DAO.TesseraDAO;
import DAO.UtenteDAO;
import Entities.Abbonamenti;
import Entities.Biglietti;
import Entities.Distributore;
import Entities.Periodicita;
import Entities.Rivenditore;
import Entities.StatoDistributore;
import Entities.Tessera;
import Entities.Utente;
import Util.JpaUtil;

public class App {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		UtenteDAO sd = new UtenteDAO(em);
		TesseraDAO ts = new TesseraDAO(em);
		AbbonamentoDAO ab = new AbbonamentoDAO(em);
		BigliettiDAO bi = new BigliettiDAO(em);

		Utente utente1 = new Utente("Giuseppe", "Petrucci", LocalDate.of(1995, 2, 17));
		Utente utente2 = new Utente("Luca", "Iannive", LocalDate.of(1980, 9, 23));
		Utente utente3 = new Utente("Ciccio", "Ciccio", LocalDate.of(1990, 2, 27));
		Utente utente4 = new Utente("Arianna", "Ciliegia", LocalDate.of(1986, 11, 14));

		Tessera tessera1 = new Tessera(utente1);

		Rivenditore rivenditore1 = new Rivenditore();

		Abbonamenti abbonamento1 = new Abbonamenti(Periodicita.SETTIMANALE, rivenditore1, tessera1);
		Abbonamenti abbonamento2 = new Abbonamenti(Periodicita.MENSILE, rivenditore1, tessera1);

		Biglietti biglietto1 = new Biglietti(rivenditore1, utente2);

		Distributore distributore1 = new Distributore(StatoDistributore.ATTIVO);
		Distributore distributore2 = new Distributore(StatoDistributore.DISATTIVO);

		sd.save(utente1);
		sd.save(utente2);
		ts.save(tessera1);
		ab.save(abbonamento2);
		ab.save(abbonamento1);
		bi.save(biglietto1);

		em.close();

	}
}
