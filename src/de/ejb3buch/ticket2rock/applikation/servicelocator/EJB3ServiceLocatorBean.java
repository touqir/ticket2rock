package de.ejb3buch.ticket2rock.applikation.servicelocator;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.session.Auskunft;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.KonzertVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.MusikerVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.TourneeVerwaltung;

/**
 * ServiceLocator Implementierung. Services werden in Form von Session Bean
 * Facaden bereitgestellt. Die Klasse kapselt diese Benutzung von JNDI in Form
 * des Anlegen des InitialContext Objektes und Lookup auf ensprechende EJBs und
 * dient als Cache der Client-Seitigen EJB Objekte.
 * 
 */
public class EJB3ServiceLocatorBean implements ServiceLocator {

	static Logger logger = Logger.getLogger(EJB3ServiceLocatorBean.class);

	private BandVerwaltung myBandVerwaltung;

	private MusikerVerwaltung myMusikerVerwaltung;

	private TourneeVerwaltung myTourneeVerwaltung;

	private KonzertVerwaltung myKonzertVerwaltung;

	private Auskunft myAuskunft;

	public EJB3ServiceLocatorBean() {
		try {
			InitialContext ctx = new InitialContext();
			myBandVerwaltung = (BandVerwaltung) ctx
					.lookup("ticket2rock/BandVerwaltungBean/local");
			logger.info("Service BandVerwaltung steht zur Verfügung");

			myMusikerVerwaltung = (MusikerVerwaltung) ctx
					.lookup("ticket2rock/MusikerVerwaltungBean/local");
			logger.info("Service MusikerVerwaltung steht zur Verfügung");

			myTourneeVerwaltung = (TourneeVerwaltung) ctx
					.lookup("ticket2rock/TourneeVerwaltungBean/local");
			logger.info("Service TourneeVerwaltung steht zur Verfügung");

			myKonzertVerwaltung = (KonzertVerwaltung) ctx
					.lookup("ticket2rock/KonzertVerwaltungBean/local");
			logger.info("Service KonzertVerwaltung steht zur Verfügung");

			myAuskunft = (Auskunft) ctx
					.lookup("ticket2rock/AuskunftBean/local");
			logger.info("Service Auskunft steht zur Verfügung");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public BandVerwaltung getBandVerwaltung() {
		return myBandVerwaltung;
	}

	public MusikerVerwaltung getMusikerVerwaltung() {
		return myMusikerVerwaltung;
	}

	public Auskunft getAuskunft() {
		return myAuskunft;
	}

	public TourneeVerwaltung getTourneeVerwaltung() {
		return myTourneeVerwaltung;
	}

	public KonzertVerwaltung getKonzertVerwaltung() {
		return myKonzertVerwaltung;
	}

}
