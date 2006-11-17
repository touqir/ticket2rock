package de.ejb3buch.ticket2rock.applikation.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectManyListbox;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.applikation.businessdelegate.BandVerwaltungDelegate;
import de.ejb3buch.ticket2rock.applikation.businessdelegate.BandVerwaltungEJB3Delegate;
import de.ejb3buch.ticket2rock.applikation.helper.SelectItemsComparator;
import de.ejb3buch.ticket2rock.applikation.helper.SelectItemsMapBuilder;
import de.ejb3buch.ticket2rock.applikation.model.BandBackingBean;

public class BandController {

	static Logger logger = Logger.getLogger(BandController.class);

	private BandVerwaltungDelegate myBandVerwalter = new BandVerwaltungEJB3Delegate();

	private boolean editMode = false;

	private BandBackingBean band = null;

	private DataModel bandListDataModel = new ListDataModel();

	private Map<String, SelectItem> musikerMap;

	private Map<String, SelectItem> bandMusikerMap;

	private List<String> musikerList = new ArrayList<String>();

	private List<String> bandMusikerList = new ArrayList<String>();

	private HtmlSelectManyListbox musikerSelectComponent;

	private HtmlSelectManyListbox bandMusikerSelectComponent;

	/**
	 * Über den BusinessDelegate alle Bands aus der Datenbank selektieren und in
	 * Form eines DataModel Objektes zurückgeben. Ein DataModel Objekt kann in
	 * einer jsp mit dem dataTable-Tag zur Anzeige gebracht werden
	 * 
	 * @return DataModel Objekt, das alle Bands beinhaltet.
	 */
	public DataModel getBands() {
		bandListDataModel.setWrappedData(myBandVerwalter.getBands());
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
		this.bandMusikerMap = new HashMap<String, SelectItem>();
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
			for (String bandMusikerName : bandMusikerNamen) {
				musikerMap.remove(bandMusikerName);
			}
		}
		return "bandform";
	}

	public String addBand() {
		logger.debug("adding a band");
		if ((myBandVerwalter.getBandByName(band.getName())) != null) {

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = MessageUtils.createErrorMessage(
					"bandform_error_bandexists", context);
			context.addMessage("bandForm:bandname", msg);
			return "error";
		}
		band.setMusikerIdListe(this.bandMusikerMap.keySet());
		myBandVerwalter.createBand(band);
		logger.debug("added a band, returning to bandlist");
		return "bandlist";
	}

	public boolean isEditMode() {
		return editMode;
	}

	public String updateBand() {
		logger.debug("T2RController.updateBand() called ");
		band.setMusikerIdListe(this.bandMusikerMap.keySet());
		myBandVerwalter.updateBand(band);
		return "bandlist";
	}

	public String deleteBand() {
		logger.debug("about to delete a band");
		band = (BandBackingBean) bandListDataModel.getRowData();
		myBandVerwalter.deleteBand(band.getId());

		logger.debug("deleted a band");
		return "bandlist";
	}
	
	
	public String cancel() {
	  return "bandlist";	  	
	}

	public BandBackingBean getBand() {
		return band;
	}

	public void setBand(BandBackingBean band) {
		this.band = band;
	}

	private Map<String, SelectItem> populateMusikerMap() {
		SelectItemsMapBuilder mapBuilder = new SelectItemsMapBuilder();
		myBandVerwalter.buildMusikerCollection(mapBuilder);
		return mapBuilder.getSelectItemsMap();
	}

	private Map<String, SelectItem> populateBandMusikerMap() {
		SelectItemsMapBuilder mapBuilder = new SelectItemsMapBuilder();
		myBandVerwalter.buildBandMusikerCollection(mapBuilder, band.getId());
		return mapBuilder.getSelectItemsMap();
	}

	
	//TODO refacture subsequent two methods 
	@SuppressWarnings("unchecked")
	public void musikerSelected(ValueChangeEvent event) {

		logger.debug(" -------  musikerSelected is called");
		logger.debug("new value: " + event.getNewValue());
		List<Long> newValues = (ArrayList<Long>) event.getNewValue();
		boolean removedItem = false;
		for (Long musikerId : newValues) {
			SelectItem musikerSelectItem = musikerMap.get(musikerId.toString());
			bandMusikerMap.put(musikerId.toString(), musikerSelectItem);
			musikerMap.remove(musikerId.toString());
			removedItem = true;
		}
		if (removedItem) {
			// nowendig, damit sich das Entfernen eines Items nicht als
			// valueChange in der Komponente registriert wird
			musikerSelectComponent.setSubmittedValue(null);
			musikerSelectComponent.setValue(null);
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.renderResponse();
	}

	@SuppressWarnings("unchecked")
	public void bandMusikerSelected(ValueChangeEvent event) {
		logger.debug(" -------  bandMusikerSelected is called");
		logger.debug("new value: " + event.getNewValue());
		List<Long> newValues = (ArrayList<Long>) event.getNewValue();
		boolean removedItem = false;
		for (Long musikerId : newValues) {
			SelectItem selectItem = bandMusikerMap.get(musikerId.toString());
			musikerMap.put(musikerId.toString(), selectItem);
			bandMusikerMap.remove(musikerId.toString());
			removedItem = true;
		}
		if (removedItem) {
			// nowendig, damit sich das Entfernen eines Items nicht als
			// valueChange in der Komponente registriert wird
			bandMusikerSelectComponent.setSubmittedValue(null);
			bandMusikerSelectComponent.setValue(null);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.renderResponse();
	}

	public List<String> getMusikerList() {
		return musikerList;
	}

	public void setMusikerList(List<String> musikerList) {
		this.musikerList = musikerList;
	}

	
	@SuppressWarnings("unchecked")
	public Collection<SelectItem> getMusikerSelectItems() {
       return sortSelectedItems(musikerMap.values());
	}

	
	@SuppressWarnings("unchecked")
	public Collection<SelectItem> getBandMusikerSelectItems() {
		return sortSelectedItems(bandMusikerMap.values());
	}
	
	@SuppressWarnings("unchecked")
	private Collection sortSelectedItems(Collection<SelectItem> selectItems) {
		SortedSet sortedSet = new TreeSet<SelectItem>(new SelectItemsComparator());
		sortedSet.addAll(selectItems);
		return sortedSet;
		
	}

	public List<String> getBandMusikerList() {
		return bandMusikerList;
	}

	public void setBandMusikerList(List<String> bandMusikerList) {
		this.bandMusikerList = bandMusikerList;
	}

	public HtmlSelectManyListbox getBandMusikerSelectComponent() {
		return bandMusikerSelectComponent;
	}

	public void setBandMusikerSelectComponent(
			HtmlSelectManyListbox bandMusikerSelectComponent) {
		this.bandMusikerSelectComponent = bandMusikerSelectComponent;
	}

	public HtmlSelectManyListbox getMusikerSelectComponent() {
		return musikerSelectComponent;
	}

	public void setMusikerSelectComponent(
			HtmlSelectManyListbox musikerSelectComponent) {
		this.musikerSelectComponent = musikerSelectComponent;
	}
	

}
