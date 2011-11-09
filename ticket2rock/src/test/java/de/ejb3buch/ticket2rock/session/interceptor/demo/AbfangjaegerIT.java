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
package de.ejb3buch.ticket2rock.session.interceptor.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.ejb3buch.ticket2rock.session.demo.Aufrufstatistik;
import de.ejb3buch.ticket2rock.session.demo.AufrufstatistikBean;
import de.ejb3buch.ticket2rock.session.demo.AufrufstatistikBeanLocal;
import de.ejb3buch.ticket2rock.session.demo.Geburtenkontrolle;
import de.ejb3buch.ticket2rock.session.demo.GeburtenkontrolleBean;

@RunWith(Arquillian.class)
public class AbfangjaegerIT {

	@EJB
	private Aufrufstatistik aufrufstatistik;

	@EJB
	private Geburtenkontrolle geburtenkontrolle;

	@EJB(mappedName = "ZielobjektBeanKlasseninterzeptorDD/no-interface")
	private Zielobjekt zielobjektBeanKlassenMitInterzeptorDD;

	@EJB(mappedName = "ZielobjektBeanKlasseninterzeptor/no-interface")
	private Zielobjekt zielobjektBeanMitKlassenMitInterzeptor;

	@EJB(mappedName = "ZielobjektBeanMitMethodeninterzeptor/no-interface")
	private Zielobjekt ZielobjektBeanMitMethodeninterzeptor;

	@EJB(mappedName = "ZielobjektBeanMethodeninterzeptorDD/no-interface")
	private Zielobjekt ZielobjektBeanMitMethodeninterzeptorDD;

	private List<Zielobjekt> zielobjekte = new ArrayList<Zielobjekt>();

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addAsManifestResource(
						new File("src/main/resources/META-INF/persistence.xml"),
						ArchivePaths.create("persistence.xml"))
				.addAsManifestResource(new File("src/test/java/de/ejb3buch/ticket2rock/session/interceptor/demo/ejb-jar.xml"), ArchivePaths.create("ejb-jar.xml"))
				.addAsManifestResource(new File("src/test/java/de/ejb3buch/ticket2rock/session/interceptor/demo/orm.xml"), ArchivePaths.create("orm.xml"))
				.addPackage(AbfangjaegerIT.class.getPackage())
				.addClasses(Aufrufstatistik.class, GeburtenkontrolleBean.class,Geburtenkontrolle.class,AufrufstatistikBeanLocal.class,AufrufstatistikBean.class);

	}

	boolean intialized = false;

	@Before
	public void init() throws NamingException {
		if (!intialized) {
			zielobjekte.add(zielobjektBeanKlassenMitInterzeptorDD);
			zielobjekte.add(zielobjektBeanMitKlassenMitInterzeptor);
			zielobjekte.add(ZielobjektBeanMitMethodeninterzeptor);
			zielobjekte.add(ZielobjektBeanMitMethodeninterzeptorDD);
			intialized = true;
		}
	}

	@Test
	public void entfernteObjekteSindErreichbar() {
		assertNotNull(aufrufstatistik);
		assertNotNull(geburtenkontrolle);
		for (Zielobjekt zielobjekt : zielobjekte) {
			assertNotNull(zielobjekt);
		}
	}

	
	//TODO: Carl fix test.
    @Ignore
	@Test
	public void erfolgreicherAufruf() {
		int alleAufrufe;
		int fehlgeschlageneAufrufe;

		for (Zielobjekt zielobjekt : zielobjekte) {
			alleAufrufe = aufrufstatistik.gibAnzahlMethodenaufrufe();
			fehlgeschlageneAufrufe = aufrufstatistik.gibAnzahlAusnahmen();
			zielobjekt.fangMichAb();
			zielobjekt.michAuch(null);
			assertEquals(alleAufrufe + 2,
					aufrufstatistik.gibAnzahlMethodenaufrufe());
			assertEquals(fehlgeschlageneAufrufe,
					aufrufstatistik.gibAnzahlAusnahmen());
		}
	}

    //TODO: Carl fix test.
    @Ignore
	@Test
	public void aufrufMitAusnahme() throws InterruptedException {
		int alleAufrufe;
		int fehlgeschlageneAufrufe;

		for (Zielobjekt zielobjekt : zielobjekte) {
			alleAufrufe = aufrufstatistik.gibAnzahlMethodenaufrufe();
			fehlgeschlageneAufrufe = aufrufstatistik.gibAnzahlAusnahmen();
			zielobjekt.duKriegstMichNicht();
			assertEquals(alleAufrufe + 1,
					aufrufstatistik.gibAnzahlMethodenaufrufe());
			assertEquals(fehlgeschlageneAufrufe + 1,
					aufrufstatistik.gibAnzahlAusnahmen());
		}
	}

	@Test
	public void aufrufOhneInterzeptor() {
		int alleAufrufe = aufrufstatistik.gibAnzahlMethodenaufrufe();
		int fehlgeschlageneAufrufe = aufrufstatistik.gibAnzahlAusnahmen();

		for (Zielobjekt zielobjekt : zielobjekte) {
			zielobjekt.lassMichInRuhe();
			assertEquals(alleAufrufe,
					aufrufstatistik.gibAnzahlMethodenaufrufe());
			assertEquals(fehlgeschlageneAufrufe,
					aufrufstatistik.gibAnzahlAusnahmen());
		}
	}

	// @AroundTimeout interception for timeout method need to be implemented.
	// It's currently missing from AS 6.0.0.M4.
	// https://jira.jboss.org/browse/EJBTHREE-2142
	@Test
	@Ignore
	public void aufrufMitTimeout() throws InterruptedException {
		int anzahlTimeouts;

		for (Zielobjekt zielobjekt : zielobjekte) {
			anzahlTimeouts = aufrufstatistik.gibAnzahlTimeouts();
			zielobjekt.gibMirZeit();
			assertEquals(anzahlTimeouts + 1,
					aufrufstatistik.gibAnzahlTimeouts());
		}
	}

    //TODO: Carl fix test.
    @Ignore
	@Test
	public void entiGeburt() throws NamingException {
		int anzahlGeburten;

		for (Zielobjekt zielobjekt : zielobjekte) {
			anzahlGeburten = geburtenkontrolle.gibAnzahlGeburten();
			Enti enti = zielobjekt.bruete();
			assertNotNull(enti);
			assertEquals("Enti Alfred Jodocus Kwack", enti.getName());
			assertEquals(anzahlGeburten + 1,
					geburtenkontrolle.gibAnzahlGeburten());
		}
	}
}