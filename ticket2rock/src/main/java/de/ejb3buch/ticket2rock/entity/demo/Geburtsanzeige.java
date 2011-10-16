/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerten. 
 *
 *  Copyright (C) 2011
 *  Jo Ehm, Stefan M. Heldt, Oliver Ihns, Holger Koschek,
 *  Carsten Sahling, Roman Schloemmer, Norman Erck, Daniel Steinhöfer,
 *  Carl A. Düvel.
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
