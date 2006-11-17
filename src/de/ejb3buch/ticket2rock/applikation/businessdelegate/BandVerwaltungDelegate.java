package de.ejb3buch.ticket2rock.applikation.businessdelegate;

import java.util.List;

import de.ejb3buch.ticket2rock.applikation.helper.IViewCollectionBuilder;
import de.ejb3buch.ticket2rock.applikation.model.BandBackingBean;


public interface BandVerwaltungDelegate {
    /**
     * Selektiert alle Band-Entitäten
     * @return Liste aller Band-Entitäten representiert
     * durch BandBackingBeans
     */
	public List<BandBackingBean> getBands();

	/**
	 * Kreiert eine neue Band-Entität
	 * @param band BandBackingBean mit den Attributen der
	 * neu anzulegenden Band-Entität
	 */
	public void createBand(BandBackingBean band);
	
    /**
     * Selektiert eine Band-Entität mit einem gegebenen 
     * Bandnamen
     * @param name Name der Band
     * @return Band-Entität mit dem gegebenen Namen representiert
     * durch eine BandBackingBean
     */
	public BandBackingBean getBandByName(String name);

	/**
	 * Aktualisiere die Attribute der Band-Entity in der 
	 * Persistenzschicht
	 * @param bandBackingBean BandBackingBean mit den neuen 
	 * Attributen
	 */
	public void updateBand(BandBackingBean bandBackingBean);

	/**
	 * Lösche eine Band-Entität. 
	 * @param bandId id der zu löschenden Band Entität
	 */
	public void deleteBand(Integer bandId);
	
	
    /**
     * generiere eine Kollektion der Musiker einer Band. 
     * Die Representation der Kollektion ist abhängig des übergebenen
     * Builders (Anwendung des GoF Builder-Patterns)
     * @param collectionBuilder Ein Builder, der eine Kollektion erstellt, die
     * im View verwendet werden kann
     */
	public void buildMusikerCollection(IViewCollectionBuilder collectionBuilder);

    /**
     * generiere eine Kollektion der Musiker-Entitäten. 
     * Die Representation der Kollektion ist abhängig des übergebenen
     * Builders (Anwendung des GoF Builder-Patterns)
     * @param collectionBuilder Ein Builder, der eine Kollektion erstellt, die
     * im View verwendet werden kann
     */	
	public void buildBandMusikerCollection(IViewCollectionBuilder collectionBuilder,Integer bandId);
	
}
