package de.ejb3buch.ticket2rock.applikation.helper;

import java.util.Comparator;

import javax.faces.model.SelectItem;

public class SelectItemsComparator implements Comparator{

	public int compare(Object obj1, Object obj2) {
		String value1 = ((SelectItem) obj1).getLabel();
		String value2 = ((SelectItem) obj2).getLabel();
		return value1.toUpperCase().compareTo (value2.toUpperCase()) ; 	
	}

}
