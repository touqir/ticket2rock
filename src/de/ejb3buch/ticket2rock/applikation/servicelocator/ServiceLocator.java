package de.ejb3buch.ticket2rock.applikation.servicelocator;

import de.ejb3buch.ticket2rock.session.Auskunft;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.KonzertVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.MusikerVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.TourneeVerwaltung;
import de.ejb3buch.ticket2rock.session.ticketbestellung.Bestellvorgang;

public interface ServiceLocator {

	public BandVerwaltung getBandVerwaltung();
	
	public MusikerVerwaltung getMusikerVerwaltung();
	
	public TourneeVerwaltung getTourneeVerwaltung();
	
	public KonzertVerwaltung getKonzertVerwaltung();
	
	public Auskunft getAuskunft();
	
	public Bestellvorgang getWarenkorb();
}
