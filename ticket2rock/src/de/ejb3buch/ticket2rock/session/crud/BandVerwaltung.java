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

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Band;

@Local
public interface BandVerwaltung {

    /**
     * Legt eine Band-Entität in der Persistenzschicht an
     * @param band band pojo mit den Attributen der Band
     */
	public void createBand(Band band);
	
	/**
	 * Selektiert eine Band-Entität mit einen gegebenen Namen
	 * @param name Name der Band, die selektiert werden soll
	 * @return Band Objekt, das den bestimmten Namen besitzt.
	 * Existiert keine Band mit diesem Namen, ist der Rückgabewert null
	 */
	public Band getBandByName(String name);

	/**
	 * Aktualisiert eine Band-Entität in der Persistenzschicht
	 * @param band Band Objekt, das persistiert werden soll
	 */
	public void updateBand(Band band);

	/**
	 * Löscht eine Band-Entität aus der Persistenzschicht
	 * @param bandId id der zu löschenden Band
	 */
	public void deleteBand(Integer bandId);


	
	/**
	 * selektiert alle Band-Entitäten
	 * @return Band-Entitäten
	 */
	public Collection<Band> getBands();
	
	/**
	 * Selektiert eine Band für eine gegebene Id
	 * @param bandId
	 * @return Band Entität, null fallse keine Band-Entität
	 * mit dieser id existiert
	 */
	public Band getBandById(Integer bandId);
    

}
