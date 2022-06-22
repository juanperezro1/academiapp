package com.academiapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.academiapp.R;
import com.academiapp.activities.EditActivity;

import java.util.List;

public class AuthortiesAdapter extends RecyclerView.Adapter<AuthortiesAdapter.AuthoritiesView> {

    List<String> list;
    Context context;

    public AuthortiesAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AuthoritiesView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.auditorias_design, parent, false);
        return new AuthoritiesView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthoritiesView holder, int position) {
        holder.txtView.setText("Breve descripción del trabajo de grado.");
        holder.txMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EditActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AuthoritiesView extends RecyclerView.ViewHolder {
        TextView txtView;
        TextView txMore;

        public AuthoritiesView(@NonNull View itemView) {
            super(itemView);

            txtView = itemView.findViewById(R.id.txtDescription);
            txMore = itemView.findViewById(R.id.btnMore);

        }
    }
}
