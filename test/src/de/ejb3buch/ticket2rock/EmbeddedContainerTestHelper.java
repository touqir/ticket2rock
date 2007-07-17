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

import java.net.URL;
import java.util.Hashtable;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;
import org.jboss.ejb3.embedded.EJB3StandaloneBootstrap;
import org.jboss.ejb3.embedded.EJB3StandaloneDeployer;

/**
 * Diese Klasse kapselt die Zugriffe auf den Embedded Jboss Server. Um den
 * Server nicht unnoetig zu stopenn und zu starten, wird ein wenig Buch gefuehrt
 * ueber die Anzahl der Start und Stoppanfragen.
 * 
 * @author Dierk
 * 
 */
public class EmbeddedContainerTestHelper
{

    private static final Logger log = Logger.getLogger(EmbeddedContainerTestHelper.class);

    private InitialContext ctx = null;

    private EJB3StandaloneDeployer deployer = null;

    private int runlevel = 0;

    private static EmbeddedContainerTestHelper INSTANCE = new EmbeddedContainerTestHelper();

    /**
     * Startet den Embedded Container.
     * 
     * @param extraResources
     * @throws Exception
     */
    public static void startupEmbeddedContainer(List<String> extraResources) throws Exception
    {
        INSTANCE.startup(extraResources);
    }

    /*
     * Liefert den InitialContext, der innerhalb dieser Klasse im Cache gehalten
     * wird.
     */
    public static InitialContext getInitialContext() throws Exception
    {
        return INSTANCE.getTheInitialContext();
    }

    /**
     * Faehrt den Container wieder runter, zerstoert ausserdem das gecachte
     * InitialContext Object
     * 
     */
    public static void shutdownEmbeddedContainer()
    {
        INSTANCE.shutdown();
    }

    /**
     * Startet eine UserTransaction. Wir benoetigen eine Transaction um die
     * Operationen der Persistenzschicht herum, weil sonst u.U. Objektnetze
     * nicht mehr verfuegbar sind.
     * 
     * @return die gestartete UserTransaction
     * @throws Exception
     */
    public static UserTransaction startUserTransaction() throws Exception
    {
        UserTransaction utx = (UserTransaction) EmbeddedContainerTestHelper.lookup("UserTransaction");
        utx.begin();
        return utx;
    }

    /**
     * Sucht ein Object im JNDI Baum
     * 
     * @param name
     *            Name des gesuchten Objektes
     * @return das Object
     * @throws NamingException
     * @throws Exception
     */
    public static Object lookup(String name) throws NamingException, Exception
    {
        return getInitialContext().lookup(name);
    }

    // ------------------------- private Methods ---------------------------
    private void startup(List<String> extraResources) throws Exception
    {
        if (runlevel == 0)
        {
            log.info("Starting Embedded JBoss");
            long clock = System.currentTimeMillis();

            try
            {
                setSystemJNDIProperties();

                EJB3StandaloneBootstrap.boot(null);

                if (extraResources != null)
                {
                    for (String resource : extraResources)
                    {
                        EJB3StandaloneBootstrap.deployXmlResource(resource);
                    }
                }

                deployer = EJB3StandaloneBootstrap.createDeployer();
                deployer.getArchives().add(getArchiveURL());

                deployer.create();
                deployer.start();
                runlevel++;
            } catch (Exception ex)
            {
                log.fatal(ex);
                throw new RuntimeException(ex);
            } finally
            {
                long duration = System.currentTimeMillis() - clock;
                log.info("Embedded JBoss started, duration = " + duration + "ms");
            }
        } else
        {
            runlevel++;
            log.debug("Embedded JBoss activated " + runlevel + " times");
        }
    }

    /**
     * @return die URL, unter der die EJB Klassen zu finden sind.
     * @throws Exception
     */
    private static URL getArchiveURL() throws Exception
    {

        URL res = Thread.currentThread().getContextClassLoader().getResource("META-INF/persistence.xml");
        return EJB3StandaloneDeployer.getContainingUrlFromResource(res, "META-INF/persistence.xml");
    }

    private InitialContext getTheInitialContext() throws Exception
    {
        if (ctx == null)
        {
            Hashtable props = getInitialContextProperties();
            ctx = new InitialContext(props);
        }
        return ctx;
    }

    /**
     * Liefert die notwendigen Properties fuer den Initial-Context.
     * 
     * @return die notwendigen Properties fuer den Context
     */
    private Hashtable<String, String> getInitialContextProperties()
    {
        Hashtable<String, String> props = new Hashtable<String, String>();
        props.put("java.naming.factory.initial", "org.jnp.interfaces.LocalOnlyContextFactory");
        props.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        return props;
    }

    /**
     * Setzt die JNDI Properties auf Systemebene, wird fuer den Start des
     * Containers benoetigt. Dadurch sparen wird uns die sonst notwendige
     * jndi.properties Konfigurationsdatei
     * 
     */
    private void setSystemJNDIProperties()
    {
        Hashtable<String, String> props = getInitialContextProperties();
        for (String elem : props.keySet())
        {
            System.setProperty(elem, props.get(elem));
        }
    }

    /**
     * Faehrt den Container wieder runter, zerstoert ausserdem das gecachte cts
     * Object
     * 
     */
    private void shutdown()
    {

        if (--runlevel > 0)
        {
            return;
        }

        ctx = null;
        try
        {

            if (deployer != null)
            {
                deployer.stop();
                deployer.destroy();
            }
            EJB3StandaloneBootstrap.shutdown();
        } catch (Exception ex)
        {
            log.fatal(ex);
            throw new RuntimeException(ex);
        } finally
        {
            log.info("Shutdown of Embedded JBoss completed");
        }
    }
}
