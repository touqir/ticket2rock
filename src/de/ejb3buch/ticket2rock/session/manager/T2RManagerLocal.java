package de.ejb3buch.ticket2rock.session.manager;

import java.util.List;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Band;

@Local
public interface T2RManagerLocal {

	public List<Band> getBands();
	
	public void createBand(Band band);
	
	public Band getBandByName(String name);

	public void updateBand(Band band);

	public void deleteBand(Band band);

}
