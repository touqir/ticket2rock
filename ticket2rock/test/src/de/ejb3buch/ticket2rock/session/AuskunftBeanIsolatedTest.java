/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Jo Ehm, Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg, Holger Koschek,
 *  Carsten Sahling, Roman Schloemmer
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

import static org.easymock.EasyMock.*;
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
public class AuskunftBeanIsolatedTest
{

    private EntityManager em;

    private Query query;

    private JavaEEGloss gloss;

    private List<Konzert> data;

    @Before
    public void umgebungAufbauen()
    {
        em = createMock(EntityManager.class);
        query = createMock(Query.class);
        gloss = new JavaEEGloss();
        gloss.addEM(em);

        Konzert a = new Konzert();
        Konzert b = new Konzert();
        data = new ArrayList<Konzert>();
        data.add(a);
        data.add(b);
    }

    @After
    public void mocksChecken()
    {
        verify(query);
        verify(em);
    }

    @Test
    public void findeKeinKonzert()
    {

        expect(em.createQuery("FROM Konzert k ")).andReturn(query);
        expect(query.getResultList()).andReturn(null);
        replay(query);
        replay(em);

        AuskunftBean auskunft = gloss.make(AuskunftBean.class);
        auskunft.sucheKonzerte(null, null, null);

    }

    @Test
    public void findeKonzertMitEinemParameter()
    {

        expect(em.createQuery("FROM Konzert k where upper(k.ort.name) like :ortsName")).andReturn(query);
        expect(query.setParameter("ortsName", "%HAMBURG%")).andReturn(query);
        expect(query.getResultList()).andReturn(null);
        replay(query);
        replay(em);

        AuskunftBean auskunft = gloss.make(AuskunftBean.class);
        auskunft.sucheKonzerte("Hamburg", null, null);

    }

    @Test
    public void findeKonzertMitZweiParametern()
    {
        Date datum = new Date();
        expect(em.createQuery("FROM Konzert k where upper(k.ort.name) like :ortsName and k.datum >= :vonDatum"))
                .andReturn(query);
        expect(query.setParameter("ortsName", "%HAMBURG%")).andReturn(query);
        expect(query.setParameter("vonDatum", datum, TemporalType.DATE)).andReturn(query);

        expect(query.getResultList()).andReturn(null);
        replay(query);
        replay(em);

        AuskunftBean auskunft = gloss.make(AuskunftBean.class);
        auskunft.sucheKonzerte("Hamburg", datum, null);
    }

    @Test
    public void findeZweiKonzerte()
    {

        expect(em.createQuery("FROM Konzert k ")).andReturn(query);
        expect(query.getResultList()).andReturn(data);
        replay(query);
        replay(em);

        AuskunftBean auskunft = gloss.make(AuskunftBean.class);
        List result = auskunft.sucheKonzerte(null, null, null);

        assertEquals(result, data);
    }
}
