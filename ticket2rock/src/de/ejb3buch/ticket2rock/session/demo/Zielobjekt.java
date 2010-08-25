package de.ejb3buch.ticket2rock.session.demo;
import javax.ejb.Remote;

import de.ejb3buch.ticket2rock.entity.demo.Enti;

@Remote
public interface Zielobjekt {

	public void fangMichAb();
	public String michAuch(Object obj);
	public void duKriegstMichNicht();
	public void lassMichInRuhe();
	public void gibMirZeit();
	public Enti bruete();
}
