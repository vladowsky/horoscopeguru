package hr.horoskop.horoskop.model;

/**
 * Created by Zoran on 24.7.2015..
 */
public class Moon {

    private int signId;
    private String sign;
    private String text;

    public Moon() {
    }

    public Moon(int signId, String sign, String text) {
        this.signId = signId;
        this.sign = sign;
        this.text = text;
    }


    public int getSignId() {
        return signId;
    }

    public void setSignId(int signId) {
        this.signId = signId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
