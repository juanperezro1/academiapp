package com.academiapp.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.academiapp.R;
import com.academiapp.activities.ConsultancyDModel;
import com.academiapp.content.degree_work.adapter.WorkDegreeAdapter;

import java.security.PublicKey;
import java.util.List;

public class AsessoriasAdapter extends RecyclerView.Adapter<AsessoriasAdapter.AsessoriasView> {

    Context context;
    List<? extends ConsultancyDModel.ProjectsConsultancies> list;

    public AsessoriasAdapter(Context context, List<? extends ConsultancyDModel.ProjectsConsultancies> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AsessoriasView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.asesorias_design, parent, false);
        return new AsessoriasView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsessoriasView holder, int position) {
        holder.textView2.setText(list.get(position).getConsultancy());
        if(list.get(position).getConsultancy_type()){
            holder.textView3.setText("virtual");
        }
        else{
            holder.textView3.setText("presencial.");
        }

        holder.textView1.setText(list.get(position).getConsultancy_date());
        holder.textView4.setText(list.get(position).getUser().getUsername());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AsessoriasView extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3,textView4;

        public AsessoriasView(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.txt_asesorias_date);
            textView2 = itemView.findViewById(R.id.txt_Nombre_detail);
            textView3 = itemView.findViewById(R.id.txt_Tipo_de_asesoria_details);
            textView4 = itemView.findViewById(R.id.txt_Persona_details);
        }
    }
}
