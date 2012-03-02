/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerten. 
 *
 *  Copyright (C) 2006-2011
 *  Holisticon AG
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
package de.ejb3buch.ticket2rock.exception;

import javax.ejb.ApplicationException;

import de.ejb3buch.ticket2rock.entity.Konzert;

/**
 * Diese Exception wird geworfen, wenn die Kapazität eines Konzerts beim
 * Bestellen von Tickets erschöpft ist.
 */
@ApplicationException(rollback = true)
public class KapazitaetErschoepftException extends Exception {

	private static final long serialVersionUID = 1L;

	public KapazitaetErschoepftException(Konzert konzert,
			int anzahl_bestellungen) {
		super("Das Ticket-Restkontingent des Konzerts von "
				+ konzert.getInterpret().getName() + " am "
				+ konzert.getDatum() + " in " + konzert.getOrt().getName()
				+ " (" + konzert.getTicketkontingent()
				+ " Tickets) reicht nicht aus, um die Bestellung ("
				+ anzahl_bestellungen + " Tickets) auszuführen.");
	}
}
