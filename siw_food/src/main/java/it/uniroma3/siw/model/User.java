package it.uniroma3.siw.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	
	
	/*===============================================================================================*/
	/*                                           VARIABLES                                           */
	/*===============================================================================================*/
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
//	@NotBlank
//	private String nome;
//	
//	@NotBlank
//	private String cognome;

	@OneToOne(cascade=CascadeType.ALL)
	private Cuoco cuoco;
	
	
	/*===============================================================================================*/
	/*                                      GETTERS AND SETTERS                                      */
	/*===============================================================================================*/
	
	
	
	public Cuoco getCuoco() {
		return cuoco;
	}
	public void setCuoco(Cuoco cuoco) {
		this.cuoco = cuoco;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public String getNome() {
//		return nome;
//	}
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//	public String getCognome() {
//		return cognome;
//	}
//	public void setCognome(String cognome) {
//		this.cognome = cognome;
//	}

}
