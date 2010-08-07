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

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;

/**
 * News sind Nachrichten, die den Ticket2Rock-Benutzer z.B. über neue Konzerte
 * informieren.
 */

@Entity
@ExcludeDefaultListeners
public class News {

	private int id;

	private Date datum;

	private String nachricht;

	public static final int MAX_ALTER = 20;

	protected News() {
		super();
	}

	public News(Object entity) {
		// Datum der News automatisch beim Erzeugen setzen
		this.datum = new Date();

		if (entity instanceof Band || entity instanceof Album
				|| entity instanceof Konzert) {
			if (entity instanceof Band) {
				nachricht = "Neue Band \"" + ((Band) entity).getName() + "\"";
			} else if (entity instanceof Album) {
				nachricht = "Neues Album \"" + ((Album) entity).getTitel()
						+ "\" von \""
						+ ((Album) entity).getInterpret().getName();
			} else if (entity instanceof Konzert) {
				nachricht = "Neues Konzert der \""
						+ ((Konzert) entity).getTournee().getName()
						+ "\"-Tournee von \""
						+ ((Konzert) entity).getInterpret().getName() + " am "
						+ ((Konzert) entity).getDatum() + " in "
						+ ((Konzert) entity).getOrt().getName();
			}
		} else {
			throw new RuntimeException("Invalid object: " + entity);
		}
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getNachricht() {
		return nachricht;
	}

	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
	}

	@PostLoad
	protected void pruefeAlter() {
		Date heute = new Date();

		long heuteInTagen = heute.getTime() / 86400000;
		long newsAlterInTagen = this.datum.getTime() / 86400000;
		if (heuteInTagen - newsAlterInTagen > News.MAX_ALTER) {
			// throw new NewsZuAltException(this);
		}
	}
}
