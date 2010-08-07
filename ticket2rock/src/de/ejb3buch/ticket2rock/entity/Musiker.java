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

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

/**
 * Ein Musiker ist ein Interpret. Er kann Mitglied einer oder mehrerer Bands
 * sein.
 */

@Entity
@DiscriminatorValue("M")
public class Musiker extends Interpret {

	private String geburtsname;

	private Set<Band> bands;

	public String getGeburtsname() {
		return geburtsname;
	}

	public void setGeburtsname(String geburtsname) {
		this.geburtsname = geburtsname;
	}

	// Hier muss der Default Fetch Type (LAZY) überschrieben werden,
	// sonst beschwert sich JBoss ("org.hibernate.LazyInitializationException:
	// failed to lazily initialize a collection of role:
	// de.ejb3buch.ticket2rock.entity.Musiker.bands,
	// no session or session was closed")
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "musiker")
	public Set<Band> getBands() {
		return bands;
	}

	public void setBands(Set<Band> bands) {
		this.bands = bands;
	}
}
