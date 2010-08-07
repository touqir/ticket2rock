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

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.ejb3buch.ticket2rock.entity.embedded.GeoKoordinaten;

/**
 * An einen Veranstaltungsort finden kommt es zur Durchführung eines oder
 * mehrerer Konzerte. Ein Veranstaltungsort besitzt eine Adresse und eine
 * Zuschauerkapazität.
 */

@Entity
public class Veranstaltungsort {

	private int id;

	private String name;

	private String adresse;

	private int kapazitaet;

	private GeoKoordinaten koordinaten;

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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getKapazitaet() {
		return kapazitaet;
	}

	public void setKapazitaet(int kapazitaet) {
		this.kapazitaet = kapazitaet;
	}

	@Embedded
	@AttributeOverrides( {
			@AttributeOverride(name = "breite", column = @Column(name = "GEO_BREITE")),
			@AttributeOverride(name = "laenge", column = @Column(name = "GEO_LAENGE")) })
	public GeoKoordinaten getKoordinaten() {
		return koordinaten;
	}

	public void setKoordinaten(GeoKoordinaten koordinaten) {
		this.koordinaten = koordinaten;
	}
}
