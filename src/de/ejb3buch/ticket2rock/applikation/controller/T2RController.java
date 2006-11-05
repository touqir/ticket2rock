package de.ejb3buch.ticket2rock.applikation.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.applikation.businessdelegate.T2RManagerDelegate;
import de.ejb3buch.ticket2rock.applikation.businessdelegate.T2RManagerEJB3Delegate;
import de.ejb3buch.ticket2rock.applikation.model.BandBackingBean;

public class T2RController {

	static Logger logger = Logger.getLogger(T2RController.class);

	private T2RManagerDelegate myT2RManager = new T2RManagerEJB3Delegate();

	private boolean editMode = false;

	private BandBackingBean band = null;
	
	private DataModel bandListDataModel = new ListDataModel();
	
	private Map<String,Integer> musikerMap;
	
	private Map<String,Integer> bandMusikerMap;

	/**
	 * Über den BusinessDelegate alle Bands aus der Datenbank selektieren und in
	 * Form eine DataModel Objektes zurückgeben. Ein DataModel Objekt kann in
	 * einer jsp mit dem dataTable-Tag zur Anzeige gebracht werden
	 * 
	 * @return DataModel Objekt, das alle Bands beinhaltet.
	 */
	public DataModel getBands() {
		bandListDataModel.setWrappedData(myT2RManager.getBands());
		return bandListDataModel;
	}

	/**
	 * Vorbereitungen treffen, um eine weiter Band anzulegen
	 */
	public String addNewBand() {
		logger.debug("preparing band input form");
		editMode = false;
		band = new BandBackingBean();
        // fülle die Musiker Maps
		this.bandMusikerMap = new HashMap<String,Integer>();
		this.musikerMap = this.populateMusikerMap();
		return "bandform";
	}

	/**
	 * Vorbereitungen treffen, um eine Band zu editieren
	 */
	public String editBand() {
		band = (BandBackingBean) bandListDataModel.getRowData();
		this.editMode = true;
		// fülle die Musiker Maps
		this.bandMusikerMap = this.populateBandMusikerMap();
		this.musikerMap = this.populateMusikerMap();
		// entferne die Musiker der Band von der Map der zu Verfügung
		// stehenden Musiker
		if (bandMusikerMap != null) {
          Collection<String> bandMusikerNamen = bandMusikerMap.keySet();
          for (String bandMusikerName:bandMusikerNamen) {
        	 musikerMap.remove(bandMusikerName); 
          }
		}
		return "bandform";
	}

	public String addBand() {
		logger.debug("adding a band");
		if ((myT2RManager.getBandByName(band.getName())) != null) {

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = MessageUtils.createErrorMessage(
					"bandform_error_bandexists", context);
			context.addMessage("bandForm:bandname", msg);
			return "error";
		}
		myT2RManager.createBand(band);
		logger.debug("added a band, returning to bandlist");
		return "bandlist";
	}

	public boolean isEditMode() {
		return editMode;
	}

	public String updateBand() {
		myT2RManager.updateBand(band);
		return "bandlist";
	}

	public String deleteBand() {
		logger.debug("about to delete a band");
		band = (BandBackingBean) bandListDataModel.getRowData();
		myT2RManager.deleteBand(band);

		logger.debug("deleted a band");
		return "bandlist";
	}

	public BandBackingBean getBand() {
		return band;
	}

	public void setBand(BandBackingBean band) {
		this.band = band;
	}

	private Map<String,Integer> populateMusikerMap() {
		return myT2RManager.getMusikerMap();
	}

	
	private Map<String,Integer> populateBandMusikerMap() {
		return myT2RManager.getBandMusikerMap(band.getId());
	}

	public Map<String, Integer> getBandMusikerMap() {
		return bandMusikerMap;
	}

	public Map<String, Integer> getMusikerMap() {
		return musikerMap;
	}
}
