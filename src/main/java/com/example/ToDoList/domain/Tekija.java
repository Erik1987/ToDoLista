package com.example.ToDoList.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tekija {
	/*Tekija luokka joka viittaa Lista luokkaan, eli täytetään tekijän perustiedot*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;
	private String postiosoite;
	private String postitmp;
	private String sahkoposti;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "listaId")
	private Lista lista;

	public Tekija() {
		super();
	}

	public Tekija(String firstName, String lastName, String postiosoite, String postitmp, String sahkoposti) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.postiosoite = postiosoite;
		this.postitmp = postitmp;
		this.sahkoposti = sahkoposti;
	}
	
	

	public Tekija(String firstName, String lastName, String postiosoite, String postitmp, String sahkoposti,
			Lista lista) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.postiosoite = postiosoite;
		this.postitmp = postitmp;
		this.sahkoposti = sahkoposti;
		this.lista = lista;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPostiosoite() {
		return postiosoite;
	}

	public void setPostiosoite(String postiosoite) {
		this.postiosoite = postiosoite;
	}

	public String getPostitmp() {
		return postitmp;
	}

	public void setPostitmp(String postitmp) {
		this.postitmp = postitmp;
	}

	public String getSahkoposti() {
		return sahkoposti;
	}

	public void setSahkoposti(String sahkoposti) {
		this.sahkoposti = sahkoposti;
	}

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	@Override
	public String toString() {
		return "Tekija [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", postiosoite="
				+ postiosoite + ", postitmp=" + postitmp + ", sahkoposti=" + sahkoposti + "]";
	}

}
