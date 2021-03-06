package unispark.view.viewadapter.communications;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import unispark.engeneeringclasses.bean.communications.BeanUniCommunication;


import java.util.List;

public class UniCommunicationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    //Attributes
    private List<BeanUniCommunication> beanUniCommunicationList;
    private OnUniComClickListener onUniComClickListener;

    //Click ExamItem Interface
    public interface OnUniComClickListener {
        void onUniClick(int position);
    }


    //Methods
    public UniCommunicationsAdapter(OnUniComClickListener onUniComClickListener){

        this.onUniComClickListener = onUniComClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UniCommunicationViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_uni_communication,
                        parent,
                        false
                ), onUniComClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BeanUniCommunication universityCommunication = (BeanUniCommunication) beanUniCommunicationList.get(position);
        ((UniCommunicationViewHolder) holder).setCommunicationDate(universityCommunication);
    }

    @Override
    public int getItemCount() {
        return beanUniCommunicationList.size();
    }


    static class UniCommunicationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Attributes
        ImageView imgCommunication;
        int imageID;
        TextView txtTitle;
        String date;
        String communication;
        String faculty;
        OnUniComClickListener onUniComClickListener;

        //Methods
        //Constructor
        public UniCommunicationViewHolder(@NonNull View itemView, OnUniComClickListener onUniComClickListener) {
            super(itemView);
            imgCommunication = itemView.findViewById(R.id.img_communication_background);
            txtTitle = itemView.findViewById(R.id.txt_communication_title);

            this.onUniComClickListener = onUniComClickListener;
            itemView.setOnClickListener(this);
        }

        void setCommunicationDate(BeanUniCommunication universityCommunicationModel){
            imgCommunication.setImageResource(universityCommunicationModel.getBackground());
            imageID = universityCommunicationModel.getBackground();
            txtTitle.setText(universityCommunicationModel.getTitle());
            date = universityCommunicationModel.getDate();
            communication = universityCommunicationModel.getCommunication();
            faculty = universityCommunicationModel.getFaculty();
        }

        @Override
        public void onClick(View view) {
            onUniComClickListener.onUniClick(getAdapterPosition());
        }
    }

    public void setBeanUniCommunicationList(List<BeanUniCommunication> beanUniCommunicationList) {
        this.beanUniCommunicationList = beanUniCommunicationList;
    }
}

