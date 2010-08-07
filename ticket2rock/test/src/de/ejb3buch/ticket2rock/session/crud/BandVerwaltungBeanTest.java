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
import de.ejb3buch.ticket2rock.entity.Band;

/**
 * @author Dierk
 * 
 */
public class BandVerwaltungBeanTest extends EmbeddedContainerTestBase {
	private static final Logger logger = Logger
			.getLogger(BandVerwaltungBeanTest.class);

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getBands()}.
	 */
	@Test
	public void testGetBands() throws Exception {

		Collection<Band> alleBands = getBandVerwaltung().getBands();
		assertTrue(alleBands.size() > 0);
	}

	private BandVerwaltungLocal getBandVerwaltung() throws NamingException,
			Exception {
		BandVerwaltungLocal bandVerwaltung = (BandVerwaltungLocal) lookup("BandVerwaltungBean/local");
		return bandVerwaltung;
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getBandByName(java.lang.String)}.
	 */
	@Test
	public void testGetBandByName() throws Exception {

		Band dieBand = getBandVerwaltung().getBandByName("Green Day");
		assertTrue(dieBand.getAlben().size() > 0);
		assertTrue(dieBand.getMusiker().size() > 0);
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#createBand(de.ejb3buch.ticket2rock.entity.Band)}.
	 */
	@Test
	public void testCreateBand() throws Exception {
		Band dieBand = new Band();
		final String BANDNAME = "Baumanns Tod";
		dieBand.setName(BANDNAME);

		BandVerwaltungLocal bandVerwaltung = getBandVerwaltung();
		int anzahlVorher = bandVerwaltung.getBands().size();

		logger.debug("Versuche eine neue Band zu erzeugen...");
		bandVerwaltung.createBand(dieBand);

		assertEquals(anzahlVorher + 1, bandVerwaltung.getBands().size());

		assertEquals(bandVerwaltung.getBandByName(BANDNAME).getName(), dieBand
				.getName());
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#updateBand(de.ejb3buch.ticket2rock.entity.Band)}.
	 */
	@Test
	public void testUpdateBand() throws Exception {
		BandVerwaltungLocal bandVerwaltung = getBandVerwaltung();

		logger.debug("Versuche eine neue Band zu modifizieren...");
		Band dieBand = bandVerwaltung.getBandById(10);

		dieBand.setName("Die Neue Band");

		bandVerwaltung.updateBand(dieBand);

		assertEquals(dieBand.getName(), bandVerwaltung.getBandById(10)
				.getName());
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#deleteBand(java.lang.Integer)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testDeleteBand() throws Exception {
		BandVerwaltungLocal bv = getBandVerwaltung();

		logger.debug("Versuche eine neue Band zu loeschen");
		int anzahlVorher = bv.getBands().size();
		bv.deleteBand(10);

		assertEquals(anzahlVorher - 1, bv.getBands().size());

		bv.getBandById(10).getName();
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getBandById(java.lang.Integer)}.
	 */
	@Test
	public void testGetBandById() throws Exception {
		Band band = getBandVerwaltung().getBandById(10);
		assertNotNull(band);

		band = getBandVerwaltung().getBandById(99999);
		assertNull(band);
	}

}
