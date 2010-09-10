package de.ejb3buch.ticket2rock.session.demo;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

@Stateless
@Local(Geburtenkontrolle.class)
public class GeburtenkontrolleBean implements Geburtenkontrolle {

	@PersistenceContext
	private EntityManager em;

	private Logger logger = Logger.getRootLogger();

	public int gibAnzahlGeburten() {
		Query query = em.createQuery("SELECT g from Geburtsanzeige g");
		return query.getResultList().size();
	}

	@SuppressWarnings("unused")
	private void onPostConstruct() {
		logger.info(getClass().getName() + " wurde erzeugt");
	}
}
