package de.ejb3buch.ticket2rock.applikation.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.applikation.helper.FacesUtils;
import de.ejb3buch.ticket2rock.applikation.servicelocator.ServiceLocator;
import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.session.BuchungsVorgang;

public class TicketController {

	static Logger logger = Logger.getLogger(TicketController.class);

	private ServiceLocator serviceLocator;

	private boolean hasOrderedTicket = false;

	private BuchungsVorgang buchungsVorgang;

	private Konzert konzert;

	private String availableTicketsExpression;

	private int ticketanzahl;

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public boolean isTicketOrdered() {
		return hasOrderedTicket;
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
	 * Session zugeornet. Deshalb sollte das TicketController Bean unbedingt im
	 * Scope session Kontext sein.
	 * 
	 * @return Identifier für den JSF page flow
	 */
	public String orderTickets() {

		// hole über den ServiceLocator einen BuchungsVorgang, falls dies für
		// diese
		// für diese Session noch nicht geschehen ist
		if (buchungsVorgang == null) {
			buchungsVorgang = serviceLocator.getWarenkorb();
		}
		buchungsVorgang.reserviereTickets(this.konzert, ticketanzahl);
		this.hasOrderedTicket = true;
		return "reservierungsmeldung";
	}

	/**
	 * verzweigt den Screenflow zur Anzeige der reservierten Tickets
	 * 
	 * @return
	 */
	public String showReservations() {
		// TODO implement show reservation use case
		return "";

	}

	public void validateTicketOrder(FacesContext context,
			UIComponent toValidate, Object value) {
		Integer numOfTickets = (Integer) value;

		if (numOfTickets.intValue() < 1) {
			((UIInput)toValidate).setValid(false);
			String messageString = FacesUtils.getMessageResourceString(
					context.getApplication().getMessageBundle(),
					"ticketbestellung_invalidNumber", null, context
							.getViewRoot().getLocale());
			FacesMessage message = new FacesMessage(messageString);
			context.addMessage(toValidate.getClientId(context), message);
		}
		 
		if (numOfTickets.intValue() > konzert.getTicketkontingent()) {
			((UIInput)toValidate).setValid(false);
			String messageString = FacesUtils.getMessageResourceString(
					context.getApplication().getMessageBundle(),
					"ticketbestellung_exceedsContingent", null, context
							.getViewRoot().getLocale());
			FacesMessage message = new FacesMessage(messageString);
			context.addMessage(toValidate.getClientId(context), message);
		}		

		
	}

	public String getAvailableTicketsExpression() {
		return availableTicketsExpression;
	}

}
