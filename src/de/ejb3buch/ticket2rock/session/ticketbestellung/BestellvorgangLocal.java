package de.ejb3buch.ticket2rock.session.ticketbestellung;

import java.util.Collection;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entity.Ticketbestellung;
import de.ejb3buch.ticket2rock.exception.KapazitaetErschoepftException;

@Local
public interface BestellvorgangLocal  {

	/**
	 * bucht eine Anzahl von Tickets zu einer Konzertveranstaltung. Falls das Konzert
	 * schon ausgebucht ist bzw. die Tickets nicht mehr verfügbar sind wird eine Exception
	 * geworfen.
	 * @param konzert das Konzert, für das Tickets gebucht werden
	 * @param ticketAnzahl Anzahl der zu buchenden Tickets
	 */
	public void bestelleTickets(Konzert konzert, int ticketAnzahl);
	
    /**
     * 
     * @return eine Collection der Ticketbestellungen, die diesem Bestellvorgang zugeordnet
     * sind
     */
	public Collection<Ticketbestellung> getTicketbestellungen(); 	
	
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
	 * alle gebuchten Tickets werden bezahlt.
	 * @param email Email Adresse des Kunden, von dem die Tickets reserviert 
	 * werden.
	 */
	public void bezahleTickets(String email)throws KapazitaetErschoepftException;
	
    /**
     * 
     * @return true falls diesem Bestellvorgang  Bestellungen enthält, false falls nicht
     */	
	public boolean hasBestellungen();

}
