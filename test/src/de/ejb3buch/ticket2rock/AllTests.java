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

package de.ejb3buch.ticket2rock;

import junit.framework.JUnit4TestAdapter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.ejb3buch.ticket2rock.session.AuskunftBeanTest;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBeanTest;
import de.ejb3buch.ticket2rock.session.interceptor.BeanStatisticsInterceptorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses( { AllPOJTests.class, BandVerwaltungBeanTest.class, AuskunftBeanTest.class,
        BeanStatisticsInterceptorTest.class })
public class AllTests
{

    /**
     * Den eingebetteten EJB Container starten. Die Tests in der Suite machen
     * das zwar auch noch mal, aber unsere Hilfsklasse wacht darueber und macht
     * eine Buchhaltung, sodass mehrfaches Starten vermieden wird.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        EmbeddedContainerTestHelper.startupEmbeddedContainer(null);
    }

    /**
     * Den eingebetteten EJB Container fahren wir hier ganz brav wieder runter.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        EmbeddedContainerTestHelper.shutdownEmbeddedContainer();
    }

    // Used for backward compatibility (IDEs, Ant and JUnit 3 text runner)
    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter(AllTests.class);
    }
}
