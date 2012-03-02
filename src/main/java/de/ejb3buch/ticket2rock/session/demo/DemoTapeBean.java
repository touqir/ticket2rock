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
package de.ejb3buch.ticket2rock.session.demo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.ejb3buch.ticket2rock.entity.Album;
import de.ejb3buch.ticket2rock.entity.Band;
import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entity.Musiker;
import de.ejb3buch.ticket2rock.entity.News;
import de.ejb3buch.ticket2rock.entity.Song;
import de.ejb3buch.ticket2rock.entity.Ticketbestellung;
import de.ejb3buch.ticket2rock.entity.Tournee;

/**
 * DemoTape ist die Testklasse des Ticket2Rock-Systems - die Spielwiese für
 * neugierige EJB3-Forscher.
 */

@SuppressWarnings("unchecked")
@Stateless
public class DemoTapeBean implements DemoTape {

	@PersistenceContext
	protected EntityManager em;

	public List<Band> getBands() {
		return em.createQuery("FROM Interpret i WHERE typ='B' ORDER BY name")
				.getResultList();
	}

	public List<Musiker> getMusiker() {
		return em.createQuery("FROM Interpret i WHERE typ='M'").getResultList();
	}

	public List<Song> getSongs() {
		return em.createQuery("FROM Song").getResultList();
	}

	public List<Album> getAlben() {
		return em.createQuery("FROM Album a ORDER BY erscheinungsdatum")
				.getResultList();
	}

	public List<Tournee> getTourneen() {
		return em.createQuery("FROM Tournee").getResultList();
	}

	public List<Konzert> getKonzerte() {
		return em.createQuery("FROM Konzert").getResultList();
	}

	public List<News> getNews() {
		return em.createQuery("FROM News n ORDER BY datum DESC")
				.getResultList();
	}

	public List<Ticketbestellung> getBestellungen() {
		return em.createQuery("FROM Ticketbestellung").getResultList();
	}
}
