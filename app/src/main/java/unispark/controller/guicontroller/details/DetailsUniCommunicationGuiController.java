package unispark.controller.guicontroller.details;



import unispark.engeneeringclasses.bean.communications.BeanUniCommunication;
import unispark.view.details.DetailsUniCommunicationView;

public class DetailsUniCommunicationGuiController{

    private DetailsUniCommunicationView uniCommunicationView;
    private BeanUniCommunication beanUniCommunication;


    public DetailsUniCommunicationGuiController(DetailsUniCommunicationView uniCommunicationView, BeanUniCommunication beanUniCommunication) {
        this.beanUniCommunication = beanUniCommunication;
        this.uniCommunicationView = uniCommunicationView;
    }


    public void showDetails(){
        this.uniCommunicationView.setImgComBackground(this.beanUniCommunication.getBackground());
        this.uniCommunicationView.setTxtTitle(this.beanUniCommunication.getTitle());
        this.uniCommunicationView.setTxtDate(this.beanUniCommunication.getDate());
        this.uniCommunicationView.setTxtCommunication(this.beanUniCommunication.getCommunication());
    }
}
