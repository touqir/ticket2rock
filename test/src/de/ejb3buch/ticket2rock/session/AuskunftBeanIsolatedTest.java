/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets f�r
 *  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen J�rg und Holger Koschek
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

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.ejb3buch.ticket2rock.entity.Konzert;

/**
 * Beispiel fuer einen Unit-Tests unter Zuhilfename des EasyGloss Frameworks
 * 
 * @author Dierk
 * 
 */
public class AuskunftBeanIsolatedTest {

    private EntityManager em;

    private Query query;

    private JavaEEGloss gloss;

    private List<Konzert> data;

    @Before
    public void umgebungAufbauen() {
	em = EasyMock.createMock(EntityManager.class);
	query = EasyMock.createMock(Query.class);
	gloss = new JavaEEGloss();
	gloss.addEM(em);
	Konzert a = new Konzert();
	Konzert b = new Konzert();

	data = new ArrayList<Konzert>();
	data.add(a);
	data.add(b);
    }

    @After
    public void mocksChecken() {
	EasyMock.verify(query);
	EasyMock.verify(em);
    }

    @Test
    public void findeKeinKonzert() {

	EasyMock.expect(em.createQuery("FROM Konzert k ")).andReturn(query);
	EasyMock.expect(query.getResultList()).andReturn(null);
	EasyMock.replay(query);
	EasyMock.replay(em);

	AuskunftBean auskunft = gloss.make(AuskunftBean.class);
	auskunft.sucheKonzerte(null, null, null);

    }

    @Test
    public void findeKonzertMitEinemParameter() {

	EasyMock.expect(
		em.createQuery("FROM Konzert k where upper(k.ort.name) like :ortsName"))
		.andReturn(query);
	EasyMock.expect(query.setParameter("ortsName", "%HAMBURG%")).andReturn(
		query);
	EasyMock.expect(query.getResultList()).andReturn(null);
	EasyMock.replay(query);
	EasyMock.replay(em);

	AuskunftBean auskunft = gloss.make(AuskunftBean.class);
	auskunft.sucheKonzerte("Hamburg", null, null);

    }

    @Test
    public void findeKonzertMitZweiParametern() {
	Date datum = new Date();
	EasyMock.expect(
		em.createQuery("FROM Konzert k where upper(k.ort.name) like :ortsName and k.datum >= :vonDatum"))
		.andReturn(query);
	EasyMock.expect(query.setParameter("ortsName", "%HAMBURG%")).andReturn(
		query);
	EasyMock.expect(
		query.setParameter("vonDatum", datum, TemporalType.DATE))
		.andReturn(query);

	EasyMock.expect(query.getResultList()).andReturn(null);
	EasyMock.replay(query);
	EasyMock.replay(em);

	AuskunftBean auskunft = gloss.make(AuskunftBean.class);
	auskunft.sucheKonzerte("Hamburg", datum, null);
    }

    @Test
    public void findeZweiKonzerte() {

	EasyMock.expect(em.createQuery("FROM Konzert k ")).andReturn(query);
	EasyMock.expect(query.getResultList()).andReturn(data);
	EasyMock.replay(query);
	EasyMock.replay(em);

	AuskunftBean auskunft = gloss.make(AuskunftBean.class);
	List result = auskunft.sucheKonzerte(null, null, null);

	assertEquals(result, data);
    }
}
