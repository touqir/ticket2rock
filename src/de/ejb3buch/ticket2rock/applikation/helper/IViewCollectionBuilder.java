package de.ejb3buch.ticket2rock.applikation.helper;

/**
 * 
 * @author jojo1de
 * Builder Interface zur Erstellung von Kollektionen, die im View 
 * benötigt werden
 */
public interface IViewCollectionBuilder {
  
	/**
	 * 
	 * @param id
	 * @param value
	 */
	public void buildItem(Object id, Object value);

}
