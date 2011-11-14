package de.ejb3buch.ticket2rock.session.statistics;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;
import javax.ejb.EJBException;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.ejb3buch.ticket2rock.SystestUtils;
import de.ejb3buch.ticket2rock.session.ticketbestellung.Stornator;
import de.ejb3buch.ticket2rock.session.ticketbestellung.StornatorBean;
import de.ejb3buch.ticket2rock.session.ticketbestellung.StornatorLocal;

@RunWith(Arquillian.class)
public class StornatorbeanIT {
	@EJB(mappedName = "StornatorBean/remote")
	Stornator stornator;

	@Deployment
	public static WebArchive createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addAsLibraries(SystestUtils.getArchiveWithPersistenceUnit())
				.addClasses(StornatorBean.class, StornatorLocal.class,
						Stornator.class);
	}

	@Test
	public void deleteInexistantOrder() {
		try {
			stornator.storniereBestellung(-1);
		} catch (EJBException e) {
			Throwable expected = e.getCause();
			assertEquals(IllegalArgumentException.class, expected.getClass());
			assertEquals("Es gibt keine Ticketbestellung mit der id '-1'!",
					expected.getMessage());

		}
	}

}
