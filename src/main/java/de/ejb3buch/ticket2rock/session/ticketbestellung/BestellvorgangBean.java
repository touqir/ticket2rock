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
package de.ejb3buch.ticket2rock.session.ticketbestellung;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entity.Kunde;
import de.ejb3buch.ticket2rock.entity.Ticketbestellung;
import de.ejb3buch.ticket2rock.exception.KapazitaetErschoepftException;
import de.ejb3buch.ticket2rock.session.crud.KundenVerwaltungLocal;

@Stateful
@ConversationScoped
public class BestellvorgangBean implements Bestellvorgang, BestellvorgangLocal {

	static Logger logger = Logger.getLogger(BestellvorgangBean.class);

	List<Ticketbestellung> ticketBestellungen = new ArrayList<Ticketbestellung>();

	@PersistenceContext
	private EntityManager em;

	private @Inject
	Conversation conversation;

	@EJB
	private BenachrichtigungsserviceLocal benachrichtigungsService;

	@EJB
	private KundenVerwaltungLocal kundenverwaltung;

	/**
	 * @throws KapazitaetErschoepftException
	 * @inheritDoc
	 */
	public void bestelleTickets(Konzert konzert, int ticketAnzahl) {
		Ticketbestellung ticketBestellung = new Ticketbestellung();
		ticketBestellung.setKonzert(konzert);
		ticketBestellung.setAnzahl(ticketAnzahl);
		if (!hasBestellungen())
			conversation.begin();
		ticketBestellungen.add(ticketBestellung);
	}

	/**
	 * @inheritDoc
	 */
	public float getGesamtpreis() {
		float bestellungspreis = 0;
		for (Ticketbestellung bestellung : this.ticketBestellungen) {
			bestellungspreis += bestellung.getKonzert().getTicketpreis()
					* bestellung.getAnzahl();
		}
		return bestellungspreis;
	}

	/**
	 * @inheritDoc
	 */
	public Collection<Ticketbestellung> getTicketbestellungen() {
		return ticketBestellungen;
	}

	/**
	 * @inheritDoc
	 */
	@Remove
	public void verwerfeTicketbestellungen() {
		conversation.end();

	}

	/**
	 * @inheritDoc
	 */
	public void verwerfeTicketbestellung(Ticketbestellung bestellung) {
		ticketBestellungen.remove(bestellung);
		if (!hasBestellungen())
			conversation.end();
	}

	/**
	 * @inheritDoc
	 */
	public boolean hasBestellungen() {
		return !this.ticketBestellungen.isEmpty();
	}

	/**
	 * @inheritDoc
	 */
	public Collection<Ticketbestellung> bezahleTickets(String email)
			throws KapazitaetErschoepftException {

		// suche nach einen Kunden anhand der übergebenen email Adresse
		Kunde kunde = kundenverwaltung.getKundeByEmail(email);
		if (kunde == null) {
			// kunde gibt es nicht --> persistiere neuen Kunden
			kunde = new Kunde();
			kunde.setEmail(email);
			em.persist(kunde);
		} else {
			// persistiere vorhandenen Kunden
			em.merge(kunde);
		}
		if ((ticketBestellungen != null) && (!ticketBestellungen.isEmpty())) {
			kunde.setBestellungen(ticketBestellungen);
			for (Ticketbestellung bestellung : ticketBestellungen) {
				Konzert konzert = bestellung.getKonzert();
				konzert = em.find(Konzert.class, konzert.getId());
				// konzert.bestelleTickets(bestellung.getAnzahl());
				// konzert = em.merge(konzert);
				bestellung.setKonzert(konzert);
				bestellung.setKunde(kunde);

				em.persist(bestellung);
			}
		}

		benachrichtigungsService.installiereKonzerterinnerungen(email,
				ticketBestellungen);
		conversation.end();
		return ticketBestellungen;
	}

	@Remove
	public void destroy() {
	}

	// Live-Statistik zur Nutzung dieser Bean

	@PostConstruct
	public void onPostConstruct() {
		// Session-Statistik aktualisieren
		BestellvorgangSessionStatistics.totalSessions++;
		BestellvorgangSessionStatistics.activeSessions++;
	}

	@PreDestroy
	public void onPreDestroy() {
		// Session-Statistik aktualisieren
		BestellvorgangSessionStatistics.totalSessions--;
		BestellvorgangSessionStatistics.activeSessions--;
	}

	@PrePassivate
	public void onPrePassivate() {
		// Session-Statistik aktualisieren
		BestellvorgangSessionStatistics.passiveSessions++;
		BestellvorgangSessionStatistics.activeSessions--;
	}

	@PostActivate
	public void onPostActivate() {
		// Session-Statistik aktualisieren
		BestellvorgangSessionStatistics.passiveSessions--;
		BestellvorgangSessionStatistics.activeSessions++;
	}

}
