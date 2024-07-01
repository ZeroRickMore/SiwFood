package it.uniroma3.siw.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")


@Entity
public class Ingrediente {

	
	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/
	
	
	public static final String[] unitàDiMisuraPossibili = {"grammi", "unità", "cucchiai", "spicchi"};


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Column(length=300)
	private String image_path;

	@NotBlank
	private String unitàDiMisura;
	
	
	
	/*===============================================================================================*/
	/*                                      GETTERS AND SETTERS                                      */
	/*===============================================================================================*/
	
	
	
	public static String[] getUnitàdimisurapossibili() {
		return unitàDiMisuraPossibili;
	}
	public String getUnitàDiMisura() {
		return unitàDiMisura;
	}
	public void setUnitàDiMisura(String unitàDiMisura) {
		this.unitàDiMisura = unitàDiMisura;
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	
	
	
	/*===============================================================================================*/
	/*                                        EQUALS HASHCODE                                        */
	/*===============================================================================================*/
	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(nome, unitàDiMisura);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		return Objects.equals(nome, other.getNome()) && Objects.equals(unitàDiMisura, other.getUnitàDiMisura());
	}


	
	
	/*===============================================================================================*/
	/*                                          CLASS METHODS                                        */
	/*===============================================================================================*/
	
	
	
	
	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nome=" + nome + ", image_path=" + image_path + "]";
	}
	
	
	
	
	
}
