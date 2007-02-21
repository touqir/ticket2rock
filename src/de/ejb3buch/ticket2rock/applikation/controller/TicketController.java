package de.ejb3buch.ticket2rock.applikation.controller;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.applikation.helper.FacesUtils;
import de.ejb3buch.ticket2rock.applikation.servicelocator.ServiceLocator;
import de.ejb3buch.ticket2rock.entity.Konzert;

public class TicketController {

	static Logger logger = Logger.getLogger(TicketController.class);

	private ServiceLocator serviceLocator;

	private Konzert konzert;

	private String availableTicketsExpression;

	private int ticketanzahl;

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public Konzert getKonzert() {
		return konzert;
	}

	public void setKonzert(Konzert konzert) {
		this.konzert = konzert;

		// set the expression for available Ticktes based on the available
		// koncert ticktets
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

	public String orderTickets() {
		logger.info("ordering ticktes: " + ticketanzahl);
		logger.info("available Ticketbestellung String: "
				+ this.availableTicketsExpression);
		return "";
	}

	public String getAvailableTicketsExpression() {
		return availableTicketsExpression;
	}

}
