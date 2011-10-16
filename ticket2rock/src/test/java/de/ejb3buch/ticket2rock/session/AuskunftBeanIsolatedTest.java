/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerte auf Basis von EJB 3.1 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Jo Ehm, Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg, Holger Koschek,
 *  Carsten Sahling, Roman Schloemmer, Norman Erck, Carl Anders Duevel, Daniel Steinhoefer
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

package de.ejb3buch.ticket2rock.session;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.ejb3buch.ticket2rock.entity.Konzert;

/**
 * Beispiel fuer einen Unit-Tests unter Zuhilfename des EasyGloss Frameworks.
 * 
 * @author Dierk
 * 
 */
public class AuskunftBeanIsolatedTest {

	private EntityManager em;

	private Query query;

	private JavaEEGloss gloss;

	private List<Konzert> data;

	private ArrayList<Konzert> konzerteInHamburg;

	private Konzert konzertInHamburg;

	@Before
	public void umgebungAufbauen() {
		em = Mockito.mock(EntityManager.class);
		query = Mockito.mock(Query.class);
		// em = createMock(EntityManager.class);
		// query = createMock(Query.class);
		gloss = new JavaEEGloss();
		gloss.addEM(em);

		konzertInHamburg = new Konzert();
		Konzert b = new Konzert();
		konzerteInHamburg = new ArrayList<Konzert>();
		konzerteInHamburg.add(konzertInHamburg);

		data = new ArrayList<Konzert>();
		data.add(konzertInHamburg);
		data.add(b);
	}

	@After
	public void mocksChecken() {
		Mockito.verify(em).createQuery(Mockito.anyString());
		Mockito.verify(query).getResultList();
	}

	@Test
	public void findeKeinKonzert() {
		Mockito.when(em.createQuery("SELECT k FROM Konzert k ")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(null);

		AuskunftBean auskunft = gloss.make(AuskunftBean.class);
		auskunft.sucheKonzerte(null, null, null);
	}

	@Test
	public void findeKonzertMitEinemParameter() {
		Mockito.when(em.createQuery("SELECT k FROM Konzert k where upper(k.ort.name) like :ortsName")).thenReturn(query);
		Mockito.when(query.setParameter("ortsName", "%HAMBURG%")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(konzerteInHamburg);

		AuskunftBean auskunft = gloss.make(AuskunftBean.class);
		List<Konzert> gefundeneKonzerte = auskunft.sucheKonzerte("Hamburg", null, null);

		assertEquals(1, gefundeneKonzerte.size());
		assertEquals(konzertInHamburg, gefundeneKonzerte.get(0));
	}

	@Test
	public void findeKonzertMitZweiParametern() {
		Date datum = new Date(); 
		Mockito.when(em.createQuery("SELECT k FROM Konzert k where upper(k.ort.name) like :ortsName and k.datum >= :vonDatum")).thenReturn(query);
		Mockito.when(query.setParameter("ortsName", "%HAMBURG%")).thenReturn(query);
		Mockito.when(query.setParameter("vonDatum", datum, TemporalType.DATE)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(konzerteInHamburg);

		AuskunftBean auskunft = gloss.make(AuskunftBean.class);
		List<Konzert> gefundeneKonzerte = auskunft.sucheKonzerte("Hamburg", datum, null);

		assertEquals(1, gefundeneKonzerte.size());
		assertEquals(konzertInHamburg, gefundeneKonzerte.get(0));
	}

	@Test
	public void findeZweiKonzerte() {
		Mockito.when(em.createQuery("SELECT k FROM Konzert k ")).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(data);

		AuskunftBean auskunft = gloss.make(AuskunftBean.class);
		List<Konzert> gefundeneKonzerte = auskunft.sucheKonzerte(null, null, null);

		assertEquals(2, gefundeneKonzerte.size());
	}
}
