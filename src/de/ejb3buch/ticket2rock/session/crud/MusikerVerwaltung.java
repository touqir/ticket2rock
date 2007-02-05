package de.ejb3buch.ticket2rock.session.crud;

import java.util.Collection;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Musiker;

@Local
public interface MusikerVerwaltung {

    /**
     * Legt eine Musiker-Entität in der Persistenzschicht an
     * @param musiker Musiker-POJO mit den Attributen der Band
     */
	public void createMusiker(Musiker musiker);
	
	/**
	 * Selektiert eine Musiker-Entität mit einen gegebenen Namen
	 * @param name Name der Musiker, die selektiert werden soll
	 * @return Musiker Objekt, das den bestimmten Namen besitzt.
	 * Existiert kein Musiker mit diesem Namen, ist der Rückgabewert null
	 */
	public Musiker getMusikerByName(String name);

	/**
	 * Aktualisiert eine Musiker-Entität in der Persistenzschicht
	 * @param Musiker Musiker Objekt, das persistiert werden soll
	 */
	public void updateMusiker(Musiker musiker);
	/**
	 * Löscht eine Musiker-Entität aus der Persistenzschicht
	 * @param musiker id des zu löschenden Musikers
	 */
	public void deleteMusiker(Integer musikerId);

	/**
	 * selektiert alle Musiker-Entitäten
	 * @return Musiker-Entitäten
	 */
	public Collection<Musiker> getMusiker();

    
	/**
	 * Selektiert einen Musiker für eine gegebene Id
	 * @param musikerId
	 * @return Musiker Entität, null falls kein Musiker-Entität
	 * mit dieser id existiert
	 */
	public Musiker getMusikerById(Integer musikerId);

}
