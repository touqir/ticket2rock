package de.ejb3buch.ticket2rock.session.interceptor.demo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PostPersist;

import de.ejb3buch.ticket2rock.entity.demo.Enti;
import de.ejb3buch.ticket2rock.entity.demo.Geburtsanzeige;

public class EntiGeburtenkontrolle {

	@PostPersist
	protected void neuesEnti(Object entity) throws NamingException {
		Context ctx = new InitialContext();
		EntityManager em = (EntityManager) ctx
				.lookup("ticket2rockEntityManager");

		if (entity instanceof de.ejb3buch.ticket2rock.entity.demo.Enti) {
			Geburtsanzeige geburtsanzeige = new Geburtsanzeige(
					((Enti) entity).getId());
			em.persist(geburtsanzeige);
		}
	}
}
