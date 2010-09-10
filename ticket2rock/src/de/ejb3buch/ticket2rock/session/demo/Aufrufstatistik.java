package de.ejb3buch.ticket2rock.session.demo;

import javax.ejb.Local;

@Local
public interface Aufrufstatistik {
	public int gibAnzahlMethodenaufrufe();

	public int gibAnzahlAusnahmen();
	
	public int gibAnzahlTimeouts();
}
