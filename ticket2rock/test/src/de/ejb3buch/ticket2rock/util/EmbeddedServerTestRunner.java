package de.ejb3buch.ticket2rock.util;

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

import static de.ejb3buch.ticket2rock.util.DataUtil.createConnectionPoolAndJDBCResource;
import static de.ejb3buch.ticket2rock.util.DataUtil.insertTestData;
import static de.ejb3buch.ticket2rock.util.ServerUtil.createServer;
import static de.ejb3buch.ticket2rock.util.ServerUtil.deploy;
import static de.ejb3buch.ticket2rock.util.ServerUtil.deleteFiles;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.glassfish.api.embedded.LifecycleException;
import org.glassfish.api.embedded.Server;
import org.junit.Assert;
import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.TestClassRunner;
import org.junit.runner.notification.RunNotifier;

/**
 * Dieser TestRunner ermöglicht das Testen mit dem embedded Server. Da zum
 * Zeitpunkt der Erstellung kein lauffähiger embedded JBoss 6 zur Verfügung
 * steht, wird der embedded Glassfish Server genutzt.
 * 
 * @author Carl
 * 
 */
public class EmbeddedServerTestRunner extends TestClassRunner {
	private static int startUpRequests;
	private static final Logger LOGGER = Logger
			.getLogger(EmbeddedServerTestRunner.class);
	private static Server server;
	static Context ctx;

	/**
	 * Der Konstruktor wird dazu benutzt, die Anzahl der Tests festzustellen,
	 * die den embedded Server benötigen.
	 * 
	 * @param klass
	 * @throws InitializationError
	 */
	public EmbeddedServerTestRunner(Class<?> klass) throws InitializationError {
		super(klass);
		startUpRequests++;
	}

	/**
	 * Diese Methode wird für jede Testklasse einmal aufgerufen. Sie startet den
	 * Server beim ersten Aufruf und fährt den Server nach der letzten Klasse
	 * wieder herunter.
	 * 
	 */
	@Override
	public void run(RunNotifier arg0) {
		startServerIfNecessary();
		super.run(arg0);
		startUpRequests--;
		tearDownServerIfNecessary();
	}

	private void tearDownServerIfNecessary() {
		if (startUpRequests == 0) {
			Assert.assertNotNull(server);
			try {
				LOGGER.info("All startupRequests are done, shutting down the server.");
				undeployAll();
				shutDownServer();
				deleteFiles();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	

	private static void startServerIfNecessary() {
		if (server == null) {
			deleteFiles();
			LOGGER.info(String.format("Starting the server for: %d requests.",
					startUpRequests));
			Assert.assertTrue(startUpRequests > 0);
			try {
				server = createServer();
				server.start();
				createConnectionPoolAndJDBCResource(server);
				deploy(server);
				ctx = new InitialContext();
				insertTestData();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}


	private static void undeployAll() throws LifecycleException {
		if (server != null && server.getDeployer() != null) {
			server.getDeployer().undeployAll();
			server.stop();
		}
	}

	private static void shutDownServer() throws LifecycleException {
		if (server != null) {
			server.stop();
		}
	}

	public static Object lookup(String name) throws Exception {
		return ctx.lookup(name);
	}

	public static UserTransaction getStartedTransaction() {
		try {
			UserTransaction utx = (UserTransaction) lookup("java:comp/UserTransaction");
			utx.begin();
			return utx;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}