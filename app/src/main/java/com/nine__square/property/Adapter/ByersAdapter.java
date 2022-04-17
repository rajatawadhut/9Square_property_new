package com.nine__square.property.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nine__square.property.Models.ByersDatamodel;
import com.nine__square.property.R;

import java.util.List;

public class ByersAdapter extends RecyclerView.Adapter<ByersAdapter.ShowData>{

    private List<ByersDatamodel> dataSet;
    private Context context;
    String contextvalue;

    public ByersAdapter(
            List<ByersDatamodel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anemities, parent, false);
        return new ShowData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowData holder, final int position) {
        holder.text.setText(dataSet.get(position).getText().trim());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ShowData extends RecyclerView.ViewHolder {
        TextView text;


        public ShowData(@NonNull View itemView) {
            super(itemView);


            text = itemView.findViewById(R.id.anemities);

        }
    }

}



