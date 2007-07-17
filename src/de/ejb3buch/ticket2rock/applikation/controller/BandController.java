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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

import de.ejb3buch.ticket2rock.applikation.helper.IViewCollectionBuilder;
import de.ejb3buch.ticket2rock.applikation.helper.SelectItemsComparator;
import de.ejb3buch.ticket2rock.applikation.helper.SelectItemsMapBuilder;
import de.ejb3buch.ticket2rock.applikation.model.BandBackingBean;
import de.ejb3buch.ticket2rock.applikation.servicelocator.ServiceLocator;
import de.ejb3buch.ticket2rock.entity.Band;
import de.ejb3buch.ticket2rock.entity.Musiker;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltungLocal;

public class BandController {

	static Logger logger = Logger.getLogger(BandController.class);

	
	private ServiceLocator serviceLocator;

	private boolean editMode = false;

	private BandBackingBean bandBackingBean = null;

	private DataModel bandListDataModel = new ListDataModel();

	private Map<String, SelectItem> musikerMap;

	private Map<String, SelectItem> bandMusikerMap;

	//TODO might be obsolete
	private List<String> musikerList = new ArrayList<String>();

	//TODO might be obsolete
	private List<String> bandMusikerList = new ArrayList<String>();

	private HtmlSelectManyListbox musikerSelectComponent;

	private HtmlSelectManyListbox bandMusikerSelectComponent;

	// ajax test
	private String textAjax;
	public String getTextAjax() {
		return textAjax;
	}

	public void setTextAjax(String textAjax) {
		this.textAjax = textAjax;
	}

	/**
	 * Hole die Liste aller Band EJBs und konvertiere diese in eine Liste von
	 * BandBacking Beans, die anschließend im DataModel Objekt gesetzt
	 * wird
	 * 
	 * @return DataModel Objekt, das alle Bands beinhaltet.
	 */
	public DataModel getBands() {
		List<BandBackingBean> bandBackingBeans = new ArrayList<BandBackingBean>();
		Collection<Band> bands = serviceLocator.getBandVerwaltung().getBands();
		for (Band band:bands) {
		   	bandBackingBeans.add(new BandBackingBean(band));
		}
		bandListDataModel.setWrappedData(bandBackingBeans);
		return bandListDataModel;
	}

	/**
	 * Vorbereitungen treffen, um eine weiter Band anzulegen
	 */
	public String addNewBand() {
		logger.debug("preparing bandBackingBean input form");
		editMode = false;
		bandBackingBean = new BandBackingBean();
		// fülle die Musiker Maps
		this.bandMusikerMap = new HashMap<String, SelectItem>();
		this.musikerMap = this.populateMusikerMap();
		return "bandform";
	}

	/**
	 * Vorbereitungen treffen, um eine Band zu editieren
	 */
	public String editBand() {
		bandBackingBean = (BandBackingBean) bandListDataModel.getRowData();
		this.editMode = true;
		// fülle die Musiker Maps
		this.bandMusikerMap = this.populateBandMusikerMap();
		this.musikerMap = this.populateMusikerMap();
		// entferne die bereits zugeordeneten Musiker von der Map der zu Verfügung
		// stehenden Musiker
		if (bandMusikerMap != null) {
			Collection<String> bandMusikerNamen = bandMusikerMap.keySet();
			for (String bandMusikerName : bandMusikerNamen) {
				musikerMap.remove(bandMusikerName);
			}
		}
		return "bandform";
	}

	/**
	 * erstellt und persistiert eine neue Band Entität
	 * @return Identifier für die JSF Navigation
	 */
	public String addBand() {
		BandVerwaltungLocal bandVerwaltung = serviceLocator.getBandVerwaltung();
		// Überprüfe, ob es eine Band mit diesem Namen bereits gibt		
		if (bandVerwaltung.getBandByName(bandBackingBean.getName()) != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = MessageUtils.createErrorMessage(
					"bandform_error_bandexists", context);
			context.addMessage("bandForm:bandname", msg);
			return "error";
		}
		// setzen der ausgewählten Bandmusiker im BandBackingBean
		bandBackingBean.setMusikerIdListe(this.bandMusikerMap.keySet());
		bandVerwaltung.createBand(bandBackingBean.getBand());
		return "bandlist";
	}

	public boolean isEditMode() {
		return editMode;
	}

	/**
	 * Aktualisiert eine Band Entität mit den aktuellen Werten und persistiert 
	 * diese in der Datenbank.
	 * @return Identifier für die JSF Navigation
	 */
	public String updateBand() {
		logger.debug("T2RController.updateBand() called ");
		bandBackingBean.setMusikerIdListe(this.bandMusikerMap.keySet());
		serviceLocator.getBandVerwaltung().updateBand(bandBackingBean.getBand());
		return "bandlist";
	}

	/**
	 * Löscht eine Band Entität
	 * @return Identifier für die JSF Navigation
	 */
	public String deleteBand() {
		bandBackingBean = (BandBackingBean) bandListDataModel.getRowData();
		serviceLocator.getBandVerwaltung().deleteBand(bandBackingBean.getId());
		return "bandlist";
	}

	/**
	 * 
	 * @return Identifier für die JSF Navigation
	 */
	public String cancel() {
		return "bandlist";
	}

	public BandBackingBean getBand() {
		return bandBackingBean;
	}

	public void setBand(BandBackingBean band) {
		this.bandBackingBean = band;
	}

	private Map<String, SelectItem> populateMusikerMap() {
		SelectItemsMapBuilder mapBuilder = new SelectItemsMapBuilder();
		this.buildMusikerCollection(mapBuilder);
		return mapBuilder.getSelectItemsMap();
	}

	private Map<String, SelectItem> populateBandMusikerMap() {
		SelectItemsMapBuilder mapBuilder = new SelectItemsMapBuilder();
		this.buildBandMusikerCollection(mapBuilder, bandBackingBean.getId());
		return mapBuilder.getSelectItemsMap();
	}

	// TODO refacture subsequent two methods
	@SuppressWarnings("unchecked")
	public void musikerSelected(ValueChangeEvent event) {

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

	//TODO might be obsolete
	public List<String> getMusikerList() {
		return musikerList;
	}

	//TODO might be obsolete
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
		SortedSet sortedSet = new TreeSet<SelectItem>(
				new SelectItemsComparator());
		sortedSet.addAll(selectItems);
		return sortedSet;

	}
	
    /**
     * generiere eine Kollektion der Musiker-Entitäten. 
     * Die Representation der Kollektion ist abhängig vom übergebenen
     * Builder Objekt (Anwendung des GoF Builder-Patterns)
     * @param collectionBuilder Ein Builder, der eine Kollektion erstellt, die
     * im View verwendet werden kann
     */
	private void buildBandMusikerCollection(IViewCollectionBuilder collectionBuilder,Integer bandId) {
		Band band = serviceLocator.getBandVerwaltung().getBandById(bandId);
		Set<Musiker> musikerSet = band.getMusiker();
		if (musikerSet != null) {
			for (Musiker musiker : musikerSet) {
              collectionBuilder.buildItem(Integer.toString(musiker.getId()),musiker.getName());
			}
		}
	}
	
	 /**
     * generiere eine Kollektion der Musiker einer Band. 
     * Die Representation der Kollektion ist abhängig vom übergebenen
     * Builder Objekt (Anwendung des GoF Builder-Patterns)
     * @param collectionBuilder Ein Builder, der eine Kollektion erstellt, die
     * im View verwendet werden kann
     */
	private void buildMusikerCollection(IViewCollectionBuilder collectionBuilder) {
		Collection<Musiker> musikerSet = serviceLocator.getMusikerVerwaltung().getMusiker();
		if (musikerSet != null) {
			for (Musiker musiker : musikerSet) {
              collectionBuilder.buildItem(Integer.toString(musiker.getId()),musiker.getName());
			}
		}
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

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

}
