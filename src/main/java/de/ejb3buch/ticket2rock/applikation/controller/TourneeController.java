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
package de.ejb3buch.ticket2rock.applikation.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Tournee;
import de.ejb3buch.ticket2rock.session.crud.TourneeVerwaltungLocal;

@Named("TourneeController")
@SessionScoped
public class TourneeController implements Serializable {

	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(TourneeController.class);
	
	@Inject
	private TourneeVerwaltungLocal tourneeVerwaltungLocal;

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
		Collection<Tournee> tourneen = tourneeVerwaltungLocal.getTourneen();
		tourneeListDataModel.setWrappedData(tourneen);
		return tourneeListDataModel;
	}

	public boolean isEditMode() {
		return editMode;
	}

}
