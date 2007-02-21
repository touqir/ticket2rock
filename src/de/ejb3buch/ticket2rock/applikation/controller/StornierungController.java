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
