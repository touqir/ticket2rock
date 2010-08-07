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

package de.ejb3buch.ticket2rock.applikation.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.ejb3buch.ticket2rock.entity.Band;
import de.ejb3buch.ticket2rock.entity.Musiker;

/**
 * Band Baking Bean für die Verwendung im JSF-Frameworks
 * 
 * @author Jochen Jörg
 */
public class BandBackingBean {

	private Band band = null;

	private Collection<String> musikerIdListe = null;

	public BandBackingBean() {
		band = new Band();
	}

	public BandBackingBean(Band aBand) {
		this.band = aBand;
	}

	public String getName() {
		return band.getName();
	}

	public void setName(String aName) {
		this.band.setName(aName);
	}

	public Integer getId() {
		return new Integer(band.getId());
	}
	
	/**
	 * 
	 * @return Collection der Namen der Musiker der Band, die durch den
	 *         BandBackingBean representiert wird
	 */
	public Collection<String> getMusikerNamensListe() {

		List<String> musikerNamensListe = new ArrayList<String>();
		Set<Musiker> musikerList = band.getMusiker();
		if ((musikerList != null) && (!musikerList.isEmpty())) {
			for (Musiker musiker : musikerList) {
				musikerNamensListe.add(musiker.getName());
			}
		}

		return musikerNamensListe;
	}

	/**
	 * Erzeugt eine kommagetrennte List der Musikernamen
	 * 
	 * @return kommagetrennte Liste der Bandmusiker-Namen
	 */
	public List<String> getMusikerListeKommaGetrennt() {
        if (this.getMusikerNamensListe() == null) {
        	return null;
        }
		List<String> list = new ArrayList<String>();
		boolean isFirst = true;
		for (String name : getMusikerNamensListe()) {
			if (isFirst) {
				list.add(name);
				isFirst = false;
			} else {
				list.add(", " + name);
			}
		}
		return list;
	}

	public Collection<String> getMusikerIdListe() {
		return musikerIdListe;
	}

	/**
	 * Instanziiere ein Musiker Objekt prop übergebene Id. Lege die Musiker
	 * Objekte in ein Set ab und weise dieses dem Band Objekt zu 
	 * @param musikerIdListe String Collection bestehend aus musiker Ids
	 */
	public void setMusikerIdListe(Collection<String> musikerIdListe) {
		Set<Musiker> musikerSet = new HashSet<Musiker>();
		for (String musikerId:musikerIdListe) {
			Musiker musiker = new Musiker();
			musiker.setId(Integer.parseInt(musikerId));
			musikerSet.add(musiker);
		}		
		band.setMusiker(musikerSet);
	}

	/**
	 * gibt das persistent Band Entity Objekt zurück, das durch die BackingBean
	 * gewrapped ist.
	 * @return Band Persistent Entity Objekt
	 */
	public Band getBand() {
		return this.band;
	}

}
