package de.ejb3buch.ticket2rock.session.crud;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Band;
import de.ejb3buch.ticket2rock.entity.Musiker;

/**
 * Stateless Session Bean zur Verwaltung der Musiker Entitäten.
 *
 */
@Stateless
public class MusikerVerwaltungBean implements MusikerVerwaltung {

	static Logger logger = Logger.getLogger(MusikerVerwaltungBean.class);

	@PersistenceContext
	private EntityManager em;



    /**
     *@inheritDoc
     */
	@SuppressWarnings("unchecked")
	public Musiker getMusikerByName(String name) {
		Query query = em.createQuery("from Musiker i where i.name = :name");
		query.setParameter("name", name);
		// query.setParameter("typ",interpretType);
		List<Musiker> musiker = (List<Musiker>) query.getResultList();
		if ((musiker == null) || (musiker.isEmpty())) {
			return null;
		}
		return musiker.iterator().next();
	}

    /**
     *@inheritDoc
     */
	public void createMusiker(Musiker musiker) {
		em.persist(musiker);
	}

    /**
     *@inheritDoc
     */
	public void updateMusiker(Musiker musiker) {
		em.merge(musiker);
	}

    /**
     *@inheritDoc
     */	
	public void deleteMusiker(Integer musikerId) {
		Musiker musiker = em.find(Musiker.class,musikerId.intValue());
		try {

			Set<Band> musikerBands = (Set<Band>) musiker.getBands();
			if (musikerBands != null) {
				for (Band band : musikerBands) {
					Set<Musiker> bandMusiker = band.getMusiker();
					if ((bandMusiker != null) && (!bandMusiker.isEmpty())) {
						logger.debug("removed band " + band.getName()
								+ " from Musiker " + musiker.getName());
						bandMusiker.remove(musiker);
					}
				}
			}
			em.remove(musiker);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			throw new EJBException("Fehler beim Löschen eines Musikers");
		}

	}
	
	
    /**
     *@inheritDoc
     */
	@SuppressWarnings("unchecked")
	public Collection<Musiker> getMusiker() {
		Query query = em.createQuery("from Musiker");
		return (Collection<Musiker>) query.getResultList();
	}
	
    /**
     *@inheritDoc
     */	
	public Musiker getMusikerById(Integer musikerId) {
		return em.find(Musiker.class,musikerId);
	}

}
