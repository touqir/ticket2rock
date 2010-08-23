package de.ejb3buch.ticket2rock.session.demo.methodeninterzeptor;

import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.interceptor.Interceptors;

import de.ejb3buch.ticket2rock.entity.demo.Enti;
import de.ejb3buch.ticket2rock.session.demo.ZielobjektBase;

@Stateless(name="ZielobjektBeanMethodeninterzeptor")
public class ZielobjektBean extends ZielobjektBase {

	@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
	public void fangMichAb() {
		super.fangMichAb();
	}

	@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
	public void duKriegstMichNicht() {
		super.duKriegstMichNicht();
	}

	// Hier verzichten wir auf die @Interceptors-Annotation, weil diese Methode
	// nicht abgefangen werden soll
	public void lassMichInRuhe() {
	}

	@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
	public void gibMirZeit() {
		super.gibMirZeit();
	}

	@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
	public Enti bruete() {
		return super.bruete();
	}

	@Timeout
	protected void deineZeitIstAbgelaufen(Timer timer) {
		super.deineZeitIstAbgelaufen(timer);
	}
}
