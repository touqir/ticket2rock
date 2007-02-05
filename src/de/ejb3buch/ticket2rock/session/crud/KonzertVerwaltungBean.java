package de.ejb3buch.ticket2rock.session.crud;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Konzert;

/**
 * Stateless Session Bean zur Verwaltung der Konzert Entitäten.
 * 
 */
@SuppressWarnings("unchecked")
@Stateless
public class KonzertVerwaltungBean implements KonzertVerwaltung {

	static Logger logger = Logger.getLogger(KonzertVerwaltungBean.class);

	@PersistenceContext
	private EntityManager em;

	public List<Konzert> getConcerts() {
		List resultList = em.createQuery(
				"FROM Konzert").getResultList();
		return resultList;
	}


    /**
     *@inheritDoc
     */
	public void createConcert(Konzert konzert) {
		em.persist(konzert);
	}

    /**
     *@inheritDoc
     */
	public void updateConcert(Konzert konzert) {
		em.merge(konzert);
	}

    /**
     *@inheritDoc
     */	
	public void deleteConcert(Integer konzertId) {
		Konzert konzert = em.find(Konzert.class, konzertId.intValue());
		try {
			em.remove(konzert);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			throw new EJBException("Fehler beim Löschen eines Konzertes");
		}

	}
	
    /**
     *@inheritDoc
     */	
	public Konzert getConcertById(Integer konzertId) {
		return em.find(Konzert.class, konzertId);
	}

 
}
