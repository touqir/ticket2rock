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

package de.ejb3buch.ticket2rock.session.ticketbestellung;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 * Diese Session Bean dient der Online-Reservierung und -Bestellung von Tickets.
 * Reservierungen für Tickets können storniert werden. Die Bean führt eine
 * Live-Statistik über ihre Nutzung.
 */

@Stateful
public class TicketbestellungBean implements Ticketbestellung {

	public void storniereReservierung(long reservierungsnr) {
		System.err.println("Storniere Reservierung " + reservierungsnr);
		// TODO: Reservierung stornieren
	}

	@Remove
	public void abmelden() {

	}

	// Live-Statistik zur Nutzung dieser Bean

	@PostConstruct
	public void onPostConstruct() {
		// Session-Statistik aktualisieren
		TicketbestellungSessionStatistics.totalSessions++;
		TicketbestellungSessionStatistics.activeSessions++;
	}

	@PreDestroy
	public void onPreDestroy() {
		// Session-Statistik aktualisieren
		TicketbestellungSessionStatistics.totalSessions--;
		TicketbestellungSessionStatistics.activeSessions--;
	}

	@PrePassivate
	public void onPrePassivate() {
		// Session-Statistik aktualisieren
		TicketbestellungSessionStatistics.passivatedSessions++;
		TicketbestellungSessionStatistics.activeSessions--;
	}

	@PostActivate
	public void onPostActivate() {
		// Session-Statistik aktualisieren
		TicketbestellungSessionStatistics.passivatedSessions--;
		TicketbestellungSessionStatistics.activeSessions++;
	}
}
