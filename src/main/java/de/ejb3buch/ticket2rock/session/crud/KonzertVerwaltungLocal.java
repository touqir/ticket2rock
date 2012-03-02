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

import java.util.Collection;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Konzert;

@Local
public interface KonzertVerwaltungLocal {

    /**
     * Legt eine Konzert-Entität in der Persistenzschicht an
     * @param konzert pojo mit den Attributen des anzulegenden Konzerts
     */
	public void createConcert(Konzert konzert);
	

	/**
	 * Aktualisiert eine Konzert-Entität in der Persistenzschicht
	 * @param konzert Konzert, das persistiert werden soll
	 */
	public void updateConcert(Konzert konzert);

	/**
	 * Löscht eine Konzert-Entität aus der Persistenzschicht
	 * @param konzertId id des zu löschenden Konzerts
	 */
	public void deleteConcert(Integer konzertId);

	
	/**
	 * selektiert alle Konzert-Entitäten
	 * @return Konzert-Entitäten
	 */
	public Collection<Konzert> getConcerts();
	
	/**
	 * Selektiert ein Konzert für eine gegebene Id
	 * @param konzertId
	 * @return Konzert Entität, null fallse keine Konzert-Entität
	 * mit dieser id existiert
	 */
	public Konzert getConcertById(Integer konzertId);
    
	/**
	 * Selektiert ein Konzert für eine gegebene Id und lädt die Beziehungen für das Konzert.
	 * @param konzertId
	 * @return Konzert Entität, null fallse keine Konzert-Entität
	 * mit dieser id existiert
	 */
	public Konzert getConcertWithDetailsById(Integer konzertId);

}
