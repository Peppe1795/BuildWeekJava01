package Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_punto_vendita")
@Table(name = "Punti_vendita")
@NoArgsConstructor
public class Rivenditore {

	@Id
	@SequenceGenerator(name = "my_sequence2", sequenceName = "my_sequence2", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence2")
	private long id;

	@OneToMany(mappedBy = "puntoVendita")
	private List<Biglietti> biglietti = new ArrayList<>();
	@OneToMany(mappedBy = "puntoVendita")
	private List<Abbonamenti> abbonamenti = new ArrayList<>();;

	@Override
	public String toString() {
		return "Rivenditore id: " + id;
	}

}
