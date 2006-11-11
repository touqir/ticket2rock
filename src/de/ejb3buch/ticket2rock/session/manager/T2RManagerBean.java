package de.ejb3buch.ticket2rock.session.manager;

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

@Stateless
public class T2RManagerBean implements T2RManagerLocal, T2RManagerRemote {

	static Logger logger = Logger.getLogger(T2RManagerBean.class);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Band> getBands() {
		List resultList = em.createQuery(
				"FROM Interpret i WHERE typ='B' ORDER BY name").getResultList();
		logger.debug("Anzahl der gefundenen Bands: " + resultList.size());
		return resultList;
	}

	/**
	 * Selektiere eine Band mit einem bestimmten Namen.
	 * 
	 * @param name
	 *            Name der Band
	 * @return Band Entity als POJO. Falls es keine Band mit diesem Namen gibt,
	 *         ist der Rückgabewert null.
	 */
	@SuppressWarnings("unchecked")
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

	public void createBand(Band band) {
		em.persist(band);
	}

	public void updateBand(Band band) {
		em.merge(band);
	}

	public void deleteBand(Band aBand) {
		Band band = em.find(Band.class,aBand.getId());
		try {
			
			if (band == null) {
				logger.debug("band is null");
			} else {
				logger.debug("band is not null");
			}
			//em.merge(band);
			//logger.debug("after merge");
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

	@SuppressWarnings("unchecked")
	public Collection<Musiker> getMusiker() {
		Query query = em.createQuery("from Musiker");
		return (Collection<Musiker>) query.getResultList();
	}

	public Band getBandById(Integer bandId) {
		return em.find(Band.class,bandId);
	}

	public Musiker getMusikerById(Integer musikerId) {
		return em.find(Musiker.class,musikerId);
	}

}
