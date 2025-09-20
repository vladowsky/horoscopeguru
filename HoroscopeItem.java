package hr.horoskop.horoskop.model;

import java.io.Serializable;

/**
 * Created by ZoPa on 5.4.2015..
 */
public class HoroscopeItem implements Serializable {

    private static final long serialVersionUID = 1L;
    String id;
    String scopeid;
    String horoscope;
    String sign;
    String txt;
    String startDate;
    String endDate;
    String featured;

    public HoroscopeItem() {
    }

    public HoroscopeItem( String horoscope, String sign) {
        this.horoscope = horoscope;
        this.sign = sign;
    }

    public HoroscopeItem(String id, String scopeid, String horoscope, String sign) {
        this.id = id;
        this.scopeid = scopeid;
        this.horoscope = horoscope;
        this.sign = sign;
    }

    public HoroscopeItem(String sign, String txt, String startDate, String endDate, String featured) {
        this.sign = sign;
        this.txt = txt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.featured = featured;
    }


    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScopeid() {
        return scopeid;
    }

    public void setScopeid(String scopeid) {
        this.scopeid = scopeid;
    }

    public String getHoroscope() {
        return horoscope;
    }

    public void setHoroscope(String horoscope) {
        this.horoscope = horoscope;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
