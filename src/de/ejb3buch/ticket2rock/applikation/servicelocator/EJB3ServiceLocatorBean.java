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

package de.ejb3buch.ticket2rock.applikation.servicelocator;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.session.AuskunftLocal;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltungLocal;
import de.ejb3buch.ticket2rock.session.crud.KonzertVerwaltungLocal;
import de.ejb3buch.ticket2rock.session.crud.KundenVerwaltungLocal;
import de.ejb3buch.ticket2rock.session.crud.MusikerVerwaltungLocal;
import de.ejb3buch.ticket2rock.session.crud.TourneeVerwaltungLocal;
import de.ejb3buch.ticket2rock.session.ticketbestellung.BestellvorgangLocal;

/**
 * ServiceLocator Implementierung. Services werden in Form von Session Bean
 * Facaden bereitgestellt. Die Klasse kapselt diese Benutzung von JNDI in Form
 * des Anlegen des InitialContext Objektes und Lookup auf ensprechende EJBs und
 * dient als Cache der Client-Seitigen EJB Objekte.
 * 
 */
public class EJB3ServiceLocatorBean implements ServiceLocator {

	static Logger logger = Logger.getLogger(EJB3ServiceLocatorBean.class);

	private BandVerwaltungLocal myBandVerwaltung;

	private MusikerVerwaltungLocal myMusikerVerwaltung;

	private TourneeVerwaltungLocal myTourneeVerwaltung;

	private KonzertVerwaltungLocal myKonzertVerwaltung;
	
	private KundenVerwaltungLocal myKundenVerwaltung;
	

	private AuskunftLocal myAuskunft;
	
	private InitialContext ctx;

	public EJB3ServiceLocatorBean() {
		try {
			ctx = new InitialContext();
			myBandVerwaltung = (BandVerwaltungLocal) ctx
					.lookup("ticket2rock/BandVerwaltungBean/local");
			logger.info("Service BandVerwaltungLocal steht zur Verfügung");

			myMusikerVerwaltung = (MusikerVerwaltungLocal) ctx
					.lookup("ticket2rock/MusikerVerwaltungBean/local");
			logger.info("Service MusikerVerwaltungLocal steht zur Verfügung");

			myTourneeVerwaltung = (TourneeVerwaltungLocal) ctx
					.lookup("ticket2rock/TourneeVerwaltungBean/local");
			logger.info("Service TourneeVerwaltungLocal steht zur Verfügung");

			myKonzertVerwaltung = (KonzertVerwaltungLocal) ctx
					.lookup("ticket2rock/KonzertVerwaltungBean/local");
			logger.info("Service KonzertVerwaltungLocal steht zur Verfügung");

			myKundenVerwaltung = (KundenVerwaltungLocal) ctx
			.lookup("ticket2rock/KundenVerwaltungBean/local");
	        logger.info("Service Kundenverwaltung steht zur Verfügung");			
			
			myAuskunft = (AuskunftLocal) ctx
					.lookup("ticket2rock/AuskunftBean/local");
			logger.info("Service AuskunftLocal steht zur Verfügung");
			
		} catch (Exception e) {
		    logger.error(e);
			e.printStackTrace();
			throw new EJBException("Bei der Allokierung der ServiceBeans ist ein Fehler aufgetreten",e);
		}
	}

	public BandVerwaltungLocal getBandVerwaltung() {
		return myBandVerwaltung;
	}

	public MusikerVerwaltungLocal getMusikerVerwaltung() {
		return myMusikerVerwaltung;
	}

	public AuskunftLocal getAuskunft() {
		return myAuskunft;
	}

	public TourneeVerwaltungLocal getTourneeVerwaltung() {
		return myTourneeVerwaltung;
	}

	public KonzertVerwaltungLocal getKonzertVerwaltung() {
		return myKonzertVerwaltung;
	}

	public KundenVerwaltungLocal getKundenVerwaltung() {
		return myKundenVerwaltung;
	}	
	
	public BestellvorgangLocal getBestellvorgang() {
		BestellvorgangLocal einkaufskorb;
		try {
			einkaufskorb = (BestellvorgangLocal) ctx
			.lookup("ticket2rock/BestellvorgangBean/local");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new EJBException("BestellvorgangLocal konnte nicht allokiert werden",e);
		}
      logger.info("Stateful Session Bean BestellvorgangLocal wurde allokiert");
      return einkaufskorb;
	}



}
