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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Ein Kunde kann Konzerttickets bestellen. Er wird derzeit nur über eine
 * E-Mail-Adresse identifiziert.
 */

@Entity
public class Kunde {

	private int id;

	private String email;

	private List<Ticketbestellung> bestellungen = null;
	

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "kunde")
	public List<Ticketbestellung> getBestellungen() {
		return this.bestellungen;
	}

	/**
	 * fügt eine Liste von Ticketbestellungen den Ticketbestellungen, die diesem
	 * Kunden zugeordnet sind hinzu
	 * 
	 * @param bestellungen
	 */
	public void addBestellungen(List<Ticketbestellung> dieBestellungen) {
		if (bestellungen == null) {
			bestellungen = new ArrayList<Ticketbestellung>();
		}
		bestellungen.addAll(dieBestellungen);
	}

	public void setBestellungen(List<Ticketbestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}
	

	
}
