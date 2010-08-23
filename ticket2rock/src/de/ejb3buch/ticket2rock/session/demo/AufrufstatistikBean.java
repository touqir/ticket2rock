package de.ejb3buch.ticket2rock.session.demo;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.Timer;

@Stateless
// Fehler im JBoss AS 6.0.0.M4: Das Deployment von Singletons, die einmal
// fehlerhaft registriert wurden, wird mit einer IllegalStateException und dem
// Hinweis "is already registered" abgebrochen.
// @Singleton
public class AufrufstatistikBean implements AufrufstatistikBeanLocal,
		Aufrufstatistik {

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
