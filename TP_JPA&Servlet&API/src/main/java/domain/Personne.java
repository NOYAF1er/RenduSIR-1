package domain;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Personne {

	private int id;
	private String nom;
	private String prenom;
	private int age;
	private Collection<Personne> amis;
	private Collection<Appareil> appareils;
	private Collection<Maison> maisons;

	public Personne() {
		this.maisons = new ArrayList<Maison>();
	}


	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@JsonIgnore
	@ManyToMany
	public Collection<Personne> getAmis() {
		return amis;
	}

	public void setAmis(Collection<Personne> amis) {
		this.amis = amis;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "proprio", cascade = CascadeType.PERSIST)
	public Collection<Appareil> getAppareils() {
		return appareils;
	}

	public void setAppareils(Collection<Appareil> appareils) {
		this.appareils = appareils;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "proprio", cascade = CascadeType.PERSIST)
	public Collection<Maison> getMaisons() {
		return maisons;
	}

	public void setMaisons(Collection<Maison> maisons) {
		this.maisons = maisons;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
