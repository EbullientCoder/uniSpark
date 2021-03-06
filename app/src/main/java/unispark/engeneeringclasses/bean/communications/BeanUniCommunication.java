package unispark.engeneeringclasses.bean.communications;

import java.io.Serializable;

public class BeanUniCommunication implements Serializable {

    //Attributes
    private int background;
    private String title;
    private String date;
    private String communication;
    private String faculty;

    //Getter
    public int getBackground() {
        return background;
    }
    public String getTitle() {
        return title;
    }
    public String getDate() {
        return date;
    }
    public String getCommunication() {
        return communication;
    }
    public String getFaculty() {
        return faculty;
    }

    //Setter
    public void setBackground(int background) {
        this.background = background;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setCommunication(String communication) {
        this.communication = communication;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
