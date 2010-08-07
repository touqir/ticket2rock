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
 * Eine Band ist ein Interpret. Eine Band besteht aus mehreren Musikern.
 */

@Entity
@DiscriminatorValue("B")
public class Band extends Interpret {

	private int gruendungsjahr;
	
	private Set<Musiker> musiker;

	public int getGruendungsjahr() {
		return gruendungsjahr;
	}

	public void setGruendungsjahr(int gruendungsjahr) {
		this.gruendungsjahr = gruendungsjahr;
	}

	// Hier muss der Default Fetch Type (LAZY) überschrieben werden,
	// sonst beschwert sich JBoss ("org.hibernate.LazyInitializationException:
	// failed to lazily initialize a collection of role:
	// de.ejb3buch.ticket2rock.entity.Band.musiker,
	// no session or session was closed")
	@ManyToMany(fetch = FetchType.EAGER)
	public Set<Musiker> getMusiker() {
		return musiker;
	}

	public void setMusiker(Set<Musiker> musiker) {
		this.musiker = musiker;
	}
}
