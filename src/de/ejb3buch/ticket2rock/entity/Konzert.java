/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg und Holger Koschek
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

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Ein Konzert ist eine Veranstaltung, bei dem ein Interpreten einige seiner
 * Songs vorträgt. Ein Konzert findet zu einem Zeitpunkt an einem
 * Veranstaltungsort statt. Ein Konzert besitzt ein Ticketkontingent.
 */

@Entity
public class Konzert {

	private int id;

	private Interpret interpret;

	private Date datum;

	private Veranstaltungsort ort;

	private Tournee tournee;

	private int ticketkontingent;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	public Interpret getInterpret() {
		// Bei Konzerten im Rahmen einer Tournee wird der Interpret bei der
		// Tournee gespeichert.
		if (interpret == null && tournee != null) {
			return tournee.getInterpret();
		} else {
			return interpret;
		}
	}

	public void setInterpret(Interpret interpret) {
		this.interpret = interpret;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@ManyToOne
	// Die JoinColumn muss benannt werden, da das Attribut nur "ort" heißt
	@JoinColumn(name = "VERANSTALTUNGSORT_ID")
	public Veranstaltungsort getOrt() {
		return ort;
	}

	public void setOrt(Veranstaltungsort ort) {
		this.ort = ort;
	}

	@ManyToOne
	public Tournee getTournee() {
		return tournee;
	}

	public void setTournee(Tournee tournee) {
		this.tournee = tournee;
	}

	public int getTicketkontingent() {
		return ticketkontingent;
	}

	public void setTicketkontingent(int ticketkontingent) {
		this.ticketkontingent = ticketkontingent;
	}
}
