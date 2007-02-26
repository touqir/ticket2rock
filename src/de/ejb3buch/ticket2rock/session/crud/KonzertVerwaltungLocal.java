package de.ejb3buch.ticket2rock.session.crud;

import java.util.Collection;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Konzert;

@Local
public interface KonzertVerwaltungLocal {

    /**
     * Legt eine Konzert-Entität in der Persistenzschicht an
     * @param konzert pojo mit den Attributen des anzulegenden Konzerts
     */
	public void createConcert(Konzert konzert);
	

	/**
	 * Aktualisiert eine Konzert-Entität in der Persistenzschicht
	 * @param konzert Konzert, das persistiert werden soll
	 */
	public void updateConcert(Konzert konzert);

	/**
	 * Löscht eine Konzert-Entität aus der Persistenzschicht
	 * @param konzertId id des zu löschenden Konzerts
	 */
	public void deleteConcert(Integer konzertId);

	
	/**
	 * selektiert alle Konzert-Entitäten
	 * @return Konzert-Entitäten
	 */
	public Collection<Konzert> getConcerts();
	
	/**
	 * Selektiert ein Konzert für eine gegebene Id
	 * @param konzertId
	 * @return Konzert Entität, null fallse keine Konzert-Entität
	 * mit dieser id existiert
	 */
	public Konzert getConcertById(Integer konzertId);
    

}
