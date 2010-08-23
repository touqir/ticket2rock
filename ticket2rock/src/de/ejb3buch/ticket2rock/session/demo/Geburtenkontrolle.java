package de.ejb3buch.ticket2rock.session.demo;
import javax.ejb.Remote;

@Remote
public interface Geburtenkontrolle {
	public int gibAnzahlGeburten();
}
