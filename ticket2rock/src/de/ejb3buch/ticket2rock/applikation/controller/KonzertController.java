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

import java.util.Date;
import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.applikation.servicelocator.ServiceLocator;
import de.ejb3buch.ticket2rock.entity.Konzert;

public class KonzertController {

	static Logger logger = Logger.getLogger(KonzertController.class);

	private ServiceLocator serviceLocator;
	private TicketController ticketController;
	
	private String ortsName;
	
	private Date vonDatum;
	
	private Date bisDatum;
	
	private DataModel konzertListDataModel = new ListDataModel();
	
	private Konzert konzert;
	
	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}	
	

	public String getOrtsName() {
		return ortsName;
	}

	public void setOrtsName(String ortsName) {
		this.ortsName = ortsName;
	}	

	public String search() {
		List<Konzert> konzerte = this.serviceLocator.getAuskunft().sucheKonzerte(ortsName,vonDatum,bisDatum);
		logger.debug("detected number of concerts: " + konzerte.size());
		this.konzertListDataModel.setWrappedData(konzerte);
		return "konzertsuchergebnis";
	}
	
	public String selectConcert() {
		konzert = (Konzert) this.konzertListDataModel.getRowData();	
		ticketController.setKonzert(konzert);
		return "ticketbestellung";
	}	
	

	public DataModel getKonzertSuchErgebnis() {
		return konzertListDataModel;
	}

	public void setVonDatum(Date vonDatum) {
		this.vonDatum = vonDatum;
	}
	
	public Date getVonDatum() {
		return vonDatum;
	}

	public Date getBisDatum() {
		return bisDatum;
	}

	public void setBisDatum(Date bisDatum) {
		this.bisDatum = bisDatum;
	}

	public Konzert getKonzert() {
		return konzert;
	}

	public void setKonzert(Konzert konzert) {
		this.konzert = konzert;
	}

	public TicketController getTicketController() {
		return ticketController;
	}

	public void setTicketController(TicketController ticketController) {
		this.ticketController = ticketController;
	}

}
