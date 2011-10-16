package de.ejb3buch.ticket2rock.session.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public interface TypkompatibelInterface {
	Future<List> allgemeinerTyp();
	
	ArrayList speziellerTyp();

}
