package Main;

import java.time.LocalDate;
import java.util.Collections;
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
import DAO.TrattaService;
import DAO.UtenteDAO;
import Entities.Abbonamenti;
import Entities.Biglietti;
import Entities.Distributore;
import Entities.ParcoMezzi;
import Entities.Periodicita;
import Entities.Periodo;
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
		TrattaService trattaService = new TrattaService();
		ParcoMezzi mezzop = new ParcoMezzi(TipoMezzo.AUTOBUS, StatoMezzi.IN_SERVIZIO, LocalDate.now(), 50);
		ParcoMezzi mezzop2 = new ParcoMezzi(TipoMezzo.TRAM, StatoMezzi.IN_SERVIZIO, LocalDate.now(), 50);
		ParcoMezzi mezzop3 = new ParcoMezzi(TipoMezzo.AUTOBUS, StatoMezzi.IN_MANUTENZIONE, LocalDate.now(), 50);

		LocalDate dataInizioManutenzioneMezzoP = LocalDate.of(2023, 7, 20);
		LocalDate dataFineManutenzioneMezzoP = LocalDate.of(2023, 7, 25);
		mezzop.avviaManutenzione(dataInizioManutenzioneMezzoP, dataFineManutenzioneMezzoP);
		LocalDate dataInizioManutenzioneMezzoTram = LocalDate.of(2023, 7, 18);
		LocalDate dataFineManutenzioneMezzoTram = LocalDate.of(2023, 7, 22);
		mezzop2.avviaManutenzione(dataInizioManutenzioneMezzoTram, dataFineManutenzioneMezzoTram);
		pm.save(mezzop);
		pm.save(mezzop2);
		pm.save(mezzop3);
		Rivenditore ok = new Rivenditore();
		rb.save(ok);
		Distributore gg = new Distributore(StatoDistributore.ATTIVO);
		rb.save(gg);
		Rivenditore rivenditore = null;
		Tessera tessera = null;
		String risposta = null;
		LocalDate dataScadenza = null;
		Tratta tratta1 = new Tratta("Palermo", "Roma", 12.5);
		Tratta tratta4 = new Tratta("Milano", "Bologna", 1.5);
		Tratta tratta3 = new Tratta("Genova", "Napoli", 9.2);
		tr.save(tratta1);
		mezzop.addTratta(tratta1);
		tr.save(tratta4);
		mezzop2.addTratta(tratta4);
		tr.save(tratta3);
		mezzop3.addTratta(tratta3);
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
				System.out.println("11: Recupera biglietti di una determinata data");
				System.out.println("12: Recupera abbonamenti di una determinata data");
				System.out.println("13: Verifica Validità abbonamento inserendo l'ID utente");
				System.out.println("14: Verifica periodi di servizio dei mezzi di trasporto");
				System.out.println("15: Verifica numero di volte che un mezzo percorre una tratta");
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

					for (int i = 0; i < numeroBiglietti; i++) {
						Biglietti biglietto = new Biglietti();

						System.out.println("ID Utente: " + utente.getId());
						System.out.println("ID Rivenditore: " + rivenditore.getId());

						utente.addBiglietto(biglietto);
						rivenditore.addBiglietto(biglietto);
						bi.save(biglietto);
					}

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

					ab.save(abbonamento);

					rivenditore.addAbbonamento(abbonamento);
					tessera.setAbbonamenti(Collections.singletonList(abbonamento));

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

				System.out.println("Scegli una tratta inserendo l'ID: ");
				System.out.println(mezzo.getTratte().toString());
				long idTratta = Long.parseLong(scanner.next());
				Tratta tratta2 = tr.ricercaTrattaDaId(idTratta);
				System.out.println("Hai scelto la tratta: " + tratta2);

				Boolean accessoServizioMezzo = false;
				while (!accessoServizioMezzo) {
					System.out.println("Usare un biglietto o un abbonamento oppure esci  (b/a/e)");
					String rispostaSulMezzo = scanner.next();
					if (rispostaSulMezzo.equals("b")) {
						System.out.println("Biglietti a disposizione: ");
						List<Biglietti> listaBigliettiId = bi.getBigliettiByUtente(utente);
						listaBigliettiId.forEach(e -> System.out.println("id: " + e));

						System.out.print("Inserire l'ID del biglietto: ");
						long idBiglietto = Long.parseLong(scanner.next());
						Biglietti biglietto = bi.ricercaBigliettoDaId(idBiglietto);

						if (biglietto != null && !biglietto.isVidimato()) {
							System.out.println(
									"Per salire sul mezzo devi vidimare il biglietto, seleziona y per continuare");
							String risposta5 = scanner.next();

							if (risposta5.equals("y")) {
								bi.vidimazione(Collections.singletonList(idBiglietto));
								System.out.println("Biglietto trovato. Puoi partire. Buon viaggio.");
								mezzo.addBiglietto(biglietto);
							}
						} else if (biglietto == null) {
							System.out.println("Biglietto non trovato");
						} else if (biglietto.isVidimato()) {
							System.out.println(
									"Il biglietto con ID " + idBiglietto + " è già stato vidimato e non è più valido.");
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
							List<Abbonamenti> abbonamenti = tesseraPerVerifica.getAbbonamenti();
							if (abbonamenti.isEmpty()) {
								System.out.println("Nessun abbonamento trovato");
							} else {
								for (Abbonamenti abbonamento : abbonamenti) {
									System.out.println("Abbonamento: " + abbonamento);
									if (abbonamento.getDataScadenza().isBefore(LocalDate.now())) {
										System.out.println(
												"Abbonamento scaduto, usare un biglietto o fare un altro abbonamento da un rivenditore.");
									} else {
										System.out.println("Abbonamento valido. Puoi usufruire dei servizi.");
										// Esegui altre operazioni specifiche sull'abbonamento valido, se necessario.
									}
								}
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
					System.out.println("Inserisci l'ID di un mezzo: ");
					List<ParcoMezzi> mezzi = pm.visualizzaParcoMezzi();
					for (ParcoMezzi mezzo1 : mezzi) {
						System.out.println(mezzo1);
					}
					long idMezzo = Long.parseLong(scanner.next());
					pm.updateStatoMezzo(idMezzo);
				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 7:
				if (risposta.equals("admin")) {

					System.out.println("Inserisci l'ID di un mezzo: ");
					List<ParcoMezzi> mezzi = pm.visualizzaParcoMezzi();
					for (ParcoMezzi mezzo1 : mezzi) {
						System.out.println(mezzo1);
					}
					long idMezzo = Long.parseLong(scanner.next());
					mezzo = pm.ricercaMezziDaId(idMezzo);

					// Avvia la manutenzione del mezzo (esempio)
					LocalDate dataInizioManutenzione = LocalDate.of(2023, 7, 20);
					LocalDate dataFineManutenzione = LocalDate.of(2023, 7, 25);
					mezzo.avviaManutenzione(dataInizioManutenzione, dataFineManutenzione);

					// Aggiorna il conteggio dei giorni di servizio e manutenzione (esempio)
					mezzo.aggiornaGiorniServizio();
					mezzo.aggiornaGiorniManutenzione();

					// Completamento della manutenzione e rimessa in servizio del mezzo (esempio)
					mezzo.completamentoManutenzione();

					// Aggiorna nuovamente il conteggio dei giorni di servizio (esempio)
					mezzo.aggiornaGiorniServizio();

					// Ottieni il numero totale di giorni di servizio e manutenzione (esempio)
					int giorniServizio = mezzo.getGiorniTotaleServizio();
					int giorniManutenzione = mezzo.getGiorniTotaleManutenzione();
					pm.update(mezzo);
					System.out.println("Numero totale di giorni di servizio: " + giorniServizio);
					System.out.println("Numero totale di giorni di manutenzione: " + giorniManutenzione);
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
					System.out.println("Inserisci un punto di partenza");
					String puntoDiPartenza = scanner.next();
					System.out.println("Inserisci un capolinea");
					String capolinea = scanner.next();
					System.out.println("Inserisci il tempo di percorrenza");
					double tempoDiPercorrenza = Double.parseDouble(scanner.next());
					Tratta tratta = new Tratta(puntoDiPartenza, capolinea, tempoDiPercorrenza);
					tr.save(tratta);
					System.out.println("Inserisci l'ID di un mezzo: ");
					List<ParcoMezzi> mezzi = pm.visualizzaParcoMezzi();
					for (ParcoMezzi mezzo1 : mezzi) {
						System.out.println(mezzo1);
					}
					long idMezzo = Long.parseLong(scanner.next());
					mezzo = pm.ricercaMezziDaId(idMezzo);
					mezzo.addTratta(tratta);
				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 10:
				if (risposta.equals("admin")) {
					System.out.println("Inserisci l'ID di un mezzo: ");
					List<ParcoMezzi> mezzi = pm.visualizzaParcoMezzi();
					for (ParcoMezzi mezzo1 : mezzi) {
						System.out.println(mezzo1);
					}
					long idMezzo = Long.parseLong(scanner.next());
					mezzo = pm.ricercaMezziDaId(idMezzo);
					System.out.println(mezzo.getTratte());
					List<Tratta> tratte = mezzo.getTratte();
					List<List<Tratta>> tratteRaggruppate = trattaService.raggruppaTratte(tratte);
					trattaService.calcolaTempoPercorrenzaTotalePerLista(tratteRaggruppate);
				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 11:
				if (risposta.equals("admin")) {
					System.out.println("Inserire una data: anno-mese-giorno");
					String dataS = scanner.next();

					LocalDate dataRicerca = LocalDate.parse(dataS);
					int bigliettiDaData = bi.getNumeroBigliettiPerData(dataRicerca);
					System.out.println("il numero di biglietti è: " + bigliettiDaData);

				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 12:
				if (risposta.equals("admin")) {
					System.out.println("Inserire una data: anno-mese-giorno");
					String dataS = scanner.next();

					LocalDate dataRicerca = LocalDate.parse(dataS);
					List<Abbonamenti> abbonamentiDaData = ab.ricercaAbbonamentiPerData(dataRicerca);
					for (Abbonamenti abbonamento : abbonamentiDaData) {
						System.out.println(abbonamento);
					}
				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;

			case 13:
				if (risposta.equals("admin")) {
					System.out.print("Inserisci l'ID dell'utente: ");
					long idUtente = Long.parseLong(scanner.next());
					Utente utenteDaVerificare = sd.ricercaUtenteDaId(idUtente);
					if (utenteDaVerificare == null) {
						System.out.println("Utente non trovato");
					} else {
						Tessera tessera1 = utenteDaVerificare.getTessera();
						if (tessera1 == null) {
							System.out.println("L'utente con ID " + idUtente + " non ha una tessera");
						} else if (tessera1.getDataScadenza().isBefore(LocalDate.now())) {
							System.out.println("La tessera dell'utente con ID " + idUtente + " è scaduta");
						} else {
							List<Abbonamenti> abbonamenti = tessera1.getAbbonamenti();
							if (abbonamenti.isEmpty()) {
								System.out.println("L'utente con ID " + idUtente + " non ha un abbonamento");
							} else {
								for (Abbonamenti abbonamento : abbonamenti) {
									if (abbonamento.getDataScadenza().isBefore(LocalDate.now())) {
										System.out
												.println("L'abbonamento dell'utente con ID " + idUtente + " è scaduto");
									} else {
										System.out.println(
												"L'abbonamento dell'utente con ID " + idUtente + " è ancora valido");
									}
								}
							}
						}
					}
				} else {
					System.out.println("Non puoi accedere a questa funzione");
				}
				break;
			case 14:
				System.out.println("Inserisci l'ID del mezzo: ");
				long idMezzo = Long.parseLong(scanner.next());

				ParcoMezzi mezzoScelto = pm.ricercaMezziDaId(idMezzo);

				if (mezzoScelto == null) {
					System.out.println("Mezzo non trovato.");
				} else {
					System.out.println("Hai scelto il mezzo: ");
					System.out.println(mezzoScelto);

					Periodo periodoServizio = mezzoScelto.getPeriodoServizio();
					Periodo periodoManutenzione = mezzoScelto.getPeriodoManutenzione();

					System.out.println("Periodo di servizio: " + periodoServizio.getDataInizio() + " - "
							+ periodoServizio.getDataFine());
					System.out.println("Periodo di manutenzione: " + periodoManutenzione.getDataInizio() + " - "
							+ periodoManutenzione.getDataFine());
				}
				break;
			case 15:
				if (risposta.equals("admin")) {
					System.out.println("Inserisci l'ID del mezzo: ");
					long idMezzo1 = Long.parseLong(scanner.next());

					ParcoMezzi mezzoScelto1 = pm.ricercaMezziDaId(idMezzo1);

					if (mezzoScelto1 == null) {
						System.out.println("Mezzo non trovato.");
					} else {
						System.out.println("Hai scelto il mezzo: ");
						System.out.println(mezzoScelto1);

						List<Tratta> tratteMezzo = mezzoScelto1.getTratte();

						if (tratteMezzo.isEmpty()) {
							System.out.println("Questo mezzo non ha ancora percorso alcuna tratta.");
						} else {
							TrattaService trattaService1 = new TrattaService();
							List<List<Tratta>> tratteRaggruppate = trattaService1.raggruppaTratte(tratteMezzo);

							if (tratteRaggruppate.isEmpty()) {
								System.out.println("Nessuna tratta disponibile per questo mezzo.");
							} else {
								trattaService1.calcolaTempoPercorrenzaTotalePerLista(tratteRaggruppate);
								for (List<Tratta> listaTratte : tratteRaggruppate) {
									Tratta trattaDiRiferimento = listaTratte.get(0);
									int numeroPercorsiTratta = pm.getNumeroPercorsiTratta(mezzoScelto1,
											trattaDiRiferimento);
									String chiave = trattaDiRiferimento.getZonaDiPartenza() + "-"
											+ trattaDiRiferimento.getCapolinea();
									System.out.println("Numero di volte che il mezzo ha percorso la tratta " + chiave
											+ ": " + numeroPercorsiTratta);
								}
							}
						}
					}
				} else {
					System.out.println("Non esiste questo mezzo.");
				}
				break;
			}
		}

	}
}
