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

package de.ejb3buch.ticket2rock.entity;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Beispiel fuer einen Unit-Test, der ohne Container laeuft, sondern direkt den
 * Entitymanager nutzt
 * 
 * @author Carsten
 */
public class VeranstaltungsortTest {
	private static final Logger log = Logger
			.getLogger(VeranstaltungsortTest.class);

	private static final String AOL_ARENA = "AOL-Arena";

	private static final String HAMBURG_VOLKSPARK = "Hamburg Volkspark";

	private static final int KAPAZITAET = 51500;

	private static EntityManagerFactory emf = null;

	private static EntityManager em = null;

	/**
	 * Initialisieren des JPA Kontexts. Weil die Einstellungen in der
	 * persistence.xml automatisch geladen werden, hier aber nicht passen,
	 * werden sie hier ueberschrieben.
	 * 
	 */
	@BeforeClass
	public static void setUpEntityManager() throws Exception {

		BasicConfigurator.configure();
		Logger.getLogger("org").setLevel(Level.OFF);
		long start = System.currentTimeMillis();
		Map<String, String> configOverrides = new HashMap<String, String>();
		configOverrides.put("javax.persistence.transactionType",
				"RESOURCE_LOCAL");
		configOverrides.put("javax.persistence.jtaDataSource", "");
		configOverrides.put("hibernate.dialect",
				"org.hibernate.dialect.HSQLDialect");
		configOverrides.put("hibernate.cache.provider_class",
				"org.hibernate.cache.HashtableCacheProvider");
		configOverrides.put("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		configOverrides.put("hibernate.connection.url",
				"jdbc:hsqldb:mem:mem:aname");
		emf = Persistence.createEntityManagerFactory("ticket2rock",
				configOverrides);

		em = emf.createEntityManager();
		long stop = System.currentTimeMillis();
		log.setLevel(Level.INFO);
		log.info("Dauer fuer den Start des Entitymanagers: " + (stop-start) + " Millisekunden");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownEntityManager() throws Exception {
		em.close();
		emf.close();
	}

	@Test
	public void testInsertVeranstaltungsort() throws NamingException, Exception {
		Veranstaltungsort ort = new Veranstaltungsort();
		// id wird automatisch vergeben
		ort.setName(AOL_ARENA);
		ort.setAdresse(HAMBURG_VOLKSPARK);
		ort.setKapazitaet(KAPAZITAET);

		em.getTransaction().begin(); // ohne Transaktion geht hier
		// garnichts...
		em.persist(ort);

		// und die neue automatisch vergebene ID ist im Objekt vorhanden
		int volksparkId = ort.getId();

		// Kontrolle über EJBQL
		Query query = em.createQuery("from Veranstaltungsort v where v.id = "
				+ volksparkId);
		Veranstaltungsort ort2 = (Veranstaltungsort) query.getSingleResult();

		assertEquals(AOL_ARENA, ort2.getName());
		assertEquals(HAMBURG_VOLKSPARK, ort2.getAdresse());
		assertEquals(KAPAZITAET, ort2.getKapazitaet());

		em.getTransaction().rollback(); // jetzt koennen wir den Kram auch
		// wegschmeissen
	}

}
