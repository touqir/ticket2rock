package de.ejb3buch.ticket2rock.applikation.servicelocator;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.session.Auskunft;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.KonzertVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.MusikerVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.TourneeVerwaltung;
import de.ejb3buch.ticket2rock.session.ticketbestellung.Bestellvorgang;

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
	
	private InitialContext ctx;

	public EJB3ServiceLocatorBean() {
		try {
			ctx = new InitialContext();
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
			e.printStackTrace();
			throw new EJBException("Bei der Allokierung der ServiceBeans ist ein Fehler aufgetreten",e);
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
	
	public Bestellvorgang getWarenkorb() {
		Bestellvorgang einkaufskorb;
		try {
			einkaufskorb = (Bestellvorgang) ctx
			.lookup("ticket2rock/BestellvorgangBean/local");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new EJBException("Bestellvorgang konnte nicht allokiert werden",e);
		}
      logger.info("Stateful Session Bean Bestellvorgang wurde allokiert");
      return einkaufskorb;
	}

}
