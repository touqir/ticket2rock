package de.ejb3buch.ticket2rock.session.interceptor.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.ejb3buch.ticket2rock.entity.demo.Enti;
import de.ejb3buch.ticket2rock.session.demo.Aufrufstatistik;
import de.ejb3buch.ticket2rock.session.demo.Geburtenkontrolle;
import de.ejb3buch.ticket2rock.session.demo.Zielobjekt;

public class AbfangjaegerTest {

	private static Aufrufstatistik aufrufstatistik;
	private static Geburtenkontrolle geburtenkontrolle;
	private static List<Zielobjekt> zielobjekte;

	@BeforeClass
	public static void init() throws NamingException {
		Context ctx = new InitialContext();

		aufrufstatistik = (Aufrufstatistik) ctx
				.lookup("ticket2rock/AufrufstatistikBean/remote");
		geburtenkontrolle = (Geburtenkontrolle) ctx
				.lookup("ticket2rock/GeburtenkontrolleBean/remote");

		zielobjekte = new ArrayList<Zielobjekt>();
		zielobjekte.add((Zielobjekt) ctx
				.lookup("ticket2rock/ZielobjektBeanKlasseninterzeptor/remote"));
		zielobjekte
				.add((Zielobjekt) ctx
						.lookup("ticket2rock/ZielobjektBeanKlasseninterzeptorDD/remote"));
		zielobjekte
				.add((Zielobjekt) ctx
						.lookup("ticket2rock/ZielobjektBeanMethodeninterzeptor/remote"));
		zielobjekte
				.add((Zielobjekt) ctx
						.lookup("ticket2rock/ZielobjektBeanMethodeninterzeptorDD/remote"));
	}

	@Test
	public void entfernteObjekteSindErreichbar() {
		assertNotNull(aufrufstatistik);
		assertNotNull(geburtenkontrolle);
		for (Zielobjekt zielobjekt : zielobjekte) {
			assertNotNull(zielobjekt);
		}
	}

	@Test
	public void erfolgreicherAufruf() {
		int alleAufrufe;
		int fehlgeschlageneAufrufe;

		for (Zielobjekt zielobjekt : zielobjekte) {
			alleAufrufe = aufrufstatistik.gibAnzahlMethodenaufrufe();
			fehlgeschlageneAufrufe = aufrufstatistik.gibAnzahlAusnahmen();
			zielobjekt.fangMichAb();
			zielobjekt.michAuch();
			assertEquals(alleAufrufe + 2,
					aufrufstatistik.gibAnzahlMethodenaufrufe());
			assertEquals(fehlgeschlageneAufrufe,
					aufrufstatistik.gibAnzahlAusnahmen());
		}
	}

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
	// @Test
	public void aufrufMitTimeout() throws InterruptedException {
		int anzahlTimeouts;

		for (Zielobjekt zielobjekt : zielobjekte) {
			anzahlTimeouts = aufrufstatistik.gibAnzahlTimeouts();
			zielobjekt.gibMirZeit();
			assertEquals(anzahlTimeouts + 1,
					aufrufstatistik.gibAnzahlTimeouts());
		}
	}

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
