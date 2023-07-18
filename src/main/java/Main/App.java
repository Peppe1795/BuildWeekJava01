package Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Entities.Utente;

public class App {

	public static void main(String[] args) {
		List<Utente> lista1 = new ArrayList<>(
				Arrays.asList(new Utente("Giuseppe", "Petrucci", LocalDate.of(1995, 2, 17))));

	}

}
