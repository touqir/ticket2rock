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

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Tournee;

/**
 * Stateless Session Bean zur Verwaltung der Tournee Entitäten.
 * 
 */
@SuppressWarnings("unchecked")
@Stateless
public class TourneeVerwaltungBean implements TourneeVerwaltungLocal {

	static Logger logger = Logger.getLogger(TourneeVerwaltungBean.class);

	@PersistenceContext
	private EntityManager em;

	public List<Tournee> getTourneen() {
		List resultList = em.createQuery(
				"FROM Tournee t ORDER BY name").getResultList();
		logger.debug("Anzahl der gefundenen Tourneen: " + resultList.size());
		return resultList;
	}

    /**
     *@inheritDoc
     */
	public Tournee getTourneeByName(String name) {
		Query query = em.createQuery("from Tournee t where t.name = :name");
		query.setParameter("name", name);
		// query.setParameter("typ",interpretType);
		List<Tournee> tourneen = (List<Tournee>) query.getResultList();
		if ((tourneen == null) || (tourneen.isEmpty())) {
			return null;
		}
		return tourneen.iterator().next();
	}


    /**
     *@inheritDoc
     */
	public void createTournee(Tournee tournee) {
		em.persist(tournee);
	}

    /**
     *@inheritDoc
     */
	public void updateTournee(Tournee tournee) {
		em.merge(tournee);
	}

    /**
     *@inheritDoc
     */	
	public void deleteTournee(Integer tourneeId) {
		Tournee tournee = em.find(Tournee.class, tourneeId.intValue());
		try {

			if (tournee == null) {
				logger.debug("tournee is null");
			} else {
				logger.debug("tournee is not null");
			}
			em.remove(tournee);

		} catch (Exception ex) {
			logger.error(ex);
			throw new EJBException("Error while deleting a tournee");
		}
		logger.debug("removed tournee " + tournee.getName());
		logger.debug("end of method deleteTournee()");

	}

    /**
     *@inheritDoc
     */	
	public Tournee getTourneeById(Integer tourneeId) {
		return em.find(Tournee.class, tourneeId);
	}
}
