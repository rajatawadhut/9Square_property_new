package com.nine__square.property.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nine__square.property.Avtivities.SearchCity;
import com.nine__square.property.Models.SearchCityDataModel;
import com.nine__square.property.R;
import com.nine__square.property.Utils.VolleySingleton;


import java.util.List;

public class SearchCityAdapter extends RecyclerView.Adapter<SearchCityAdapter.ShowData>{

    private List<SearchCityDataModel> dataSet;
    private Context context;
    String contextvalue;

    public SearchCityAdapter(
            List<SearchCityDataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchcity_layout, parent, false);
        return new ShowData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowData holder, final int position) {
        holder.text.setText(dataSet.get(position).getCity());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolleySingleton.getInstance(context).setsearchcity(dataSet.get(position).getCity());
                ((SearchCity) context).finish();
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class ShowData extends RecyclerView.ViewHolder {
        TextView text;
        CardView card;


        public ShowData(@NonNull View itemView) {
            super(itemView);


            text = itemView.findViewById(R.id.text);
            card = itemView.findViewById(R.id.card);

        }
    }

}



