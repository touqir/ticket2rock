/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerte auf Basis von EJB 3.1 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Jo Ehm, Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg, Holger Koschek,
 *  Carsten Sahling, Roman Schloemmer, Norman Erck, Carl Anders Duevel, Daniel Steinhoefer
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import de.ejb3buch.ticket2rock.entity.Musiker;

/**
 * @author Holger
 * 
 */
// TODO: Carl Has to be ported to Arquillian.
public class MusikerVerwaltungBeanTest {
	private static final Logger logger = Logger
			.getLogger(MusikerVerwaltungBeanTest.class);
	MusikerVerwaltungLocal musikerVerwaltung;

	@Test
	@Ignore
	public void testGetMusiker() throws Exception {
		Collection<Musiker> alleMusiker = musikerVerwaltung.getMusiker();
		assertTrue(alleMusiker.size() > 0);
	}

	@Test
	@Ignore
	public void testGetMusikerById() throws Exception {
		Musiker musiker = musikerVerwaltung.getMusikerById(1);
		assertNotNull(musiker);
		musiker = musikerVerwaltung.getMusikerById(99999);
		assertNull(musiker);
	}

	@Test
	@Ignore
	public void testGetMusikerByName() throws Exception {
		Musiker musiker = musikerVerwaltung
				.getMusikerByName("Wildecker Herzbuben");
		// ...sind keine Musiker ;-)
		assertNull(musiker);
		musiker = musikerVerwaltung.getMusikerByName("Chris Cornell");
		// ...rockt!
		assertNotNull(musiker);
	}

	@Test
	@Ignore
	public void testCreateMusiker() throws Exception {
		Musiker musiker = new Musiker();
		final String MUSIKER_NAME = "Joe Satriani";
		musiker.setName(MUSIKER_NAME);
		int anzahlVorher = musikerVerwaltung.getMusiker().size();
		logger.debug("Versuche, einen neuen Musiker zu erzeugen...");
		musikerVerwaltung.createMusiker(musiker);
		assertEquals(anzahlVorher + 1, musikerVerwaltung.getMusiker().size());

		assertEquals(
				musikerVerwaltung.getMusikerByName(MUSIKER_NAME).getName(),
				musiker.getName());
	}

	@Test
	@Ignore
	public void testUpdateMusiker() throws Exception {
		logger.debug("Versuche, einen Musiker zu modifizieren...");
		Musiker musiker = musikerVerwaltung.getMusikerById(1);

		musiker.setName("Ein anderer Mensch");

		musikerVerwaltung.updateMusiker(musiker);

		assertEquals(musiker.getName(), musikerVerwaltung.getMusikerById(1)
				.getName());
	}

	@Test(expected = NullPointerException.class)
	@Ignore
	public void testDeleteMusiker() throws Exception {
		logger.debug("Versuche, einen Musiker zu loeschen");
		int anzahlVorher = musikerVerwaltung.getMusiker().size();
		musikerVerwaltung.deleteMusiker(1);

		assertEquals(anzahlVorher - 1, musikerVerwaltung.getMusiker().size());

		musikerVerwaltung.getMusikerById(1).getName();
	}
}
