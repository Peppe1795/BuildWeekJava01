package Main;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import DAO.AbbonamentoDAO;
import DAO.BigliettiDAO;
import DAO.DistributoriDAO;
import DAO.ParcoMezziDAO;
import DAO.RivenditoreDAO;
import DAO.TesseraDAO;
import DAO.TrattaDAO;
import DAO.UtenteDAO;
import Entities.Abbonamenti;
import Entities.Biglietti;
import Entities.Distributore;
import Entities.ParcoMezzi;
import Entities.Periodicita;
import Entities.Rivenditore;
import Entities.StatoDistributore;
import Entities.StatoMezzi;
import Entities.Tessera;
import Entities.TipoMezzo;
import Entities.Tratta;
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
		DistributoriDAO ds = new DistributoriDAO(em);
		RivenditoreDAO rb = new RivenditoreDAO(em);
		TrattaDAO tr = new TrattaDAO(em);
		ParcoMezziDAO pm = new ParcoMezziDAO(em);

		Utente utente1 = new Utente("Giuseppe", "Petrucci", LocalDate.of(1995, 2, 17));
		Utente utente2 = new Utente("Luca", "Iannive", LocalDate.of(1980, 9, 23));
		Utente utente3 = new Utente("Ciccio", "Ciccio", LocalDate.of(1990, 2, 27));
		Utente utente4 = new Utente("Arianna", "Ciliegia", LocalDate.of(1986, 11, 14));

		Tessera tessera1 = new Tessera(utente1);

		Rivenditore rivenditore1 = new Rivenditore();
		rb.save(rivenditore1);
		Abbonamenti abbonamento1 = new Abbonamenti(Periodicita.SETTIMANALE, rivenditore1, tessera1);
		Abbonamenti abbonamento2 = new Abbonamenti(Periodicita.MENSILE, rivenditore1, tessera1);

		Biglietti biglietto1 = new Biglietti(rivenditore1);

		Distributore distributore1 = new Distributore(StatoDistributore.ATTIVO);
		Distributore distributore2 = new Distributore(StatoDistributore.DISATTIVO);

		sd.save(utente1);
		sd.save(utente2);
		ts.save(tessera1);
		ab.save(abbonamento2);
		ab.save(abbonamento1);
		bi.save(biglietto1);
		ds.save(distributore2);
		ds.save(distributore1);

		Tratta tratta1 = new Tratta("Napoli", "Roma", 3.15);
		Tratta tratta2 = new Tratta("Bergamo", "Milano", 2.45);

		ParcoMezzi tram1 = new ParcoMezzi(TipoMezzo.TRAM, StatoMezzi.IN_SERVIZIO, 60, tratta1);
		ParcoMezzi autobus1 = new ParcoMezzi(TipoMezzo.AUTOBUS, StatoMezzi.IN_SERVIZIO, 100, tratta2);

		tr.save(tratta1);
		tr.save(tratta2);
		pm.save(autobus1);
		pm.save(tram1);

		em.close();

	}
}
