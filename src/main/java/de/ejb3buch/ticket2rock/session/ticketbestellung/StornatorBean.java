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
package de.ejb3buch.ticket2rock.session.ticketbestellung;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Ticketbestellung;

@Stateless
@Local(StornatorLocal.class)
@Remote(Stornator.class)
public class StornatorBean implements StornatorLocal {

	@PersistenceContext
	EntityManager em;

	static Logger logger = Logger.getLogger(StornatorBean.class);

	public void storniereBestellung(int bestellnr) {

		Ticketbestellung bestellung = em
				.find(Ticketbestellung.class, bestellnr);
		if (bestellung == null) {
			throw new IllegalArgumentException(String.format(
					"Es gibt keine Ticketbestellung mit der id '%d'!",
					bestellnr));
		}
		logger.info("Storniere Bestellung Nr. " + bestellnr);
		em.remove(bestellung);
	}
}
