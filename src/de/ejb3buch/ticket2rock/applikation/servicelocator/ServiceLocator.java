package de.ejb3buch.ticket2rock.applikation.servicelocator;

import de.ejb3buch.ticket2rock.session.crud.BandVerwaltung;
import de.ejb3buch.ticket2rock.session.crud.MusikerVerwaltung;

public interface ServiceLocator {

	public BandVerwaltung getBandVerwaltung();
	
	public MusikerVerwaltung getMusikerVerwaltung();
}
