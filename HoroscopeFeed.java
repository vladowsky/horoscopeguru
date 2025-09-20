package hr.horoskop.horoskop.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ZoPa on 22.4.2015..
 */
public class HoroscopeFeed implements Serializable {

    private static final long serialVersionUID = 1L;
    private int _itemcount = 0;
    private ArrayList<HoroscopeItem> _itemlist;

    public HoroscopeFeed() {
        _itemlist = new ArrayList<HoroscopeItem>(0);
    }

    public void addItem(HoroscopeItem item) {
        _itemlist.add(item);
        _itemcount++;
    }

    public HoroscopeItem getItem(int location) {
        return _itemlist.get(location);
    }

    public int getItemCount() {
        return _itemcount;
    }
}
