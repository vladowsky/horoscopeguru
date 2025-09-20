package hr.horoskop.horoskop.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Zoran on 24.7.2015..
 */
public class PlanetFeed implements Serializable{

    private static final long serialVersionUID = 1L;
    private int _itemcount = 0;
    private ArrayList<PlanetItem> _itemlist;

    public PlanetFeed() {
        _itemlist = new ArrayList<PlanetItem>(0);
    }

    public void addItem(PlanetItem item) {
        _itemlist.add(item);
        _itemcount++;
    }

    public PlanetItem getItem(int location) {
        return _itemlist.get(location);
    }

    public int getItemCount() {
        return _itemcount;
    }
}
