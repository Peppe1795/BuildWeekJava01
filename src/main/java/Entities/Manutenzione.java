package Entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Manutenzione")
@Getter
@Setter
@NoArgsConstructor
public class Manutenzione {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate dataInizioManutenzione;
    private int durataInterventoManutenzione;

    @Enumerated(EnumType.STRING)
    private Intervento tipoIntervento;

    @OneToOne
    @JoinColumn(name = "manutenzione_mezzo")
    private ParcoMezzi parcoMezzi;

    public Manutenzione(Intervento tipoIntervento, LocalDate dataInizioManutenzione, int durataInterventoManutenzione) {
        this.tipoIntervento = tipoIntervento;
        this.dataInizioManutenzione = dataInizioManutenzione;
        this.durataInterventoManutenzione = durataInterventoManutenzione;
    }
}
