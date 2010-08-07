/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Jo Ehm, Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg, Holger Koschek,
 *  Carsten Sahling, Roman Schloemmer
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package de.ejb3buch.ticket2rock.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 * Ein Interpret ist entweder ein Musiker oder eine Band. Er hat einen Namen,
 * interpretiert einen oder mehrere Songs, kann Konzerte geben und Alben
 * veröffentlichen.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYP", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("I")
public abstract class Interpret {

	private int id;

	private String name;
	
	private Set<Album> alben;
	
	private List<Song> songs;
	
	private List<Konzert> konzerte;
	
	private List<Tournee> tourneen;

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="interpret")
	public List<Konzert> getKonzerte() {
		return konzerte;
	}

	
	public void setKonzerte(List<Konzert> konzerte) {
		this.konzerte = konzerte;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "interpret")
	public List<Song> getSongs() {
		return songs;	
	}
	
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="interpret")
	public Set<Album> getAlben() {
	      return alben;
	}

	public void setAlben(Set<Album> alben) {
		this.alben = alben;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="interpret")
	public List<Tournee> getTourneen() {
		return tourneen;
	}


	public void setTourneen(List<Tournee> tourneen) {
		this.tourneen = tourneen;
	}
	

}
