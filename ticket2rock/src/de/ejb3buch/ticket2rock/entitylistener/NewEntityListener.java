/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerten. 
 *
 *  Copyright (C) 2011
 *  Jo Ehm, Stefan M. Heldt, Oliver Ihns, Holger Koschek,
 *  Carsten Sahling, Roman Schloemmer, Norman Erck, Daniel Steinhöfer,
 *  Carl A. Düvel.
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
package de.ejb3buch.ticket2rock.entitylistener;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostPersist;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.News;

/**
 * Der NewEntityListener "lauscht" auf neue Entitäten in der ticket2rock-
 * Datenbank. Für bestimmte Objekttypen (z.B. Konzerte) wird ein News- Objekt
 * erzeugt, das wiederum über verschiedene Kanäle publiziert wird (siehe Klasse
 * de.ejb3buch.ticket2rock.entity.News).
 */

public class NewEntityListener {

	private Logger logger = Logger.getRootLogger();

	@PersistenceContext
	private EntityManager em;

	@PostPersist
	protected void newEntity(Object entity) {
		logger.info("PostPersist for entity " + entity);
		try {
			News news = new News(entity);

			em.persist(news);

		} catch (Exception e) {
			// Keine Nachrichten sind gute Nachrichten ;-)
		}
	}
}
