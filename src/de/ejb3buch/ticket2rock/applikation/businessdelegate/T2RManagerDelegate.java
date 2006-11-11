package de.ejb3buch.ticket2rock.applikation.businessdelegate;

import java.util.List;
import java.util.Map;

import de.ejb3buch.ticket2rock.applikation.helper.IViewCollectionBuilder;
import de.ejb3buch.ticket2rock.applikation.model.BandBackingBean;


public interface T2RManagerDelegate {
	public List<BandBackingBean> getBands();

	public void createBand(BandBackingBean band);
	
	public BandBackingBean getBandByName(String name);

	public void updateBand(BandBackingBean bandBackingBean);

	public void deleteBand(BandBackingBean bandBackingBean);

	public void buildMusikerCollection(IViewCollectionBuilder collectionBuilder);

	public void buildBandMusikerCollection(IViewCollectionBuilder collectionBuilder,Integer bandId);
	
}
