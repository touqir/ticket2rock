package de.ejb3buch.ticket2rock.session.demo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.demo.Enti;

public class ZielobjektBase implements Zielobjekt {

	@Resource
	private TimerService timerService;
	
	@PersistenceContext
	private EntityManager em;

	private Logger logger = Logger.getRootLogger();

	public void fangMichAb() {
	}

	public void duKriegstMichNicht() {
		throw new RuntimeException("Mich kriegst Du nicht!");
	}

	public void lassMichInRuhe() {
	}

	public void gibMirZeit() {
		Calendar timerCal = GregorianCalendar.getInstance();
		timerCal.add(Calendar.MILLISECOND, 1);
		String info = "Deine Zeit läuft ab um "
				+ timerCal.get(Calendar.HOUR_OF_DAY) + " Uhr "
				+ timerCal.get(Calendar.MINUTE) + " Minuten, "
				+ timerCal.get(Calendar.SECOND) + " Sekunden und "
				+ timerCal.get(Calendar.MILLISECOND) + " Millisekunden!";
		timerService.createTimer(timerCal.getTime(), info);
		logger.info("Timer created");
	}
	
	public Enti bruete() {
		Enti enti = new Enti();
		enti.setName("Alfred Jodocus Kwack");
		em.persist(enti);
		return enti;
	}

	protected void deineZeitIstAbgelaufen(Timer timer) {
		logger.info("Bean timeout: " + (String) timer.getInfo());
	}
}
