package de.ejb3buch.ticket2rock.session.crud;

import java.util.Collection;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Tournee;

@Local
public interface TourneeVerwaltungLocal {

    /**
     * Legt eine Tournee-Entität in der Persistenzschicht an
     * @param tournee pojo mit den Attributen der Tournee
     */
	public void createTournee(Tournee tournee);
	
	/**
	 * Selektiert eine Tournee-Entität mit einen gegebenen Namen
	 * @param name Name der Tournee, die selektiert werden soll
	 * @return Tournee Objekt, das den bestimmten Namen besitzt.
	 * Existiert keine Tournee mit diesem Namen, ist der Rückgabewert null
	 */
	public Tournee getTourneeByName(String name);

	/**
	 * Aktualisiert eine Tournee-Entität in der Persistenzschicht
	 * @param tournee Tournee Objekt, das persistiert werden soll
	 */
	public void updateTournee(Tournee tournee);

	/**
	 * Löscht eine Tournee-Entität aus der Persistenzschicht
	 * @param tourneeId id der zu löschenden Tournee
	 */
	public void deleteTournee(Integer tourneeId);

	/**
	 * selektiert alle Tournee-Entitäten
	 * @return Tournee-Entitäten
	 */
	public Collection<Tournee> getTourneen();
	
	/**
	 * Selektiert eine Tournee für eine gegebene Id
	 * @param tourneeId
	 * @return Tournee Entität, null fallse keine Tournee-Entität
	 * mit dieser id existiert
	 */
	public Tournee getTourneeById(Integer tourneeId);
}
