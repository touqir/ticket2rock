package de.ejb3buch.ticket2rock.applikation.helper;

import java.util.HashMap;
import java.util.Map;

import javax.faces.model.SelectItem;
/**
 * 
 * @author jojo1de
 * Builder Klasse zur Erstellung einer Kollektion von SelectItem Objekte
 */
public class SelectItemsMapBuilder implements IViewCollectionBuilder {

	private Map<String,SelectItem> selectItemsMap = new HashMap<String,SelectItem>();
	
	public void buildItem(Object id, Object value) {
		SelectItem selectItem = new SelectItem();
		selectItem.setValue(id);
		selectItem.setLabel((String) value);
		selectItemsMap.put((String)id,selectItem);
	}
	
	public Map<String,SelectItem> getSelectItemsMap() {
		return selectItemsMap;
	}

}
