package de.ejb3buch.ticket2rock.session.manager;

import java.util.List;

import javax.ejb.Remote;

import de.ejb3buch.ticket2rock.entity.Band;

@Remote
public interface T2RManagerRemote {

	public List<Band> getBands();
}
