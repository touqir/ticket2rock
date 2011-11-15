/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerten. 
 *
 *  Copyright (C) 2006-2011
 *  Holisticon AG
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
package de.ejb3buch.ticket2rock.session;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;
import org.jboss.ejb3.annotation.RemoteBinding;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.session.crud.KonzertVerwaltungLocal;

@Stateless
@WebService(serviceName = "KonzertInfo")
@SOAPBinding(style = Style.RPC)
@RemoteBinding(jndiBinding = AuskunftBean.JNDI_REMOTE)
public class AuskunftBean implements Auskunft, AuskunftLocal {
	public static final String JNDI_REMOTE = "Auskunft/remote";
	static Logger logger = Logger.getLogger(AuskunftBean.class);

	@PersistenceContext
	private EntityManager em;

	@EJB
	private KonzertVerwaltungLocal konzertVerwaltung;

	/**
	 * @inheritDoc
	 */
	@WebMethod
	public String sucheKonzerteWeb(
			@WebParam(name = "Ortsname") String ortsName,
			@WebParam(name = "Startdatum") Date vonDatum,
			@WebParam(name = "Enddatum") Date bisDatum) {
		List<Konzert> konzerte = sucheKonzerte(ortsName, vonDatum, bisDatum);
		StringBuffer resultate = new StringBuffer("<konzert-liste>\n");
		for (Konzert konzert : konzerte) {
			resultate.append("  <konzert>\n");
			resultate.append("    <ort>");
			resultate.append(konzert.getOrt().getName());
			resultate.append("</ort>\n");
			resultate.append("    <location>");
			resultate.append(konzert.getOrt().getAdresse());
			resultate.append("</location>\n");
			resultate.append("    <datum>");
			resultate.append(konzert.getDatum());
			resultate.append("</datum>\n");
			resultate.append("    <interpret>");
			resultate.append(konzert.getInterpret().getName());
			resultate.append("</interpret>\n");
			if (konzert.getTournee() != null) {
				resultate.append("    <tournee>");
				resultate.append(konzert.getTournee().getName());
				resultate.append("</tournee>\n");
			}
			resultate.append("  </konzert>\n");
		}
		resultate.append("</konzert-liste>");
		return resultate.toString();
	}

	@WebMethod(exclude = true)
	public List<Konzert> sucheKonzerte(String ortsName, Date vonDatum,
			Date bisDatum) {

		// generiere den query String dynamisch abhängig von der
		// Belegung der Übergabeparameter
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT k FROM Konzert k ");
		boolean firstPredicate = true;
		if ((ortsName != null) && (ortsName.length() > 0)) {
			buf.append("where ");
			buf.append("upper(k.ort.name) like :ortsName");
			firstPredicate = false;
		}
		if (vonDatum != null) {
			if (firstPredicate) {
				buf.append("where ");
			} else {
				buf.append(" and ");
			}
			buf.append("k.datum >= :vonDatum");
			firstPredicate = false;
		}

		if (bisDatum != null) {
			if (firstPredicate) {
				buf.append("where ");
			} else {
				buf.append(" and ");
			}
			buf.append("k.datum <= :bisDatum");
		}

		// setze die Query Parameter dynamisch
		Query query = em.createQuery(buf.toString());
		if ((ortsName != null) && (ortsName.length() > 0)) {
			query.setParameter("ortsName", "%" + ortsName.toUpperCase() + "%");
		}
		if (vonDatum != null) {
			query.setParameter("vonDatum", vonDatum, TemporalType.DATE);
		}
		if (bisDatum != null) {
			query.setParameter("bisDatum", bisDatum, TemporalType.DATE);
		}

		List resultList = query.getResultList();
		if (logger.isDebugEnabled() && resultList != null) {
			logger.debug("Anzahl der gefundenen Konzerte: " + resultList.size());
		}
		return resultList;
	}

	@Override
	@Asynchronous
	public Future<Integer> schaetzeErwarteteBesucher(int konzertId) {
		Integer result;
		// Sehr komplexe Berechnung der zu erwartenden Besucher eines Konzert
		// basierend auf statistischen Werten, die
		// empirisch Ÿber die gloreichen Jahre der Rockgeschichte hinweg
		// ermittelt worden sind ;-)
		Konzert konzert = konzertVerwaltung
				.getConcertWithDetailsById(konzertId);
		int maxBesucher = konzert.getOrt().getKapazitaet();
		int anzahlKonzerte = konzert.getInterpret().getKonzerte().size();
		int anzahlAlben = konzert.getInterpret().getAlben().size();

		result = new Integer((int) Math.min(
				maxBesucher,
				Math.round(maxBesucher * 0.7 + 0.05
						* (anzahlAlben + anzahlKonzerte))));

		return new AsyncResult<Integer>(result);
	}

}
