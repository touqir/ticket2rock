package de.ejb3buch.ticket2rock.applikation.servicelocator;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.session.crud.BandVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.MusikerVerwaltung;

/**
 * ServiceLocator Implementierung. Services werden in Form von Session Bean Facaden
 * bereitgestellt. Die Klasse kapselt diese Benutzung von JNDI in Form des Anlegen des
 * InitialContext Objektes und Lookup auf ensprechende EJBs und dient als Cache der
 * Client-Seitigen EJB Objekte. 
 *
 */
public class EJB3ServiceLocatorBean implements ServiceLocator {
    
	static Logger logger = Logger.getLogger(EJB3ServiceLocatorBean.class);
	private BandVerwaltung myBandVerwaltung;
	private MusikerVerwaltung myMusikerVerwaltung;

	public EJB3ServiceLocatorBean() {
		try {
			InitialContext ctx = new InitialContext();
			myBandVerwaltung = (BandVerwaltung) ctx
					.lookup("ticket2rock/BandVerwaltungBean/local");
			logger.info("Service BandVerwaltung steht zur Verfügung");
			
			myMusikerVerwaltung = (MusikerVerwaltung) ctx.lookup("ticket2rock/MusikerVerwaltungBean/local");
			logger.info("Service MusikerVerwaltung steht zur Verfügung");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public BandVerwaltung getBandVerwaltung() {
       return myBandVerwaltung;
	}

	public MusikerVerwaltung getMusikerVerwaltung() {
       return myMusikerVerwaltung;
	}
	
	

}
