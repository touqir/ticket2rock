package de.ejb3buch.ticket2rock.applikation.model;

import java.util.List;

/**
 * Band Baking Bean für die Verwendung im JSF-Frameworks
 * @author Jochen Jörg
 */
public class BandBakingBean {
   
	private int id;
	private String name;
	private List<String> musikerNamensListe;
	
	public BandBakingBean () {

	}
	
	public BandBakingBean (int aId, String aName) {
		id = aId;
		name = aName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}


	public void setMusikerNamensListe(List<String> musikerNamen) {
		this.musikerNamensListe = musikerNamen;		
	}

	public List<String> getMusikerNamensListe() {
		return musikerNamensListe;
	}

	
}
