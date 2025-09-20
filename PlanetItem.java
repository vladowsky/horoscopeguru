package hr.horoskop.horoskop.model;

import java.io.Serializable;

/**
 * Created by Zoran on 24.7.2015..
 */
public class PlanetItem  implements Serializable{

    private static final long serialVersionUID = 1L;
    private String planet;
    private String position;
    private String sign;

    public PlanetItem() {
    }

    public PlanetItem(String planet, String position, String sign) {
        this.planet = planet;
        this.position = position;
        this.sign = sign;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
