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

import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class EmbeddedContainerTestBase
{

    private UserTransaction utx = null;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        EmbeddedContainerTestHelper.startupEmbeddedContainer(null);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        EmbeddedContainerTestHelper.shutdownEmbeddedContainer();
    }

    public EmbeddedContainerTestBase()
    {
        super();
    }

    /**
     * Startet eine UserTransaction vor jedem Test
     */
    @Before
    public void startTransaction() throws Exception
    {
        utx = EmbeddedContainerTestHelper.startUserTransaction();
    }

    /**
     * und nach jedem Test schmeissen wir die Aenderungen an der DB weg.
     */
    @After
    public void rollbackTransaction() throws Exception
    {
        utx.rollback();
    }

    protected Object lookup(String name) throws Exception
    {
        return EmbeddedContainerTestHelper.lookup(name);
    }

    protected InitialContext getInitialContext() throws Exception
    {
        return EmbeddedContainerTestHelper.getInitialContext();
    }
}