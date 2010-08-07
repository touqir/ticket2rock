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

package de.ejb3buch.ticket2rock.entity.primarykey;

import javax.persistence.Embeddable;


/**
 * Die Primärschlüsselklasse für die Entität Veranstalter.
 */

@Embeddable
public class VeranstalterPK implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long handelsregisternummer;

	private String amtsgericht;

	public VeranstalterPK() {
	}

	public VeranstalterPK(long handelsregisternummer, String amtsgericht) {
		super();
		this.handelsregisternummer = handelsregisternummer;
		this.amtsgericht = amtsgericht;
	}

	public long getHandelsregisternummer() {
		return handelsregisternummer;
	}

	public void setHandelsregisternummer(long handelsregisternummer) {
		this.handelsregisternummer = handelsregisternummer;
	}

	public String getAmtsgericht() {
		return amtsgericht;
	}

	public void setAmtsgericht(String amtsgericht) {
		this.amtsgericht = amtsgericht;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof VeranstalterPK)) {
			return false;
		}
		VeranstalterPK pk = (VeranstalterPK) obj;
		return (this.handelsregisternummer == pk.handelsregisternummer && this.amtsgericht
				.equals(pk.amtsgericht));
	}
	
	public int hashCode() {
		return (int)this.handelsregisternummer + this.amtsgericht.hashCode();
	}
}
