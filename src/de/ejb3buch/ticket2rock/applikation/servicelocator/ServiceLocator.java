package de.ejb3buch.ticket2rock.applikation.servicelocator;

import de.ejb3buch.ticket2rock.session.Auskunft;
import de.ejb3buch.ticket2rock.session.BuchungsVorgang;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.KonzertVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.MusikerVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.TourneeVerwaltung;

public interface ServiceLocator {

	public BandVerwaltung getBandVerwaltung();
	
	public MusikerVerwaltung getMusikerVerwaltung();
	
	public TourneeVerwaltung getTourneeVerwaltung();
	
	public KonzertVerwaltung getKonzertVerwaltung();
	
	public Auskunft getAuskunft();
	
	public BuchungsVorgang getWarenkorb();
}
