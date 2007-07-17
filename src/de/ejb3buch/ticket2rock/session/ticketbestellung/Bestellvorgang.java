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

package de.ejb3buch.ticket2rock.session.ticketbestellung;

import java.util.Collection;

import javax.ejb.Remote;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entity.Ticketbestellung;
import de.ejb3buch.ticket2rock.exception.KapazitaetErschoepftException;

@Remote
public interface Bestellvorgang {

	/**
	 * bucht eine Anzahl von Tickets zu einer Konzertveranstaltung. Falls das Konzert
	 * schon ausgebucht ist bzw. die Tickets nicht mehr verfügbar sind wird eine Exception
	 * geworfen.
	 * @param konzert das Konzert, für das Tickets gebucht werden
	 * @param ticketAnzahl Anzahl der zu buchenden Tickets
	 * @throws KapazitaetErschoepftException 
	 */
	public void bestelleTickets(Konzert konzert, int ticketAnzahl);
	
    /**
     * 
     * @return eine Collection der Ticketbestellungen, die diesem Bestellvorgang zugeordnet
     * sind
     */
	public Collection<Ticketbestellung> getTicketbestellungen(); 	
	
	/**
	 * Berechnet den Preis aller Ticketbestellungen, die diesem Bestellvorgang
	 * zugeordnet sind
	 * @return der aktuelle Wert der Bestellung
	 */
	public float getGesamtpreis();	
	
	
	/**
	 * Alle Ticketbestellungen werden verworfen. 
	 *
	 */
	public void verwerfeTicketbestellungen();
	
	
    /**
     * Verwerfe eine Ticketbestellung. Ein Ticketbestellungs Objekt wird von der Collection
     * entfernt.
     * @param bestellung
     */
	public void verwerfeTicketbestellung(Ticketbestellung bestellung);
	
	/**
	 * Alle gebuchten Tickets werden bezahlt.
	 * Ticketbestellungen werden in der Datenbank persisitiert. Das Ticketkontingent der
	 * betroffenen Konzerte wird angepasst und in der Datenbank gespeichert. Zusätzlich 
	 * wird ein Kunden-persistent Entity persistiert. 
	 * @throws KapazitaetErschoepftException 
	 * @param email Email Adresse des Kunden, von dem die Tickets reserviert 
	 * werden.
	 * @return Collection aller der bezahlten Ticketbestellungen
	 */
	public Collection<Ticketbestellung> bezahleTickets(String email) throws KapazitaetErschoepftException;
	
    /**
     * 
     * @return true falls diesem Bestellvorgang  Bestellungen enthält, false falls nicht
     */	
	public boolean hasBestellungen();
	

}
