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

package de.ejb3buch.ticket2rock.applikation.controller;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.applikation.servicelocator.ServiceLocator;

public class StornierungController {

	static Logger logger = Logger.getLogger(StornierungController.class);

	private ServiceLocator serviceLocator;

	private String messageQueue;

	private String bestellnummer;

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public String getMessageQueue() {
		return messageQueue;
	}

	public void setMessageQueue(String messageQueue) {
		this.messageQueue = messageQueue;
	}

	public String getBestellnummer() {
		return bestellnummer;
	}

	public void setBestellnummer(String bestellnummer) {
		this.bestellnummer = bestellnummer;
	}

	public String storno() {
		QueueConnection cnn = null;
		QueueSender sender = null;
		QueueSession sess = null;
		Queue queue = null;

		try {
			InitialContext ctx = new InitialContext(System.getProperties());

			// Der Name der JMS-Queue wurde in eine <managed-property>
			// (in faces-config.xml) ausgelagert, um ihn beim Deployment
			// an eine konkrete Queue in einer Applikationsserver-Instanz
			// anpassen zu können.
			// Für die Message-Driven Bean ist der Queue-Name im
			// Deployment-Deskriptor ejb-jar.xml festgelegt.
			queue = (Queue) ctx.lookup(this.messageQueue);
			QueueConnectionFactory factory = (QueueConnectionFactory) ctx
					.lookup("ConnectionFactory");
			cnn = factory.createQueueConnection();
			sess = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

			TextMessage msg = sess.createTextMessage("STORNO "
					+ this.bestellnummer);
			// The sent timestamp acts as the message's ID
			long sent = System.currentTimeMillis();
			msg.setLongProperty("sent", sent);

			logger.debug("Sending message: " + msg);

			sender = sess.createSender(queue);
			sender.send(msg);
			sess.close();
		} catch (Exception e) {
			logger.error(e);
		}

		return "stornierungsergebnis";
	}
}
