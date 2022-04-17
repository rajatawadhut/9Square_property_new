package com.nine__square.property.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nine__square.property.Models.FlatTypeModel;
import com.nine__square.property.R;

import java.util.List;

public class FlatTypeAdapter extends RecyclerView.Adapter<FlatTypeAdapter.ShowData>{



    private List<FlatTypeModel> dataSet;
    private Context context;



    public FlatTypeAdapter(
            List<FlatTypeModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flat_type, parent, false);
        return new ShowData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowData holder, final int position) {
        FlatTypeModel data = dataSet.get(position);
        holder.tvFlatType.setText(data.getFlatType());
        holder.tvFlatTypeNo.setText(data.getFlatTypeNo());
        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet.remove(data);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ShowData extends RecyclerView.ViewHolder {
        
        TextView tvFlatTypeNo;
        TextView tvFlatType;
        ImageView ivRemove;

        public ShowData(@NonNull View itemView) {
            super(itemView);

            tvFlatType = itemView.findViewById(R.id.tv_flat_type);
            tvFlatTypeNo = itemView.findViewById(R.id.tv_flat_type_no);
            ivRemove = itemView.findViewById(R.id.iv_remove);

        }
    }

}



