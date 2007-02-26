package de.ejb3buch.ticket2rock.applikation.servicelocator;

import de.ejb3buch.ticket2rock.session.AuskunftLocal;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltungLocal;
import de.ejb3buch.ticket2rock.session.crud.KonzertVerwaltungLocal;
import de.ejb3buch.ticket2rock.session.crud.MusikerVerwaltungLocal;
import de.ejb3buch.ticket2rock.session.crud.TourneeVerwaltungLocal;
import de.ejb3buch.ticket2rock.session.ticketbestellung.BestellvorgangLocal;

public interface ServiceLocator {

	public BandVerwaltungLocal getBandVerwaltung();
	
	public MusikerVerwaltungLocal getMusikerVerwaltung();
	
	public TourneeVerwaltungLocal getTourneeVerwaltung();
	
	public KonzertVerwaltungLocal getKonzertVerwaltung();
	
	public AuskunftLocal getAuskunft();
	
	public BestellvorgangLocal getWarenkorb();
}
