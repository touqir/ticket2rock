/**
 * 
 */
package de.ejb3buch.ticket2rock.session.crud;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.ejb3buch.ticket2rock.EmbeddedContainerTestHelper;
import de.ejb3buch.ticket2rock.entity.Band;
import de.ejb3buch.ticket2rock.session.Auskunft;

/**
 * @author Dierk
 *
 */
public class BandVerwaltungBeanTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EmbeddedContainerTestHelper.startupEmbeddedContainer(null);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EmbeddedContainerTestHelper.shutdownEmbeddedContainer();
	}

	/**
	 * Test method for {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getBands()}.
	 */
	@Test
	public void testGetBands() throws Exception {
		BandVerwaltung bandVerwaltung = (BandVerwaltung) EmbeddedContainerTestHelper
		.getInitialContext().lookup("BandVerwaltungBean/local");
		
		Collection<Band> alleBands = bandVerwaltung.getBands();
		assertTrue(alleBands.size() > 0);
	}

	/**
	 * Test method for {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getBandByName(java.lang.String)}.
	 */
	@Test
	public void testGetBandByName() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#createBand(de.ejb3buch.ticket2rock.entity.Band)}.
	 */
	@Test
	public void testCreateBand() throws Exception{
		
		Band trashMakers = new Band();
		trashMakers.setId(999);
		trashMakers.setName("TrashMakers");
		trashMakers.setAlben(null);
		trashMakers.setKonzerte(null);
		trashMakers.setMusiker(null);
		trashMakers.setSongs(null);
		trashMakers.setTourneen(null);
	
		BandVerwaltung bandVerwaltung = (BandVerwaltung) EmbeddedContainerTestHelper
		.getInitialContext().lookup("BandVerwaltungBean/local");
	
	
		bandVerwaltung.createBand(trashMakers);
		
		assertEquals(trashMakers, bandVerwaltung.getBandById(999));
	}

	/**
	 * Test method for {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#updateBand(de.ejb3buch.ticket2rock.entity.Band)}.
	 */
	@Test
	public void testUpdateBand() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#deleteBand(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteBand() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getMusiker()}.
	 */
	@Test
	public void testGetMusiker() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getBandById(java.lang.Integer)}.
	 */
	@Test
	public void testGetBandById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean#getMusikerById(java.lang.Integer)}.
	 */
	@Test
	public void testGetMusikerById() {
		fail("Not yet implemented"); // TODO
	}

}
