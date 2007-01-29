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
	 * Hole die Liste aller Tournee EJBs, die anschlieﬂend im DataModel Objekt gesetzt
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
