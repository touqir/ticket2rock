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
package de.ejb3buch.ticket2rock;

import java.net.URL;
import java.util.Hashtable;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.jboss.ejb3.embedded.EJB3StandaloneBootstrap;
import org.jboss.ejb3.embedded.EJB3StandaloneDeployer;

public class EmbeddedContainerTestHelper {

	private static final Logger log = Logger
			.getLogger(EmbeddedContainerTestHelper.class);

	private InitialContext ctx = null;

	private EJB3StandaloneDeployer deployer = null;

	private static EmbeddedContainerTestHelper INSTANCE = new EmbeddedContainerTestHelper();

	/**
	 * Startet den Embedded Container.
	 * 
	 * @param extraResources
	 * @throws Exception
	 */
	public static void startupEmbeddedContainer(List<String> extraResources)
			throws Exception {
		INSTANCE.startup(extraResources);
	}

	/*
	 * Hier tricksen wir ein bisschen mit einer Instanz dieser Klasse, damitwir
	 * den Context cachen koennen.
	 */
	public static InitialContext getInitialContext() throws Exception {
		return INSTANCE.getTheInitialContext();
	}

	/**
	 * Faehrt den Container wieder runter, zerstoert ausserdem das gecachte cts
	 * Object
	 * 
	 */
	public static void shutdownEmbeddedContainer() {
		INSTANCE.shutdown();
	}

	// ------------------------- private Methods ---------------------------
	private void startup(List<String> extraResources)
			throws Exception {
		log.info("Starting Embedded JBoss");
		long clock = System.currentTimeMillis();

		try {
			setSystemJNDIProperties();

			EJB3StandaloneBootstrap.boot(null);

			if (extraResources != null) {
				for (String resource : extraResources) {
					EJB3StandaloneBootstrap.deployXmlResource(resource);
				}
			}

			deployer = EJB3StandaloneBootstrap.createDeployer();
			deployer.getArchives().add(getArchiveURL());
			deployer.create();
			deployer.start();

		} catch (Exception ex) {
			log.fatal(ex);
			throw new RuntimeException(ex);
		} finally {
			long duration = System.currentTimeMillis() - clock;
			log.info("Embedded JBoss started, duration = " + duration + "ms");
		}
	}

	/**
	 * @return die URL, unter der die EJB Klassen zu finden sind.
	 * @throws Exception
	 */
	private static URL getArchiveURL() throws Exception {

		URL res = Thread.currentThread().getContextClassLoader().getResource(
				"META-INF/persistence.xml");
		return EJB3StandaloneDeployer.getContainingUrlFromResource(res,
				"META-INF/persistence.xml");
	}

	private InitialContext getTheInitialContext() throws Exception {
		if (ctx == null) {
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
	private Hashtable<String, String> getInitialContextProperties() {
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put("java.naming.factory.initial",
				"org.jnp.interfaces.LocalOnlyContextFactory");
		props.put("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		return props;
	}

	/**
	 * Setzt die JNDI Properties auf Systemebene, wird fuer den Start des
	 * Containers benoetigt. Dadurch sparen wird uns die sonst notwendige
	 * jndi.properties Konfigurationsdatei
	 * 
	 */
	private void setSystemJNDIProperties() {
		Hashtable<String, String> props = getInitialContextProperties();
		for (String elem : props.keySet()) {
			System.setProperty(elem, props.get(elem));
		}

	}

	/**
	 * Faehrt den Container wieder runter, zerstoert ausserdem das gecachte cts
	 * Object
	 * 
	 */
	private void shutdown() {

		ctx = null;
		try {
			if (deployer != null) {
				deployer.stop();
				deployer.destroy();
			}
			EJB3StandaloneBootstrap.shutdown();
		} catch (Exception ex) {
			log.fatal(ex);
			throw new RuntimeException(ex);
		} finally {
			log.info("Shutdown of Embedded JBoss completed");
		}
	}

}