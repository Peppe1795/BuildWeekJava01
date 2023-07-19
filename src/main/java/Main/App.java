package Main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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

	@SuppressWarnings("unused")
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

		Rivenditore rivenditore = null;
		Tessera tessera = null;
		/*
		 * Rivenditore rivenditore1 = new Rivenditore(); rb.save(rivenditore1);
		 * 
		 * Distributore distributore1 = new Distributore(StatoDistributore.ATTIVO);
		 * Distributore distributore2 = new Distributore(StatoDistributore.DISATTIVO);
		 * 
		 * ds.save(distributore2); ds.save(distributore1);
		 * 
		 * Tratta tratta1 = new Tratta("Napoli", "Roma", 3.15); Tratta tratta2 = new
		 * Tratta("Bergamo", "Milano", 2.45);
		 * 
		 * ParcoMezzi tram1 = new ParcoMezzi(TipoMezzo.TRAM, StatoMezzi.IN_SERVIZIO,
		 * LocalDate.now(), 60, tratta1); ParcoMezzi autobus1 = new
		 * ParcoMezzi(TipoMezzo.AUTOBUS, StatoMezzi.IN_SERVIZIO, LocalDate.now(), 100,
		 * tratta2);
		 * 
		 * tr.save(tratta1); tr.save(tratta2); pm.save(autobus1); pm.save(tram1);
		 */
		Scanner scanner = new Scanner(System.in);
		Utente utente = null;

		while (utente == null) {
		    System.out.println("Sei già registrato? (y/n)");
		    String risposta = scanner.next();

		    if (risposta.equals("y")) {
		        System.out.print("Inserisci il tuo ID: ");
		        long id = Long.parseLong(scanner.next());
		        Utente utenteEsistente = sd.ricercaUtenteDaId(id);

		        if (utenteEsistente != null) {
		            utente = utenteEsistente;
		            System.out.println("Utente loggato");
		        } else {
		            System.out.println("ID non valido. Riprova.");
		        }
		    } else if (risposta.equals("n")) {
		        System.out.print("Inserisci il nome dell'utente: ");
		        String nomeUtente = scanner.next();
		        System.out.print("Inserisci il cognome dell'utente: ");
		        String cognomeUtente = scanner.next();
		        System.out.print("Inserisci la data di nascita dell'utente (formato: anno-mese-giorno): ");
		        String dataNascitaUtente = scanner.next();

		        LocalDate dataNascita;
		        dataNascita = LocalDate.parse(dataNascitaUtente);

		        Utente nuovoUtente = new Utente(nomeUtente, cognomeUtente, dataNascita);
		        sd.save(nuovoUtente);

		        utente = nuovoUtente;
		    } else {
		        System.out.println("Scelta non valida. Riprova.");
		    }
		}
		int scelta = 0;

		while (scelta != 3) {
			System.out.println("\nScegli un'azione da eseguire:");
			System.out.println("1. Vai al distributore o al venditore");
			System.out.println("2. Sali su un mezzo");
			System.out.println("3. Esci");

			scelta = scanner.nextInt();

			switch (scelta) {
			
			case 1: 
				Boolean sceltaVenditore = false;
				while(!sceltaVenditore) {
				System.out.println("Rivenditori o distributori nelle vicinanze:");
				List<Rivenditore> AllRivenditori = rb.visualizzaRivenditori();
				for (Rivenditore rivenditori : AllRivenditori) {
					System.out.println(rivenditore);
				}
				System.out.print("Inserisci ID: ");
				long id = Long.parseLong(scanner.next());
				rivenditore = rb.ricercaRivenditoreDaId(id);
				if (rivenditore instanceof Distributore) {
					Distributore distributore = (Distributore) rivenditore;
					if(distributore.getStato()== StatoDistributore.DISATTIVO) {
						System.out.println("Distributore non attivo. Vai da un altro venditore.");
						
					} else {
						System.out.println("Hai scelto un distributore");
						sceltaVenditore = true;
					}
				} else {
					
					System.out.println("Hai scelto un rivenditore");
					sceltaVenditore = true;
				}
				}
				System.out.println("Vuoi un biglietto o un abbonamento? (b/a)");
				String risposta2 = scanner.next();
				if(risposta2.equals("b")) {
					
					System.out.println("Quanti biglietti vuoi?");
					int numeroBiglietti = scanner.nextInt();
					for(int i = 0 ; i < numeroBiglietti; i++) {
						
						Biglietti biglietto= new Biglietti();
						utente.getBiglietti().add(biglietto);
						rivenditore.getBiglietti().add(biglietto);
						bi.save(biglietto);						
					}
					System.out.println("Acquisto effettuato.");
					
				} else if(risposta2.equals("a")) {
					System.out.println("Per comprare un abbonamento devi possedere una tessera");
					System.out.println("Hai gia una tessera? (y/n)");
					String risposta3 = scanner.next();
					if (risposta3.equals("y")) {
						
						while(tessera == null) {
						System.out.print("Inserisci l'ID della tessera: ");
						long id = Long.parseLong(scanner.next());
						tessera = ts.ricercaTesseraDaId(id);
								if (tessera == null) {
									System.out.println("Tessera non trovata");				
								} else if (tessera.getDataScadenza().isBefore(LocalDate.now())) {
									System.out.println("Tessera scaduta");
									tessera.setDataEmissione(LocalDate.now());
									System.out.println("Rinnovo effettuato");
								} else {
									System.out.println("Tessera valida");
								}
						}
					} else if (risposta3.equals("n")) {
						
						tessera = new Tessera(utente);
					}
					
				}
				break;
			
			
			case 2: break;
			
			
			case 3: break;
			}
		}

		scanner.close();
		em.close();

	}
}
