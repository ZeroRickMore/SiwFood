package it.uniroma3.siw.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;

@Entity
public class Ricetta {
	
	/*##############################################################*/
	/*#########################VARIABLES############################*/
	/*##############################################################*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private String descrizione;
	
	private List<String> tuttiPathDelleImmagini;
	
	//Map handling annotations
	@ElementCollection
	@CollectionTable(name = "RicettaIngrediente2Quantità", joinColumns = @JoinColumn(name = "ricetta_id"))
    @MapKeyColumn(name = "ingrediente_id")
    @Column(name = "quantità")
	private Map<Ingrediente, Integer> ingrediente2quantity;
	
	@ManyToOne
	private Cuoco cuoco;
	
	/*##############################################################*/
	/*####################GETTERS AND SETTERS#######################*/
	/*##############################################################*/
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public List<String> getTuttiPathDelleImmagini() {
		return tuttiPathDelleImmagini;
	}
	public Map<Ingrediente, Integer> getIngrediente2quantity() {
		return ingrediente2quantity;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setTuttiPathDelleImmagini(List<String> tuttiPathDelleImmagini) {
		this.tuttiPathDelleImmagini = tuttiPathDelleImmagini;
	}
	public void setIngrediente2quantity(Map<Ingrediente, Integer> ingrediente2quantity) {
		this.ingrediente2quantity = ingrediente2quantity;
	}

	/*##############################################################*/
	/*#####################EQUALS, HASHCODE#########################*/
	/*##############################################################*/
	
	@Override
	public int hashCode() {
		return Objects.hash(cuoco, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ricetta other = (Ricetta) obj;
		return Objects.equals(cuoco, other.cuoco) && Objects.equals(nome, other.nome);
	}
	
	/*##############################################################*/
	/*#######################CLASS METHODS##########################*/
	/*##############################################################*/
	
	
	
}
