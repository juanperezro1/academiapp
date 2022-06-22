package com.academiapp.content.degree_work.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.academiapp.R;
import com.academiapp.activities.AddNewActivity;
import com.academiapp.activities.MainActivity;
import com.academiapp.activities.WorkDModel;
import com.academiapp.content.degree_work.DWorkActivity;
import com.academiapp.services.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkDegreeAdapter extends RecyclerView.Adapter<WorkDegreeAdapter.WorkDegreeView> {

    Context context;
    public List<? extends WorkDModel.ProjectsDegree> list;
    SharedPreferencesManager sharedPreferencesManager;

    public WorkDegreeAdapter(Activity context, List<? extends WorkDModel.ProjectsDegree> list) {
        this.context = context;
        this.list=list;
        sharedPreferencesManager=new SharedPreferencesManager(context);
    }

    @NonNull
    @Override
    public WorkDegreeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.degree_work_item, parent, false);
        return new WorkDegreeView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkDegreeView holder, int position) {
        if(sharedPreferencesManager.getisteacher()){
            holder.txtEdit.setText("Add Consultancies");
        }
        holder.txttitle.setText(list.get(position).getTitle());
        holder.txtDescr.setText(list.get(position).getDescription());

        if(!((DWorkActivity)context).editpermission){
            holder.txtEdit.setVisibility(View.GONE);
        }

        if(!((DWorkActivity)context).viewpermission){
            holder.txtMore.setVisibility(View.GONE);
        }
        else{
            holder.txtEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sharedPreferencesManager.getisteacher()){
                        Intent intt=new Intent(context, MainActivity.class);
                        intt.putExtra("isaddconsultant", true);
                        intt.putExtra("modeldata", list.get(holder.getAdapterPosition()));
                        context.startActivity(intt);
                    }
                    else{
                        if(((DWorkActivity)context).editpermission)
                        {
                            Intent intt=new Intent(context, AddNewActivity.class);
                            intt.putExtra("modeldata", list.get(holder.getAdapterPosition()));
                            context.startActivity(intt);
                        }
                    }

                }
            });
        }




        holder.txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intt=new Intent(context, MainActivity.class);
                intt.putExtra("modeldata", list.get(holder.getAdapterPosition()));
                context.startActivity(intt);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WorkDegreeView extends RecyclerView.ViewHolder {
        TextView txtEdit,txtMore,txttitle,txtDescr;

        public WorkDegreeView(@NonNull View itemView) {
            super(itemView);
            txtEdit = itemView.findViewById(R.id.btnedit);
            txtMore = itemView.findViewById(R.id.btnMore);
            txttitle = itemView.findViewById(R.id.txtTitle);
            txtDescr = itemView.findViewById(R.id.txtDescription);
        }
    }

}
