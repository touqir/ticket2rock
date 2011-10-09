package de.ejb3buch.ticket2rock.session.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
public class AsyncBean implements TypkompatibelInterface {

	@Override
	public Future<List> allgemeinerTyp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList speziellerTyp() {
		// TODO Auto-generated method stub
		return null;
	}

}
