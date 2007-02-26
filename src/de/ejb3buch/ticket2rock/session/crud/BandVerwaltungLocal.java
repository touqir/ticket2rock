package de.ejb3buch.ticket2rock.session.crud;

import java.util.Collection;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Band;
import de.ejb3buch.ticket2rock.entity.Musiker;

@Local
public interface BandVerwaltungLocal {

    /**
     * Legt eine Band-Entität in der Persistenzschicht an
     * @param band band pojo mit den Attributen der Band
     */
	public void createBand(Band band);
	
	/**
	 * Selektiert eine Band-Entität mit einen gegebenen Namen
	 * @param name Name der Band, die selektiert werden soll
	 * @return Band Objekt, das den bestimmten Namen besitzt.
	 * Existiert keine Band mit diesem Namen, ist der Rückgabewert null
	 */
	public Band getBandByName(String name);

	/**
	 * Aktualisiert eine Band-Entität in der Persistenzschicht
	 * @param band Band Objekt, das persistiert werden soll
	 */
	public void updateBand(Band band);

	/**
	 * Löscht eine Band-Entität aus der Persistenzschicht
	 * @param bandId id der zu löschenden Band
	 */
	public void deleteBand(Integer bandId);

//TODO move the Musiker methods to the interface for MusikerVerwaltungLocal	
	/**
	 * selektiert alle Musiker-Entitäten
	 * @return Musiker-Entitäten
	 */
	public Collection<Musiker> getMusiker();
	
	/**
	 * selektiert alle Band-Entitäten
	 * @return Band-Entitäten
	 */
	public Collection<Band> getBands();
	
	/**
	 * Selektiert eine Band für eine gegebene Id
	 * @param bandId
	 * @return Band Entität, null fallse keine Band-Entität
	 * mit dieser id existiert
	 */
	public Band getBandById(Integer bandId);
    
	/**
	 * Selektiert einen Musiker für eine gegebene Id
	 * @param musikerId
	 * @return Musiker Entität, null falls kein Musiker-Entität
	 * mit dieser id existiert
	 */
	public Musiker getMusikerById(Integer musikerId);

}
