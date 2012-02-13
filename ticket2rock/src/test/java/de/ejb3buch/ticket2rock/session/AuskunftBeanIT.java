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
package de.ejb3buch.ticket2rock.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entitylistener.NewEntityListener;
import de.ejb3buch.ticket2rock.exception.KapazitaetErschoepftException;
import de.ejb3buch.ticket2rock.session.crud.KonzertVerwaltungBean;
import de.ejb3buch.ticket2rock.session.crud.KonzertVerwaltungLocal;

@RunWith(Arquillian.class)
public class AuskunftBeanIT {

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addClasses(Auskunft.class, AuskunftLocal.class,
						AuskunftBean.class, KonzertVerwaltungBean.class,
						KonzertVerwaltungLocal.class)
						.addPackages(true, Konzert.class.getPackage())
						.addClass(NewEntityListener.class)
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
	
	@EJB(mappedName=AuskunftBean.JNDI_REMOTE)
	Auskunft alleKonzerte;

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.AuskunftBean#sucheKonzerte(java.lang.String, java.util.Date, java.util.Date)}
	 * .
	 */
	@Ignore
	public void sucheAlleKonzerte() throws Exception {
		// wir suchen alle Konzerte, daher keine Einschränkung....
		List<Konzert> konzerte = alleKonzerte.sucheKonzerte(null, null, null);
		assertTrue(konzerte.size() > 1);
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.AuskunftBean#sucheKonzerte(java.lang.String, java.util.Date, java.util.Date)}
	 * .
	 */
	@Test
	public void sucheHamburgerKonzerte() throws Exception {
		// wir suchen ein Konzert in der Colorline Arena....
		List<Konzert> konzerte = alleKonzerte.sucheKonzerte("Olympiastadion",
				null, null);
		assertEquals(konzerte.size(), 1);
	}

	/**
	 * Test method for
	 * {@link de.ejb3buch.ticket2rock.session.AuskunftBean#sucheKonzerte(java.lang.String, java.util.Date, java.util.Date)}
	 * .
	 */
	@Test
	public void sucheFalschesKonzert() throws Exception {
		// wir suchen ein Konzert im Kuhstall....
		List<Konzert> konzerte = alleKonzerte.sucheKonzerte("Kuhstall", null,
				null);
		assertEquals(konzerte.size(), 0);
	}
}
