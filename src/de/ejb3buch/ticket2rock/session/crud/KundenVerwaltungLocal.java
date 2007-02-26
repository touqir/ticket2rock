package de.ejb3buch.ticket2rock.session.crud;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Kunde;

@Local
public interface KundenVerwaltungLocal {

    /**
     * Legt eine Kunden-Entität in der Persistenzschicht an
     * @param musiker Kunden-POJO mit den Attributen des anzulegenden
     * Kunden.
     */
	public void createKunde(Kunde kunde);
	
	/**
	 * Selektiert eine Kunden-Entität mit einer gegebenen Email Adresse
	 * @param email email Adresse
	 * @return Kunden Objekt, das die bestimmte email Adresse besitzt.
	 * Existiert kein Kunde mit dieser email Adresse, ist der Rückgabewert null
	 */
	public Kunde getKundeByEmail(String email);

}
