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
package de.ejb3buch.ticket2rock.session;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entity.Ticketreservierung;

@Stateful
@SuppressWarnings("unchecked")
public class BuchungsVorgangBean implements BuchungsVorgang {

	static Logger logger = Logger.getLogger(BuchungsVorgangBean.class);
	Collection<Ticketreservierung> ticketReservierungen = new ArrayList<Ticketreservierung>();

	@PersistenceContext
	private EntityManager em;

	/**
	 * @inheritDoc
	 */
	public void reserviereTickets(Konzert konzert, int ticketAnzahl) {
		Ticketreservierung ticketReservierung = new Ticketreservierung();
		ticketReservierung.setKonzert(konzert);
		ticketReservierung.setAnzahl(ticketAnzahl);
		ticketReservierungen.add(ticketReservierung);
	}

	/**
	 * @inheritDoc
	 */
	@Remove
	public void verwerfeTicketReservierungen() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @inheritDoc
	 */
	@Remove
	public void bezahleTickets(String email) {
		
		
	}
	
}
