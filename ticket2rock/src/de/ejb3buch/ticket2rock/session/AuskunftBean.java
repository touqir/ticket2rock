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

package de.ejb3buch.ticket2rock.session;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Konzert;

@Stateless
@SuppressWarnings("unchecked")
@WebService
@SOAPBinding(style = Style.RPC)
public class AuskunftBean implements Auskunft, AuskunftLocal {

	static Logger logger = Logger.getLogger(AuskunftBean.class);

	@PersistenceContext
	private EntityManager em;

	/**
	 * @inheritDoc
	 */
	@WebMethod
	public String sucheKonzerteWeb(String ortsName, Date vonDatum, Date bisDatum) {
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
			resultate.append("    <tournee>");
			resultate.append(konzert.getTournee().getName());
			resultate.append("</tournee>\n");
			resultate.append("  </konzert>\n");
		}
		resultate.append("</konzert-liste>");
		return resultate.toString();
	}

	public List<Konzert> sucheKonzerte(String ortsName, Date vonDatum,
			Date bisDatum) {

		// generiere den query String dynamisch abhängig von der
		// Belegung der Übergabeparameter
		StringBuffer buf = new StringBuffer();
		buf.append("FROM Konzert k ");
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
			logger
					.debug("Anzahl der gefundenen Konzerte: "
							+ resultList.size());
		}
		return resultList;
	}

}
