package de.ejb3buch.ticket2rock.applikation.businessdelegate;

import java.util.List;

import de.ejb3buch.ticket2rock.applikation.model.BandBakingBean;


public interface T2RManagerDelegate {
	public List<BandBakingBean> getBands();

	public void createBand(BandBakingBean band);
	
	public BandBakingBean getBandByName(String name);

	public void updateBand(BandBakingBean bandBackingBean);

	public void deleteBand(BandBakingBean bandBackingBean);
}
