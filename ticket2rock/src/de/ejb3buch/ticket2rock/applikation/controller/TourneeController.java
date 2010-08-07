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

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.applikation.servicelocator.ServiceLocator;
import de.ejb3buch.ticket2rock.entity.Tournee;

public class TourneeController {

	static Logger logger = Logger.getLogger(TourneeController.class);

	
	private ServiceLocator serviceLocator;

	private boolean editMode = false;

	private DataModel tourneeListDataModel = new ListDataModel();

	// ajax test
	private String textAjax;
	public String getTextAjax() {
		return textAjax;
	}

	public void setTextAjax(String textAjax) {
		this.textAjax = textAjax;
	}

	/**
	 * Hole die Liste aller Tournee EJBs, die anschließend im DataModel Objekt gesetzt
	 * wird
	 * 
	 * @return DataModel Objekt, das alle Tourneen beinhaltet.
	 */
	public DataModel getTourneen() {
		Collection<Tournee> tourneen = serviceLocator.getTourneeVerwaltung().getTourneen();
		tourneeListDataModel.setWrappedData(tourneen);
		return tourneeListDataModel;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

}
