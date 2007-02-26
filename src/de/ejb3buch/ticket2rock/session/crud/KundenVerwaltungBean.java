package de.ejb3buch.ticket2rock.session.crud;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Kunde;

/**
 * Stateless Session Bean zur Verwaltung der Kunden Entitäten.
 *
 */
@Stateless
public class KundenVerwaltungBean implements KundenVerwaltungLocal {

	static Logger logger = Logger.getLogger(KundenVerwaltungBean.class);

	@PersistenceContext
	private EntityManager em;



    /**
     *@inheritDoc
     */
	@SuppressWarnings("unchecked")
	public Kunde getKundeByEmail(String email) {
		Query query = em.createQuery("from Kunde k where k.email = :email");
		query.setParameter("email", email);
		List<Kunde> kunden = (List<Kunde>) query.getResultList();
		if ((kunden == null) || (kunden.isEmpty())) {
			return null;
		}
		return kunden.iterator().next();
	}

    /**
     *@inheritDoc
     */
	public void createKunde(Kunde kunde) {
		em.persist(kunde);
	}


}
