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
	private Long id;
	private String firstName;
	private String lastName;
	private String postiosoite;
	private String postitmp;
	private String sahkoposti;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "listaId")
	private TodoItem todoItem;

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
			TodoItem item) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.postiosoite = postiosoite;
		this.postitmp = postitmp;
		this.sahkoposti = sahkoposti;
		this.todoItem = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public TodoItem getLista() {
		return todoItem;
	}

	public void setLista(TodoItem lista) {
		this.todoItem = lista;
	}

	@Override
	public String toString() {
		return "Tekija [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", postiosoite="
				+ postiosoite + ", postitmp=" + postitmp + ", sahkoposti=" + sahkoposti + "]";
	}

}
