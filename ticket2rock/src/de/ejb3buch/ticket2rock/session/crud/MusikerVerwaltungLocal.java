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

import de.ejb3buch.ticket2rock.entity.Musiker;

@Local
public interface MusikerVerwaltungLocal {

    /**
     * Legt eine Musiker-Entität in der Persistenzschicht an
     * @param musiker Musiker-POJO mit den Attributen der Band
     */
	public void createMusiker(Musiker musiker);
	
	/**
	 * Selektiert eine Musiker-Entität mit einen gegebenen Namen
	 * @param name Name der Musiker, die selektiert werden soll
	 * @return Musiker Objekt, das den bestimmten Namen besitzt.
	 * Existiert kein Musiker mit diesem Namen, ist der Rückgabewert null
	 */
	public Musiker getMusikerByName(String name);

	/**
	 * Aktualisiert eine Musiker-Entität in der Persistenzschicht
	 * @param Musiker Musiker Objekt, das persistiert werden soll
	 */
	public void updateMusiker(Musiker musiker);
	/**
	 * Löscht eine Musiker-Entität aus der Persistenzschicht
	 * @param musiker id des zu löschenden Musikers
	 */
	public void deleteMusiker(Integer musikerId);

	/**
	 * selektiert alle Musiker-Entitäten
	 * @return Musiker-Entitäten
	 */
	public Collection<Musiker> getMusiker();

    
	/**
	 * Selektiert einen Musiker für eine gegebene Id
	 * @param musikerId
	 * @return Musiker Entität, null falls kein Musiker-Entität
	 * mit dieser id existiert
	 */
	public Musiker getMusikerById(Integer musikerId);

}
