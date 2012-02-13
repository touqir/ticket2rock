/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerten. 
 *
 *  Copyright (C) 2006-2011
 *  Holisticon AG
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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.LocalBinding;

import de.ejb3buch.ticket2rock.entity.Band;
import de.ejb3buch.ticket2rock.entity.Musiker;

/**
 * Stateless Session Bean zur Verwaltung der Band Entitäten.
 * 
 */
@SuppressWarnings("unchecked")
@Stateless
@LocalBinding(jndiBinding = BandVerwaltungLocal.JNDI_BINDING)
public class BandVerwaltungBean implements BandVerwaltungLocal {

	static Logger logger = Logger.getLogger(BandVerwaltungBean.class);

	@PersistenceContext
	private EntityManager em;

	public List<Band> getBands() {
		List<Band> resultList = em.createQuery(
				"SELECT i FROM Band i ORDER BY i.name").getResultList();
		logger.debug("Anzahl der gefundenen Bands: " + resultList.size());
		return resultList;
	}

	/**
	 * @inheritDoc
	 */
	public Band getBandByName(String name) {
		Query query = em
				.createQuery("select i from Band i where i.name = :name");
		query.setParameter("name", name);
		List<Band> bands = (List<Band>) query.getResultList();
		if ((bands == null) || (bands.isEmpty())) {
			return null;
		}
		return bands.iterator().next();
	}

	/**
	 * @inheritDoc
	 */
	public void createBand(Band band) {
		em.persist(band);
	}

	/**
	 * @inheritDoc
	 */
	public void updateBand(Band band) {
		em.merge(band);
	}

	/**
	 * @inheritDoc
	 */
	public void deleteBand(Integer bandId) {
		Band band = em.find(Band.class, bandId.intValue());
		Set<Musiker> bandmusiker = (Set<Musiker>) band.getMusiker();
		for (Musiker musiker : bandmusiker) {
			Set<Band> bandsOfMusiker = musiker.getBands();
			if ((bandsOfMusiker != null) && (!bandsOfMusiker.isEmpty())) {
				logger.debug("removed band " + band.getName()
						+ " from Musiker " + musiker.getName());
				bandsOfMusiker.remove(band);
			}
		}
		em.remove(band);
	}

	/**
	 * @inheritDoc
	 */
	public Band getBandById(Integer bandId) {
		return em.find(Band.class, bandId);
	}

}
