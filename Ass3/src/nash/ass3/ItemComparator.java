package nash.ass3;

import java.util.Comparator;

public class ItemComparator implements Comparator<ItemImpl> {

	@Override
	public int compare(ItemImpl arg0, ItemImpl arg1) {
		return(arg0.getName().compareTo(arg1.getName()));
	}

}
