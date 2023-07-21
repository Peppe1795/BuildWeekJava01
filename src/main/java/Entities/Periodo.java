package Entities;

import java.time.LocalDate;

public class Periodo {
	private LocalDate dataInizio;
	private LocalDate dataFine;

	public Periodo(LocalDate dataInizio, LocalDate dataFine) {
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}
}
