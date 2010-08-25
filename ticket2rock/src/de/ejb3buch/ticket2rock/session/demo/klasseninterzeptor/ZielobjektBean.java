package de.ejb3buch.ticket2rock.session.demo.klasseninterzeptor;

import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;

import de.ejb3buch.ticket2rock.session.demo.ZielobjektBase;

@Stateless(name = "ZielobjektBeanKlasseninterzeptor")
@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
public class ZielobjektBean extends ZielobjektBase {

	@ExcludeClassInterceptors
	public void lassMichInRuhe() {
	}

	@Timeout
	protected void deineZeitIstAbgelaufen(Timer timer) {
		super.deineZeitIstAbgelaufen(timer);
	}
}
