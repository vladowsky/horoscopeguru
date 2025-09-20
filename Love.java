package hr.horoskop.horoskop.model;

/**
 * Created by zoran on 31.08.2015..
 */
public class Love {

    int id;
    String category;
    String text;


    int percentage;

    public Love() {
    }

    public Love(int id, String category, String text, int percentage) {
        this.id = id;
        this.category = category;
        this.text = text;
        this.percentage = percentage;
    }

    public Love(int id, String category, String text) {
        this.id = id;
        this.category = category;
        this.text = text;
    }


    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
