package unispark.engeneeringclasses.bean.exams;

public class BeanBookExam extends BeanExam {
    //Attributes
    private String classroom;
    private String building;


    //Getter
    public String getClassroom() {
        return classroom;
    }
    public String getBuilding() {
        return building;
    }


    //Setter
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
}
