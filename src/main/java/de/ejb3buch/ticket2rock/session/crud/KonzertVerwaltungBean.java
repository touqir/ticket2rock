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
public class KonzertVerwaltungBean implements KonzertVerwaltungLocal {

	static Logger logger = Logger.getLogger(KonzertVerwaltungBean.class);

	@PersistenceContext
	private EntityManager em;

	public List<Konzert> getConcerts() {
		List resultList = em.createQuery("FROM Konzert").getResultList();
		return resultList;
	}

	/**
	 * @inheritDoc
	 */
	public void createConcert(Konzert konzert) {
		em.persist(konzert);
	}

	/**
	 * @inheritDoc
	 */
	public void updateConcert(Konzert konzert) {
		em.merge(konzert);
	}

	/**
	 * @inheritDoc
	 */
	public void deleteConcert(Integer konzertId) {
		Konzert konzert = em.find(Konzert.class, konzertId.intValue());
		try {
			em.remove(konzert);

		} catch (Exception ex) {
			logger.error(ex);
			throw new EJBException("Fehler beim Löschen eines Konzertes");
		}

	}

	/**
	 * @inheritDoc
	 */
	public Konzert getConcertById(Integer konzertId) {
		return em.find(Konzert.class, konzertId);
	}

	@Override
	public Konzert getConcertWithDetailsById(Integer konzertId) {
		Konzert konzert = em.find(Konzert.class, konzertId);
		konzert.getInterpret().getAlben().size();
		konzert.getInterpret().getKonzerte().size();
		konzert.getInterpret().getSongs().size();
		konzert.getInterpret().getTourneen().size();
		return konzert;
	}

}
