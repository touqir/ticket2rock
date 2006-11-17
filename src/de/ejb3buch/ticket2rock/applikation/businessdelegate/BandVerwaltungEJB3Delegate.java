package de.ejb3buch.ticket2rock.applikation.businessdelegate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.applikation.helper.IViewCollectionBuilder;
import de.ejb3buch.ticket2rock.applikation.model.BandBackingBean;
import de.ejb3buch.ticket2rock.entity.Band;
import de.ejb3buch.ticket2rock.entity.Musiker;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltungLocal;

/**
 * BusinessDelegate, der die Dienste an EJB3 Session Beans delegiert. 
 *
 */
public class BandVerwaltungEJB3Delegate implements BandVerwaltungDelegate {

	BandVerwaltungLocal myBandVerwaltung;

	static Logger logger = Logger.getLogger(BandVerwaltungEJB3Delegate.class);

	/**
	 * Konstruktor des BusinessDelegates. Über den InitialContext wird eine
	 * lokale Referenz der Session Bean geholt
	 * 
	 */
	public BandVerwaltungEJB3Delegate() {
		try {
			InitialContext ctx = new InitialContext();
			myBandVerwaltung = (BandVerwaltungLocal) ctx
					.lookup("ticket2rock/BandVerwaltungBean/local");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hole die Liste aller Band EJBs und konvertiere diese in eine Liste von
	 * BandBacking Beans. 
	 * 
	 * @return Liste von BandBackingBeans
	 */
	@SuppressWarnings("unchecked")
	public List<BandBackingBean> getBands() {
		List<Band> bandEntityBeans = myBandVerwaltung.getBands();
		if (bandEntityBeans == null) {
			return new ArrayList();
		}
		List bandBakingBeans = new ArrayList();
		for (Band bandEBean : bandEntityBeans) {
			BandBackingBean bandBBean = new BandBackingBean(bandEBean.getId(),
					bandEBean.getName());
			// Generiere Musikernamensliste und setze sie in der BackingBean  
			Set<Musiker> musikerList = bandEBean.getMusiker();
			List musikerNamen = new ArrayList();
			if ((musikerList != null) && (!musikerList.isEmpty())) {
				for (Musiker musiker : musikerList) {
					musikerNamen.add(musiker.getName());
				}
			}
			bandBBean.setMusikerNamensListe(musikerNamen);
			bandBakingBeans.add(bandBBean);
		}
		return bandBakingBeans;
	}

	/**
	 * Selektiere die Band mit einem gegebenen Namen
	 * 
	 * @param name Name der Band
	 * @return BandBackingBean mit dem gegebenen Namen
	 */
	public BandBackingBean getBandByName(String name) {
		Band band = myBandVerwaltung.getBandByName(name);
		if (band != null) {
			return new BandBackingBean(band.getId(), band.getName());
		}
		return null;
	}

	public void createBand(BandBackingBean bandBackingBean) {
		Band band = new Band();
		band.setName(bandBackingBean.getName());
		Set<Musiker> bandMusikerSet = getMusikerEntitiesForIds(bandBackingBean);
		band.setMusiker(bandMusikerSet);	
		this.myBandVerwaltung.createBand(band);
	}

	private Set<Musiker> getMusikerEntitiesForIds(BandBackingBean bandBackingBean) {
		Set<Musiker> bandMusikerSet = new HashSet<Musiker>();
        // für alle musikerIds der BandBackingBean hole entsprechende
		// Musiker Entittäten und weise diese der Band zu
		for (String musikerId:bandBackingBean.getMusikerIdListe()) {
			System.out.println("Musiker id which is assigned to band: " + musikerId);
			Musiker musiker = myBandVerwaltung.getMusikerById(Integer.valueOf(musikerId));
			bandMusikerSet.add(musiker);
		}
		return bandMusikerSet;
	}

	public void updateBand(BandBackingBean bandBackingBean) {
		Band band = new Band();
		band.setId(bandBackingBean.getId());
		band.setName(bandBackingBean.getName());		
		Set<Musiker> bandMusikerSet = getMusikerEntitiesForIds(bandBackingBean);
		band.setMusiker(bandMusikerSet);		
		this.myBandVerwaltung.updateBand(band);

	}

	public void deleteBand(Integer bandId) {
		myBandVerwaltung.deleteBand(bandId);
	}

	public void buildBandMusikerCollection(IViewCollectionBuilder collectionBuilder,Integer bandId) {
		Band band = myBandVerwaltung.getBandById(bandId);
		Set<Musiker> musikerSet = band.getMusiker();
		if (musikerSet != null) {
			for (Musiker musiker : musikerSet) {
              collectionBuilder.buildItem(Integer.toString(musiker.getId()),musiker.getName());
			}
		}
	}
	
	public void buildMusikerCollection(IViewCollectionBuilder collectionBuilder) {
		Collection<Musiker> musikerSet = myBandVerwaltung.getMusiker();
		if (musikerSet != null) {
			for (Musiker musiker : musikerSet) {
              collectionBuilder.buildItem(Integer.toString(musiker.getId()),musiker.getName());
			}
		}
	}	

}
