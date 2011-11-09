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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.ejb3buch.ticket2rock.entity.Band;
import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entitylistener.NewEntityListener;
import de.ejb3buch.ticket2rock.exception.KapazitaetErschoepftException;
import de.ejb3buch.ticket2rock.session.interceptor.demo.EntiGeburtenkontrolle;

@RunWith(Arquillian.class)
public class BandVerwaltungBeanIT {

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addClasses(BandVerwaltung.class, BandVerwaltungLocal.class,
						BandVerwaltungBean.class)
				.addPackages(true, Konzert.class.getPackage())
				.addClass(NewEntityListener.class)
				.addClass(EntiGeburtenkontrolle.class)
				.addClass(KapazitaetErschoepftException.class)
				.addAsManifestResource(
						new File("src/main/resources/META-INF/persistence.xml"),
						ArchivePaths.create("persistence.xml"))
				.addAsManifestResource(
						new File("src/main/resources/META-INF/orm.xml"),
						ArchivePaths.create("orm.xml"))
				.addAsResource(new File("src/main/resources/import.sql"),
						ArchivePaths.create("import.sql"));
	}

	@EJB(mappedName="BandVerwaltung/local")
	BandVerwaltungLocal bandVerwaltung;

	private static final Logger logger = Logger
			.getLogger(BandVerwaltungBeanIT.class);

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getBands()}
	 * .
	 */
	@Test
	public void testGetBands() throws Exception {

		Collection<Band> alleBands = bandVerwaltung.getBands();
		assertTrue(alleBands.size() > 0);
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getBandByName(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetBandByName() throws Exception {

		Band dieBand = bandVerwaltung.getBandByName("Green Day");
		assertTrue(dieBand.getMusiker().size() > 0);
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#createBand(de.ejb3buch.ticket2rock.entity.Band)}
	 * .
	 */
	@Test
	public void testCreateBand() throws Exception {
		Band dieBand = new Band();
		final String BANDNAME = "Baumanns Tod";
		dieBand.setName(BANDNAME);

		int anzahlVorher = bandVerwaltung.getBands().size();

		logger.debug("Versuche eine neue Band zu erzeugen...");
		bandVerwaltung.createBand(dieBand);

		assertEquals(anzahlVorher + 1, bandVerwaltung.getBands().size());

		assertEquals(bandVerwaltung.getBandByName(BANDNAME).getName(),
				dieBand.getName());
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#updateBand(de.ejb3buch.ticket2rock.entity.Band)}
	 * .
	 */
	@Test
	public void testUpdateBand() throws Exception {
		logger.debug("Versuche eine neue Band zu modifizieren...");
		Band dieBand = bandVerwaltung.getBandById(10);

		dieBand.setName("Die Neue Band");

		bandVerwaltung.updateBand(dieBand);

		assertEquals(dieBand.getName(), bandVerwaltung.getBandById(10)
				.getName());
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#deleteBand(java.lang.Integer)}
	 * .
	 */
	@Test(expected = NullPointerException.class)
	public void testDeleteBand() throws Exception {
		BandVerwaltung bv = bandVerwaltung;

		logger.debug("Versuche eine neue Band zu loeschen");
		int anzahlVorher = bv.getBands().size();
		bv.deleteBand(10);

		assertEquals(anzahlVorher - 1, bv.getBands().size());

		bv.getBandById(10).getName();
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getBandById(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testGetBandById() throws Exception {
		Band band = bandVerwaltung.getBandById(5);
		assertNotNull(band);
		band = bandVerwaltung.getBandById(99999);
		assertNull(band);
	}

}
