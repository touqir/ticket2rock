package de.ejb3buch.ticket2rock.session.crud;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Band;
import de.ejb3buch.ticket2rock.entity.Musiker;

@Local
public interface BandVerwaltungLocal {

//TODO Dokumentiere die Schnittstelle
	public List<Band> getBands();
	
	public void createBand(Band band);
	
	public Band getBandByName(String name);

	public void updateBand(Band band);

	public void deleteBand(Integer bandId);

	public Collection<Musiker> getMusiker();

	public Band getBandById(Integer bandId);

	public Musiker getMusikerById(Integer musikerId);

}
