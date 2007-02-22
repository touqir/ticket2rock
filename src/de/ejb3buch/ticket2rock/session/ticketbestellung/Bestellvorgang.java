package de.ejb3buch.ticket2rock.session.ticketbestellung;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Konzert;

@Local
public interface Bestellvorgang {

	/**
	 * bucht eine Anzahl von Tickets zu einer Konzertveranstaltung. Falls das Konzert
	 * schon ausgebucht ist bzw. die Tickets nicht mehr verfügbar sind wird eine Exception
	 * geworfen.
	 * @param konzert das Konzert, für das Tickets gebucht werden
	 * @param ticketAnzahl Anzahl der zu buchenden Tickets
	 */
	public void reserviereTickets(Konzert konzert, int ticketAnzahl);
	
	
	
	/**
	 * Alle Ticketbuchungen werden verworfen. Die verworfenen Tickets werden dem
	 * Ticketkontingent des jeweiligen Konzertes wieder gutgeschrieben.
	 *
	 */
	public void verwerfeTicketReservierungen();
	
	/**
	 * alle gebuchten Tickets werden bezahlt.
	 * @param email Email Adresse des Kunden, von dem die Tickets reserviert 
	 * werden.
	 */
	public void bezahleTickets(String email);

}
