package de.ejb3buch.ticket2rock.session.demo;
import javax.ejb.Local;

@Local
public interface Geburtenkontrolle {
	public int gibAnzahlGeburten();
}
