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

package de.ejb3buch.ticket2rock.applikation.controller;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.applikation.helper.FacesUtils;
import de.ejb3buch.ticket2rock.applikation.servicelocator.ServiceLocator;
import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entity.Ticketbestellung;
import de.ejb3buch.ticket2rock.exception.KapazitaetErschoepftException;
import de.ejb3buch.ticket2rock.session.ticketbestellung.BestellvorgangLocal;

public class TicketController {

	static Logger logger = Logger.getLogger(TicketController.class);

	private ServiceLocator serviceLocator;

	private boolean bestellungExistiert = false;

	private BestellvorgangLocal bestellvorgang;

	private DataModel bestellungen = new ListDataModel();
	
	private DataModel bezahlteBestellungen = new ListDataModel();

	private Konzert konzert;

	private String availableTicketsExpression;

	private int ticketanzahl;
	
	private String email;

	private float rechnungsBetrag;

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public boolean isTicketOrdered() {
		return bestellungExistiert;
	}

	public Konzert getKonzert() {
		return konzert;
	}

	public void setKonzert(Konzert konzert) {
		this.konzert = konzert;

		// set the expression for available Ticktes based on the available
		// concert ticktets
		if (konzert.getTicketkontingent() <= 100) {
			availableTicketsExpression = Integer.toString(konzert
					.getTicketkontingent());
		} else {
			FacesContext context = FacesContext.getCurrentInstance();

			availableTicketsExpression = FacesUtils.getMessageResourceString(
					context.getApplication().getMessageBundle(),
					"ticketbestellung_moreThanHundred", null, context
							.getViewRoot().getLocale());
		}
	}

	public int getTicketanzahl() {
		return ticketanzahl;
	}

	public void setTicketanzahl(int ticketanzahl) {
		this.ticketanzahl = ticketanzahl;
	}

	/**
	 * legt die ausgewählten Konzerttickets in den Einkaufswagen, sofern diese
	 * noch zur Verfügung stehen. Falls noch kein Einkaufswagen-Objekt vorhanden
	 * ist, wird dieses über den ServiceLocator allokiert und implizit der
	 * Session zugeordnet. Deshalb sollte das TicketController Bean unbedingt im
	 * Scope session Kontext sein.
	 * 
	 * @return Identifier für den JSF page flow
	 */
	public String bestelleTickets() {
        bezahlteBestellungen = new ListDataModel();
		// hole über den ServiceLocator einen Bestellvorgang, falls dies für
		// diese Session noch nicht geschehen ist
		if (bestellvorgang == null) {
			bestellvorgang = serviceLocator.getBestellvorgang();
		}
		bestellvorgang.bestelleTickets(this.konzert, ticketanzahl);

		this.bestellungExistiert = true;

		// nach einer Bestellung wird die ticketanzahl wieder auf 0 gesetzt, so
		// dass in der entsprechenden JSF das Eingabefeld nicht den Wert der
		// vorherigen
		// Bestellung enthält
		ticketanzahl = 0;
		return "reservierungsmeldung";
	}

	/**
	 * 
	 * @return DataModel das eine Kollektion von Ticketbestellungen beinhaltet
	 */
	public DataModel getBestellungen() {
		Collection<Ticketbestellung> orders = bestellvorgang
				.getTicketbestellungen();
		bestellungen.setWrappedData(orders);
		return bestellungen;
	}
	


	/**
	 * Löschen die in der Form selektierte Ticketbestellung
	 * 
	 * @return Rückgabewert zur Definition der Folgeseite
	 */
	public String deleteOrder() {
		Ticketbestellung bestellung = (Ticketbestellung) this.bestellungen
				.getRowData();
		bestellvorgang.verwerfeTicketbestellung(bestellung);
		bestellungExistiert = bestellvorgang.hasBestellungen();
		return "showBestellungen";
	}

	/**
	 * Bezahlung der bestellten Tickets
	 * 
	 * @return Rückgabewert zur Definition der Folgeseite
	 */
	public String bezahle() {
		try {
		  rechnungsBetrag = bestellvorgang.getGesamtpreis();
		  Collection<Ticketbestellung> bestellungen = bestellvorgang.bezahleTickets(email);	
		  this.bezahlteBestellungen.setWrappedData(bestellungen);
		} catch (KapazitaetErschoepftException e) {
			FacesUtils.addMessage(null, "ticketbestellung_exceedsContingent");
		}		
		bestellvorgang = null;
		bestellungExistiert = false;
		return "ticketkaufmeldung";
	}

	public void validateTicketOrder(FacesContext context,
			UIComponent toValidate, Object value) {
		Integer numOfTickets = (Integer) value;

		if (numOfTickets.intValue() < 1) {
			((UIInput) toValidate).setValid(false);
			FacesUtils.addMessage(toValidate
					.getClientId(context), "ticketbestellung_invalidNumber");
		}

		if (numOfTickets.intValue() > konzert.getTicketkontingent()) {
			((UIInput) toValidate).setValid(false);
			String messageString = FacesUtils.getMessageResourceString(context
					.getApplication().getMessageBundle(),
					"ticketbestellung_exceedsContingent", null, context
							.getViewRoot().getLocale());
			FacesMessage message = new FacesMessage(messageString);
			context.addMessage(toValidate.getClientId(context), message);
		}

	}
	
	/**
	 * 
	 * @return Gesamtpreis aller Bestellungen, die in Zusammenhang mit diesem
	 * Bestellvorgang bestellt wurden.
	 * 
	 */
	public float getGesamtpreis(){
		return this.bestellvorgang.getGesamtpreis();		
	}

	public String getAvailableTicketsExpression() {
		return availableTicketsExpression;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getRechnungsBetrag() {
		return rechnungsBetrag;
	}

	public DataModel getBezahlteBestellungen() {
		return bezahlteBestellungen;
	}

}
