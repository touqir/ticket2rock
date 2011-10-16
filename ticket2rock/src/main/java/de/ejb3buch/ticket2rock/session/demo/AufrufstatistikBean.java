/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerten. 
 *
 *  Copyright (C) 2011
 *  Jo Ehm, Stefan M. Heldt, Oliver Ihns, Holger Koschek,
 *  Carsten Sahling, Roman Schloemmer, Norman Erck, Daniel Steinhöfer,
 *  Carl A. Düvel.
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
package de.ejb3buch.ticket2rock.session.demo;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.Timer;

@Stateless
// Fehler im JBoss AS 6.0.0.M4: Das Deployment von Singletons, die einmal
// fehlerhaft registriert wurden, wird mit einer IllegalStateException und dem
// Hinweis "is already registered" abgebrochen.
// @Singleton
@Local(Aufrufstatistik.class)
public class AufrufstatistikBean implements AufrufstatistikBeanLocal, Aufrufstatistik {

	private static int alleAufrufe;
	private static int fehlgeschlageneAufrufe;
	private static int timeouts;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		alleAufrufe = 0;
		fehlgeschlageneAufrufe = 0;
		timeouts = 0;
	}

	public void notiereMethodenaufruf(final String className,
			final String methodName, final long callTime) {
		alleAufrufe++;
	}

	public void notiereAusnahme(final String className,
			final String methodName, final long callTime, final Exception e) {
		fehlgeschlageneAufrufe++;
	}

	public void notiereTimeout(final Timer timer) {
		timeouts++;
	}

	public int gibAnzahlMethodenaufrufe() {
		return alleAufrufe;
	}

	public int gibAnzahlAusnahmen() {
		return fehlgeschlageneAufrufe;
	}

	public int gibAnzahlTimeouts() {
		return timeouts;
	}
}
