package de.ejb3buch.ticket2rock.entity.demo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ExcludeDefaultListeners
public class Geburtsanzeige implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int babyId;

	// Default-Konstruktor ist Pflicht
	public Geburtsanzeige() {
	}
	
	public Geburtsanzeige(final int babyId) {
		this.babyId = babyId;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBabyId() {
		return babyId;
	}

	public void setBabyId(final int babyId) {
		this.babyId = babyId;
	}
}
