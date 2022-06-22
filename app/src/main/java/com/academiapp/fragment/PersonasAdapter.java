package com.academiapp.fragment;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.academiapp.R;
import com.academiapp.activities.WorkDModel;
import com.academiapp.content.degree_work.adapter.WorkDegreeAdapter;

import java.util.List;

public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.PersonasView> {

    Context context;

    public PersonasAdapter(Context context, List<? extends WorkDModel.ProjectsDegree.People> list) {
        this.list = list;
        this.context = context;

    }

    List<? extends WorkDModel.ProjectsDegree.People> list;

    @NonNull
    @Override
    public PersonasView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.personas_design, parent, false);
        return new PersonasView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonasView holder, int position) {
        holder.textView.setText(list.get(position).getPerson().getFirst_name()+" "+list.get(position).getPerson().getLast_name());
        holder.textView1.setText(list.get(position).getRol().getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PersonasView extends RecyclerView.ViewHolder {
        TextView textView,textView1;

        public PersonasView(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_Nombre_detail);
            textView1 = itemView.findViewById(R.id.txt_Rol_details);
        }
    }
}
