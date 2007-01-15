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
 * Stateless Session Bean zur Verwaltung der Band Entitäten.
 * 
 */
@SuppressWarnings("unchecked")
@Stateless
public class BandVerwaltungBean implements BandVerwaltung {

	static Logger logger = Logger.getLogger(BandVerwaltungBean.class);

	@PersistenceContext
	private EntityManager em;

	public List<Band> getBands() {
		List resultList = em.createQuery(
				"FROM Interpret i WHERE typ='B' ORDER BY name").getResultList();
		logger.debug("Anzahl der gefundenen Bands: " + resultList.size());
		return resultList;
	}

    /**
     *@inheritDoc
     */
	public Band getBandByName(String name) {
		Query query = em.createQuery("from Band i where i.name = :name");
		query.setParameter("name", name);
		// query.setParameter("typ",interpretType);
		List<Band> bands = (List<Band>) query.getResultList();
		if ((bands == null) || (bands.isEmpty())) {
			return null;
		}
		return bands.iterator().next();
	}


    /**
     *@inheritDoc
     */
	public void createBand(Band band) {
		em.persist(band);
	}

    /**
     *@inheritDoc
     */
	public void updateBand(Band band) {
		em.merge(band);
	}

    /**
     *@inheritDoc
     */	
	public void deleteBand(Integer bandId) {
		Band band = em.find(Band.class, bandId.intValue());
		try {

			if (band == null) {
				logger.debug("band is null");
			} else {
				logger.debug("band is not null");
			}
			// em.merge(band);
			// logger.debug("after merge");
			Set<Musiker> bandmusiker = (Set<Musiker>) band.getMusiker();
			if (bandmusiker == null) {
				logger.debug("bandmusiker is null");
			} else {
				logger.debug("bandmusiker is not null");
			}
			if (bandmusiker != null) {
				for (Musiker musiker : bandmusiker) {
					Set<Band> bandsOfMusiker = musiker.getBands();
					if ((bandsOfMusiker != null) && (!bandsOfMusiker.isEmpty())) {
						logger.debug("removed band " + band.getName()
								+ " from Musiker " + musiker.getName());
						bandsOfMusiker.remove(band);
					}
				}
			}
			em.remove(band);

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			ex.printStackTrace();
			throw new EJBException("Error while deleting a band");
		}
		logger.debug("removed band " + band.getName());
		logger.debug("end of method deleteBand()");

	}
	
	
    /**
     *@inheritDoc
     */
	public Collection<Musiker> getMusiker() {
		Query query = em.createQuery("from Musiker");
		return (Collection<Musiker>) query.getResultList();
	}

    /**
     *@inheritDoc
     */	
	public Band getBandById(Integer bandId) {
		return em.find(Band.class, bandId);
	}

    /**
     *@inheritDoc
     */	
	public Musiker getMusikerById(Integer musikerId) {
		return em.find(Musiker.class, musikerId);
	}
}
