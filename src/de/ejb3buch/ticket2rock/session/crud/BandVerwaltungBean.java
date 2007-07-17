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

package de.ejb3buch.ticket2rock.session.crud;

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
public class BandVerwaltungBean implements BandVerwaltungLocal {

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
			//TODO move into entity Band
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
			logger.error(ex);
			throw new EJBException("Error while deleting a band");
		}
		logger.debug("removed band " + band.getName());
		logger.debug("end of method deleteBand()");

	}
		

    /**
     *@inheritDoc
     */	
	public Band getBandById(Integer bandId) {
		return em.find(Band.class, bandId);
	}

}
