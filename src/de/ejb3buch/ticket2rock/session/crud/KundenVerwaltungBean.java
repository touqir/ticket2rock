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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Kunde;

/**
 * Stateless Session Bean zur Verwaltung der Kunden Entitäten.
 *
 */
@Stateless
public class KundenVerwaltungBean implements KundenVerwaltungLocal {

	static Logger logger = Logger.getLogger(KundenVerwaltungBean.class);

	@PersistenceContext
	private EntityManager em;



    /**
     *@inheritDoc
     */
	@SuppressWarnings("unchecked")
	public Kunde getKundeByEmail(String email) {
		Query query = em.createQuery("from Kunde k where k.email = :email");
		query.setParameter("email", email);
		List<Kunde> kunden = (List<Kunde>) query.getResultList();
		if ((kunden == null) || (kunden.isEmpty())) {
			return null;
		}
		return kunden.iterator().next();
	}

    /**
     *@inheritDoc
     */
	public void createKunde(Kunde kunde) {
		em.persist(kunde);
	}


}
