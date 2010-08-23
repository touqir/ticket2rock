package de.ejb3buch.ticket2rock.entity.demo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//@EntityListeners(de.ejb3buch.ticket2rock.session.interceptor.demo.EntiGeburtenkontrolle.class)
public class Enti implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;

	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
	@SuppressWarnings("unused")
//	@PrePersist
	private void onPrePersist() {
		name = "Enti " + name;
	}
}
