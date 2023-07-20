package Main;

import java.time.LocalDate;
import java.util.ArrayList;
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
		String risposta = null;
		LocalDate dataScadenza = null;
		
		Scanner scanner = new Scanner(System.in);
		Utente admin = new Utente("Admin", "Admin", LocalDate.now());
		sd.save(admin);
		Utente utente = null;

		while (utente == null) {
			System.out.println("Sei già registrato? (y/n)");
			risposta = scanner.next();

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
				System.out.println("Utente aggiunto con successo.");
				utente = nuovoUtente;
			} else if (risposta.equals("admin")) {
				utente = admin;

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
			if (risposta.equals("admin")) {
				System.out.println("4: Recupera vendite biglietti");
				System.out.println("5: Recupera vendite abbonamenti");
				System.out.println("6: Cambia stato mezzo");
				System.out.println("7: Recupera stati di un mezzo");
				System.out.println("8: Recupera biglietti vidimati di un mezzo");
				System.out.println("9: Aggiungi tratta ad un mezzo");
				System.out.println("10: Recupera tratte di un mezzo");
			}

			scelta = scanner.nextInt();

			switch (scelta) {

			case 1:
				Boolean sceltaVenditore = false;
				while (!sceltaVenditore) {
					System.out.println("Rivenditori o distributori nelle vicinanze:");
					List<Rivenditore> AllRivenditori = rb.visualizzaRivenditori();
					for (Rivenditore rivenditori : AllRivenditori) {
						System.out.println(rivenditori);
					}
					System.out.print("Inserisci ID: ");
					long id = Long.parseLong(scanner.next());
					rivenditore = rb.ricercaRivenditoreDaId(id);
					if (rivenditore instanceof Distributore) {
						Distributore distributore = (Distributore) rivenditore;
						if (distributore.getStato() == StatoDistributore.DISATTIVO) {
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
				if (risposta2.equals("b")) {

					System.out.println("Quanti biglietti vuoi?");
					int numeroBiglietti = scanner.nextInt();
					List<Biglietti> biglietti = new ArrayList<>();
					List<Biglietti> biglietti1 = new ArrayList<>();
					for (int i = 0; i < numeroBiglietti; i++) {

						Biglietti biglietto = new Biglietti();

						biglietto.setPuntoVendita(rivenditore);
						bi.save(biglietto);

						biglietti = utente.getBiglietti();
						biglietti.add(biglietto);

						biglietti1 = rivenditore.getBiglietti();
						biglietti1.add(biglietto);

					}
					utente.setBiglietti(biglietti);
					rivenditore.setBiglietti(biglietti);
					System.out.println("Acquisto effettuato.");

				} else if (risposta2.equals("a")) {
					System.out.println("Per comprare un abbonamento devi possedere una tessera");
					while (tessera == null) {
						System.out.println("Hai gia una tessera? (y/n)");
						String risposta3 = scanner.next();
						if (risposta3.equals("y")) {

							System.out.print("Inserisci l'ID della tessera: ");
							long id = Long.parseLong(scanner.next());
							Tessera tessera1 = ts.ricercaTesseraDaId(id);
							if (tessera1 == null) {
								System.out.println("Tessera non trovata");

							} else if (tessera1.getDataScadenza().isBefore(LocalDate.now())) {
								System.out.println("Tessera scaduta");
								tessera1.setDataEmissione(LocalDate.now());
								System.out.println("Rinnovo effettuato");
								tessera = tessera1;
							} else {
								System.out.println("Tessera valida");
								tessera = tessera1;
							}
						} else if (risposta3.equals("n")) {
							tessera = new Tessera(utente);
							ts.save(tessera);
							System.out.println("Tessera creata correttamente.");
						}

					}
					System.out.println("Che tipo di abbonamento vuoi? SETTIMANALE/MENSILE");
					String periodicitaAbbonamento = scanner.next();

					Periodicita periodicita = null;
					while (periodicita == null) {
						switch (periodicitaAbbonamento.toUpperCase()) {
						case "SETTIMANALE":
							periodicita = Periodicita.SETTIMANALE;
							dataScadenza = LocalDate.now().plusDays(7);
							break;
						case "MENSILE":
							periodicita = Periodicita.MENSILE;
							dataScadenza = LocalDate.now().plusMonths(1);
							break;
						default:
							System.out.println("Periodicità non valida.");
							periodicita = null;
							break;
						}
					}

					Abbonamenti abbonamento = new Abbonamenti(periodicita, dataScadenza, rivenditore, tessera);
					abbonamento.setPuntoVendita(rivenditore);
					ab.save(abbonamento);
					List<Abbonamenti> abbonamenti = new ArrayList<Abbonamenti>();
					abbonamenti = rivenditore.getAbbonamenti();
					abbonamenti.add(abbonamento);
					rivenditore.setAbbonamenti(abbonamenti);
					tessera.setAbbonamento(abbonamento);

					System.out.println("Abbonamento effettuato");
				}
				break;

			case 2:
				System.out.println("Mezzi in stazione: ");
				List<ParcoMezzi> mezziPresenti = pm.visualizzaParcoMezzi();
				for (ParcoMezzi mezzi : mezziPresenti) {
					System.out.println(mezzi);
				}
				System.out.print("Inserisci l'ID del mezzo: ");
				long id = Long.parseLong(scanner.next());
				ParcoMezzi mezzo = pm.ricercaMezziDaId(id);

				if (mezzo == null) {
					System.out.println("Mezzo non trovato. Torna alla lista dei mezzi.");
					break;
				}

				if (mezzo.getStatoMezzi() == StatoMezzi.IN_MANUTENZIONE) {
					System.out.println("Il mezzo è attualmente in manutenzione. Torna alla lista dei mezzi.");
					break;
				}

				if (mezzo.getTipoMezzo() == TipoMezzo.AUTOBUS) {
					System.out.println("Sei salito su un autobus");
				} else {
					System.out.println("Sei salito su un tram");
				}

				Boolean accessoServizioMezzo = false;
				while (!accessoServizioMezzo) {
					System.out.println("Usare un biglietto o un abbonamento oppure esci  (b/a/e)");
					String rispostaSulMezzo = scanner.next();
					if (rispostaSulMezzo.equals("b")) {
						System.out.print("Inserire l'ID del biglietto: ");
						long idBiglietto = Long.parseLong(scanner.next());
						Biglietti biglietto = bi.ricercaBigliettoDaId(idBiglietto);
						try {
							if (biglietto.isVidimato() == false) {
								System.out.println("Biglietto trovato. Puoi partire.");
								biglietto.setVidimato(true);
								biglietto.setDataVidimazione(LocalDate.now());
								biglietto.setPuntoVidimazione(mezzo);
								List<Biglietti> biglietti = mezzo.getBiglietti();
								biglietti.add(biglietto);
								mezzo.setBiglietti(biglietti);
							}
						} catch (Exception e) {
							System.out.println("Biglietto non valido");
						}
					} else if (rispostaSulMezzo.equals("a")) {
						System.out.print("Inserire l'ID della Tessera: ");
						long idTessera = Long.parseLong(scanner.next());
						Tessera tesseraPerVerifica = ts.ricercaTesseraDaId(idTessera);
						if (tesseraPerVerifica == null) {
							System.out.println("Tessera non trovata");
						} else if (tesseraPerVerifica.getDataScadenza().isBefore(LocalDate.now())) {
							System.out.println(
									"Tessera scaduta, usare un biglieto o rinnovare la tessera da un rivenditore.");
						} else {
							Abbonamenti abbonamento = tesseraPerVerifica.getAbbonamento();
							if (abbonamento.getDataScadenza().isBefore(LocalDate.now())) {
								System.out.println(
										"Abbonamento scaduto, usare un biglietto o fare un altro abbonamento da un rivenditore.");
							} else if (abbonamento == null) {
								System.out.println("Nessun abbonamento trovato");
							} else {
								System.out.println("Ciao");
								accessoServizioMezzo = true;
							}
						}
					} else if (rispostaSulMezzo.equals("e")) {
						System.out.println("Sei sceso dal mezzo.");
						break;
					} else {
						System.out.println("Scelta non valida");
					}
				}
				break;
			case 3:
				System.out.println("Uscita...");
				scanner.close();
				em.close();

				break;

			case 4:
				if (risposta.equals("admin")) {

					System.out.println("Inserisci l'ID di un punto vendita: ");
					List<Rivenditore> AllRivenditori = rb.visualizzaRivenditori();
					for (Rivenditore rivenditori : AllRivenditori) {
						System.out.println(rivenditori);
					}
					long idPuntoVendita = Long.parseLong(scanner.next());
					rivenditore = rb.ricercaRivenditoreDaId(idPuntoVendita);
					List<Biglietti> biglietti = rivenditore.getBiglietti();
					if (biglietti.size() > 0) {
						for (Biglietti biglietto : biglietti) {
							System.out.println(biglietto);
						}
					} else {
						System.out.println("Questo venditore non ha venduto biglietti");
					}
				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 5:
				if (risposta.equals("admin")) {

					System.out.println("Inserisci l'ID di un punto vendita: ");
					List<Rivenditore> AllRivenditori = rb.visualizzaRivenditori();
					for (Rivenditore rivenditori : AllRivenditori) {
						System.out.println(rivenditori);
					}
					long idPuntoVendita = Long.parseLong(scanner.next());
					rivenditore = rb.ricercaRivenditoreDaId(idPuntoVendita);
					List<Abbonamenti> abbonamenti = rivenditore.getAbbonamenti();
					if (abbonamenti.size() > 0) {
						for (Abbonamenti abbonamento : abbonamenti) {
							System.out.println(abbonamento);
						}
					} else {
						System.out.println("Questo venditore non ha venduto abbonamenti");
					}
				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 6:
				if (risposta.equals("admin")) {

				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 7:
				if (risposta.equals("admin")) {

				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 8:
				if (risposta.equals("admin")) {
					System.out.println("Inserisci l'ID di un mezzo: ");
					List<ParcoMezzi> mezzi = pm.visualizzaParcoMezzi();
					for (ParcoMezzi mezzo1 : mezzi) {
						System.out.println(mezzo1);
					}
					long idMezzo = Long.parseLong(scanner.next());
					mezzo = pm.ricercaMezziDaId(idMezzo);
					List<Biglietti> bigliettiVidimati = mezzo.getBiglietti();
					if (bigliettiVidimati.size() > 0) {
						for (Biglietti bigliettoVidimato : bigliettiVidimati) {
							System.out.println(bigliettoVidimato);
						}
					} else {
						System.out.println("Questo mezzo non ha vidimato alcun biglietto");
					}
				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 9:
				if (risposta.equals("admin")) {

				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 10:
				if (risposta.equals("admin")) {

				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;
			}
		}

	}
}
