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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.Test;

import de.ejb3buch.ticket2rock.EmbeddedContainerTestBase;
import de.ejb3buch.ticket2rock.entity.Musiker;

/**
 * @author Holger
 * 
 */
public class MusikerVerwaltungBeanTest extends EmbeddedContainerTestBase {
	private static final Logger logger = Logger
			.getLogger(MusikerVerwaltungBeanTest.class);

	private MusikerVerwaltungLocal getMusikerVerwaltung()
			throws NamingException, Exception {
		MusikerVerwaltungLocal musikerVerwaltung = (MusikerVerwaltungLocal) lookup("MusikerVerwaltungBean/local");
		return musikerVerwaltung;
	}

	@Test
	public void testGetMusiker() throws Exception {
        Collection<Musiker> alleMusiker = getMusikerVerwaltung().getMusiker();
        assertTrue(alleMusiker.size() > 0);
	}

	@Test
	public void testGetMusikerById() throws Exception {
		Musiker musiker = getMusikerVerwaltung().getMusikerById(1);
		assertNotNull(musiker);
		
		musiker = getMusikerVerwaltung().getMusikerById(99999);
		assertNull(musiker);
	}

	@Test
	public void testGetMusikerByName() throws Exception {
		Musiker musiker = getMusikerVerwaltung().getMusikerByName(
				"Wildecker Herzbuben");
		// ...sind keine Musiker ;-)
		assertNull(musiker);

		musiker = getMusikerVerwaltung().getMusikerByName("Chris Cornell");
		// ...rockt!
		assertNotNull(musiker);
	}

	@Test
	public void testCreateMusiker() throws Exception {
		Musiker musiker = new Musiker();
		final String MUSIKER_NAME = "Joe Satriani";
		musiker.setName(MUSIKER_NAME);

		int anzahlVorher = getMusikerVerwaltung().getMusiker().size();

		logger.debug("Versuche, einen neuen Musiker zu erzeugen...");
		getMusikerVerwaltung().createMusiker(musiker);

		assertEquals(anzahlVorher + 1, getMusikerVerwaltung().getMusiker()
				.size());

		assertEquals(getMusikerVerwaltung().getMusikerByName(MUSIKER_NAME)
				.getName(), musiker.getName());
	}

	@Test
	public void testUpdateMusiker() throws Exception {
		logger.debug("Versuche, einen Musiker zu modifizieren...");
		Musiker musiker = getMusikerVerwaltung().getMusikerById(1);

		musiker.setName("Ein anderer Mensch");

		getMusikerVerwaltung().updateMusiker(musiker);

		assertEquals(musiker.getName(), getMusikerVerwaltung()
				.getMusikerById(1).getName());
	}

	@Test(expected = NullPointerException.class)
	public void testDeleteMusiker() throws Exception {
		logger.debug("Versuche, einen Musiker zu loeschen");
		int anzahlVorher = getMusikerVerwaltung().getMusiker().size();
		getMusikerVerwaltung().deleteMusiker(1);

		assertEquals(anzahlVorher - 1, getMusikerVerwaltung().getMusiker()
				.size());

		getMusikerVerwaltung().getMusikerById(1).getName();
	}
}
