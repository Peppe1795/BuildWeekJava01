package DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entities.Tratta;

public class TrattaService {

    public List<List<Tratta>> raggruppaTratte(List<Tratta> tratte) {
        Map<String, List<Tratta>> mapTratte = new HashMap<>();

        for (Tratta tratta : tratte) {
            String chiave = tratta.getZonaDiPartenza() + "-" + tratta.getCapolinea();
            if (!mapTratte.containsKey(chiave)) {
                mapTratte.put(chiave, new ArrayList<>());
            }
            mapTratte.get(chiave).add(tratta);
        }

        return new ArrayList<>(mapTratte.values());
    }

    public void calcolaTempoPercorrenzaTotalePerLista(List<List<Tratta>> tratteRaggruppate) {
        System.out.println("Totale percorrenza per ogni combinazione di punto di partenza e capolinea:");

        for (List<Tratta> listaTratte : tratteRaggruppate) {
            double tempoPercorrenzaTotale = 0.0;
            for (Tratta tratta : listaTratte) {
                tempoPercorrenzaTotale += tratta.getTempoMedioDiPercorrenza();
            }

            Tratta trattaDiRiferimento = listaTratte.get(0);
            String chiave = trattaDiRiferimento.getZonaDiPartenza() + "-" + trattaDiRiferimento.getCapolinea();
            System.out.println(chiave + ": " + tempoPercorrenzaTotale);
        }
    }
}
