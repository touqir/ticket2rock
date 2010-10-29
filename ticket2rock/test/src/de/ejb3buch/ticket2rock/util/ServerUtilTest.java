package de.ejb3buch.ticket2rock.util;

import org.junit.Test;

import de.ejb3buch.ticket2rock.migration.session.AuskunftBean;
import de.ejb3buch.ticket2rock.session.AuskunftLocal;
import static org.junit.Assert.*;

public class ServerUtilTest {
	@Test
	public void getJNDIAddress(){
		final String exp = "java:global/ticket2rock/ticket2rock/AuskunftBean!de.ejb3buch.ticket2rock.session.AuskunftLocal";
		assertEquals(exp,ServerUtil.getJNDIAddress(AuskunftBean.class, AuskunftLocal.class));
	}

}
