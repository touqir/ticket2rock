package de.ejb3buch.ticket2rock.session;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean;

@Stateless
@SuppressWarnings("unchecked")
public class AuskunftBean implements Auskunft {

	static Logger logger = Logger.getLogger(AuskunftBean.class);

	@PersistenceContext
	private EntityManager em;

	/**
	 * @inheritDoc
	 */
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
			query.setParameter("ortsName","%" + ortsName.toUpperCase() + "%");
		}
		if (vonDatum != null) {
			query.setParameter("vonDatum",vonDatum,TemporalType.DATE);
		}
		if (bisDatum != null) {
			query.setParameter("bisDatum",bisDatum,TemporalType.DATE);	
		}
		
		List resultList = query.getResultList();
		logger.debug("Anzahl der gefundenen Konzerte: " + resultList.size());
		return resultList;
	}

}
