package com.example.ToDoList.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class TodoItem {

	/*Lista luokka jolla suhde Tekija luokkaan, tässä halutaan että käyttäjä syöttää
	 * päivämäärän sekä muistilistan tekstin, päiväys jolloin luotu pitäisi saada valikosta asetettua*/
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long listaId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateCreated;
	//SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private String teksti;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "todoItem")
	private List<Tekija> tekijat; 
	
	public TodoItem() {}
	
	public TodoItem(String teksti) {
		super();
		this.teksti = teksti;
	}

	public TodoItem(Date dateCreated, String teksti) {
		super();
		this.dateCreated = dateCreated;
		this.teksti = teksti;
	}

	public Long getListaId() {
		return listaId;
	}

	public void setListaId(Long listaId) {
		this.listaId = listaId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<Tekija> getTekijat() {
		return tekijat;
	}

	public void setTekijat(List<Tekija> tekijat) {
		this.tekijat = tekijat;
	}

	public String getTeksti() {
		return teksti;
	}

	public void setTeksti(String teksti) {
		this.teksti = teksti;
	}

	//@Override
	//public String toString() {
	//	return "Lista [listaId=" + listaId + ", dateCreated=" + dateCreated + ", teksti=" + teksti + "]";
	//}
	
	
}
