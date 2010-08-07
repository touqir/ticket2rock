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
public class MusikerVerwaltungBean implements MusikerVerwaltungLocal {

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
			logger.error(ex);
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
